package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class CourseServlet
 */
@WebServlet(name = "course", urlPatterns = { "/course" })
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
		String opcion = "";
		
		if (request.getParameter("opcion") != null ) opcion = request.getParameter("opcion");
		
		switch(opcion) {
			case"lista":this.lista(request, response); break;
			case"editar":this.editar(request, response); break;
			case"registrar":this.registrar(request, response); break;
			default:
				lista(request, response);
		}
		
		
	}
	
	protected void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		request.getRequestDispatcher("/course/courseList.jsp").forward(request, response);
	}
	protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/course/courseEdit.jsp").forward(request, response);
	}
	
	// regsitra los datos del formulario
	protected void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String codigo = request.getParameter("codigo");
		String nombre = request.getParameter("nombres");
		int vacante = Integer.parseInt(request.getParameter("vacantes"));
		
		response.getWriter().print("El curso es: "+nombre);
		
		request.setAttribute("codigo", codigo);
		request.setAttribute("nombres", nombre);
		request.setAttribute("vacantes", vacante);
	}

}
