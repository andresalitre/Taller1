/*Integrante 1: Andrés Rojas 22.065.446-k ICCI
 *Integrante 2: Jorge Callejas 21.926.182-9 ICI 
 * 
 * 
 */
package taller1;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	private static String[][] listaUsuarios = new String[3][2];
	private static String[][] listaActividades = new String[300][4];
	private static String[] listaPosicion = new String[2];
	
	public static void main(String[] args) throws FileNotFoundException {
		guardarListas(listaUsuarios, "Usuarios");
		guardarListas(listaActividades, "Registros");
		menu();

	}

	private static void menu() { // Menu principal donde estara el login y el menu analisis
		String eleccion;
		do {
		System.out.println("1) Menu de Usuarios\r\n" + "2) Menu de Analisis\r\n" + "3) Salir");

		System.out.print("Ingrese su opción: ");
		Scanner sc = new Scanner(System.in);
		eleccion = sc.nextLine();

		switch (eleccion) {
		case "1":
			System.out.print("Usuario: ");
			String usuario = sc.nextLine();
			System.out.print("Contraseña: ");
			String contraseña = sc.nextLine();

			if (verificarUsuario(usuario, contraseña)) {
				System.out.println("Acceso correcto!");
				menuUsuarioVerificado(usuario);
			} else
				System.out.println("Usuario no existente"); 

			break;
		case "2":
			System.out.println("caso 2");

			break;
		case "3":
			System.out.println("¡Ha salido correctamente!");
			break;
		default:
			System.out.println("Opcion no valida, ingrese nuevamente"); 
		} 
	} while (!eleccion.equals("3"));
}

	private static boolean verificarUsuario(String usuario, String contraseña) {
		/*funcion que revisa la matriz de usuarios y 
		contraseñas, y si las credenciales de quien esta ingresando son correctas y se encuentran
		en el sistema se retorna true, con lo cual ingresa al menu de usuario verificado, en otro caso retorna false y vuelve
		al menu inicial
		*/
		for (String[] cuenta : listaUsuarios) {
			if (usuario.equals(cuenta[0]) && contraseña.equals(cuenta[1]))
				listaPosicion = cuenta;
				return true;
		}
		return false;
	}

	private static void guardarListas(String[][] lista, String archivo) throws FileNotFoundException {
		/*Esta función guarda en matrices los valores de los archivos, se llama a la matriz y el nombre del archivo
		 * para guardar en contenido del archivo en la matriz
		 */
		Scanner lector = new Scanner(new File(archivo + ".txt"));
		int i = 0;
		while (lector.hasNextLine()) {
			String linea = lector.nextLine();
			String[] partes = linea.split(";");
			lista[i] = partes;
			i++;
		}
	}

	private static void menuUsuarioVerificado(String usuario) {
		
		
		String opcion;
		do {
		Scanner sc = new Scanner(System.in);
		System.out.println("Bienvenido " + usuario + "!\r\n" + "\r\n" + "¿Que deseas realizar?\r\n" + "\r\n"
				+ "1) Registrar actividad.\r\n" + "2) Modificar actividad.\r\n" + "3) Eliminar actividad.\r\n"
				+ "4) Cambiar contraseña.\r\n" + "5) Salir.");
		System.out.print("Ingrese su opción: ");
		opcion = sc.nextLine();

		switch (opcion) {
		case "1":
			System.out.println("Mostrando actividades del usuario:");
			printActividades(usuario);
			System.out.println("Presione enter para continuar");
			sc.nextLine();
			break;
			
		case "2":
			System.out.println("¿Cual actividad deseas modificar?");
				int seleccionModificar = recorrerActividades(usuario,sc);
				if (seleccionModificar == 0)  opcion = "0"; 
				else {
				System.out.println("Que deseas modificar?\r\n"
						+ "\r\n"
						+ "0) Regresar.\r\n"
						+ "1) Fecha\r\n"
						+ "2) Duracion\r\n"
						+ "3) Tipo de actividad");
				System.out.print("Ingrese su opción: ");
				String opcionMenuModificar = sc.nextLine();
				switch (opcionMenuModificar) {
				case "0":
					System.out.println("¡Ha salido corractamente!"); break;
				
				case "1":
					seleccionModificar = seleccionModificar - 1;
					System.out.print("Ingrese nueva fecha: ");
					for (int i = 0; i < listaActividades.length; i++) {
						if (usuario.equals(listaActividades[i][0])) {
							if (seleccionModificar == 0) {
								listaActividades[i][1] = sc.nextLine();
								break;
							} else seleccionModificar = seleccionModificar - 1;
						}
					}		
					System.out.println("¡Fecha modificada con exito!");
				break;
				case"2":
					seleccionModificar = seleccionModificar - 1;
					System.out.print("Ingrese nueva duración: ");
					for (int i = 0; i < listaActividades.length; i++) {
						if (usuario.equals(listaActividades[i][0])) {
							if (seleccionModificar == 0) {
								listaActividades[i][2] = sc.nextLine();
								break;
							} else seleccionModificar = seleccionModificar - 1;
						}
					}				
					System.out.println("¡Duración modificada con exito!");
				break;
				case"3":
					seleccionModificar = seleccionModificar - 1;
					System.out.print("Ingrese nuevo tipo de actividad: ");
					for (int i = 0; i < listaActividades.length; i++) {
						if (usuario.equals(listaActividades[i][0])) {
							if (seleccionModificar == 0) {
								listaActividades[i][3] = sc.nextLine();
								break;
							} else seleccionModificar = seleccionModificar - 1;
						}
					}
					System.out.println("¡Actividad modificada con exito!");
				break;
				
				default:
					System.out.println("Opción no valida, regresando al menu de usuario"); break;
				}
				
	}
			break;

		case "3":
			System.out.println("¿Cual actividad deseas eliminar?");
				int seleccionEliminar = recorrerActividades(usuario,sc);
				System.out.println("¿Seguro de querer eliminar esta actividad?\n"
						+ "1) Si\n"
						+ "2) No");
				System.out.print("Ingrese respuesta: ");
				String opcionMenuEliminar = sc.nextLine();
				switch (opcionMenuEliminar) {
				case "1":
					
				break;
				
				case "2":
					System.out.println("Volviendo al menu de usuario.");
				break;
				default:
					System.out.println("Respuesta no valida, volviendo al menu de usuario.");
				break;	
				}		
			break;

		case "4":
			System.out.println("Ingrese la nueva contraseña: ");
			cambiarContraseña(sc);
			// DEJARLO COMO PREGUNTA AL AYUDANTE
			break;

		case "5":
			System.out.println("¡Ha salido corractamente!");
			break;
		default:
			System.out.println("Opcion no valida, ingrese nuevamente.");
			}
		} while (!opcion.equalsIgnoreCase("5"));
	}

	private static int recorrerActividades(String usuario, Scanner sc) {
		System.out.println("0) Regresar.");
		int i = 1;
		for (String[] linea : listaActividades) {
			if (usuario.equals(linea[0])) {
				System.out.println(i + ") " + linea[0] + ";" + linea[1] + ";" + linea[2] + ";" + linea[3]);
				i++;
			}
		}
		int caso = 0;
		try {
			caso = Integer.valueOf(sc.nextLine());
		} catch (Exception e) {
			System.out.println("Número invalido");
		}
		return caso;
	}
	
	private static void cambiarContraseña(Scanner sc) {
		System.out.print("Ingrese su nueva contraseña");
		String contraseña = sc.nextLine();
		System.out.println(listaUsuarios[0][1]);
		listaPosicion[1] = contraseña; 
		System.out.println(listaUsuarios[0][1]);
		System.out.println(listaPosicion[1]);
			
	}
	
	private static void printActividades(String usuario) { 
		/* funcion para printear las actividades cuando se selecciona
		la primera opcion del menu de usuario verificado (registrar actividad) */
		int i = 1;
		for (String[] linea : listaActividades) {
			if (usuario.equals(linea[0])) {
				System.out.println(i + ") " + linea[0] + ";" + linea[1] + ";" + linea[2] + ";" + linea[3]);
				i++;
					}
				}
			}
		}
