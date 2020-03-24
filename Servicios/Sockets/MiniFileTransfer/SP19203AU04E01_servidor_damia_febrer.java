package Servicios.Sockets.MiniFileTransfer;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;

public class SP19203AU04E01_servidor_damia_febrer{
    static File[] listOfFiles = null;
    static String[] files = new String[10000];
    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(44014);

            while(true) {
                Socket servidor = socket.accept();

                InputStream is = servidor.getInputStream();
                OutputStream os = servidor.getOutputStream();

                byte[] solicitud = new byte[50];
                is.read(solicitud);
                String accion = new String(solicitud);
                accion = cleanString(accion);

                if (accion.equals("LLISTA")) {
                    initializateListOfFiles();
                }
                else if(accion.equals("OBTENIR")){
                    //Solicitar el ID del fichero
                    String dameElId = "introduzca el ID del fichero que quiere obtener";
                    byte[] pregunta = dameElId.getBytes();
                    os.write(pregunta);

                    //recibimos el ID del fichero
                    byte[] recibido = new byte[50];
                    is.read(recibido);
                    String id = new String(recibido);
                    
                    id = cleanString(id);

                    if(listOfFiles == null){
                        initializateListOfFiles();
                    }

                    if(Integer.parseInt(id) >= listOfFiles.length){
                        String error = "-1";
                        byte[] fail = error.getBytes();
                        os.write(fail);
                    }
                    else{
                        File folder = new File("F:\\DAM\\Segundo\\2DAM\\2DAM_Java\\Servicios\\Sockets\\MiniFileTransfer");
                        listOfFiles = folder.listFiles();

                        File ficheroSolicitado = listOfFiles[Integer.parseInt(id)];

                        byte[] fileContent = Files.readAllBytes(ficheroSolicitado.toPath());
                        os.write(fileContent);
                    }
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

    public static void initializateListOfFiles(){
      // Mostrar listado de ficheros
      File folder = new File("F:\\DAM\\Segundo\\2DAM\\2DAM_Java\\Servicios\\Sockets\\MiniFileTransfer");
      listOfFiles = folder.listFiles();      

      for(int i = 0; i < listOfFiles.length; i++){          
          if(listOfFiles[i].isFile()){
            files[i] = listOfFiles[i].getName();    
          }
      }  
    }
}