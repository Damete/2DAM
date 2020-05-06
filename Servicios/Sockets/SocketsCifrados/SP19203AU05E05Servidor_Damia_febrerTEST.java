package Servicios.Sockets.SocketsCifrados;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;

public class SP19203AU05E05Servidor_Damia_febrerTEST {
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

    public static byte[] descifrarMensaje(byte[] encriptado, SecretKey key) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decripted = cipher.doFinal(encriptado);
        return decripted;
    }
    public static void main(String[] args) {
        try{
            ServerSocket servidor = new ServerSocket(44014);
            Socket nuevo = servidor.accept();

            InputStream is = nuevo.getInputStream();

            //Recibimos la llave
            byte[] clave = new byte[32];
            is.read(clave);

            //Quitamos el Base64 a la llave recibida
            byte[] decoded = Base64.getDecoder().decode(clave);

            //Reconstruimos la llave mediante el texto decodificado
            SecretKey sk = new SecretKeySpec(decoded, "AES");

            //Recibimos el mensaje del cliente
            byte[] delCliente = new byte[200];
            is.read(delCliente);
            String mensaje = new String(delCliente);
            mensaje = cleanString(mensaje);

            //Quitamos el Basa64 del mensaje que nos envia el cliente
            byte[] sinBase = Base64.getDecoder().decode(mensaje);

            System.out.println("Ciphered Message " + new String(sinBase));

            byte[] decripted = descifrarMensaje(sinBase, sk);
            String descifrado = new String(decripted);

            System.out.println("Decripted Message " + descifrado);

            nuevo.close();
            servidor.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}