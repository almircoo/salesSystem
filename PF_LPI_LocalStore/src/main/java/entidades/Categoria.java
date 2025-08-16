package entidades;

public class Categoria {
	
	private int categoriaId;
	private String nombre;
	private boolean estado;
	
	public Categoria() {
		this(0, "", true);
	}

	public Categoria(String nombre, boolean estado) {
		this.nombre = nombre;
		this.estado = estado;
	}

	public Categoria(int categoriaId, String nombre, boolean estado) {
		this.categoriaId = categoriaId;
		this.nombre = nombre;
		this.estado = estado;
	}

	public int getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	
	
}
