/*Integrante 1: Andrés Rojas 22.065.446-k ICCI
 *Integrante 2: Jorge Callejas 21.926.182-9 ICI 
 * 
 * 
 */
package taller1;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Main {
	private static String[][] listaUsuarios = new String[3][2];
	private static String[][] listaActividades = new String[300][4];
	private static String[] listaPosicion = new String[2];
	
	public static void main(String[] args) throws IOException {
		guardarListas(listaUsuarios, "Usuarios");
		guardarListas(listaActividades, "Registros");
		menu();
	}

	private static void menu() throws IOException { // Menu principal donde estara el login y el menu analisis
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
			menuAnalisis(sc);
			break;
		case "3":
			System.out.println("¡Ha salido correctamente!");
			break;
		default:
			System.out.println("Opción no valida, ingrese nuevamente");
			break;
		} 
	} while (!eleccion.equalsIgnoreCase("3"));
	}
	
	private static void menuAnalisis(Scanner sc) throws IOException {
		System.out.print("Bienvenido al menu de analisis!\r\n"
				+ "\r\n"
				+ "¿Que deseas realizar?\r\n"
				+ "\r\n"
				+ "1) Actividad más realizada\r\n"
				+ "2) Actividad más realizada por cada usuario\r\n"
				+ "3) Usuario con mayor procastinacion\r\n"
				+ "4) Ver todas las actividades\r\n"
				+ "5) Salir\n\nIngrese su opción: ");
		
		String eleccion = sc.nextLine();
		String[] opciones = {"1", "2", "3", "4"};

		boolean valida = false;
		for (String opcion : opciones) {
		    if (opcion.equals(eleccion)) {
		        valida = true;
		        break;
		    } else if(eleccion.equals("5")) {
		    	System.out.println("Has salido correctamente");
		    	break;
		    }
		}

		if (valida) {
		    recorrer(listaActividades, eleccion);
		} else {
		    System.out.println("Opción no válida");
		}
	
	}
	
	private static void usuarioMayorProcastinacion(String[][] mx) {
	    String usuarioMax = "";
	    int duracionMax = -1;

	    for (int u = 0; u < listaUsuarios.length; u++) {
	        String usuario = listaUsuarios[u][0];
	        int duracionTotal = 0;

	        for (int i = 0; i < mx.length; i++) {
	            if (mx[i][0] == null || !mx[i][0].equals(usuario)) continue;
	            duracionTotal += Integer.parseInt(mx[i][2]); // columna 2 = duración
	        }

	        if (duracionTotal > duracionMax) {
	            duracionMax = duracionTotal;
	            usuarioMax = usuario;
	        }
	    }

	    System.out.println("Usuario con mayor procrastinación: " + usuarioMax 
	        + " con " + duracionMax + " horas totales");
	}
	
	private static int[] agregarContador(int[] contadores) {
	    int[] nuevosContadores = new int[contadores.length + 1];
	    for (int i = 0; i < contadores.length; i++) {
	        nuevosContadores[i] = contadores[i];
	    }
	    nuevosContadores[contadores.length] = 1; 
	    return nuevosContadores;
	}
	
	private static String[] agregarActividad(String[] listaUnica, String datoNuevo) {
	    String[] nuevaLista = new String[listaUnica.length + 1];
	    for (int i = 0; i < listaUnica.length; i++) {
	        nuevaLista[i] = listaUnica[i];
	    }
	    nuevaLista[listaUnica.length] = datoNuevo;
	    return nuevaLista;
	}
	
	private static void recorrer(String[][] mx, String opcion) {
	    String[] listaActividadUnica = new String[0];
	    int[] contadores = new int[0]; 

	    for (int i = 0; i < mx.length; i++) {
	        if (mx[i][3] == null) continue;

	        String datoActual = mx[i][3];
	        
	
	        boolean encontrado = false;
	        for (int j = 0; j < listaActividadUnica.length; j++) {
	            if (listaActividadUnica[j].equals(datoActual)) {
	                contadores[j]++; 
	                encontrado = true;
	                break;
	            }
	        }
	        
	        if (!encontrado) {
	            listaActividadUnica = agregarActividad(listaActividadUnica, datoActual);
	            contadores = agregarContador(contadores);
	        }
	    }
	    switch (opcion) {
		case "1":
			actividadMasRealizada(listaActividadUnica, contadores);
			
			break;
		case "2":
			actividadMasRealizadaPorUsuario(listaActividades);
			break;
		case "3":
			usuarioMayorProcastinacion(listaActividades);
			break;
		case "4":
			printerActividades(listaActividadUnica);
	    
	    }
	}

	private static void actividadMasRealizadaPorUsuario(String[][] mx) {
	    for (int u = 0; u < listaUsuarios.length; u++) {
	        String usuario = listaUsuarios[u][0]; 

	        String[] actividadesUsuario = new String[0];
	        int[] contadoresUsuario = new int[0];

	        for (int i = 0; i < mx.length; i++) {
	            if (mx[i][0] == null || !mx[i][0].equals(usuario)) continue;

	            String actividad = mx[i][3];
	            boolean encontrado = false;

	            for (int j = 0; j < actividadesUsuario.length; j++) {
	                if (actividadesUsuario[j].equals(actividad)) {
	                    contadoresUsuario[j]++;
	                    encontrado = true;
	                    break;
	                }
	            }
	            if (!encontrado) {
	                actividadesUsuario = agregarActividad(actividadesUsuario, actividad);
	                contadoresUsuario = agregarContador(contadoresUsuario);
	            }
	        }

	       
	        int[] lista = {-1, 0};
	        for (int i = 0; i < actividadesUsuario.length; i++) {
	            if (contadoresUsuario[i] > lista[0]) {
	                lista[0] = contadoresUsuario[i];
	                lista[1] = i;
	            }
	        }
	        System.out.println(usuario + " -> "
	            + actividadesUsuario[lista[1]] + " -> con " + lista[0] + " horas registradas");
	    }
	}
	
	private static void actividadMasRealizada(String[] listaUnica, int[]contadores) {
		int[] lista = {-1, 0};
		
		for(int i = 0; i < listaUnica.length; i++) {
			if(contadores[i] > lista[0]) { // falta poner algo por si llega el contador o lista nulo
				lista[0] = contadores[i];
				lista[1] = i;
			}
		}
		System.out.println("La actividad más realizada fue: " + listaUnica[lista[1]] + " con " + lista[0] + " repeticiones");
	}
	
	private static boolean verificarUsuario(String usuario, String contraseña) {
		/*funcion que revisa la matriz de usuarios y 
		contraseñas, y si las credenciales de quien esta ingresando son correctas y se encuentran
		en el sistema se retorna true, con lo cual ingresa al menu de usuario verificado, en otro caso retorna false y vuelve
		al menu inicial
		*/
		for (String[] cuenta : listaUsuarios) {
			if (usuario.equals(cuenta[0]) && contraseña.equals(cuenta[1])) {
				listaPosicion = cuenta;
				return true; }
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
		lector.close();
	}

	private static void menuUsuarioVerificado(String usuario) throws IOException {
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
			boolean agrego = false;
			for (int i = 0; i < listaActividades.length; i++) {
				if (listaActividades[i][0] == null || listaActividades[i][0] == "") {
					listaActividades[i] = new String[4];
					listaActividades[i][0] = usuario;
			        System.out.print("Ingrese fecha de la actividad: ");
			        listaActividades[i][1] = sc.nextLine();
			        int horas;
			        String horaString;
			        System.out.print("Ingrese horas de la actividad: ");
			        do {
			            horaString = sc.nextLine();
			            try {
			                horas = Integer.valueOf(horaString);
			                if (horas < 1) System.out.print("Ingrese una hora valida: ");
			            } catch (NumberFormatException e) {
			            	System.out.print("Ingrese una hora valida: "); horas = 0;
			            }
			        } while (horas < 1);
			        listaActividades[i][2] = String.valueOf(horas);
			        System.out.print("Ingrese la actividad: ");
			        listaActividades[i][3] = sc.nextLine();
			        System.out.println("¡Actividad registrada correctamente!");
			        modificarArchivo(listaActividades, "Registros.txt");
			        agrego = true;
			        break;
				} 
			} if (!agrego) System.out.println("¡No queda espacio para nuevas actividades!"); 
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
				
				/* para cualquiera de los casos que no sea regresar, se recorre listaActividades (la cual es la matriz global que
				 contiene las actividades de todos los usuarios) y el valor que se retorna de "recorrerActividades" se 
				 usara como contador */
				case "1":
					seleccionModificar = seleccionModificar - 1;
					System.out.print("Ingrese nueva fecha: ");
					for (int i = 0; i < listaActividades.length; i++) { //se recorren todas las actividades de la matriz 
						if (usuario.equals(listaActividades[i][0])) { //si el usuario ingresa coincide con el de la actividad pueden pasar 2 cosas
							if (seleccionModificar == 0) { //si el contador llega a 0 se pide al usuario modificar lo pedido
								listaActividades[i][1] = sc.nextLine();
								break;
							} else seleccionModificar = seleccionModificar - 1; //si el contador no es igual a 0 se resta uno
						}
					}		
					System.out.println("¡Fecha modificada con exito!");
					modificarArchivo(listaActividades, "Registros.txt");
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
					modificarArchivo(listaActividades, "Registros.txt");
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
					modificarArchivo(listaActividades, "Registros.txt");
				break;
				
				default:
					System.out.println("Opción no valida, regresando al menu de usuario"); break;
				}
				
	}
			break;
		case "3": 
			/* Se usa la funcion "recorrerActividades" para que retorne el valor de la posicion de la actividad que se va a 
			 eliminar */
			System.out.println("¿Cual actividad deseas eliminar?");
				int seleccionEliminar = recorrerActividades(usuario,sc);
				System.out.println("¿Seguro de querer eliminar esta actividad?\n" //se pregunta si quiere o no quiere eliminar
						+ "1) Si\n"
						+ "2) No");
				System.out.print("Ingrese respuesta: ");
				String opcionMenuEliminar = sc.nextLine();
				switch (opcionMenuEliminar) {
				case "1":
					seleccionEliminar = seleccionEliminar - 1; 
					System.out.print("Ingrese nuevo tipo de actividad: ");
					for (int i = 0; i < listaActividades.length; i++) {
						if (usuario.equals(listaActividades[i][0])) {
							if (seleccionEliminar == 0) {
								listaActividades[i] = new String[4]; /* se reemplaza la actividad (usuario,fecha,hora,actividad) por una 
																variable vacia (null,null,null,null) para simular que se ha eliminado*/
								break;
							} else seleccionEliminar = seleccionEliminar - 1;
						}
					}
					System.out.println("¡Actividad eliminada con exito!");
					modificarArchivo(listaActividades, "Registros.txt");	
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
			System.out.print("Ingrese la nueva contraseña: ");
			cambiarContraseña(sc);
			break;

		case "5":
			System.out.println("¡Ha salido correctamente!");
			return;
		default:
			System.out.println("Opción no valida, ingrese nuevamente.");
			}
		} while (!opcion.equalsIgnoreCase("5"));
	}

	private static int recorrerActividades(String usuario, Scanner sc) {
		/* esta funcion printea todas las actividades del usuario en listadas, luego el scanner solicita una valor,
		 este sera retornado  y utilizado para encontrar la posicion de la actividad que se selecciona */
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
		return 0;
			} 
		return caso;
	}
	
	private static void cambiarContraseña(Scanner sc) throws IOException {
		String contraseña = sc.nextLine();
		listaPosicion[1] = contraseña; 
		modificarArchivo(listaUsuarios, "Usuarios.txt");
	}
	
	private static void printerActividades(String[] listaUnicos) {
		if(listaUnicos[0] == null) {
			return;
		}
		for(int i = 0; i < listaUnicos.length; i++) {
			System.out.println(listaUnicos[i]);
		}
	}
	
	private static void modificarArchivo(String[][] matriz, String archivo) throws IOException {
	    try (BufferedWriter reescritor = new BufferedWriter(new FileWriter(archivo))) {
	        for (int i = 0; i < matriz.length; i++) {
	            if (matriz[i][0] == null) {
	                reescritor.newLine();
	                
	            } else {
	            String lineaArchivo = "";
	            for (int j = 0; j < matriz[i].length; j++) {
	                lineaArchivo = lineaArchivo + matriz[i][j];
	                if (j < matriz[i].length - 1) {
	                    lineaArchivo = lineaArchivo + ";";
	                }
	            }
	            reescritor.write(lineaArchivo); reescritor.newLine(); //escribir nueva linea y salto para la siguiente
	        }
	    }
	}
	}
}