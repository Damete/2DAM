import java.net.*;
import java.io.*;

public class SPU03E01e4_2cliente_Damia_Febrer{
    public static void main(String[] args) throws Exception{
        boolean correcto = false;
        DatagramSocket ds = new DatagramSocket(5555);  
        String enviar = "token";
 
        InetAddress addr = InetAddress.getByName("localhost");  
            
        DatagramPacket dp = new DatagramPacket(enviar.getBytes(), enviar.length(), addr, 44014);  
        ds.send(dp);  
        System.out.println("Mensaje enviado: " + enviar);

        do{
            byte[] recibido = new byte[1024];  
            DatagramPacket dpr = new DatagramPacket(recibido, 1024);  
            ds.receive(dpr);  
            String delServer = new String(dpr.getData(), 0, dpr.getLength());  
            if(delServer.equals("rebut")){
                correcto = true;
                ds.close();
            }
            System.out.println(delServer);
        }
        while(!correcto);
    }
}