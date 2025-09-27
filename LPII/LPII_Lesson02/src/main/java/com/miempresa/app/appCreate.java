package com.miempresa.app;

import com.miempresa.model.Empleado;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class appCreate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LPII_Lesson02PU");
		EntityManager em = emf.createEntityManager();
		
		Empleado objEmp = new Empleado();
		objEmp.setName("Jesus");
		objEmp.setEmail("almirco@mail.com");
		objEmp.setSalary(244.50);
		
		em.getTransaction().begin();
		em.persist(objEmp);
		em.getTransaction().commit();
		System.out.println("Usuario EMPLEADO guardado en DB");
	}

}
