package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class PruebaServlet
 */
@WebServlet(name = "prueba", urlPatterns = { "/prueba" })
public class PruebaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PruebaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*String nombre = request.getParameter("nombre");
		int edad = Integer.parseInt(request.getParameter("edad"));
		
		response.getWriter().print("Hola " + nombre + ", tu edad es " + edad);*/
		
		// cargar una vista
		String nombre = request.getParameter("nombre");
		int edad = Integer.parseInt(request.getParameter("edad"));
		
		request.setAttribute("nombre", nombre);
		request.setAttribute("edad", edad);
		request.getRequestDispatcher("/prueba/saludo.jsp").forward(request, response);
		
	}

}
