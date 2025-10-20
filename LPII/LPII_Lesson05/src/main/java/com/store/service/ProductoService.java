package com.store.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.store.model.Producto;

@Service
public class ProductoService {
	// simular una database
	private List<Producto> listaProducto = new ArrayList<>();
	private AtomicLong contadorId = new AtomicLong(1);
	public ProductoService() {
		listaProducto.add(new Producto(contadorId.getAndIncrement(), "Cafe", 10.50, 100));
		listaProducto.add(new Producto(contadorId.getAndIncrement(), "Cafe", 10.50, 100));
		listaProducto.add(new Producto(contadorId.getAndIncrement(), "Cafe", 10.50, 100));
	}
	
	//listar
	public List<Producto> obtenerTodos() {
		return listaProducto;
	}
	
	// injecion de depnecias
	
}
