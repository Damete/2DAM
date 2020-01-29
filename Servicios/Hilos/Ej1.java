package Servicios.Hilos;

import java.io.IOException;

public class Ej1 {
	public static void main(String[] args) {  
		if (args.length <= 0) {
			System.err.println("Se necessita un programa a executar");  
			System.exit(-1);
		}

		Runtime runtime = Runtime.getRuntime();  
		try {
			Process process = runtime.exec(args);  
			process.destroy();
		}catch(IOException ex){
			System.err.println("Excepci� de E/S!!");  
			System.exit(-1);
		}
	}
}