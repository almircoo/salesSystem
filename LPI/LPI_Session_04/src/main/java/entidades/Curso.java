package entidades;

public class Curso {
	private String codigo, nombre;
	private int vacantes;
	
	public Curso(String codigo, String nombre, int vacantes) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.vacantes = vacantes;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVacantes() {
		return vacantes;
	}

	public void setVacantes(int vacantes) {
		this.vacantes = vacantes;
	}
	
	
}
