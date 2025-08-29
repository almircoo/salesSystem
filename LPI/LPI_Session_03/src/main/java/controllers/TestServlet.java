package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet(name = "test", urlPatterns = { "/test" })
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// creacion de respuestas a peticiones del cliente
		 String nombre = request.getParameter("nombre");
		 int edad = Integer.parseInt(request.getParameter("edad"));
//		response.getWriter().print("Hola " + nombre + " tu edad es : " + edad);
		request.setAttribute("nombre", nombre);
		request.setAttribute("edad", edad);
		request.getRequestDispatcher("/test/FirstView.jsp").forward(request, response);
	}

}
