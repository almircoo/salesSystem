package interfaces;

import java.util.ArrayList;

import entidades.DetallePedido;

public interface IDetallePedidoDAO {
	public int crear(DetallePedido detallePedido);
	public ArrayList<DetallePedido> listar();
	public DetallePedido obtener(int id);
	public int actualizar(DetallePedido detallePedido);
	public int eliminar(int id);
	
	public ArrayList<DetallePedido> listarPorPedido(int pedidoId);
	public int eliminarPorPedido(int pedidoId);
}
