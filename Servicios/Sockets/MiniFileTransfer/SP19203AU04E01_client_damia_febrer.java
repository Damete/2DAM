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
            Scanner s2 = new Scanner(System.in);
            boolean iterate = true;

            while(iterate){
                Socket socket = new Socket();
                InetSocketAddress addr = new InetSocketAddress("localhost", 44001);
                socket.connect(addr);

                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();

                System.out.println("\n DamoZilla \n 1- Listado de ficheros \n 2- Obtener fichero \n 3- Salir");
                String respuesta = sc.nextLine();

                switch(respuesta){
                    case "1":
                        //Obtener ID's
                        String solicitudLista = "LLISTA";
                        byte[] solicitarLista = solicitudLista.getBytes();
                        os.write(solicitarLista);

                        byte[] listadoServidor = new byte[1000];
                        is.read(listadoServidor);
                        String listado = new String(listadoServidor);
                        listado = cleanString(listado);
                        
                        //Printeamos el listado
                        System.out.println("\n Inicio del listado de ficheros \n");
                        System.out.println(listado);
                        System.out.println("\n Fin del listado de ficheros \n");
                        break;
                    
                    case "2":
                        //Enviamos la orden al servidor
                        String solicitud = "OBTENIR";
                        byte[] mensaje = solicitud.getBytes();
                        os.write(mensaje);

                        //El servidor pregunta el ID del fichero
                        byte[] pregunta = new byte[100];
                        is.read(pregunta);
                        String preguntaDelServer = new String(pregunta);

                        // Enviamos el ID del fichero
                        System.out.println(preguntaDelServer);
                        String id = sc.nextLine();

                        byte[] idFichero = id.getBytes();
                        os.write(idFichero);

                        //Recibimos la respuesta del servidor
                        byte[] resultado = new byte[50000];
                        is.read(resultado);
                        String fileContent = new String(resultado);

                        fileContent = cleanString(fileContent);

                        if(fileContent.equals("-1")){
                            System.out.println("El ID especificado no existe");
                        }
                        else{
                            System.out.println(fileContent);
                        }                        
                        break;

                    case "3":
                        iterate = false;
                        socket.close();
                        System.out.println("\n Cerrando DamoZilla, Adios :)");
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

    public static String cleanString(String dirty){
        String clean = "";

        for(int i = 0; i < dirty.length(); i++){
            if(dirty.charAt(i) != 00){
                clean += dirty.charAt(i);
            }
        }

        return clean;
    }
}