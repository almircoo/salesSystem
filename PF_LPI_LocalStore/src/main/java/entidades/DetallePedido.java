package entidades;

public class DetallePedido {
	private int detalleId;
	private int pedidoId;
	private int productoId;
	private String producto;
	private int cantidad;
	private double precioUnitario;
	private double subtotal;
	
	public DetallePedido() {
		this(0,0,0,"",0,0.0,0.0);
	}
	
	public DetallePedido(int pedidoId, int productoId, String producto, int cantidad,
			double precioUnitario, double subtotal) {
		super();
		this.pedidoId = pedidoId;
		this.productoId = productoId;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		this.subtotal = subtotal;
	}
	
	
	public DetallePedido(int detalleId, int pedidoId, int productoId, String producto, int cantidad,
			double precioUnitario, double subtotal) {
		super();
		this.detalleId = detalleId;
		this.pedidoId = pedidoId;
		this.productoId = productoId;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		this.subtotal = subtotal;
	}

	public int getDetalleId() {
		return detalleId;
	}

	public void setDetalleId(int detalleId) {
		this.detalleId = detalleId;
	}

	public int getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(int pedidoId) {
		this.pedidoId = pedidoId;
	}

	public int getProductoId() {
		return productoId;
	}

	public void setProductoId(int productoId) {
		this.productoId = productoId;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
	
	
	
	

}
