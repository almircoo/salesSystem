package entidades;

public class Usuario {
	private int usuarioId;
	private String nombre;
	private String clave;
	private String rol;
	
	public Usuario(int usuarioId, String nombre, String clave, String rol) {
		super();
		this.usuarioId = usuarioId;
		this.nombre = nombre;
		this.clave = clave;
		this.rol = rol;
	}
	public Usuario(String nombre, String clave, String rol) {
		this(0, nombre, clave, rol);
	}
	public Usuario() {
		this(0, "", "", "");
	}
	
	public int getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
}
