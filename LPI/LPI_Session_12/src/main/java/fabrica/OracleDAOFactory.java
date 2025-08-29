package fabrica;

import interfaces.ICursoDAO;
import interfaces.IDocenteDAO;
import interfaces.IUsuarioDAO;

public class OracleDAOFactory extends DAOFactory {

	private static OracleDAOFactory instancia;

	public static OracleDAOFactory getInstancia() {
		if (instancia == null) {
			instancia = new OracleDAOFactory();
		}
		return instancia;
	}
	
	private OracleDAOFactory() {}
	
	@Override
	public ICursoDAO getCursoDAO() {
		return dao.oracle.CursoDAO.getInstancia();
	}

	@Override
	public IDocenteDAO getDocenteDAO() {
		return dao.oracle.DocenteDAO.getInstancia();
	}

	@Override
	public IUsuarioDAO getUsuarioDAO() {
		return dao.oracle.UsuarioDAO.getInstancia();
	}

}
