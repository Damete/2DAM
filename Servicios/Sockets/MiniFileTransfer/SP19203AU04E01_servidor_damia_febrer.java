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
            ServerSocket socket = new ServerSocket(44001);

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
                    String file = "";
                    for(int i = 0; i < listOfFiles.length; i ++){                                               
                        file += i + "- " + listOfFiles[i].getName() + "\n";
                    }
                    os.write(file.getBytes());
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
                    
                    if(checkInteger(id) == true){
                        if(Integer.parseInt(id) >= listOfFiles.length || Integer.parseInt(id) < 0){
                            String error = "-1";
                            byte[] fail = error.getBytes();
                            os.write(fail);
                        }
                        else{
                            File folder = new File(System.getProperty("user.home") + "/Desktop");
                            listOfFiles = folder.listFiles();
    
                            File ficheroSolicitado = listOfFiles[Integer.parseInt(id)];
    
                            byte[] fileContent = Files.readAllBytes(ficheroSolicitado.toPath());
                            os.write(fileContent);
                        }
                    }
                    else{
                        String error = "-1";
                        byte[] fail = error.getBytes();
                        os.write(fail);
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
      File folder = new File(System.getProperty("user.home") + "/Desktop");
      listOfFiles = folder.listFiles(); 
    }

    public static boolean checkInteger(String s){
        try{
            Integer.parseInt(s);
        }
        catch(NumberFormatException e){
            return false;
        }

        return true;
    }
}