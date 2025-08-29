package dao.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.MySQLConexion;
import entidades.Curso;
import interfaces.ICursoDAO;

public class CursoDAO implements ICursoDAO {

	private static ICursoDAO instancia;
	
	public static ICursoDAO getInstancia() {
		if (instancia == null) {
			instancia = new CursoDAO();
		}
		return instancia;
	}
	
	private CursoDAO() {}

	@Override
	public int crear(Curso curso) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			con.setAutoCommit(false); // no se auto confirma
			String sql = "insert into cursos values (null, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, curso.getCodigo());
			ps.setString(2, curso.getNombre());
			ps.setInt(3, curso.getVacantes());
			ps.setInt(4, curso.getDocenteId());
			
			value = ps.executeUpdate();
			
			con.commit(); // confirmo y se guarda en la base de datos
		} catch (Exception e) {
			try {
				con.rollback();
			}catch(SQLException ex){
				System.out.println("Error al crear: " + ex.getMessage());
			}
			
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return value;
	}

	@Override
	public ArrayList<Curso> listar() {
		ArrayList<Curso> lista = new ArrayList<Curso>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from cursos";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Curso curso = new Curso(
						rs.getInt("curso_id"),
						rs.getString("codigo"),
						rs.getString("nombre"),
						rs.getInt("vacantes"),
						rs.getInt("docente_id")
						);
				lista.add(curso);
			}
		} catch (Exception e) {
			System.out.println("Error al listar: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return lista;
	}

	@Override
	public Curso obtener(int cursoId) {
		Curso curso = null;		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from cursos where curso_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, cursoId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				curso = new Curso(
					rs.getInt("curso_id"),
					rs.getString("codigo"),
					rs.getString("nombre"),
					rs.getInt("vacantes"),
					rs.getInt("docente_id")
				);
			}
		} catch (Exception e) {
			System.out.println("Error al obtener el registro: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return curso;
	}

	@Override
	public int actualizar(Curso curso) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "update cursos set codigo = ?, nombre = ?, vacantes = ?, docente_id = ? where curso_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, curso.getCodigo());
			ps.setString(2, curso.getNombre());
			ps.setInt(3, curso.getVacantes());
			ps.setInt(4, curso.getDocenteId());
			ps.setInt(5, curso.getCursoId());
			
			value = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al actualizar el registro: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return value;
	}

	@Override
	public int eliminar(int cursoId) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "delete from cursos where curso_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, cursoId);
			
			value = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al eliminar el registro: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return value;
	}

	@Override
	public ArrayList<Curso> buscar(String texto) {
		ArrayList<Curso> lista = new ArrayList<Curso>();
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "{CALL usp_Buscar_Curso(?)}";
			cs = con.prepareCall(sql);
			cs.setString(1, texto);
			
			rs = cs.executeQuery();
			
			while(rs.next()) {
				Curso curso = new Curso(
						rs.getInt("curso_id"),
						rs.getString("codigo"),
						rs.getString("nombre"),
						rs.getInt("vacantes"),
						rs.getInt("docente_id")
						);
				curso.setNombreDocente(rs.getString("nombreDocente"));
				lista.add(curso);
			}
		} catch (Exception e) {
			System.out.println("Error al listar: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return lista;
	}

}
