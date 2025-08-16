package dao.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import db.MySQLConexion;
import entidades.Cliente;
import interfaces.IClienteDAO;

public class ClienteDAO implements IClienteDAO {

	private static IClienteDAO instancia;
	
	public static IClienteDAO getInstancia() {
		if (instancia == null) {
			instancia = new ClienteDAO();
		}
		return instancia;
	}
	
	private ClienteDAO() {}

	@Override
	public int crear(Cliente obj) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "insert into cliente values (null, ?, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, obj.getDni());
			ps.setString(2, obj.getNombre());
			ps.setString(3, obj.getEmail());
			ps.setString(4, obj.getTelefono());
			ps.setString(5, obj.getDireccion());
			ps.setString(6, obj.getFechaNac());
			
			value = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al crear: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return value;
	}

	@Override
	public ArrayList<Cliente> listar() {
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from cliente";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Cliente obj = new Cliente(
						rs.getInt("cliente_id"),
						rs.getString("dni"),
						rs.getString("nombre"),
						rs.getString("email"),
						rs.getString("telefono"),
						rs.getString("direccion"),
						rs.getString("fecha_nac")
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
	public Cliente obtener(int id) {
		Cliente obj = null;		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from cliente where cliente_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				obj = new Cliente(
					rs.getInt("cliente_id"),
					rs.getString("dni"),
					rs.getString("nombre"),
					rs.getString("email"),
					rs.getString("telefono"),
					rs.getString("direccion"),
					rs.getString("fecha_nac")
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
	public int actualizar(Cliente obj) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "UPDATE Cliente SET dni = ?, nombre = ?, email = ?, telefono = ?, direccion = ?, fecha_nac = ? WHERE cliente_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, obj.getDni());
			ps.setString(2, obj.getNombre());
			ps.setString(3, obj.getEmail());
			ps.setString(4, obj.getTelefono());
			ps.setString(5, obj.getDireccion());
			ps.setString(6, obj.getFechaNac());
			ps.setInt(7, obj.getClienteId());
			
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
			String sql = "delete from cliente where cliente_id = ?";
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
	public ArrayList<Cliente> buscar(String texto) {
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "{CALL usp_Buscar_Cliente(?)}";
			cs = con.prepareCall(sql);
			cs.setString(1, texto);
			
			rs = cs.executeQuery();
			
			while(rs.next()) {
				Cliente obj = new Cliente(
						rs.getInt("cliente_id"),
						rs.getString("dni"),
						rs.getString("nombre"),
						rs.getString("email"),
						rs.getString("telefono"),
						rs.getString("direccion"),
						rs.getString("fecha_nac")
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

}
