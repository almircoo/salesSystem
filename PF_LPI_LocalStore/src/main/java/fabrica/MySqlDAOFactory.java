package fabrica;

import interfaces.ICategoriaDAO;
import interfaces.IClienteDAO;
import interfaces.IProductoDAO;

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
	public ICategoriaDAO getCategoriaDAO() {
		return dao.mysql.CategoriaDAO.getInstancia();
	}

	@Override
	public IProductoDAO getProductoDAO() {
		return dao.mysql.ProductoDAO.getInstancia();
	}

	@Override
	public IClienteDAO getClienteDAO() {
		return dao.mysql.ClienteDAO.getInstancia();
	}

}
