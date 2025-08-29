package dao.oracle;

import java.util.ArrayList;

import entidades.Usuario;
import interfaces.IUsuarioDAO;

public class UsuarioDAO implements IUsuarioDAO {

	private static IUsuarioDAO instancia;
	
	public static IUsuarioDAO getInstancia() {
		if (instancia == null) {
			instancia = new UsuarioDAO();
		}
		return instancia;
	}
	
	private UsuarioDAO() {}
	
	@Override
	public int crear(Usuario usuario) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Usuario> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario obtener(int usuarioId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int actualizar(Usuario usuario) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int eliminar(int usuarioId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Usuario> buscar(String texto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario autenticar(String nombre, String clave) {
		// TODO Auto-generated method stub
		return null;
	}

}
