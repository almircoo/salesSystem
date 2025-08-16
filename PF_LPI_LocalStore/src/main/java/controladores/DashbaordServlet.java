package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entidades.Producto;
import entidades.Cliente;
import fabrica.DAOFactory;
import interfaces.IProductoDAO;
import interfaces.IClienteDAO;
import interfaces.ICategoriaDAO;

@WebServlet(name = "dashboard", urlPatterns = { "/dashboard", "" })
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private IProductoDAO productoDAO;
    private IClienteDAO clienteDAO;
    private ICategoriaDAO categoriaDAO;

    public DashboardServlet() {
        super();
        this.productoDAO = DAOFactory.getDAOFactory().getProductoDAO();
        this.clienteDAO = DAOFactory.getDAOFactory().getClienteDAO();
        this.categoriaDAO = DAOFactory.getDAOFactory().getCategoriaDAO();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.dashboard(request, response);
    }

    protected void dashboard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener métricas básicas
        ArrayList<Producto> productos = this.productoDAO.listar();
        ArrayList<Cliente> clientes = this.clienteDAO.listar();

        // Calcular estadísticas
        int totalProductos = productos.size();
        int totalClientes = clientes.size();
        int totalCategorias = this.categoriaDAO.listar().size();

        // Productos con stock bajo (menos de 5)
        int productosStockBajo = 0;
        double valorInventario = 0.0;

        for (Producto p : productos) {
            if (p.getStock() < 5) {
                productosStockBajo++;
            }
            valorInventario += (p.getPrecio() * p.getStock());
        }

        // Productos más vendidos (simulado - en una implementación real vendría de tabla de ventas)
        Map<String, Integer> productosPopulares = new HashMap<>();
        if (productos.size() > 0) {
            productosPopulares.put(productos.get(0).getNombre(), 45);
            if (productos.size() > 1) productosPopulares.put(productos.get(1).getNombre(), 32);
            if (productos.size() > 2) productosPopulares.put(productos.get(2).getNombre(), 28);
        }

        // Establecer atributos para la vista
        request.setAttribute("activepage", "dashboard");
        request.setAttribute("totalProductos", totalProductos);
        request.setAttribute("totalClientes", totalClientes);
        request.setAttribute("totalCategorias", totalCategorias);
        request.setAttribute("productosStockBajo", productosStockBajo);
        request.setAttribute("valorInventario", String.format("%.2f", valorInventario));
        request.setAttribute("productosPopulares", productosPopulares);
        request.setAttribute("productos", productos);

        request.getRequestDispatcher("/dashboard/dashboard.jsp").forward(request, response);
    }
}
