package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import entidades.Categoria;
import entidades.Cliente;
import fabrica.DAOFactory;
import interfaces.IClienteDAO;

/**
 * Servlet implementation class ClienteServlet
 */
@WebServlet(name = "cliente", urlPatterns = { "/cliente" })
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IClienteDAO clienteDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClienteServlet() {
        super();
        this.clienteDAO = DAOFactory.getDAOFactory().getClienteDAO();
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
		
		ArrayList<Cliente> lista = this.clienteDAO.buscar(texto);
		
		request.setAttribute("activepage", "cliente");
		
		request.setAttribute("lista", lista);				
		request.getRequestDispatcher("/cliente/cliente_lista.jsp").forward(request, response);
	}
	
	protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente obj = new Cliente();
		if (request.getParameter("id") != null) {
			int categoriaId = Integer.parseInt(request.getParameter("id"));
			obj = this.clienteDAO.obtener(categoriaId);
		}

		request.setAttribute("activepage", "cliente");
		
		request.setAttribute("registro", obj);
		request.getRequestDispatcher("/cliente/cliente_editar.jsp").forward(request, response);
	}
	
	protected void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("clienteId"));
		String dni = request.getParameter("dni");
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String telefono = request.getParameter("telefono");
		String direccion = request.getParameter("direccion");
		String fecha = request.getParameter("fecha");
		
		Cliente obj = new Cliente(id, dni, nombre, email, telefono, direccion, fecha);
		
		if (obj.getClienteId() == 0) {
			this.clienteDAO.crear(obj);
		} else {
			this.clienteDAO.actualizar(obj);
		}
		
		request.setAttribute("activepage", "cliente");
		
		response.sendRedirect("cliente");		
	}
	
	protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			this.clienteDAO.eliminar(id);
		}
		
		request.setAttribute("activepage", "cliente");
		
		response.sendRedirect("cliente");
	}

}
