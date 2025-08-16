package interfaces;

import java.util.ArrayList;
import entidades.Cliente;

public interface IClienteDAO {

	public int crear(Cliente obj);
	public ArrayList<Cliente> listar();
	public Cliente obtener(int id);
	public int actualizar(Cliente obj);
	public int eliminar(int id);
	
	public ArrayList<Cliente> buscar(String texto);
	
}
