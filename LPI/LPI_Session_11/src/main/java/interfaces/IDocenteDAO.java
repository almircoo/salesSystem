package interfaces;

import java.util.ArrayList;

import entidades.Docente;

public interface IDocenteDAO {

	public int crear(Docente docente);
	public ArrayList<Docente> listar();
	public Docente obtener(int docenteId);
	public int actualizar(Docente docente);
	public int eliminar(int docenteId);
	
}
