package JavaPractices;

// Damià Febrer Rebasa 03/10/2019

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

public class Ej11 {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce la ruta del fichero");
		String ruta = sc.nextLine();
		sc.close();
		
		try {
		BufferedReader br = new BufferedReader(new FileReader(ruta));
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}