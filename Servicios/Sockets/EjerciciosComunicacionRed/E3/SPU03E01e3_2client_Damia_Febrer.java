import java.net.*;
import java.io.*;

public class SPU03E01e3_2client_Damia_Febrer{
    public static void main(String[] args) throws Exception{
        DatagramSocket ds = new DatagramSocket();  
        String enviar = "Pepe";

        while(true){              
            InetAddress addr = InetAddress.getByName("localhost");  
            
            DatagramPacket dp = new DatagramPacket(enviar.getBytes(), enviar.length(), addr, 44014);  
            ds.send(dp);  
            System.out.println("Mensaje enviado");

            byte[] recibido = new byte[1024];  
            DatagramPacket dpr = new DatagramPacket(recibido, 1024);  
            ds.receive(dp);  
            String delServer = new String(dp.getData(), 0, dp.getLength());  
            System.out.println(delServer);
        }        
    }
}