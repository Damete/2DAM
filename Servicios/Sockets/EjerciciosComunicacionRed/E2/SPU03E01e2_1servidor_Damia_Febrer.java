package Servicios.Sockets.EjerciciosComunicacionRed.E2;

import java.io.*;
import java.net.*;

public class SPU03E01e2_1servidor_Damia_Febrer{
    public static void main(String[] args) {
        try{
            ServerSocket servidor = new ServerSocket(44014);            

            while(true){
                Socket nuevo = servidor.accept();

                InputStream is = nuevo.getInputStream();
                OutputStream os = nuevo.getOutputStream();

                byte[] delCliente = new byte[100];
                is.read(delCliente);
                String pregunta = new String(delCliente);
                
                if(pregunta.contains("?")){
                    if(pregunta.contains("Com et dius?")){
                        String respuesta = "Em dic Exercici 2";
                        byte [] contestacion = respuesta.getBytes();
                        os.write(contestacion);
                    }
                    else if(pregunta.contains("Quantes linies de codi tens?")){
                        String respuesta = "Tenc 49 línies de codi";
                        byte [] contestacion = respuesta.getBytes();
                        os.write(contestacion);
                    }
                    else{
                        String respuesta = "No he entès la pregunta";
                        byte [] contestacion = respuesta.getBytes();
                        os.write(contestacion);
                    }
                }
                else{
                    String respuesta = "No he entès la pregunta";
                    byte [] contestacion = respuesta.getBytes();
                    os.write(contestacion);
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}