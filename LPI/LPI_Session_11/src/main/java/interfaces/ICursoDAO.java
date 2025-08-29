package interfaces;

import java.util.ArrayList;

import entidades.Curso;

public interface ICursoDAO {

	public int crear(Curso curso);
	public ArrayList<Curso> listar();
	public Curso obtener(int cursoId);
	public int actualizar(Curso curso);
	public int eliminar(int cursoId);
	
	public ArrayList<Curso> buscar(String texto);
	
}
