package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entidades.Cliente;
import entidades.Producto;
import entidades.Usuario;
import fabrica.DAOFactory;
import interfaces.ICategoriaDAO;
import interfaces.IClienteDAO;
import interfaces.IProductoDAO;
import interfaces.IUsuarioDAO;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet(name = "dashboard", urlPatterns = { "/dashboard" })
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// iniciliza variables
	private IProductoDAO productoDAO;
	private ICategoriaDAO categoriaDAO;
	private IClienteDAO clienteDAO;
	private IUsuarioDAO usuarioDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
        super();
        this.productoDAO = DAOFactory.getDAOFactory().getProductoDAO();
        this.categoriaDAO = DAOFactory.getDAOFactory().getCategoriaDAO();
        this.clienteDAO = DAOFactory.getDAOFactory().getClienteDAO();
        this.usuarioDAO = DAOFactory.getDAOFactory().getUsuarioDAO();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario user = new Usuario();
		if (session.getAttribute("usuario") != null) user = (Usuario)session.getAttribute("usuario");
		
		if (user.getUsuarioId() == 0) response.sendRedirect("auth");
		
		else {
			String opcion = "";
			if (request.getParameter("opcion") != null) opcion = request.getParameter("opcion");
			switch (opcion) {
				default:
					this.dashboard(request, response);
			}
			
		}
		
		
	}
	
	protected void dashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//obtener metricas
		ArrayList<Producto> productos = this.productoDAO.listar();
		ArrayList<Cliente> clientes = this.clienteDAO.listar();
		
		// calcular estadisticas
		int totalProductos = productos.size();
		int totalClientes = clientes.size();
		int totalCategorias = this.categoriaDAO.listar().size();
		int totalUsuarios = this.usuarioDAO.listar().size();
		
		//productos con stock > 5
		int productosStockBajo = 0;
		double valorInventario =0.0;
		
		for (Producto p : productos) {
			if(p.getStock() < 5) {
				productosStockBajo++;
			}
			valorInventario += (p.getPrecio() * p.getStock());
		}
		
		// productos mas vendidos
		Map<String, Integer> productosPopulares = new HashMap<>();
		
		if (productos.size() > 0) {
			// agrega solo productos que existen en la lista
			productosPopulares.put(productos.get(0).getNombre(), 45); // 45, 32, 28 valores simulados de ventas - cambiar por pedidos reales con etidad pedido
			if(productos.size()>1) {
				productosPopulares.put(productos.get(1).getNombre(), 32);
			}
			if(productos.size()>2) {
				productosPopulares.put(productos.get(2).getNombre(), 28);
			}
		}
		
		// atributos para la vista
		request.setAttribute("activepage", "dashboard");
		request.setAttribute("totalProductos", totalProductos);
		request.setAttribute("totalClientes", totalClientes);
		request.setAttribute("totalCategorias", totalCategorias);
		request.setAttribute("productosStockBajo", productosStockBajo);
		request.setAttribute("valorInventario", String.format("%.2f", valorInventario));
		request.setAttribute("productosPopulares", productosPopulares);
		request.setAttribute("productos", productos);
		request.setAttribute("totalUsuarios", totalUsuarios);
		
		request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
	}

}
