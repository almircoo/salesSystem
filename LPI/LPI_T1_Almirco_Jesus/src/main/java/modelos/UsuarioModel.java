package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.MySQLConexion;
import entidades.Usuario;
import interfaces.IUsuarioModel;

public class UsuarioModel implements IUsuarioModel{
	@Override
	public int crear(Usuario usuario) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = MySQLConexion.getConexion();
			String sql = "INSERT INTO usuarios (nombres, apellidos, dni, correo, rol, intentos) VALUES (?, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getNombres());
			ps.setString(2, usuario.getApellidos());
			ps.setString(3, usuario.getDni());
			ps.setString(4, usuario.getCorreo());
			ps.setString(5, usuario.getRol());
			ps.setInt(6, usuario.getIntentos());
			value = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al crear: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return value;
	}

	@Override
	public List<Usuario> listar() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from usuarios";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Usuario usuario= new Usuario(
						rs.getInt("usuario_id"),
						rs.getString("nombres"),
						rs.getString("apellidos"),
						rs.getString("dni"),
						rs.getString("correo"),
						rs.getString("rol"),
						rs.getInt("intentos")
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
	public Usuario obtener(int cursoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int actualizar(Usuario usuario) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int eliminar(int cursoId) {
		// TODO Auto-generated method stub
		return 0;
	}

}

