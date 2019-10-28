package Servicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;
import static java.lang.Thread.sleep;

public class SPU01E02_damia_febrer {
	public static void main(String[] args) throws IOException{
		try {
			String linea;
			String numero_recibido;
			
			Process hijo = new ProcessBuilder("D:\\DAM\\SP\\aleatori.exe", "SPU01E02_fillDamia_Febrer").start(); // Se crea el proceso hijo pasandole la ruta del fichero a ejecutar
			
			BufferedReader entrada_hijo = new BufferedReader(new InputStreamReader(hijo.getInputStream())); // Esto envia al hijo
			PrintStream salidaHaciaHijo = new PrintStream(hijo.getOutputStream()); // Esto recibe del hijo
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Pulse intro para continuar, o fin para acabar...");
			while((linea = sc.nextLine()).compareTo("fin") != 0) { //Mientras lo que escribamos no sea fin se hace el bucle
				
				salidaHaciaHijo.println(""); //Se le pasa al hijo un str vacio ¡
				salidaHaciaHijo.flush(); //Se vacia el stream
				
				sleep(100); // Sleep para que el hijo tenga tiempo de hacer sus procesos y devolver sus valores
				
				System.out.println("El número recibido del hijo es: " + entrada_hijo.readLine()); // Recogemos lo recibido del hijo y lo mostramos por pantalla
				
				System.out.println("Pulsa intro para continuar, o fin para acabar..."); //Pedimos al usuario que vuelva a interactuar con el programa
				
			}
			System.out.println("Finalizando"); // Acabamos
		}
		catch(IOException e) {
			System.out.println(e);
		}
		catch(InterruptedException ex) {
			System.out.println(ex);
		}
	}
}