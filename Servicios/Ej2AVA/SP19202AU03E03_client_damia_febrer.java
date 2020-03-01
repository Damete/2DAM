package Servicios.Ej2AVA;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SP19202AU03E03_client_damia_febrer{
    public static void main(String[] args) {      

        try{
            boolean iterate = true;
            while(iterate){
            Socket cliente = new Socket();
            InetSocketAddress addr = new InetSocketAddress("localhost", 44014);
            cliente.connect(addr);

            InputStream is = cliente.getInputStream();
            OutputStream os = cliente.getOutputStream();

            Scanner sc = new Scanner(System.in);

            System.out.println("Bienvenido a la compra online de entradas, ¿que acción desea realizar? \n" +
                                "1. Mostrar número de entradas libres \n" +
                                "2. Vender entradas \n" +
                                "3. Salir \n" +
                                "=>");
    
            String opcion = sc.nextLine();
            byte [] paraServer = "Hola".getBytes();
    
            switch(opcion){
                case"1":
                paraServer = opcion.getBytes();
                os.write(paraServer);

                byte [] pregunta = new byte[200];
                is.read(pregunta);
                String preguntaZona = new String(pregunta);
                System.out.println(preguntaZona);

                String respuestaZona = sc.nextLine();
                os.write(respuestaZona.getBytes());
    
                byte [] numeroEntradas = new byte [5];
                is.read(numeroEntradas);
                String entradasDisponibles = formatearNumero(numeroEntradas);
                System.out.println("Para la zona " + respuestaZona + " quedan " + entradasDisponibles + " entradas disponibles");
                break;
    
                case"2":
                paraServer = opcion.getBytes();
                os.write(paraServer);

                byte [] delServer = new byte [200];
                is.read(delServer);
                String opcionesZona = new String(delServer);
                System.out.println(opcionesZona);

                String zona = sc.nextLine();
                if(zona.equals("platea") || zona.equals("Platea")){
                    paraServer = zona.getBytes();
                    os.write(paraServer);
                }
                else if(zona.equals("anfiteatro") || zona.equals("Anfiteatro")){
                    paraServer = zona.getBytes();
                    os.write(paraServer);
                }
                else if(zona.equals("VIP") || zona.equals("vip")){
                    paraServer = zona.getBytes();
                    os.write(paraServer);
                }
                else{
                    System.out.println("La opción introducida es invalida");
                }

                byte [] cantidad = new byte[200];
                is.read(cantidad);
                String preguntaCantidad = new String(cantidad);
                System.out.println("\n" + preguntaCantidad);

                String entradas = sc.nextLine();
                paraServer = entradas.getBytes();
                os.write(paraServer);

                byte [] resulOperacion = new byte[200];
                is.read(resulOperacion);
                String resultado = new String(resulOperacion);
                System.out.println("\n" + resultado);
                break;
    
                case"3":
                System.out.println("Tenga un buen dia");
                cliente.close();
                is.close();
                os.close();
                sc.close();
                iterate = false;
                break;
    
                default:
                System.out.println("El valor proporcionado es incorrecto, por favor vuelva a intentarlo");
                }
            }           


        } catch(IOException e)        {
            e.printStackTrace();
        }
    }

    public static String formatearNumero(byte[] numero){
        String sinFormatear = new String(numero);
        String formateado = "";

        for(int i = 0; i < sinFormatear.length(); i++){
            if(sinFormatear.charAt(i) != 00){
                formateado += sinFormatear.charAt(i);
            }
        }

        return formateado;
    }
}