package taller1;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	private static String[][] listaUsuarios = new String[3][2];
	private static String[][] listaActividades = new String[300][4];

	public static void main(String[] args) throws FileNotFoundException {
		guardarListas(listaUsuarios, "Usuarios");
		guardarListas(listaActividades, "Registros");
		menu();

	}

	private static void menu() {
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
		for (String[] cuenta : listaUsuarios) {
			if (usuario.equals(cuenta[0]) && contraseña.equals(cuenta[1]))
				return true;
		}
		return false;
	}

	private static void guardarListas(String[][] lista, String archivo) throws FileNotFoundException {
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
		Scanner sc = new Scanner(System.in);
		System.out.println("Bienvenido " + usuario + "!\r\n" + "\r\n" + "Que deseas realizar?\r\n" + "\r\n"
				+ "1) Registrar actividad.\r\n" + "2) Modificar actividad.\r\n" + "3) Eliminar actividad.\r\n"
				+ "4) Cambiar contraseña.\r\n" + "5) Salir.");
		System.out.print("Ingrese su opción: ");
		String opcion = sc.nextLine();

		switch (opcion) {
		case "1":
			System.out.print("Indique la actividad que desea registrar: ");
			break;

		case "2":
			System.out.println("¿Cual actividad deseas modificar?");
				System.out.println(recorrerActividades(usuario,sc));
			break;

		case "3":
			System.out.println("¿Cual actividad deseas eliminar?");
				System.out.println(recorrerActividades(usuario,sc));
			break;

		case "4":
			System.out.println("Ingrese la nueva contraseña: ");
			// DEJARLO COMO PREGUNTA AL AYUDANTE
			break;

		case "5":
			System.out.println("¡Ha salido corractamente!");
			break;
		default:
			System.out.println("Opcion no valida, ingrese nuevamente.");
			menuUsuarioVerificado(usuario);
		}
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

}
