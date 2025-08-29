package dao.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import db.MySQLConexion;
import entidades.Producto;
import interfaces.IProductoDAO;

public class ProductoDAO implements IProductoDAO {

	private static IProductoDAO instancia;
	
	public static IProductoDAO getInstancia() {
		if (instancia == null) {
			instancia = new ProductoDAO();
		}
		return instancia;
	}
	
	private ProductoDAO() {}

	@Override
	public int crear(Producto obj) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "insert into producto values (null, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, obj.getNombre());
			ps.setInt(2, obj.getStock());
			ps.setDouble(3, obj.getPrecio());
			ps.setBoolean(4, obj.isEstado());
			ps.setInt(5, obj.getCategoriaId());
			
			value = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al crear: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return value;
	}

	@Override
	public ArrayList<Producto> listar() {
		ArrayList<Producto> lista = new ArrayList<Producto>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from producto";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Producto obj = new Producto(
						rs.getInt("producto_id"),
						rs.getString("nombre"),
						rs.getInt("stock"),
						rs.getDouble("precio"),
						rs.getBoolean("estado"),
						rs.getInt("categoria_id")
						);
				lista.add(obj);
			}
		} catch (Exception e) {
			System.out.println("Error al listar: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return lista;
	}

	@Override
	public Producto obtener(int id) {
		Producto obj = null;		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from producto where producto_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				obj = new Producto(
						rs.getInt("producto_id"),
						rs.getString("nombre"),
						rs.getInt("stock"),
						rs.getDouble("precio"),
						rs.getBoolean("estado"),
						rs.getInt("categoria_id")
				);
			}
		} catch (Exception e) {
			System.out.println("Error al obtener el registro: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return obj;
	}

	@Override
	public int actualizar(Producto obj) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "update producto set nombre = ?, stock = ?, precio = ?, estado = ?, categoria_id = ? where producto_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, obj.getNombre());
			ps.setInt(2, obj.getStock());
			ps.setDouble(3, obj.getPrecio());
			ps.setBoolean(4, obj.isEstado());
			ps.setInt(5, obj.getCategoriaId());
			
			ps.setInt(6, obj.getProductoId());
			System.out.println("Data : " + obj.toString());
			value = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al actualizar el registro: " + e.getMessage());
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
			String sql = "delete from producto where producto_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			value = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al eliminar el registro: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return value;
	}

	@Override
	public ArrayList<Producto> buscar(String texto) {
		ArrayList<Producto> lista = new ArrayList<Producto>();
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "{CALL usp_Buscar_producto(?)}";
			cs = con.prepareCall(sql);
			cs.setString(1, texto);
			
			rs = cs.executeQuery();
			
			while(rs.next()) {
				Producto obj = new Producto(
						rs.getInt("producto_id"),
						rs.getString("nombre"),
						rs.getInt("stock"),
						rs.getDouble("precio"),
						rs.getBoolean("estado"),
						rs.getInt("categoria_id")
				);
				obj.setCategoria(rs.getString("categoria"));
				lista.add(obj);
			}
		} catch (Exception e) {
			System.out.println("Error al listar: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return lista;
	}

}
