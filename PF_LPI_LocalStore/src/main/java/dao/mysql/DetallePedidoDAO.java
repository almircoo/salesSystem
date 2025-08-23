package dao.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import entidades.DetallePedido;
import interfaces.IDetallePedidoDAO;
import db.MySQLConexion;

public class DetallePedidoDAO implements IDetallePedidoDAO {
	private static IDetallePedidoDAO instancia;
	
	public static IDetallePedidoDAO getInstancia() {
		if (instancia == null) {
			instancia = new DetallePedidoDAO();
		}
		return instancia;
	}
	
	private DetallePedidoDAO() {}

	@Override
	public int crear(DetallePedido detallePedido) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "insert into DetallePedido values (null, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);

			ps.setInt(1, detallePedido.getPedidoId());
			ps.setInt(2, detallePedido.getProductoId());
			ps.setInt(3, detallePedido.getCantidad());
			ps.setDouble(4, detallePedido.getPrecioUnitario());
			ps.setDouble(5, detallePedido.getSubtotal());
			
			value = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al crear detalle pedido: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return value;
	}

	@Override
	public ArrayList<DetallePedido> listar() {
		ArrayList<DetallePedido> lista = new ArrayList<DetallePedido>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "SELECT dp.*, p.nombre as producto_nombre FROM DetallePedido dp " +
						"INNER JOIN producto p ON dp.producto_id = p.producto_id";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				DetallePedido detalle = new DetallePedido(
						rs.getInt("detalle_id"),
						rs.getInt("pedido_id"),
						rs.getInt("producto_id"),
						rs.getString("producto_nombre"),
						rs.getInt("cantidad"),
						rs.getDouble("precio_unitario"),
						rs.getDouble("subtotal"));
				lista.add(detalle);
			}
		} catch (Exception e) {
			System.out.println("Error al listar detalles pedido: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return lista;
	}

	@Override
	public DetallePedido obtener(int id) {
		DetallePedido detalle = null;		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "SELECT dp.*, p.nombre as producto_nombre FROM DetallePedido dp " +
						"INNER JOIN producto p ON dp.producto_id = p.producto_id " +
						"WHERE dp.detalle_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				detalle = new DetallePedido(
						rs.getInt("detalle_id"),
						rs.getInt("pedido_id"),
						rs.getInt("producto_id"),
						rs.getString("producto_nombre"),
						rs.getInt("cantidad"),
						rs.getDouble("precio_unitario"),
						rs.getDouble("subtotal"));
			}
		} catch (Exception e) {
			System.out.println("Error al obtener detalle pedido: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return detalle;
	}

	@Override
	public int actualizar(DetallePedido detallePedido) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "update DetallePedido set pedido_id = ?, producto_id = ?, cantidad = ?, precio_unitario = ?, subtotal = ? where detalle_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, detallePedido.getPedidoId());
			ps.setInt(2, detallePedido.getProductoId());
			ps.setInt(3, detallePedido.getCantidad());
			ps.setDouble(4, detallePedido.getPrecioUnitario());
			ps.setDouble(5, detallePedido.getSubtotal());
			ps.setInt(6, detallePedido.getDetalleId());
			value = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al actualizar detalle pedido: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return value;
	}

	@Override
	public int eliminar(int id) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "delete from DetallePedido where detalle_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			value = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al eliminar detalle pedido: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return value;
	}

	@Override
	public ArrayList<DetallePedido> listarPorPedido(int pedidoId) {
		ArrayList<DetallePedido> lista = new ArrayList<DetallePedido>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "SELECT dp.*, p.nombre as producto_nombre FROM DetallePedido dp " +
						"INNER JOIN producto p ON dp.producto_id = p.producto_id " +
						"WHERE dp.pedido_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, pedidoId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				DetallePedido detalle = new DetallePedido(
						rs.getInt("detalle_id"),
						rs.getInt("pedido_id"),
						rs.getInt("producto_id"),
						rs.getString("producto_nombre"),
						rs.getInt("cantidad"),
						rs.getDouble("precio_unitario"),
						rs.getDouble("subtotal"));
				lista.add(detalle);
			}
		} catch (Exception e) {
			System.out.println("Error al listar detalles por pedido: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return lista;
	}

	@Override
	public int eliminarPorPedido(int pedidoId) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "delete from DetallePedido where pedido_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, pedidoId);
			
			value = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al eliminar detalles por pedido: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return value;
	}
}
