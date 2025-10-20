package com.academia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import com.academia.model.Docente;

@Service
public class DocenteService {
	private final List<Docente> listaDocente = new ArrayList<>();
	private final AtomicLong contadorId = new AtomicLong(1);
	
	public DocenteService() {
		listaDocente.add(new Docente(contadorId.getAndIncrement(), "Juan", "Diaz lopez", LocalDate.of(1998,11,9), 1500.00));
		listaDocente.add(new Docente(contadorId.getAndIncrement(), "Smith", "Ardel gomes", LocalDate.of(2000, 8,3), 1400.00));
		listaDocente.add(new Docente(contadorId.getAndIncrement(), "Luis", "Basquez luz", LocalDate.of(1999, 4,6), 1200.00));
	}
	//crear docente
	public void guardarDocente(Docente docente) {
		docente.setIdDocente(contadorId.getAndIncrement());
		listaDocente.add(docente);
	}
	//listar docente
	public List<Docente> obtenerTodo(){
		return listaDocente;
	}
}
