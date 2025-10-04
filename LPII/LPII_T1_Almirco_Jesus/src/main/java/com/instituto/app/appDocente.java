package com.instituto.app;

import java.time.LocalDate;
import java.util.Scanner;

import com.instituto.model.Docente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class appDocente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LPII_T1_ALMIRCO_JESUSPU");
		EntityManager em = emf.createEntityManager();
		int opcion = 0;
		Scanner scanner = new Scanner(System.in);
		
		while(opcion !=5) {
			System.out.println("\n==== CRUD DOCENTE =====\n");
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
					System.out.println("===== AGREGAR DOCENTE =======");
					//1 
					Docente objDoc = new Docente();
					// 2
					System.out.println("Digite su Nombre: ");
					objDoc.setNombre(scanner.nextLine());
					
					System.out.println("Digite su Email: ");
					objDoc.setEmail(scanner.nextLine());
					
					System.out.println("Digite sueldo: ");
					objDoc.setSueldo(Double.parseDouble(scanner.nextLine()));
					
					System.out.println("Digite fecha de nacimiento: ");
					objDoc.setFechaNacimiento(LocalDate.parse(scanner.nextLine()));
					// 3
					em.getTransaction().begin();
					em.persist(objDoc); //para alamcenar en db
					em.getTransaction().commit();
					System.out.println("Docente agregado correctamente \n");
					break;
				case 2:
					System.out.println("===== BUSCAR DOCENTE ======= ");
					System.out.println("Digital el ID del docente a Buscar");
					Docente estudianteEncontrado = em.find(Docente.class, scanner.nextInt());
					if (estudianteEncontrado !=null) {
						System.out.println("Id: " +  estudianteEncontrado.getId() + "\n");
						System.out.println("Nombre: " +  estudianteEncontrado.getNombre() + "\n");
						System.out.println("Email: " +  estudianteEncontrado.getEmail() + "\n");
						System.out.println("Sueldo: " +  estudianteEncontrado.getSueldo() + "\n");
						System.out.println("F. N.: " +  estudianteEncontrado.getFechaNacimiento() + "\n");
					}else {
						System.out.println("ID no encontrado \n");
					}
					break;
				case 3:
					System.out.println("===== ACTUALIZAR DOCENTE =======");
					System.out.println("Ingrese el ID de Docente a actualizar: \n");
					int idDocente = scanner.nextInt(); 
				    scanner.nextLine(); // Consumir el resto de la línea
				    
				    Docente actualizarDocente = em.find(Docente.class, idDocente);
				    
				    if(actualizarDocente !=null) {
				    	System.out.println("Ingrese un nuevo Nombre: ");
				    	actualizarDocente.setNombre(scanner.nextLine());
						
						System.out.println("Ingrese un nuevo Email: ");
						actualizarDocente.setEmail(scanner.nextLine());
						
						System.out.println("Ingrese un nuevo Sueldo: ");
						actualizarDocente.setSueldo(Double.parseDouble(scanner.nextLine()));
						
						System.out.println("Ingrese un nuevo fecha de Nacimiento: ");
						try {
							actualizarDocente.setFechaNacimiento(LocalDate.parse(scanner.nextLine()));
				        } catch (Exception e) {
				            System.out.println(" Formato de fecha incorrecto. La fecha de nacimiento no se actualizará.");
				            
				        }
						em.getTransaction().begin();
						em.merge(actualizarDocente); // se usa para hacer cambios en la entidad
						em.getTransaction().commit();
						System.out.println("Docente actualizado correctamente. ");
				    }else {
				    	System.out.println("No se encontró docente con ID: " + idDocente);
				    }
					
					break;
				case 4:
					System.out.println("===== ELIMINAR DOCENTE =======");
					
					//2 datos
					System.out.println("Ingrese el id del estudiante a eliminar: \n");
					Docente docente = em.find(Docente.class, scanner.nextLine());
					
					if(docente!=null) {
						em.getTransaction().begin();
						em.remove(docente); // elemina de la DB
						em.getTransaction().commit();
						System.out.println("Docente Eliminado correctamente \n");
					}else {
						System.out.println("ID de Docente no existe \n");
					}
					
					break;
				case 5:
					System.out.println("Hasta luego!....");
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
