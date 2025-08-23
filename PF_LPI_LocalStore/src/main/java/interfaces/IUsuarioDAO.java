package interfaces;

import java.util.ArrayList;
import entidades.Usuario;

public interface IUsuarioDAO {

	public int crear(Usuario obj);
	public ArrayList<Usuario> listar();
	public Usuario obtener(int id);
	public int actualizar(Usuario obj);
	public int eliminar(int id);
	
	public ArrayList<Usuario> buscar(String texto);
	public Usuario autenticar(String nombre, String clave);
	
}
