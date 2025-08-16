package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import entidades.Categoria;
import fabrica.DAOFactory;
import interfaces.ICategoriaDAO;

/**
 * Servlet implementation class CategoriaServlet
 */
@WebServlet(name = "categoria", urlPatterns = { "/categoria" })
public class CategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private ICategoriaDAO categoriaDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriaServlet() {
        super();
       
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
		
		ArrayList<Categoria> lista = this.categoriaDAO.listar();
		
		request.setAttribute("activepage", "categoria");
		
		request.setAttribute("lista", lista);				
		request.getRequestDispatcher("/categoria/categoria_lista.jsp").forward(request, response);
	}
	protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Categoria obj = new Categoria();
		if (request.getParameter("id") != null) {
			int categoriaId = Integer.parseInt(request.getParameter("id"));
			obj = this.categoriaDAO.obtener(categoriaId);
		}

		request.setAttribute("activepage", "categoria");
		
		request.setAttribute("registro", obj);
		request.getRequestDispatcher("/categoria/categoria_editar.jsp").forward(request, response);
	}
	protected void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int categoriaId = Integer.parseInt(request.getParameter("categoriaId"));
		String nombre = request.getParameter("nombre");
		boolean estado = Boolean.parseBoolean(request.getParameter("estado"));
		
		Categoria obj = new Categoria(categoriaId, nombre, estado);
		
		if (obj.getCategoriaId() == 0) {
			this.categoriaDAO.crear(obj);
		} else {
			this.categoriaDAO.actualizar(obj);
		}
		request.setAttribute("activepage", "categoria");
		
		response.sendRedirect("categoria");		
	}
	protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") != null) {
			int categoriaId = Integer.parseInt(request.getParameter("id"));
			this.categoriaDAO.eliminar(categoriaId);
		}
		
		request.setAttribute("activepage", "categoria");
		
		response.sendRedirect("categoria");
	}

}
