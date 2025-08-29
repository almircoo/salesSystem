package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import entidades.Curso;
import entidades.Docente;
import fabrica.DAOFactory;
import interfaces.ICursoDAO;
import interfaces.IDocenteDAO;

/**
 * Servlet implementation class CursoServlet
 */
@WebServlet(name = "curso", urlPatterns = { "/curso" })
public class CursoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ICursoDAO cursoDAO;
	private IDocenteDAO docenteDAO;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CursoServlet() {
        super();
        this.cursoDAO = DAOFactory.getDAOFactory().getCursoDAO();
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
		String texto = "";
		
		if (request.getParameter("texto") != null) {
			texto = request.getParameter("texto");
		}
		
		ArrayList<Curso> lista = this.cursoDAO.buscar(texto);
		
		request.setAttribute("lista", lista);				
		request.getRequestDispatcher("/curso/curso_lista.jsp").forward(request, response);
	}
	protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Curso curso = new Curso();
		if (request.getParameter("id") != null) {
			int cursoId = Integer.parseInt(request.getParameter("id"));
			curso = this.cursoDAO.obtener(cursoId);
		}
		ArrayList<Docente> docentes = this.docenteDAO.listar();
		
		request.setAttribute("registro", curso);
		request.setAttribute("listaDocentes", docentes);
		request.getRequestDispatcher("/curso/curso_editar.jsp").forward(request, response);
	}
	protected void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cursoId = Integer.parseInt(request.getParameter("cursoId"));
		String codigo = request.getParameter("codigo");
		String nombre = request.getParameter("nombre");
		int vacantes = Integer.parseInt(request.getParameter("vacantes"));
		int docenteId = Integer.parseInt(request.getParameter("docenteId"));
		
		Curso curso = new Curso(cursoId, codigo, nombre, vacantes, docenteId);
		
		if (curso.getCursoId() == 0) {
			this.cursoDAO.crear(curso);
		} else {
			this.cursoDAO.actualizar(curso);
		}
		
		response.sendRedirect("curso");		
	}
	protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") != null) {
			int cursoId = Integer.parseInt(request.getParameter("id"));
			this.cursoDAO.eliminar(cursoId);
		}
		
		response.sendRedirect("curso");
	}

}
