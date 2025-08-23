package interfaces;

import java.util.ArrayList;

import entidades.Pedido;

public interface IPedidoDAO {
	public int crear(Pedido pedido);
	public ArrayList<Pedido> listar();
	public Pedido obtener(int id);
	public int actualizar(Pedido pedido);
	public int eliminar(int id);
	
	public ArrayList<Pedido> buscar(String texto);
	public int cambiarEstado(int pedidoId, String nuevoEstado);
	public ArrayList<Pedido> listarPorEstado(String estado);
	public ArrayList<Pedido> listarPorFecha(String fechaInicio, String fechaFin);
}
