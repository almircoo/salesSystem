package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.MySQLConexion;
import entidades.Docente;
import interfaces.IDocenteDAO;

public class DocenteDAO implements IDocenteDAO {

	private static IDocenteDAO instancia;
	
	public static IDocenteDAO getInstancia() {
		if (instancia == null) {
			instancia = new DocenteDAO();
		}
		return instancia;
	}
	
	private DocenteDAO() {}

	@Override
	public int crear(Docente docente) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "insert into docentes values (null, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, docente.getDni());
			ps.setString(2, docente.getNombre());
			ps.setString(3, docente.getEspecialidad());
			
			value = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al crear: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return value;
	}

	@Override
	public ArrayList<Docente> listar() {
		ArrayList<Docente> lista = new ArrayList<Docente>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from docentes";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Docente docente = new Docente(
						rs.getInt("docente_id"),
						rs.getString("dni"),
						rs.getString("nombre"),
						rs.getString("especialidad")
						);
				lista.add(docente);
			}
		} catch (Exception e) {
			System.out.println("Error al listar: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return lista;
	}

	@Override
	public Docente obtener(int docenteId) {
		Docente docente = null;		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from docentes where docente_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, docenteId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				docente = new Docente(
					rs.getInt("docente_id"),
					rs.getString("dni"),
					rs.getString("nombre"),
					rs.getString("especialidad")
				);
			}
		} catch (Exception e) {
			System.out.println("Error al obtener el registro: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return docente;
	}

	@Override
	public int actualizar(Docente docente) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "update docentes set dni = ?, nombre = ?, especialidad = ? where docente_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, docente.getDni());
			ps.setString(2, docente.getNombre());
			ps.setString(3, docente.getEspecialidad());
			ps.setInt(4, docente.getDocenteId());
			
			value = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al actualizar el registro: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return value;
	}

	@Override
	public int eliminar(int docenteId) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "delete from docentes where docente_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, docenteId);
			
			value = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al eliminar el registro: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return value;
	}
}
