package taller1;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	private static String[][] listaUsuarios = new String[3][2];
	
	public static void main(String[] args) throws FileNotFoundException {
			guardarUsuarios();
			menu();
			
	}
	
	private static void menu() {
		System.out.println("1) Menu de Usuarios\r\n"
				+ "2) Menu de Analisis\r\n"
				+ "3) Salir");
		
		System.out.print("Ingrese su opción: ");
		Scanner sc = new Scanner(System.in);
		String eleccion = sc.nextLine();
		
		
		switch (eleccion) {
		case "1":
			System.out.print("Usuario: ");
			String usuario = sc.nextLine();
			System.out.print("Contraseña: ");
			String contraseña = sc.nextLine();
			verificarUsuario(usuario, contraseña);
			
			break;
		case "2":
			System.out.println("caso 2");
			break;
		case "3":
			System.out.println("Ha salido correctamente");
			break;
		default:
			System.out.println("Opcion no valida.");
		}
	}
	
	private static boolean verificarUsuario(String usuario, String contraseña) {
		for (String[] cuenta: listaUsuarios) {
			if (usuario.equals(cuenta[0]) && contraseña.equals(cuenta[1])) System.out.println("usuario conocido"); return true;
		}
		return false; 
	}
	
	private static void guardarUsuarios () throws FileNotFoundException {
		Scanner lector = new Scanner(new File("Usuarios.txt"));
        int i = 0;
        while(lector.hasNextLine()) {
            String linea = lector.nextLine();
            String[] partes = linea.split(";");
            
            listaUsuarios[i] = partes;
            i++;
        }
	}
}
