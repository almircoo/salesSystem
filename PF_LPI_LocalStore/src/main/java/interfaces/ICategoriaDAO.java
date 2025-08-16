package interfaces;

import java.util.ArrayList;
import entidades.Categoria;

public interface ICategoriaDAO {

	public int crear(Categoria obj);
	public ArrayList<Categoria> listar();
	public Categoria obtener(int id);
	public int actualizar(Categoria obj);
	public int eliminar(int id);
	
}
