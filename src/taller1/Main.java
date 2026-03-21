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
			System.out.println("caso 1");
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
		return true;
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
        
        for (i = 0; i < listaUsuarios.length; i++) {
        	for (int j = 0; j < listaUsuarios[0].length; j++) {
        		System.out.println(listaUsuarios[i][j]);
        	}
        }
	}
}
