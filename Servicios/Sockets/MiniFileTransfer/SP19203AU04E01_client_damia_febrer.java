package Servicios.Sockets.MiniFileTransfer;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.io.InputStream;
import java.io.OutputStream;

public class SP19203AU04E01_client_damia_febrer{   
    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(System.in);
            boolean iterate = true;
            Socket socket = new Socket();
            InetSocketAddress addr = new InetSocketAddress("localhost", 44014);
            socket.connect(addr);

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();            

            while(iterate){
                System.out.println("DamoZilla \n 1- Listado de ficheros \n 2- Obtener fichero \n 3- Salir");
                String respuesta = sc.nextLine();

                switch(respuesta){
                    case "1":
                        //Obtener ID's
                        break;
                    
                    case "2":
                        //Enviamos la orden al servidor
                        String solicitud = "OBTENIR";
                        byte[] mensaje = solicitud.getBytes();
                        os.write(mensaje);

                        //Recibimos la respuesta del servidor
                        byte[] resultado = new byte[200];
                        is.read(resultado);
                        String fileContent = new String(resultado);
                        System.out.println(fileContent);
                        break;

                    case "3":
                        iterate = false;
                        System.out.println("Cerrando DamoZilla, Adios :)");
                        break;

                    default:
                        System.out.println("\n La opcion solicitada es incorrecta \n");
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}