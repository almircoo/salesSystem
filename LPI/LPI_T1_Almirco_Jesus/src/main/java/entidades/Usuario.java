package entidades;

public class Usuario {
	private int usuarioId; // curso_id
	private String nombres;
	private String apellidos;
	private String dni;
	private String correo;
	private String rol;
	private int intentos;
	
	// constructores
	public Usuario() {
	}
	
	public Usuario(int usuarioId, String nombres, String apellidos, String dni, String correo, String rol,
			int intentos) {
		super();
		this.usuarioId = usuarioId;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.dni = dni;
		this.correo = correo;
		this.rol = rol;
		this.intentos = intentos;
	}
	
	public Usuario(String nombres, String apellidos, String dni, String correo, String rol,
			int intentos) {
		this(0, nombres, apellidos, dni, correo, rol, intentos);
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public int getIntentos() {
		return intentos;
	}

	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}
	
	
	
	
}
