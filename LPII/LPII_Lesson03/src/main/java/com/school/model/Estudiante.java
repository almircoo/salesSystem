package com.school.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tblEstudiantes")
public class Estudiante {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idEstudiante", unique=true, nullable=false)
	private int id;
	@Column(name="emailEs", nullable=false, unique=true, length=100)
	private String email;
	@Column(name="nombreEs", nullable=false, length=100)
	private String nombre;
	@Column(name="apellidoEs", nullable=true,length=150)
	private String apellido;
	@Column(name="fechaNacimientoEs", nullable=true, updatable=false)
	private LocalDate fechaNacimiento;
	
	public Estudiante() {
		
	}
	//set y gets

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	
	
}
