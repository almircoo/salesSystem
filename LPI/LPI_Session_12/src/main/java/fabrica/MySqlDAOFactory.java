package fabrica;

import interfaces.ICursoDAO;
import interfaces.IDocenteDAO;
import interfaces.IUsuarioDAO;

public class MySqlDAOFactory extends DAOFactory {

	private static MySqlDAOFactory instancia;

	public static MySqlDAOFactory getInstancia() {
		if (instancia == null) {
			instancia = new MySqlDAOFactory();
		}
		return instancia;
	}
	
	private MySqlDAOFactory() {}
	
	@Override
	public ICursoDAO getCursoDAO() {
		return dao.mysql.CursoDAO.getInstancia();
	}

	@Override
	public IDocenteDAO getDocenteDAO() {
		return dao.mysql.DocenteDAO.getInstancia();
	}

	@Override
	public IUsuarioDAO getUsuarioDAO() {
		return dao.mysql.UsuarioDAO.getInstancia();
	}

}
