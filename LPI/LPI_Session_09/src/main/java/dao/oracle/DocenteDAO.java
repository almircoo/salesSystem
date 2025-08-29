package dao.oracle;

import java.util.ArrayList;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Docente> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Docente obtener(int docenteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int actualizar(Docente docente) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int eliminar(int docenteId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
