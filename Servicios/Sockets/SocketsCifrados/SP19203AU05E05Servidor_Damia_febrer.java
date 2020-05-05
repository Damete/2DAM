package Servicios.Sockets.SocketsCifrados;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import sun.misc.BASE64Decoder;

public class SP19203AU05E05Servidor_Damia_febrer {

    public static String cleanString(String dirty){
        String clean = "";

        for(int i = 0; i < dirty.length(); i++){
            if(dirty.charAt(i) != 00){
                clean += dirty.charAt(i);
            }
        }

        return clean;
    }

    public static String decodeMessage(String encoded){
        String decodificado = "";

        try{
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] decoded = decoder.decodeBuffer(encoded);
            decodificado = new String(decoded);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return decodificado;
    }
    public static void main(String[] args) {
        try{
            ServerSocket servidor = new ServerSocket(44014);
            Socket nuevo = servidor.accept();

            InputStream is = nuevo.getInputStream();

            byte[] delCliente = new byte[200];
            is.read(delCliente);
            
            String mensaje = new String(delCliente);
            mensaje = cleanString(mensaje);

            System.out.println("Encoded Message: " + mensaje);

            mensaje = decodeMessage(mensaje);

            System.out.print("Decoded Message: " + mensaje);

            nuevo.close();
            servidor.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}