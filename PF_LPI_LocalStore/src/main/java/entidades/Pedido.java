package entidades;

import java.time.LocalDateTime;

public class Pedido {
	private int pedidoId;
	private int clienteId;
	private String cliente;
	private LocalDateTime fechaPedido;
	private String estado; //PENDIENTE, PROCESANDO, EN CAMINO, ENTREGADO, CANCELADO
	private double total;
	private String observaciones;
	
	
	public Pedido(int pedidoId, int clienteId, String cliente, LocalDateTime fechaPedido, String estado, double total,
			String observaciones) {
		super();
		this.pedidoId = pedidoId;
		this.clienteId = clienteId;
		this.cliente = cliente;
		this.fechaPedido = fechaPedido;
		this.estado = estado;
		this.total = total;
		this.observaciones = observaciones;
	}
	
	public Pedido(int clienteId, String cliente, LocalDateTime fechaPedido, String estado, double total,
			String observaciones) {
		super();
		this.clienteId = clienteId;
		this.cliente = cliente;
		this.fechaPedido = fechaPedido;
		this.estado = estado;
		this.total = total;
		this.observaciones = observaciones;
	}
	public Pedido() {
		this(0,0,"",null,"PENDIENTE", 0.0, "");
	}

	//GET AND SETTERS
	public int getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(int pedidoId) {
		this.pedidoId = pedidoId;
	}

	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public LocalDateTime getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(LocalDateTime fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
	

}
