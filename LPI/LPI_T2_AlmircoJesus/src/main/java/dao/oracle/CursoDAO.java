package dao.oracle;

import java.util.ArrayList;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Curso> listar() {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public ArrayList<Curso> buscar(String texto) {
		// TODO Auto-generated method stub
		return null;
	}

}
