package interfaces;

import java.util.ArrayList;
import entidades.Producto;

public interface IProductoDAO {

	public int crear(Producto obj);
	public ArrayList<Producto> listar();
	public Producto obtener(int id);
	public int actualizar(Producto obj);
	public int eliminar(int id);
	
	public ArrayList<Producto> buscar(String texto);
	
}
