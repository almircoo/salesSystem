package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import entidades.Pedido;
import entidades.Usuario;
import fabrica.DAOFactory;
import interfaces.IPedidoDAO;
import interfaces.IReporteDAO;

/**
 * Servlet implementation class ReporteServlet
 */
@WebServlet(name = "reportes", urlPatterns = { "/reportes" })
public class ReporteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IPedidoDAO pedidoDAO;
	private IReporteDAO reporteDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReporteServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.pedidoDAO = DAOFactory.getDAOFactory().getPedidoDAO();
        this.reporteDAO = DAOFactory.getDAOFactory().getReporteDAO();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Usuario user = new Usuario();
		if (session.getAttribute("usuario") != null) user = (Usuario)session.getAttribute("usuario");
		
		if (user.getUsuarioId() == 0) {
			response.sendRedirect("auth");
			return;
		}
		
		// Todos los roles pueden acceder a reportes (solo lectura)
		String opcion = request.getParameter("opcion") != null ? request.getParameter("opcion") : "";
		
		switch (opcion) {
			case "ventas": this.reporteVentas(request, response); break;
			case "general": this.reporteGeneral(request, response); break;
			case "productos": this.reporteProductos(request, response); break;
			case "clientes": this.reporteClientes(request, response); break;
			default:
				this.reporteGeneral(request, response);
		}
		
	}
	

	protected void reporteGeneral(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> estadisticas = this.reporteDAO.obtenerEstadisticasGenerales();
		
		// Obtener pedidos recientes
		ArrayList<Pedido> pedidosRecientes = this.pedidoDAO.listar();
		if (pedidosRecientes.size() > 10) {
			pedidosRecientes = new ArrayList<>(pedidosRecientes.subList(0, 10));
		}
		
		request.setAttribute("activepage", "reportes");
		request.setAttribute("estadisticas", estadisticas);
		request.setAttribute("pedidosRecientes", pedidosRecientes);
		request.getRequestDispatcher("/admin/reporte/reporte_general.jsp").forward(request, response);
	}
	
	protected void reporteVentas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fechaInicio = request.getParameter("fechaInicio");
		String fechaFin = request.getParameter("fechaFin");
		
		// Si no se proporcionan fechas, usar el mes actual
		if (fechaInicio == null || fechaFin == null) {
			java.time.LocalDate hoy = java.time.LocalDate.now();
			fechaInicio = hoy.withDayOfMonth(1).toString();
			fechaFin = hoy.toString();
		}
		
		ArrayList<Map<String, Object>> ventasPorFecha = this.reporteDAO.obtenerVentasPorFecha(fechaInicio, fechaFin);
		ArrayList<Map<String, Object>> productosMasVendidos = this.reporteDAO.obtenerProductosMasVendidos();
		
		request.setAttribute("activepage", "reportes");
		request.setAttribute("ventasPorFecha", ventasPorFecha);
		request.setAttribute("productosMasVendidos", productosMasVendidos);
		request.setAttribute("fechaInicio", fechaInicio);
		request.setAttribute("fechaFin", fechaFin);
		request.getRequestDispatcher("/admin/reporte/reporte_ventas.jsp").forward(request, response);
	}
	
	protected void reporteProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Map<String, Object>> productosStockBajo = this.reporteDAO.obtenerProductosStockBajo();
		
		request.setAttribute("activepage", "reportes");
		request.setAttribute("productosStockBajo", productosStockBajo);
		request.getRequestDispatcher("/admin/reporte/reporte_productos.jsp").forward(request, response);
	}
	
	protected void reporteClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Map<String, Object>> clientesMasActivos = this.reporteDAO.obtenerClientesMasActivos();
		
		request.setAttribute("activepage", "reportes");
		request.setAttribute("clientesMasActivos", clientesMasActivos);
		request.getRequestDispatcher("/admin/reporte/reporte_clientes.jsp").forward(request, response);
	}

}
