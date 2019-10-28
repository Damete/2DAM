package JavaPractices;

//Dami� Febrer Rebasa 30/09/2019

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;


public class Ej10{
 public static void main(String args[]){ 
     
     Scanner sc = new Scanner(System.in);
     System.out.println("Introduce la ruta del fichero");
     String ruta = sc.nextLine();
     sc.close();

     String rutaDelFichero = ruta;
     InputStream fins = null;
     
     try {
         fins = new FileInputStream(rutaDelFichero);
         byte contenido[] = new byte[2*1024];
         int read_count = 0;
         while((read_count = fins.read(contenido)) > 0){
             System.out.println(new String(contenido, 0, read_count-1));
         }
     }
     catch (FileNotFoundException e) {
         System.out.println(e);        
     }
     catch (IOException e) {
         System.out.println(e);
     } 
     finally {
         try{
             if(fins != null) fins.close();
         }
         catch(Exception ex){
              System.out.println(ex);
         }
     }
 }
}
