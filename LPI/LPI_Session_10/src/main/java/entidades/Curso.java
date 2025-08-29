package entidades;

public class Curso {
	private int cursoId; // curso_id
	private String codigo;
	private String nombre;
	private int vacantes;
	private int docenteId; // fk
	
	private String nombreDocente; // no es campo de tabla
	
	public Curso(int cursoId, String codigo, String nombre, int vacantes, int docenteId) {
		super();
		this.cursoId = cursoId;
		this.codigo = codigo;
		this.nombre = nombre;
		this.vacantes = vacantes;
		this.docenteId = docenteId;
	}
	public Curso(String codigo, String nombre, int vacantes, int docenteId) {
		this(0, codigo, nombre, vacantes, docenteId);
	}
	public Curso() {
		this(0, "", "", 0, 0);
	}
	
	public int getCursoId() {
		return cursoId;
	}
	public void setCursoId(int cursoId) {
		this.cursoId = cursoId;
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
	public int getDocenteId() {
		return docenteId;
	}
	public void setDocenteId(int docenteId) {
		this.docenteId = docenteId;
	}
	public String getNombreDocente() {
		return nombreDocente;
	}
	public void setNombreDocente(String nombreDocente) {
		this.nombreDocente = nombreDocente;
	}
}
