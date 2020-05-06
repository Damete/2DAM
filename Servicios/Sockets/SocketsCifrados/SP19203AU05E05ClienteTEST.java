package Servicios.Sockets.SocketsCifrados;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class SP19203AU05E05ClienteTEST {

    public static byte[] encriptar(byte[] datos, SecretKey key) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encriptado = cipher.doFinal(datos);
        return encriptado;
    }

    
    public static void main(String[] args) {
        SP19203AU05E05ClienteTEST clase = new SP19203AU05E05ClienteTEST();
        try{
            Socket cliente = new Socket();
            InetSocketAddress addr = new InetSocketAddress("localhost", 44014);
            cliente.connect(addr);

            OutputStream os = cliente.getOutputStream();
            
            String mensaje = "Tofol";

            //Generamos la key
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            keygen.init(192);
            SecretKey secretKey = keygen.generateKey();

            //Pasamos la llave a BASE64
            String llaveBase = Base64.getEncoder().encodeToString(secretKey.getEncoded());

            //ENVIAMOS LA LLAVE
            os.write(llaveBase.getBytes());

            //Ciframos con la clave simetrica el mensaje
            byte[] encriptado = encriptar(mensaje.getBytes(), secretKey);

            //Ciframos el mensaje con Base64
            String encoded = Base64.getEncoder().encodeToString(encriptado); 

            //Enviamos el mensaje cifrado
            os.write(encoded.getBytes());   

            cliente.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}