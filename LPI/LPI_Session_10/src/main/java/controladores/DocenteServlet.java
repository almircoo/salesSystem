package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import entidades.Docente;
import fabrica.DAOFactory;
import interfaces.IDocenteDAO;

/**
 * Servlet implementation class DocenteServlet
 */
@WebServlet(name = "docente", urlPatterns = { "/docente" })
public class DocenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IDocenteDAO docenteDAO;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocenteServlet() {
        super();
        this.docenteDAO = DAOFactory.getDAOFactory().getDocenteDAO();
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
		ArrayList<Docente> lista = this.docenteDAO.listar();
		
		request.setAttribute("lista", lista);				
		request.getRequestDispatcher("/docente/docente_lista.jsp").forward(request, response);
	}
	protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Docente docente = new Docente();
		if (request.getParameter("id") != null) {
			int docenteId = Integer.parseInt(request.getParameter("id"));
			docente = this.docenteDAO.obtener(docenteId);
		}
		
		request.setAttribute("registro", docente);
		request.getRequestDispatcher("/docente/docente_editar.jsp").forward(request, response);
	}
	protected void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int docenteId = Integer.parseInt(request.getParameter("docenteId"));
		String dni = request.getParameter("dni");
		String nombre = request.getParameter("nombre");
		String especialidad = request.getParameter("especialidad");
		
		Docente docente = new Docente(docenteId, dni, nombre, especialidad);
		
		if (docente.getDocenteId() == 0) {
			this.docenteDAO.crear(docente);
		} else {
			this.docenteDAO.actualizar(docente);
		}
		
		response.sendRedirect("docente");
	}
	protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") != null) {
			int docenteId = Integer.parseInt(request.getParameter("id"));
			this.docenteDAO.eliminar(docenteId);
		}
		
		response.sendRedirect("docente");
	}

}
