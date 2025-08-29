package interfaces;

import java.util.ArrayList;
import java.util.List;

import entidades.Usuario;

public interface IUsuarioModel {
	public int crear(Usuario usuario);
	public List<Usuario> listar();
	public Usuario obtener(int usuarioId);
	public int actualizar(Usuario usuario);
	public int eliminar(int usuarioId);
	
}
