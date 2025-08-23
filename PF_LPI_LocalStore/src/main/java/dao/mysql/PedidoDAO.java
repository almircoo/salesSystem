package dao.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import db.MySQLConexion;

import entidades.Pedido;
import interfaces.IPedidoDAO;

public class PedidoDAO implements IPedidoDAO {
private static IPedidoDAO instancia;
	
	public static IPedidoDAO getInstancia() {
		if (instancia == null) {
			instancia = new PedidoDAO();
		}
		return instancia;
	}
	
	private PedidoDAO() {}

	@Override
	public int crear(Pedido pedido) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "insert into pedido values (null, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);

			ps.setInt(1, pedido.getClienteId());
			ps.setTimestamp(2, Timestamp.valueOf(pedido.getFechaPedido()));
			ps.setString(3, pedido.getEstado());
			ps.setDouble(4, pedido.getTotal());
			ps.setString(5, pedido.getObservaciones());
			
			value = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al crear pedido: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return value;
	}

	@Override
	public ArrayList<Pedido> listar() {
		ArrayList<Pedido> lista = new ArrayList<Pedido>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "SELECT p.*, c.nombre as cliente_nombre FROM pedido p " +
						"INNER JOIN cliente c ON p.cliente_id = c.cliente_id " +
						"ORDER BY p.fecha_pedido DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Pedido pedido = new Pedido(
						rs.getInt("pedido_id"),
						rs.getInt("cliente_id"),
						rs.getString("cliente_nombre"),
						rs.getTimestamp("fecha_pedido").toLocalDateTime(),
						rs.getString("estado"),
						rs.getDouble("total"),
						rs.getString("observaciones"));
				lista.add(pedido);
			}
		} catch (Exception e) {
			System.out.println("Error al listar pedidos: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return lista;
	}

	@Override
	public Pedido obtener(int id) {
		Pedido pedido = null;		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "SELECT p.*, c.nombre as cliente_nombre FROM pedido p " +
						"INNER JOIN cliente c ON p.cliente_id = c.cliente_id " +
						"WHERE p.pedido_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				pedido = new Pedido(
						rs.getInt("pedido_id"),
						rs.getInt("cliente_id"),
						rs.getString("cliente_nombre"),
						rs.getTimestamp("fecha_pedido").toLocalDateTime(),
						rs.getString("estado"),
						rs.getDouble("total"),
						rs.getString("observaciones"));
			}
		} catch (Exception e) {
			System.out.println("Error al obtener pedido: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return pedido;
	}

	@Override
	public int actualizar(Pedido pedido) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "update pedido set cliente_id = ?, fecha_pedido = ?, estado = ?, total = ?, observaciones = ? where pedido_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, pedido.getClienteId());
			ps.setTimestamp(2, Timestamp.valueOf(pedido.getFechaPedido()));
			ps.setString(3, pedido.getEstado());
			ps.setDouble(4, pedido.getTotal());
			ps.setString(5, pedido.getObservaciones());
			ps.setInt(6, pedido.getPedidoId());
			value = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al actualizar pedido: " + e.getMessage());
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
			String sql = "delete from pedido where pedido_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			value = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al eliminar pedido: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return value;
	}

	@Override
	public ArrayList<Pedido> buscar(String texto) {
		ArrayList<Pedido> lista = new ArrayList<Pedido>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "SELECT p.*, c.nombre as cliente_nombre FROM pedido p " +
						"INNER JOIN cliente c ON p.cliente_id = c.cliente_id " +
						"WHERE c.nombre LIKE ? OR p.estado LIKE ? OR p.pedido_id LIKE ? " +
						"ORDER BY p.fecha_pedido DESC";
			ps = con.prepareStatement(sql);
			String busqueda = "%" + texto + "%";
			ps.setString(1, busqueda);
			ps.setString(2, busqueda);
			ps.setString(3, busqueda);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Pedido pedido = new Pedido(
						rs.getInt("pedido_id"),
						rs.getInt("cliente_id"),
						rs.getString("cliente_nombre"),
						rs.getTimestamp("fecha_pedido").toLocalDateTime(),
						rs.getString("estado"),
						rs.getDouble("total"),
						rs.getString("observaciones"));
				lista.add(pedido);
			}
		} catch (Exception e) {
			System.out.println("Error al buscar pedidos: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return lista;
	}

	@Override
	public int cambiarEstado(int pedidoId, String nuevoEstado) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "update pedido set estado = ? where pedido_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, nuevoEstado);
			ps.setInt(2, pedidoId);
			value = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al cambiar estado del pedido: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return value;
	}

	@Override
	public ArrayList<Pedido> listarPorEstado(String estado) {
		ArrayList<Pedido> lista = new ArrayList<Pedido>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "SELECT p.*, c.nombre as cliente_nombre FROM pedido p " +
						"INNER JOIN cliente c ON p.cliente_id = c.cliente_id " +
						"WHERE p.estado = ? ORDER BY p.fecha_pedido DESC";
			ps = con.prepareStatement(sql);
			ps.setString(1, estado);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Pedido pedido = new Pedido(
						rs.getInt("pedido_id"),
						rs.getInt("cliente_id"),
						rs.getString("cliente_nombre"),
						rs.getTimestamp("fecha_pedido").toLocalDateTime(),
						rs.getString("estado"),
						rs.getDouble("total"),
						rs.getString("observaciones"));
				lista.add(pedido);
			}
		} catch (Exception e) {
			System.out.println("Error al listar pedidos por estado: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return lista;
	}

	@Override
	public ArrayList<Pedido> listarPorFecha(String fechaInicio, String fechaFin) {
		ArrayList<Pedido> lista = new ArrayList<Pedido>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "SELECT p.*, c.nombre as cliente_nombre FROM pedido p " +
						"INNER JOIN cliente c ON p.cliente_id = c.cliente_id " +
						"WHERE DATE(p.fecha_pedido) BETWEEN ? AND ? " +
						"ORDER BY p.fecha_pedido DESC";
			ps = con.prepareStatement(sql);
			ps.setString(1, fechaInicio);
			ps.setString(2, fechaFin);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Pedido pedido = new Pedido(
						rs.getInt("pedido_id"),
						rs.getInt("cliente_id"),
						rs.getString("cliente_nombre"),
						rs.getTimestamp("fecha_pedido").toLocalDateTime(),
						rs.getString("estado"),
						rs.getDouble("total"),
						rs.getString("observaciones"));
				lista.add(pedido);
			}
		} catch (Exception e) {
			System.out.println("Error al listar pedidos por fecha: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return lista;
	}
	
}
