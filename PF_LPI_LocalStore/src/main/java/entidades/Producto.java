package entidades;

public class Producto {

	private int productoId;
	private String nombre;
	private int stock;
	private double precio;
	private boolean estado;
	
	private int categoriaId;
	private String categoria;

	public Producto() {
		this(0,"" ,0 ,0.0 ,true, 0);
	}

	public Producto(String nombre, int stock, double precio, boolean estado, int categoriaId) {
		this.nombre = nombre;
		this.stock = stock;
		this.precio = precio;
		this.estado = estado;
		this.categoriaId = categoriaId;
	}

	public Producto(int productoId, String nombre, int stock, double precio, boolean estado, int categoriaId) {
		this.productoId = productoId;
		this.nombre = nombre;
		this.stock = stock;
		this.precio = precio;
		this.estado = estado;
		this.categoriaId = categoriaId;
	}

	public int getProductoId() {
		return productoId;
	}

	public void setProductoId(int productoId) {
		this.productoId = productoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public int getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Producto [productoId=" + productoId + ", nombre=" + nombre + ", stock=" + stock + ", precio=" + precio
				+ ", estado=" + estado + ", categoriaId=" + categoriaId + ", categoria=" + categoria + "]";
	}
	
	
}
