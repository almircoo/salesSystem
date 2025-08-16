package fabrica;

import interfaces.ICategoriaDAO;
import interfaces.IClienteDAO;
import interfaces.IProductoDAO;

public abstract class DAOFactory {

	public static final int MYSQL = 1;
	public static final int ORACLE = 2;
	public static final int SQLSERVER = 3;
	public static final int POSGRESQL = 4;
	
	public static DAOFactory getDAOFactory() {
		return getDAOFactory(MYSQL); // qui debo definir con qué base de datos voy a trabajar
	}
	
	private static DAOFactory getDAOFactory(int db) {
		switch (db) {
			case MYSQL:
				return MySqlDAOFactory.getInstancia();
			case ORACLE:
				return null;
			case SQLSERVER:
				return null;
			case POSGRESQL:
				return null;
			default:
				return null;
		}
	}
	
	//métodos que retornan los DAO por cada entidad

	public abstract ICategoriaDAO getCategoriaDAO();
	public abstract IProductoDAO getProductoDAO();
	public abstract IClienteDAO getClienteDAO();
	
}
