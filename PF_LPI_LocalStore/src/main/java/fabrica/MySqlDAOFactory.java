package fabrica;

import interfaces.ICategoriaDAO;
import interfaces.IClienteDAO;
import interfaces.IDetallePedidoDAO;
import interfaces.IPedidoDAO;
import interfaces.IProductoDAO;
import interfaces.IReporteDAO;
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

	@Override
	public IUsuarioDAO getUsuarioDAO() {
		return dao.mysql.UsuarioDAO.getInstancia();
	}

	@Override
	public IPedidoDAO getPedidoDAO() {
		// TODO Auto-generated method stub
		return dao.mysql.PedidoDAO.getInstancia();
	}

	@Override
	public IDetallePedidoDAO getDetallePedidoDAO() {
		// TODO Auto-generated method stub
		return dao.mysql.DetallePedidoDAO.getInstancia();
	}

	@Override
	public IReporteDAO getReporteDAO() {
		// TODO Auto-generated method stub
		return dao.mysql.ReporteDAO.getInstancia();
	}

}
