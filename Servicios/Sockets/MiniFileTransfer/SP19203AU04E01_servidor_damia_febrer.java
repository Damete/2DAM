package Servicios.Sockets.MiniFileTransfer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;

public class SP19203AU04E01_servidor_damia_febrer{
    public static void main(String[] args) {
        try{
            ServerSocket socket = new ServerSocket(44014);

            while(true){
                Socket servidor = socket.accept();

                InputStream is = servidor.getInputStream();
                OutputStream os = servidor.getOutputStream();

                byte[] solicitud = new byte[20];
                is.read(solicitud);
                
                String accion = new String(solicitud);

                if(accion.contains("LLISTA")){
                    //Mostrar listado de ficheros
                }
                else if(accion.contains("OBTENIR")){
                    //Devolver el contenido del fichero que se ha solicitado
                    String rutaFichero = "F:\\DAM\\Segundo\\2DAM\\2DAM_Java\\Servicios\\Sockets\\MiniFileTransfer\\test.dat";
                    File file = new File(rutaFichero);
                    byte[] fileContent = Files.readAllBytes(file.toPath());

                    os.write(fileContent);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}