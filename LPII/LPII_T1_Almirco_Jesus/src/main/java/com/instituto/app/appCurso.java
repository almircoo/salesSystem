package com.instituto.app;

import java.time.LocalDate;
import java.util.Scanner;

import com.instituto.model.Curso;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class appCurso {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LPII_T1_ALMIRCO_JESUSPU");
		EntityManager em = emf.createEntityManager();
		int opcion = 0;
		Scanner scanner = new Scanner(System.in);
		
		while(opcion !=5) {
			System.out.println("\n==== CRUD CURSO =====\n");
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
					System.out.println("===== AGREGAR CURSO =======");
					//1 
					Curso objDoc = new Curso();
					// 2
					System.out.println("Digite su Nombre Curso: ");
					objDoc.setNombre(scanner.nextLine());
					
					System.out.println("Digite su codigo curso: ");
					objDoc.setCodigo(scanner.nextLine());
					
					System.out.println("Digite numero creditos del Curso: ");
					objDoc.setCreditos(scanner.nextInt());
					
					// 3
					em.getTransaction().begin();
					em.persist(objDoc); //para alamcenar en db
					em.getTransaction().commit();
					System.out.println("CURSO agregado correctamente \n");
					break;
				case 2:
					System.out.println("===== BUSCAR CURSO ======= ");
					
					System.out.println("Digital el ID del curso a Buscar");
					Curso cursoEncontrado = em.find(Curso.class, scanner.nextInt());
					if (cursoEncontrado !=null) {
						System.out.println(cursoEncontrado.toString());
					}else {
						System.out.println("ID CURSO no encontrado \n");
					}
					break;
					
				case 3:
					System.out.println("===== ACTUALIZAR CURSO =======");
					System.out.println("Ingrese el ID de curso a actualizar: \n");
					int idCurso = scanner.nextInt(); 
				    scanner.nextLine(); // Consumir el resto de la línea
				    
				    Curso actualizarCurso= em.find(Curso.class, idCurso);
				    
				    if(actualizarCurso !=null) {
				    	System.out.println("Ingrese un nuevo Nombre: ");
				    	actualizarCurso.setNombre(scanner.nextLine());
						
						System.out.println("Ingrese un nuevo codigo curso: ");
						actualizarCurso.setCodigo(scanner.nextLine());
						
						System.out.println("Ingrese un nuevo numero creditos: ");
						actualizarCurso.setCreditos(scanner.nextInt());
								
						em.getTransaction().begin();
						em.merge(actualizarCurso); // se usa para hacer cambios en la entidad
						em.getTransaction().commit();
						System.out.println("Curso actualizado correctamente. ");
				    }else {
				    	System.out.println("No se encontró Curso con ID: " + idCurso);
				    }
					
					break;
				case 4:
					System.out.println("===== ELIMINAR CURSO =======");
					
					//2 datos
					System.out.println("Ingrese el id del Curso a eliminar: \n");
					Curso curso = em.find(Curso.class, scanner.nextLine());
					
					if(curso!=null) {
						em.getTransaction().begin();
						em.remove(curso); // elemina de la DB
						em.getTransaction().commit();
						System.out.println("Curso Eliminado correctamente \n");
					}else {
						System.out.println("ID de Curso no existe \n");
					}
					
					break;
				case 5:
					System.out.println("Hasta luego, vuelva pronto!....");
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
