package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entidades.Cliente;
import entidades.DetallePedido;
import entidades.Pedido;
import entidades.Producto;
import entidades.Usuario;
import fabrica.DAOFactory;
import interfaces.IClienteDAO;
import interfaces.IDetallePedidoDAO;
import interfaces.IPedidoDAO;
import interfaces.IProductoDAO;

/**
 * Servlet implementation class PedidoServlet
 */
@WebServlet(name = "pedidos", urlPatterns = { "/pedidos" })
public class PedidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IPedidoDAO pedidoDAO;
	private IDetallePedidoDAO detallePedidoDAO;
	private IClienteDAO clienteDAO;
	private IProductoDAO productoDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PedidoServlet() {
        super();
        this.pedidoDAO = DAOFactory.getDAOFactory().getPedidoDAO();
        this.detallePedidoDAO = DAOFactory.getDAOFactory().getDetallePedidoDAO();
        this.clienteDAO = DAOFactory.getDAOFactory().getClienteDAO();
        this.productoDAO = DAOFactory.getDAOFactory().getProductoDAO();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
		HttpSession session = request.getSession();
		Usuario user = new Usuario();
		if (session.getAttribute("usuario") != null) user = (Usuario)session.getAttribute("usuario");
		
		if (user.getUsuarioId() == 0) response.sendRedirect("auth");
		
		else {
			String opcion = "";
			if (request.getParameter("opcion") != null) opcion = request.getParameter("opcion");
			// VENDEDOR: solo lectura (lista, detalles)
			// MANAGER: lectura y cambio de estado
			// ADMIN: acceso completo (CRUD)
			
			switch (opcion) {
				case "lista": this.lista(request, response); break;
				case "registrar": 
					if (!"ADMIN".equals(user.getRol())) {
						response.sendRedirect("pedidos");
					} else {
						this.registrar(request, response);
					}
					break;
				case "editar": 
					
					this.editar(request, response);
					
					break;
				case "eliminar": 
					if (!"ADMIN".equals(user.getRol())) {
						response.sendRedirect("pedidos");
					} else {
						this.eliminar(request, response);
					}
					break;
				case "agregarDetalle": 
					this.agregarDetalle(request, response); 
					break;
				case "detalles": this.detalles(request, response); break;
				case "cambiar_estado": 
					if ("VENDEDOR".equals(user.getRol())) {
						response.sendRedirect("pedidos");
					} else {
						this.cambiarEstado(request, response);
					}
					break;
				default:
					this.lista(request, response);
			}
		}
	}
	
	protected void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String texto = request.getParameter("texto") != null ? request.getParameter("texto") : "";
		String estado = request.getParameter("estado") != null ? request.getParameter("estado") : "";
		
		ArrayList<Pedido> lista;
		
		if (!estado.isEmpty()) {
			lista = this.pedidoDAO.listarPorEstado(estado);
		} else if (!texto.isEmpty()) {
			lista = this.pedidoDAO.buscar(texto);
		} else {
			lista = this.pedidoDAO.listar();
		}
		
		request.setAttribute("activepage", "pedidos");
		request.setAttribute("lista", lista);
		request.getRequestDispatcher("/admin/pedido/pedido_lista.jsp").forward(request, response);
	}
	
	protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Pedido pedido = new Pedido();
		ArrayList<Cliente> clientes = this.clienteDAO.listar();
		ArrayList<Producto> productos = this.productoDAO.listar();
		ArrayList<DetallePedido> detalles = new ArrayList<DetallePedido>();
		
		if (request.getParameter("pedidoId") != null && !request.getParameter("pedidoId").isEmpty()) {
	        try {
	            int pedidoId = Integer.parseInt(request.getParameter("pedidoId"));
	            pedido = this.pedidoDAO.obtener(pedidoId);
	            if (pedido != null) {
	                detalles = this.detallePedidoDAO.listarPorPedido(pedidoId);
	            }
	        } catch (NumberFormatException e) {
	            // Manejar el caso de que pedidoId no sea un número válido
	        }
	    } else {
	        // Inicializar la fecha para un nuevo pedido si el objeto es nuevo
	        // Esto evita el error de la expresión EL en el JSP
	        pedido.setFechaPedido(new Date()); 
	    }
		
		request.setAttribute("activepage", "pedidos");
		request.setAttribute("registro", pedido);
		request.setAttribute("clientes", clientes);
		request.setAttribute("productos", productos);
		request.setAttribute("detalles", detalles);
		request.getRequestDispatcher("/admin/pedido/pedido_editar.jsp").forward(request, response);

	}
	
	protected void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	        int pedidoId = Integer.parseInt(request.getParameter("pedidoId"));
	        int clienteId = Integer.parseInt(request.getParameter("clienteId"));
	        
	        // Obtener usuario de la sesión
	        HttpSession session = request.getSession();
	        Usuario usuarioSesion = (Usuario) session.getAttribute("usuario");
	        int usuarioId = usuarioSesion.getUsuarioId();
	        
	        // Parámetros del formulario
	        String observaciones = request.getParameter("observaciones");
	        String metodoPago = request.getParameter("metodoPago");
	        String tipoComprobante = request.getParameter("tipoComprobante");
	        
	        // Obtener los detalles del pedido desde el formulario (solo ID y cantidad)
	        String[] productosIdsStr = request.getParameterValues("detallesProductoId[]");
	        String[] cantidadesStr = request.getParameterValues("detallesCantidad[]");

	        // Validar que se hayan recibido productos
	        if (productosIdsStr == null || productosIdsStr.length == 0) {
	            throw new IllegalArgumentException("No se han seleccionado productos para el pedido.");
	        }

	        // Crear los objetos de detalle del pedido y realizar los cálculos
	        double subtotalCalculado = 0.0;
	        ArrayList<DetallePedido> detalles = new ArrayList<>();
	        
	        for (int i = 0; i < productosIdsStr.length; i++) {
	            int productoId = Integer.parseInt(productosIdsStr[i]);
	            int cantidad = Integer.parseInt(cantidadesStr[i]);

	            //  un DAO para productos que permite obtener el precio por ID
	            Producto producto = this.productoDAO.obtener(productoId); 
	            double precioUnitario = producto.getPrecio();
	            double subtotalDetalle = precioUnitario * cantidad;

	            DetallePedido det = new DetallePedido();
	            det.setPedidoId(pedidoId);
	            det.setProductoId(productoId);
	            det.setPrecioUnitario(precioUnitario);
	            det.setCantidad(cantidad);
	            det.setSubtotal(subtotalDetalle);
	            detalles.add(det);

	            subtotalCalculado += subtotalDetalle;
	        }

	        // cálculos finales en el servidor
	        double igvCalculado = subtotalCalculado * 0.18; // 18% de IGV
	        double totalCalculado = subtotalCalculado + igvCalculado;
	        
	        // valores por defecto del servidor
	        Date fechaPedido = new Date();
	        String estado = "PENDIENTE";
	        
	        // Generar número de comprobante automáticamente
	        String numeroComprobante;
	        if (pedidoId == 0) { // Solo para pedidos nuevos
	            String prefix = "BOLETA".equals(tipoComprobante) ? "B001-" : "F001-";
	            long timestamp = System.currentTimeMillis();
	            String numero = String.format("%04d", timestamp % 10000);
	            numeroComprobante = prefix + numero;
	        } else {
	            Pedido pedidoExistente = this.pedidoDAO.obtener(pedidoId);
	            numeroComprobante = pedidoExistente.getNumeroComprobante();
	        }

	        Pedido pedido = new Pedido();
	        pedido.setPedidoId(pedidoId);
	        pedido.setClienteId(clienteId);
	        pedido.setUsuarioId(usuarioId);
	        pedido.setFechaPedido(fechaPedido);
	        pedido.setEstado(estado);
	        
	        // Asignar los valores calculados
	        pedido.setSubtotal(subtotalCalculado);
	        pedido.setIgv(igvCalculado);
	        pedido.setTotal(totalCalculado);
	        
	        pedido.setObservaciones(observaciones);
	        pedido.setMetodoPago(metodoPago);
	        pedido.setTipoComprobante(tipoComprobante);
	        pedido.setNumeroComprobante(numeroComprobante);
	        pedido.setDetalles(detalles);

	        if (pedido.getPedidoId() == 0) {
	            this.pedidoDAO.crear(pedido);
	            request.getSession().setAttribute("mensaje", "Pedido creado exitosamente con número: " + numeroComprobante);
	            System.out.println("Pedido creado: " + pedido);
	        } else {
	        	int filasEliminadas = this.detallePedidoDAO.eliminarPorPedido(pedidoId);
	            if (filasEliminadas >= 0) { // >= 0 indica éxito (puede ser 0 si no había detalles)
	                this.pedidoDAO.actualizar(pedido);
	                request.getSession().setAttribute("mensaje", "Pedido actualizado exitosamente");
	            } else {
	                throw new RuntimeException("Error al eliminar los detalles del pedido existente.");
	            }
	            
	        }
	        
	        response.sendRedirect("pedidos");
	        
	    } catch (Exception e) {
	        System.out.println("Error al registrar pedido: " + e.getMessage());
	        e.printStackTrace();
	        request.getSession().setAttribute("error", "Error al procesar el pedido: " + e.getMessage());
	        response.sendRedirect("pedidos?opcion=editar");
	    }
		
	}
	
	protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") != null) {
			int pedidoId = Integer.parseInt(request.getParameter("id"));
			this.detallePedidoDAO.eliminarPorPedido(pedidoId);
			this.pedidoDAO.eliminar(pedidoId);
		}
		
		response.sendRedirect("pedidos");
	}
	
	protected void detalles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") != null) {
			int pedidoId = Integer.parseInt(request.getParameter("id"));
			Pedido pedido = this.pedidoDAO.obtener(pedidoId);
			ArrayList<DetallePedido> detalles = this.detallePedidoDAO.listarPorPedido(pedidoId);
			
			request.setAttribute("activepage", "pedidos");
			request.setAttribute("pedido", pedido);
			request.setAttribute("detalles", detalles);
			request.getRequestDispatcher("/admin/pedido/pedido_detalles.jsp").forward(request, response);
		} else {
			response.sendRedirect("pedidos");
		}
	}
	
	protected void cambiarEstado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") != null && request.getParameter("nuevoEstado") != null) {
			int pedidoId = Integer.parseInt(request.getParameter("id"));
			String nuevoEstado = request.getParameter("nuevoEstado");
			
			this.pedidoDAO.cambiarEstado(pedidoId, nuevoEstado);
		}
		
		response.sendRedirect("pedidos");
	}
	
	private void agregarDetalle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		try {
            List<DetallePedido> detalles = (List<DetallePedido>) request.getSession().getAttribute("detalles");
            if (detalles == null) {
                detalles = new ArrayList<>();
            }

            int productoId = Integer.parseInt(request.getParameter("productoId"));
            double precioUnitario = Double.parseDouble(request.getParameter("precio"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            double subtotal = cantidad * precioUnitario;
            
            Producto producto = this.productoDAO.obtener(productoId);
            
            DetallePedido detalle = new DetallePedido();
            detalle.setProductoId(productoId);
            detalle.setPrecioUnitario(precioUnitario);
            detalle.setCantidad(cantidad);
            detalle.setSubtotal(subtotal);
            detalle.setProducto(producto);

            detalles.add(detalle);

            request.getSession().setAttribute("detalles", detalles);
            request.getSession().setAttribute("mensaje", "Producto agregado al pedido");
            
            response.sendRedirect("pedidos?opcion=editar");
        } catch (Exception e) {
            System.out.println("Error al agregar detalle: " + e.getMessage());
            request.getSession().setAttribute("error", "Error al agregar producto al pedido");
            response.sendRedirect("pedidos?opcion=editar");
        }
    }

}
