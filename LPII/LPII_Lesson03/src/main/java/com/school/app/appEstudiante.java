package com.school.app;

import java.time.LocalDate;
import java.util.Scanner;

import com.school.model.Estudiante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class appEstudiante {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		Scanner scanner = new Scanner(System.in);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("appSchoolPU");
		EntityManager em = emf.createEntityManager();
		
		while(opcion !=5) {
			System.out.println("\n-- CRUD ESTUDIANTE --");
			System.out.println("1. Crear");
			System.out.println("2. Buscar");
			System.out.println("3. Actualizar");
			System.out.println("4. Eliminar");
			System.out.println("5. Salir");
			System.out.print("Elegir una opcion: ");
			
			opcion = scanner.nextInt();
			scanner.nextLine();
			
			switch(opcion) {
				case 1:
					System.out.println("Rutina crear");
					//1 
					
					Estudiante objEst = new Estudiante();
					
					System.out.println("Digite su Email: ");
					objEst.setEmail(scanner.nextLine());
					
					System.out.println("Digite su Nombre: ");
					objEst.setNombre(scanner.nextLine());
					
					System.out.println("Digite su email: ");
					objEst.setApellido(scanner.nextLine());
					
					System.out.println("Digite su fecha nacimiento: ");
					objEst.setFechaNacimiento(LocalDate.parse(scanner.nextLine()));
					
					em.getTransaction().begin();
					em.persist(objEst); //para alamcenar en db
					em.getTransaction().commit();
					System.out.println("Estudiante agregado correctamente \n");
					break;
				case 2:
					System.out.println("Digital el ID del estudiante a Buscar");
					
					Estudiante estudianteEncontrado = em.find(Estudiante.class, scanner.nextInt());
					if (estudianteEncontrado !=null) {
						System.out.println("Id: " +  estudianteEncontrado.getId() + "\n");
						System.out.println("Nombre: " +  estudianteEncontrado.getNombre() + "\n");
						System.out.println("Apellido: " +  estudianteEncontrado.getApellido() + "\n");
						System.out.println("Email: " +  estudianteEncontrado.getEmail() + "\n");
						System.out.println("F. N.: " +  estudianteEncontrado.getFechaNacimiento() + "\n");
					}else {
						System.out.println("ID no encontrado \n");
					}
					break;
					
				case 3:
					System.out.println("Ingrese el ID de estudiante a Actualizar: \n");
					Estudiante updateEstudiante = em.find(Estudiante.class, scanner.nextInt());
					scanner.nextLine();
//					if (updateEstudiante == null) {
//						System.out.println("\n⚠️  No se encontró estudiante con ID: " + updateEstudiante.getId());
//					}
					System.out.println("Ingrese un nuevo Nombre: ");
					updateEstudiante.setNombre(scanner.nextLine());
					
					System.out.println("Ingrese un nuevo Apellido: ");
					updateEstudiante.setApellido(scanner.nextLine());
					System.out.println("Ingrese un nuevo Email: ");
					updateEstudiante.setEmail(scanner.nextLine());
					System.out.println("Digite su fecha nacimiento: ");
					updateEstudiante.setFechaNacimiento(LocalDate.parse(scanner.nextLine()));
					
//					System.out.println("Ingrese un nuevo F. N.: ");
//					updateEstudiante.setFechaNacimiento(null);
					
					em.getTransaction().begin();
					em.merge(updateEstudiante); // se usa para cambios en la entidad
					em.getTransaction().commit();
					break;
				case 4:
					System.out.println("Rutina Eliminar");
					
					//2 datos
					System.out.println("Ingrese el id del estudiante a eliminar: \n");
					Estudiante estudiante = em.find(Estudiante.class, scanner.nextLine());
					
					
					em.getTransaction().begin();
					em.remove(estudiante); // elemina de l DB
					em.getTransaction().commit();
					System.out.println("Estudiante Eliminado correctamente \n");
					break;
				case 5:
					System.out.println("Hasta luego!");
					em.close();
					emf.close();
					break;
				default:
					System.out.println("Opcion no valida. \n");
					break;
			}
		}
	}

}
