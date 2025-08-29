package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.CursoModel;

import java.io.IOException;
import java.util.ArrayList;

import entidades.Curso;
import interfaces.ICursoModel;

/**
 * Servlet implementation class CursoServlet
 */
@WebServlet(name = "curso", urlPatterns = { "/curso" })
public class CursoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CursoServlet() {
        super();
        // TODO Auto-generated constructor stub
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
			default:
				this.lista(request, response);
		}
	}
	
	protected void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ICursoModel cursoModel = new CursoModel();
		ArrayList<Curso> lista = cursoModel.listar();
		
		request.setAttribute("lista", lista);				
		request.getRequestDispatcher("/curso/curso_lista.jsp").forward(request, response);
	}
	protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/curso/curso_editar.jsp").forward(request, response);
	}
	protected void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codigo = request.getParameter("codigo");
		String nombre = request.getParameter("nombre");
		int vacantes = Integer.parseInt(request.getParameter("vacantes"));
		
		Curso curso = new Curso(codigo, nombre, vacantes);
		
		ICursoModel cursoModel = new CursoModel();
		cursoModel.crear(curso);
		
		response.sendRedirect("curso");		
	}

}
