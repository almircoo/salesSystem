package com.miempresa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //convierte a una entidad la clase Empelado para conectrase a base de datos
@Table(name="tbl_empleado") // asocia o crea la tabla empleado, si no se instancia @table por defecto se crea el nombre d ela clase
public class Empleado {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="name", nullable=false, length=50) // se define como debe llmarse la columna si no se define- por defecto usa el valor name
	private String name;
	@Column(name="email", nullable=true, length=50, unique=true)
	private String email;
	@Column(name="salary", nullable=true, length=50)
	private double salary;
	
	//constructor vacio
	public Empleado() {
		
	}
	//get y set

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
}
