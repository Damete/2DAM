package Tests;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Basico1 {
	public static void main (String[] args) {
		String antes = "Damia";
		Scanner sc = new Scanner(System.in);

		System.out.println("Que desea ver ? \n" +
						"1- Listado de los articulos alquilados por un cliente \n" + 
						"2- Listado de peliculas segun nombre de actor \n " +
						"3- Listado de peliculas segun categoria \n " +
						"4- Listado de ingresos \n " +
						"5- Listado de productos alquilados en unas fechas determinadas \n" +
						"6- Listado de actores de la ultima pelicula alquilada por un cliente concreto");

		String respuesta = sc.nextLine();
	}
}