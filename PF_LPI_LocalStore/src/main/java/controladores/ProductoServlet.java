package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import entidades.Categoria;
import entidades.Producto;
import fabrica.DAOFactory;
import interfaces.ICategoriaDAO;
import interfaces.IProductoDAO;


/**
 * Servlet implementation class ProductoServlet
 */
@WebServlet(name = "producto", urlPatterns = { "/producto" })
public class ProductoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private IProductoDAO productoDAO;
	private ICategoriaDAO categoriaDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoServlet() {
        super();
        this.productoDAO = DAOFactory.getDAOFactory().getProductoDAO();
        this.categoriaDAO = DAOFactory.getDAOFactory().getCategoriaDAO();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String opcion = "";
		if (request.getParameter("opcion") != null) opcion = request.getParameter("opcion");
		switch (opcion) {
			case "lista" : this.lista(request, response); break;
			case "editar" : this.editar(request, response); break;
			case "registrar" : this.registrar(request, response); break;
			case "eliminar" : this.eliminar(request, response); break;
			default:
				this.lista(request, response);
		}
	}
	
	protected void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String texto = "";
		
		if (request.getParameter("texto") != null) {
			texto = request.getParameter("texto");
		}
		
		ArrayList<Producto> lista = this.productoDAO.buscar(texto);
		
		// valor para navegacion de botones activos
		request.setAttribute("activepage", "producto");
		
		request.setAttribute("lista", lista);				
		request.getRequestDispatcher("/producto/producto_lista.jsp").forward(request, response);
	}
	protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Producto obj = new Producto();
		if (request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			obj = this.productoDAO.obtener(id);
		}
		ArrayList<Categoria> listas = this.categoriaDAO.listar();
		
		request.setAttribute("activepage", "producto");
		
		request.setAttribute("registro", obj);
		request.setAttribute("listaCategorias", listas);
		request.getRequestDispatcher("producto/producto_editar.jsp").forward(request, response);
	}
	protected void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("productoId"));
		String nombre = request.getParameter("nombre");
		int stock = Integer.parseInt(request.getParameter("stock"));
		double price = Double.parseDouble(request.getParameter("precio"));
		//System.out.println("Precio : " + price);
		int categoriaId = Integer.parseInt(request.getParameter("categoria"));
		boolean status = Boolean.parseBoolean(request.getParameter("estado"));
		
		Producto obj = new Producto(id, nombre, stock, price, status, categoriaId);
		
		if (obj.getProductoId() == 0) {
			this.productoDAO.crear(obj);
		} else {
			this.productoDAO.actualizar(obj);
		}
		
		request.setAttribute("activepage", "producto");
		
		response.sendRedirect("producto");		
		
		
	}
	protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			this.productoDAO.eliminar(id);
		}
		
		request.setAttribute("activepage", "producto");
		
		response.sendRedirect("producto");
	}

}
