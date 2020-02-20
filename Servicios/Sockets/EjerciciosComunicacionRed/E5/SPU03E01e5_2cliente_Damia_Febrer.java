import java.net.*;
import java.io.*;

public class SPU03E01e5_2cliente_Damia_Febrer{
    public static void main(String[] args) {
        try{
            Socket cliente = new Socket();
            InetSocketAddress address = new InetSocketAddress("localhost", 44014);
            cliente.connect(address);
    
            System.out.println("Conectado al servidor");
    
            InputStream is = cliente.getInputStream();
    
            byte [] delServer = new byte[20];
            is.read(delServer);
            String cadena = new String(delServer);
    
            System.out.println("He sido la conexion " + cadena);
    
            cliente.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}