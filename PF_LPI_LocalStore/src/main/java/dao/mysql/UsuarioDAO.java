package dao.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.MySQLConexion;
import entidades.Usuario;
import interfaces.IUsuarioDAO;

public class UsuarioDAO implements IUsuarioDAO {

	private static IUsuarioDAO instancia;
	
	public static IUsuarioDAO getInstancia() {
		if (instancia == null) {
			instancia = new UsuarioDAO();
		}
		return instancia;
	}
	
	private UsuarioDAO() {}
	
	@Override
	public int crear(Usuario usuario) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "insert into usuario values (null, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getClave());
			ps.setString(3, usuario.getRol());
			ps.setString(4, usuario.getFecha());
			
			value = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al crear: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return value;
	}

	@Override
	public ArrayList<Usuario> listar() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from usuario";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Usuario usuario = new Usuario(
						rs.getInt("usuario_id"),
						rs.getString("nombre"),
						rs.getString("clave"),
						rs.getString("rol"),
						rs.getString("fecha")
						);
				lista.add(usuario);
			}
		} catch (Exception e) {
			System.out.println("Error al listar: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return lista;
	}

	@Override
	public Usuario obtener(int usuarioId) {
		Usuario usuario = null;		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from usuario where usuario_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, usuarioId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				usuario = new Usuario(
					rs.getInt("usuario_id"),
					rs.getString("nombre"),
					rs.getString("clave"),
					rs.getString("rol"),
					rs.getString("fecha")
				);
			}
		} catch (Exception e) {
			System.out.println("Error al obtener el registro: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return usuario;
	}

	@Override
	public int actualizar(Usuario usuario) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "update usuario set nombre = ?, clave = ?, rol = ?, fecha = ? where usuario_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getClave());
			ps.setString(3, usuario.getRol());
			ps.setString(4, usuario.getFecha());
			ps.setInt(5, usuario.getUsuarioId());
			
			value = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al actualizar el registro: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return value;
	}

	@Override
	public int eliminar(int usuarioId) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "delete from usuario where usuario_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, usuarioId);
			
			value = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al eliminar el registro: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return value;
	}

	@Override
	public ArrayList<Usuario> buscar(String texto) {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "{CALL usp_Buscar_Usuario(?)}";
			cs = con.prepareCall(sql);
			cs.setString(1, texto);
			
			rs = cs.executeQuery();
			
			while(rs.next()) {
				Usuario usuario = new Usuario(
						rs.getInt("usuario_id"),
						rs.getString("nombre"),
						rs.getString("clave"),
						rs.getString("rol"),
						rs.getString("fecha")
						);
				lista.add(usuario);
			}
		} catch (Exception e) {
			System.out.println("Error al listar: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return lista;
	}

	@Override
	public Usuario autenticar(String nombre, String clave) {
		Usuario usuario = null;		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from usuario where nombre = ? and clave = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, nombre);
			ps.setString(2, clave);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				usuario = new Usuario(
					rs.getInt("usuario_id"),
					rs.getString("nombre"),
					"",
					rs.getString("rol"),
					rs.getString("fecha")
				);
			}
		} catch (Exception e) {
			System.out.println("Error al obtener el registro: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return usuario;
	}

}
