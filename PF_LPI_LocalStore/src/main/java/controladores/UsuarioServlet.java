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
 * Servlet implementation class UsuarioServlet
 */
@WebServlet(name = "usuario", urlPatterns = { "/usuario" })
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IUsuarioDAO usuarioDAO;
	
    public UsuarioServlet() {
        super();
        this.usuarioDAO = DAOFactory.getDAOFactory().getUsuarioDAO();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
		Usuario user = new Usuario();
		if (session.getAttribute("usuario") != null) user = (Usuario)session.getAttribute("usuario");
		
		if (user.getUsuarioId() == 0) response.sendRedirect("auth");
		else if (!( "ADMIN".equals(user.getRol()) || "MANAGER".equals(user.getRol()) )) {
		    response.sendRedirect("dashboard");
		}

		else {
			String opcion = "";
			if (request.getParameter("opcion") != null) opcion = request.getParameter("opcion");
			// MANAGER: solo lectura (lista)
			// ADMIN: acceso completo (CRUD)
			
			if ("MANAGER".equals(user.getRol())) {
				// Solo puede ver la lista de usuarios
				if (!"lista".equals(opcion) && !"".equals(opcion)) {
					response.sendRedirect("usuario");
					return;
				}
			}
			
			switch (opcion) {
				case "lista" : this.lista(request, response); break;
				case "editar" : 
					if ("MANAGER".equals(user.getRol())) {
						response.sendRedirect("usuario");
					} else {
						this.editar(request, response);
					}
					break;
				case "registrar" : 
					if ("MANAGER".equals(user.getRol())) {
						response.sendRedirect("usuario");
					} else {
						this.registrar(request, response);
					}
					break;
				case "eliminar" : 
					if ("MANAGER".equals(user.getRol())) {
						response.sendRedirect("usuario");
					} else {
						this.eliminar(request, response);
					}
					break;
				default:
					this.lista(request, response);
			}
		}
	}
	
	protected void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String texto = "";
		
		if (request.getParameter("texto") != null) {
			texto = request.getParameter("texto");
		}
		
		ArrayList<Usuario> lista = this.usuarioDAO.buscar(texto);
		
		request.setAttribute("lista", lista);
		request.getRequestDispatcher("/admin/usuario/usuario_lista.jsp").forward(request, response);
	}
	protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		if (request.getParameter("id") != null) {
			int usuarioId = Integer.parseInt(request.getParameter("id"));
			usuario = this.usuarioDAO.obtener(usuarioId);
		}
		
		request.setAttribute("registro", usuario);
		request.getRequestDispatcher("/admin/usuario/usuario_editar.jsp").forward(request, response);
	}
	protected void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));
		String nombre = request.getParameter("nombre");
		String clave = request.getParameter("clave");
		String rol = request.getParameter("rol");
		String fecha = request.getParameter("fecha");
		
		Usuario usuario = new Usuario(usuarioId, nombre, clave, rol, fecha);
		
		if (usuario.getUsuarioId() == 0) {
			this.usuarioDAO.crear(usuario);
		} else {
			this.usuarioDAO.actualizar(usuario);
		}
		
		response.sendRedirect("usuario");
	}
	protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") != null) {
			int usuarioId = Integer.parseInt(request.getParameter("id"));
			this.usuarioDAO.eliminar(usuarioId);
		}
		
		response.sendRedirect("usuario");
	}


}
