package dao.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import db.MySQLConexion;
import entidades.Categoria;
import interfaces.ICategoriaDAO;

public class CategoriaDAO implements ICategoriaDAO {

	private static ICategoriaDAO instancia;
	
	public static ICategoriaDAO getInstancia() {
		if (instancia == null) {
			instancia = new CategoriaDAO();
		}
		return instancia;
	}
	
	private CategoriaDAO() {}

	@Override
	public int crear(Categoria obj) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "insert into categoria values (null, ?, ?)";
			ps = con.prepareStatement(sql);

			ps.setString(1, obj.getNombre());
			ps.setBoolean(2, obj.getEstado());
			
			value = ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Error al crear: " + e.getMessage());
			
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return value;
	}

	@Override
	public ArrayList<Categoria> listar() {
		ArrayList<Categoria> lista = new ArrayList<Categoria>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from categoria";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Categoria categoria = new Categoria(
						rs.getInt("categoria_id"),
						rs.getString("nombre"),
						rs.getBoolean("estado"));
				lista.add(categoria);
			}
		} catch (Exception e) {
			System.out.println("Error al listar: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return lista;
	}

	@Override
	public Categoria obtener(int id) {
		Categoria categoria = null;		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from categoria where categoria_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				categoria = new Categoria(
						rs.getInt("categoria_id"),
						rs.getString("nombre"),
						rs.getBoolean("estado"));
			}
		} catch (Exception e) {
			System.out.println("Error al obtener el registro: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return categoria;
	}

	@Override
	public int actualizar(Categoria obj) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "update categoria set nombre = ?, estado = ? where categoria_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, obj.getNombre());
			ps.setBoolean(2, obj.getEstado());
			ps.setInt(3, obj.getCategoriaId());
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
			String sql = "delete from categoria where categoria_id = ?";
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

}
