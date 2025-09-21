package com.store.app;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import com.store.model.Producto;

import jakarta.persistence.EntityManager;

public class Create {

	public static void main(String[] args) {
		// 1-Inicializar el JPA
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LPII_Lesson01PU");
		EntityManager em = emf.createEntityManager();
		//2 crear objeto producto
		Producto objPro = new Producto();
		objPro.setNombre("Arroz");
		objPro.setPrecio(200.30);
		objPro.setStock(200);
		objPro.setEstado("Inactivo");
		
		//3 transacion de persistencia.
		em.getTransaction().begin();
		em.persist(objPro);
		em.getTransaction().commit();
		
		//mensaje de confrimacion
		System.out.println("Producto guardado");
	}

}
