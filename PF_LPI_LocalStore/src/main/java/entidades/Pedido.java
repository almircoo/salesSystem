package entidades;

import java.util.Date;
import java.util.List;

public class Pedido {
	private int pedidoId;
	private int clienteId;
	private int usuarioId;
	private Date fechaPedido;
	private String tipoComprobante;
	private String numeroComprobante;
	private double subtotal;
	private double igv;
	private double total;
	private String metodoPago;
	private String estado;
	private String observaciones;

	private Cliente cliente;
	private Usuario usuario;
	private List<DetallePedido> detalles;

	public Pedido() {
		this(0, 0, new Date(), "", "", 0.0, 0.0, 0.0, "", "", "");
	}

	public Pedido(int clienteId, int usuarioId, Date fechaPedido, String tipoComprobante, String numeroComprobante,
			double subtotal, double igv, double total, String metodoPago, String estado, String observaciones) {
		this.clienteId = clienteId;
		this.usuarioId = usuarioId;
		this.fechaPedido = fechaPedido;
		this.tipoComprobante = tipoComprobante;
		this.numeroComprobante = numeroComprobante;
		this.subtotal = subtotal;
		this.igv = igv;
		this.total = total;
		this.metodoPago = metodoPago;
		this.estado = estado;
		this.observaciones = observaciones;
	}

	public Pedido(int clienteId, int usuarioId, String tipoComprobante, String numeroComprobante, double subtotal,
			double igv, double total, String metodoPago, String estado, String observaciones,
			List<DetallePedido> detalles) {
		this.clienteId = clienteId;
		this.usuarioId = usuarioId;
		this.tipoComprobante = tipoComprobante;
		this.numeroComprobante = numeroComprobante;
		this.subtotal = subtotal;
		this.igv = igv;
		this.total = total;
		this.metodoPago = metodoPago;
		this.estado = estado;
		this.observaciones = observaciones;
		this.detalles = detalles;
	}

	public Pedido(int pedidoId, int clienteId, int usuarioId, Date fechaPedido, String tipoComprobante,
			String numeroComprobante, double subtotal, double igv, double total, String metodoPago, String estado,
			String observaciones, Cliente cliente, Usuario usuario, List<DetallePedido> detalles) {
		this.pedidoId = pedidoId;
		this.clienteId = clienteId;
		this.usuarioId = usuarioId;
		this.fechaPedido = fechaPedido;
		this.tipoComprobante = tipoComprobante;
		this.numeroComprobante = numeroComprobante;
		this.subtotal = subtotal;
		this.igv = igv;
		this.total = total;
		this.metodoPago = metodoPago;
		this.estado = estado;
		this.observaciones = observaciones;
		this.cliente = cliente;
		this.usuario = usuario;
		this.detalles = detalles;
	}

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

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public String getTipoComprobante() {
		return tipoComprobante;
	}

	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getIgv() {
		return igv;
	}

	public void setIgv(double igv) {
		this.igv = igv;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<DetallePedido> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetallePedido> detalles) {
		this.detalles = detalles;
	}

	public void calcularTotales() {
		if (detalles != null) {
			this.subtotal = detalles.stream().mapToDouble(DetallePedido::getSubtotal).sum();
			this.igv = this.subtotal * 0.18;
			this.total = this.subtotal + this.igv;
		}
	}

	public void agregarDetalle(DetallePedido detalle) {
		if (detalles != null) {
			detalles.add(detalle);
			calcularTotales();
		}
	}

	@Override
	public String toString() {
		return "Pedido [pedidoId=" + pedidoId + ", clienteId=" + clienteId + ", usuarioId=" + usuarioId
				+ ", fechaPedido=" + fechaPedido + ", tipoComprobante=" + tipoComprobante + ", numeroComprobante="
				+ numeroComprobante + ", subtotal=" + subtotal + ", igv=" + igv + ", total=" + total + ", metodoPago="
				+ metodoPago + ", estado=" + estado + ", observaciones=" + observaciones + ", cliente=" + cliente
				+ ", usuario=" + usuario + ", detalles=" + detalles + "]";
	}

	
}
