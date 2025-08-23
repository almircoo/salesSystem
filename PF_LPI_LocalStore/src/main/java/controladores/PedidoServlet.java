package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
				case "eliminar": 
					if (!"ADMIN".equals(user.getRol())) {
						response.sendRedirect("pedidos");
					} else {
						this.eliminar(request, response);
					}
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
	
//	protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Pedido pedido = new Pedido();
//		ArrayList<Cliente> clientes = this.clienteDAO.listar();
//		ArrayList<Producto> productos = this.productoDAO.listar();
//		
//		if (request.getParameter("id") != null) {
//			int pedidoId = Integer.parseInt(request.getParameter("id"));
//			pedido = this.pedidoDAO.obtener(pedidoId);
//		}
//		
//		request.setAttribute("activepage", "pedidos");
//		request.setAttribute("registro", pedido);
//		request.setAttribute("clientes", clientes);
//		request.setAttribute("productos", productos);
//		request.getRequestDispatcher("/admin/pedido/pedido_editar.jsp").forward(request, response);
//	}
	
	protected void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int pedidoId = Integer.parseInt(request.getParameter("pedidoId"));
			int clienteId = Integer.parseInt(request.getParameter("clienteId"));
			String estado = request.getParameter("estado");
			double total = Double.parseDouble(request.getParameter("total"));
			String observaciones = request.getParameter("observaciones");
			
			Pedido pedido = new Pedido(pedidoId, clienteId, "", LocalDateTime.now(), estado, total, observaciones);
			
			if (pedido.getPedidoId() == 0) {
				this.pedidoDAO.crear(pedido);
			} else {
				this.pedidoDAO.actualizar(pedido);
			}
			
			response.sendRedirect("pedidos");
		} catch (Exception e) {
			System.out.println("Error al registrar pedido: " + e.getMessage());
			response.sendRedirect("pedidos?opcion=editar");
		}
	}
	
	protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") != null) {
			int pedidoId = Integer.parseInt(request.getParameter("id"));
			// Primero eliminar detalles del pedido
			this.detallePedidoDAO.eliminarPorPedido(pedidoId);
			// Luego eliminar el pedido
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

}
