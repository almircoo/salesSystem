package dao.mysql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.MySQLConexion;

import interfaces.IReporteDAO;

public class ReporteDAO implements IReporteDAO {
	private static ReporteDAO instancia;
    
    public static ReporteDAO getInstancia() {
        if (instancia == null) {
            instancia = new ReporteDAO();
        }
        return instancia;
    }
    
    private ReporteDAO() {}

    @Override
    public Map<String, Object> obtenerEstadisticasGenerales() {
        Map<String, Object> estadisticas = new HashMap<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = MySQLConexion.getConexion();
            String sql = "CALL usp_Reporte_General()";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                estadisticas.put("totalPedidos", rs.getInt("total_pedidos"));
                estadisticas.put("totalVentas", rs.getDouble("total_ventas"));
                estadisticas.put("totalClientes", rs.getInt("total_clientes"));
                estadisticas.put("productosActivos", rs.getInt("productos_activos"));
                estadisticas.put("pedidosPendientes", rs.getInt("pedidos_pendientes"));
            }
        } catch (Exception e) {
            System.out.println("Error en obtenerEstadisticasGenerales: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Error cerrando conexiones: " + e.getMessage());
            }
        }
        
        return estadisticas;
    }

    @Override
    public ArrayList<Map<String, Object>> obtenerVentasPorFecha(String fechaInicio, String fechaFin) {
        ArrayList<Map<String, Object>> ventasPorFecha = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = MySQLConexion.getConexion();
            String sql = "CALL usp_Reporte_Ventas_Por_Fecha(?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, fechaInicio);
            ps.setString(2, fechaFin);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Map<String, Object> venta = new HashMap<>();
                venta.put("fecha", rs.getDate("fecha"));
                venta.put("totalPedidos", rs.getInt("total_pedidos"));
                venta.put("totalVentas", rs.getDouble("total_ventas"));
                venta.put("promedioVenta", rs.getDouble("promedio_venta"));
                ventasPorFecha.add(venta);
            }
            
        } catch (Exception e) {
            System.out.println("Error en obtenerVentasPorFecha: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Error cerrando conexiones: " + e.getMessage());
            }
        }
        
        return ventasPorFecha;
    }

    @Override
    public ArrayList<Map<String, Object>> obtenerProductosMasVendidos() {
        ArrayList<Map<String, Object>> productosMasVendidos = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = MySQLConexion.getConexion();
            String sql = "CALL usp_Productos_Mas_Vendidos()";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Map<String, Object> producto = new HashMap<>();
                producto.put("producto", rs.getString("producto"));
                producto.put("totalVendido", rs.getInt("total_vendido"));
                producto.put("totalIngresos", rs.getDouble("total_ingresos"));
                productosMasVendidos.add(producto);
            }
            
        } catch (Exception e) {
            System.out.println("Error en obtenerProductosMasVendidos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Error cerrando conexiones: " + e.getMessage());
            }
        }
        
        return productosMasVendidos;
    }

    @Override
    public ArrayList<Map<String, Object>> obtenerProductosStockBajo() {
        ArrayList<Map<String, Object>> productosStockBajo = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = MySQLConexion.getConexion();
            String sql = "SELECT p.nombre, p.stock, p.precio, c.nombre as categoria " +
                        "FROM Producto p INNER JOIN Categoria c ON p.categoria_id = c.categoria_id " +
                        "WHERE p.stock < 5 AND p.estado = TRUE ORDER BY p.stock ASC";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Map<String, Object> producto = new HashMap<>();
                producto.put("nombre", rs.getString("nombre"));
                producto.put("stock", rs.getInt("stock"));
                producto.put("precio", rs.getDouble("precio"));
                producto.put("categoria", rs.getString("categoria"));
                productosStockBajo.add(producto);
            }
            
        } catch (Exception e) {
            System.out.println("Error en obtenerProductosStockBajo: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Error cerrando conexiones: " + e.getMessage());
            }
        }
        
        return productosStockBajo;
    }

    @Override
    public ArrayList<Map<String, Object>> obtenerClientesMasActivos() {
        ArrayList<Map<String, Object>> clientesActivos = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = MySQLConexion.getConexion();
            String sql = "SELECT c.nombre, c.email, COUNT(p.pedido_id) as total_pedidos, " +
                        "SUM(p.total) as total_gastado " +
                        "FROM Cliente c INNER JOIN Pedido p ON c.cliente_id = p.cliente_id " +
                        "WHERE c.estado = TRUE " +
                        "GROUP BY c.cliente_id " +
                        "ORDER BY total_pedidos DESC, total_gastado DESC " +
                        "LIMIT 10";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Map<String, Object> cliente = new HashMap<>();
                cliente.put("nombre", rs.getString("nombre"));
                cliente.put("email", rs.getString("email"));
                cliente.put("totalPedidos", rs.getInt("total_pedidos"));
                cliente.put("totalGastado", rs.getDouble("total_gastado"));
                clientesActivos.add(cliente);
            }
            
        } catch (Exception e) {
            System.out.println("Error en obtenerClientesMasActivos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Error cerrando conexiones: " + e.getMessage());
            }
        }
        
        return clientesActivos;
    }

}
