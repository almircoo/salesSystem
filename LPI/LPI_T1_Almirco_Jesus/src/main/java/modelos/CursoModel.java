package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.MySQLConexion;
import entidades.Curso;
import interfaces.ICursoModel;

public class CursoModel implements ICursoModel {

	@Override
	public int crear(Curso curso) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "insert into cursos values (null, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, curso.getCodigo());
			ps.setString(2, curso.getNombre());
			ps.setInt(3, curso.getVacantes());
			
			value = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al crear: " + e.getMessage());
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
						rs.getInt("vacantes")
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int actualizar(Curso curso) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int eliminar(int cursoId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
