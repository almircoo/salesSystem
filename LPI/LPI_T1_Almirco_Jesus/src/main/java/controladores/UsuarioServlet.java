package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.UsuarioModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import entidades.Usuario;
import interfaces.IUsuarioModel;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet(name = "usuario", urlPatterns = { "/usuario" })
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
	
	// lista los usuarios
	protected void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IUsuarioModel model = new UsuarioModel();
		List<Usuario> lista = model.listar();
		
		
		
		request.setAttribute("lista", lista);				
		request.getRequestDispatcher("/usuario/lista_usuario.jsp").forward(request, response);
	}
	
	protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/usuario/editar_usuario.jsp").forward(request, response);
	}
	
	// registra un nuevo usuario
	protected void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombres = request.getParameter("nombres"); // Corrected parameter names
		String apellidos = request.getParameter("apellidos");
		String dni = request.getParameter("dni");
		String correo = request.getParameter("correo");
		String rol = request.getParameter("rol");
		int intentos = Integer.parseInt(request.getParameter("intentos")); 
		
		Usuario usuario = new Usuario(nombres, apellidos, dni, correo, rol, intentos);
		
		IUsuarioModel usuarioModel = new UsuarioModel();
		usuarioModel.crear(usuario);
		
		response.sendRedirect("usuario");		
	}

}
