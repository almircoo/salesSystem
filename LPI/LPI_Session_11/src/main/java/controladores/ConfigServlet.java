package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import entidades.Usuario;
import fabrica.DAOFactory;
import interfaces.IUsuarioDAO;

/**
 * Servlet implementation class ConfigServlet
 */
@WebServlet(name = "configuracion", urlPatterns = { "/configuracion" })
public class ConfigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IUsuarioDAO usuarioDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfigServlet() {
        super();
        this.usuarioDAO = DAOFactory.getDAOFactory().getUsuarioDAO();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Usuario user = new Usuario();
		if (session.getAttribute("usuario") != null) user = (Usuario)session.getAttribute("usuario");
		
		if (user.getUsuarioId() == 0) response.sendRedirect("auth");
//		else if (!("ADMIN".equals(user.getRol()))) response.sendRedirect("dashboard");
		else {
			String opcion = "";
			if (request.getParameter("opcion") != null) opcion = request.getParameter("opcion");
			switch (opcion) {
				case "general" : this.configuracionGeneral(request, response); break;
				case "perfil" : this.editarPerfil(request, response); break;
				case "password" : this.cambiarPassword(request, response); break;
				case "actualizar_perfil" : this.actualizarPerfil(request, response); break;
				case "actualizar_password" : this.actualizarPassword(request, response); break;
				case "actualizar_general" : this.actualizarConfigGeneral(request, response); break;
				default:
					this.configuracionGeneral(request, response);
			}
		}
		
		
	}
	
	protected void configuracionGeneral(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario user = (Usuario)session.getAttribute("usuario");
		
		// Solo ADMIN puede acceder a configuración general
		if (!"ADMIN".equals(user.getRol())) {
			response.sendRedirect("dashboard");
			return;
		}
		
		// Obtener todos los usuarios para configuración general
		ArrayList<Usuario> usuarios = this.usuarioDAO.buscar("");
		request.setAttribute("usuarios", usuarios);
		request.setAttribute("usuarioActual", user);
		request.getRequestDispatcher("/admin/config/general_config.jsp").forward(request, response);
	}
	
	protected void editarPerfil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario user = (Usuario)session.getAttribute("usuario");
		
		// Obtener datos actuales del usuario
		Usuario usuarioActual = this.usuarioDAO.obtener(user.getUsuarioId());
		request.setAttribute("usuario", usuarioActual);
		request.getRequestDispatcher("/admin/config/edit_perfil.jsp").forward(request, response);
	}
	
	protected void cambiarPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario user = (Usuario)session.getAttribute("usuario");
		
		request.setAttribute("usuario", user);
		request.getRequestDispatcher("/admin/config/cambiar_password.jsp").forward(request, response);
	}
	
	protected void actualizarPerfil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario user = (Usuario)session.getAttribute("usuario");
		
		String nombre = request.getParameter("nombre");
		
		// Solo actualizar el nombre, mantener otros datos
		Usuario usuarioActualizado = this.usuarioDAO.obtener(user.getUsuarioId());
		usuarioActualizado.setNombre(nombre);
		
		this.usuarioDAO.actualizar(usuarioActualizado);
		
		// Actualizar sesion
		session.setAttribute("usuario", usuarioActualizado);
		
		request.setAttribute("mensaje", "Perfil actualizado correctamente");
		request.setAttribute("usuario", usuarioActualizado);
		request.getRequestDispatcher("/admin/config/edit_perfil.jsp").forward(request, response);
	}
	
	protected void actualizarPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario user = (Usuario)session.getAttribute("usuario");
		
		String claveActual = request.getParameter("claveActual");
		String claveNueva = request.getParameter("claveNueva");
		String confirmarClave = request.getParameter("confirmarClave");
		
		Usuario usuarioActual = this.usuarioDAO.obtener(user.getUsuarioId());
		
		// Verificar clave actual
		if (!usuarioActual.getClave().equals(claveActual)) {
			request.setAttribute("error", "La clave actual no es correcta");
			request.setAttribute("usuario", user);
			request.getRequestDispatcher("/admin/config/cambiar_password.jsp").forward(request, response);
			return;
		}
		
		// Verificar que las claves nuevas coincidan
		if (!claveNueva.equals(confirmarClave)) {
			request.setAttribute("error", "Las claves nuevas no coinciden");
			request.setAttribute("usuario", user);
			request.getRequestDispatcher("/admin/config/cambiar_password.jsp").forward(request, response);
			return;
		}
		
		// Actualizar clave
		usuarioActual.setClave(claveNueva);
		this.usuarioDAO.actualizar(usuarioActual);
		
		// Actualizar sesión
		session.setAttribute("usuario", usuarioActual);
		
		request.setAttribute("mensaje", "Contraseña actualizada correctamente");
		request.setAttribute("usuario", usuarioActual);
		request.getRequestDispatcher("/admin/config/cambiar_password.jsp").forward(request, response);
	}
	
	protected void actualizarConfigGeneral(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario user = (Usuario)session.getAttribute("usuario");
		
		// Solo ADMIN puede actualizar configuración general
		if (!"ADMIN".equals(user.getRol())) {
			response.sendRedirect("dashboard");
			return;
		}
		
		// Aquí se pueden agregar configuraciones generales del sistema
		// Por ahora solo redirigir con mensaje de éxito
		request.setAttribute("mensaje", "Configuración general actualizada correctamente");
		ArrayList<Usuario> usuarios = this.usuarioDAO.buscar("");
		request.setAttribute("usuarios", usuarios);
		request.setAttribute("usuarioActual", user);
		request.getRequestDispatcher("/admin/config/general_config.jsp").forward(request, response);
	}

}
