package com.academia.model;

import java.time.LocalDate;
public class Docente {
	private Long idDocente;
	private String nombreDocente;
	private String apellidosDocente;
	private LocalDate fechaNacimiento;
	private double sueldoDocente;
	
	public Docente() {
	}
	public Docente(Long idDocente, String nombreDocente, String apellidosDocente, LocalDate fechaNacimiento, Double sueldoDocente) {
		this.idDocente = idDocente;
		this.nombreDocente = nombreDocente;
		this.apellidosDocente = apellidosDocente;
		this.fechaNacimiento = fechaNacimiento;
		this.sueldoDocente = sueldoDocente;
	}
	public Long getIdDocente() {
		return idDocente;
	}
	public void setIdDocente(Long idDocente) {
		this.idDocente = idDocente;
	}
	public String getNombreDocente() {
		return nombreDocente;
	}
	public void setNombreDocente(String nombreDocente) {
		this.nombreDocente = nombreDocente;
	}
	public String getApellidosDocente() {
		return apellidosDocente;
	}
	public void setApellidosDocente(String apellidosDocente) {
		this.apellidosDocente = apellidosDocente;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public double getSueldoDocente() {
		return sueldoDocente;
	}
	public void setSueldoDocente(double sueldoDocente) {
		this.sueldoDocente = sueldoDocente;
	}
	
	
}
