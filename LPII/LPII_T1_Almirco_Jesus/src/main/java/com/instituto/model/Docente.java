package com.instituto.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tblDocente")
public class Docente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idDocente", unique=true, nullable=false)
	private int id;
	@Column(name="nombreDocente", nullable=true,length=50)
	private String nombre;
	@Column(name="emailDocente",  nullable=true, unique=true, length=100)
	private String email;
	@Column(name="sueldoDocente", nullable=true)
	private double sueldo;
	@Column(name="fechaNacimientoDocente", nullable=true, updatable=true)
	private LocalDate fechaNacimiento;
	
	//
	public Docente() {
		
	}
	//gets y sets

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	//tostring
	
	
}
