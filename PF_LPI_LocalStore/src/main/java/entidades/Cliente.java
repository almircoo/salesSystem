package entidades;

public class Cliente {

	private int clienteId;
	private String dni;
	private String nombre;
	private String email;
	private String telefono;
	private String direccion;
	private String fechaNac;
	
	public Cliente() {
		this(0, "", "", "", "", "", "");
	}

	public Cliente(String dni, String nombre, String email, String telefono, String direccion, String fechaNac) {
		this.dni = dni;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.direccion = direccion;
		this.fechaNac = fechaNac;
	}

	public Cliente(int clienteId, String dni, String nombre, String email, String telefono, String direccion, String fechaNac) {
		this.clienteId = clienteId;
		this.dni = dni;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.direccion = direccion;
		this.fechaNac = fechaNac;
	}

	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	@Override
	public String toString() {
		return "" + nombre + "";
	}
	
	
	
}
