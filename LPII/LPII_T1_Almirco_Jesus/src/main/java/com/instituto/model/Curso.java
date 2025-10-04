package com.instituto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tblCurso")
public class Curso {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idCurso", unique=true, nullable=false)
	private int id;
	@Column(name="nombreCurso", length=150)
	private String nombre;
	@Column(name="codigoCurso", unique=true, length=50)
	private String codigo;
	@Column(name="numCreditosCurso", length=20)
	private int creditos;
	
	//constructor vacio
	public Curso() {
		
	}
	//get y sets

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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	//toString

	@Override
	public String toString() {
		return "Curso [id=" + id + ", nombre=" + nombre + ", codigo=" + codigo + ", creditos=" + creditos + "]";
	}
	
	
}
