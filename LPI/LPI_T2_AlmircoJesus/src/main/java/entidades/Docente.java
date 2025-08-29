package entidades;

public class Docente {
	private int docenteId;
	private String dni;
	private String nombre;
	private String especialidad;
	
	public Docente(int docenteId, String dni, String nombre, String especialidad) {
		super();
		this.docenteId = docenteId;
		this.dni = dni;
		this.nombre = nombre;
		this.especialidad = especialidad;
	}
	public Docente(String dni, String nombre, String especialidad) {
		this(0, dni, nombre, especialidad);
	}
	public Docente() {
		this(0, "", "", "");
	}
	public int getDocenteId() {
		return docenteId;
	}
	public void setDocenteId(int docenteId) {
		this.docenteId = docenteId;
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
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
}
