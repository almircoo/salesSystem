package com.store.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //combierte la calse en una entidad
@Table(name="Producto") //asociamos la tabla de la DB
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="productId")
	private int idProducto;
	@Column(name="productName")
	private String nombre;
	@Column(name="productPrice")
	private double precio;
	@Column(name="productStock")
	private int stock;
	@Column(name="productStatus") //colunma en la DB agrega el valor de - estado en la columna productStatus
	private String estado;
	//constructor vacio
	public Producto() {
		
	}
	//get and set
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	//Tostring
	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombre=" + nombre + ", precio=" + precio + ", stock=" + stock
				+ ", estado=" + estado + "]";
	}
	
}
