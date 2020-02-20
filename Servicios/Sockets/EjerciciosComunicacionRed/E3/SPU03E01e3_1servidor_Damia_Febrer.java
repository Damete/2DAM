import java.net.*;
import java.io.*;

public class SPU03E01e3_1servidor_Damia_Febrer{
    public static void main(String[] args) throws Exception{
        //Creamos el socket
        DatagramSocket datagramSocket = new DatagramSocket(44014);

        //Hacemos el bucle para que el servidor se quede escuchando las conexiones
        byte[] buf = new byte[1024];  
        DatagramPacket dp = new DatagramPacket(buf, 1024);  
        datagramSocket.receive(dp);
        String str = new String(dp.getData(), 0, dp.getLength());  
        if(str.equals("Uep")){
            String respuesta = "Què tal?";
            InetAddress addr = InetAddress.getByName("localhost");
            System.out.println("Respuesta enviada");
            DatagramPacket dpe = new DatagramPacket(respuesta.getBytes(), respuesta.length(), addr, dp.getPort()); 
            datagramSocket.send(dpe);
        }
        else{
            String respuesta = "Lo que me has enviado no me gusta";
            InetAddress addr = InetAddress.getByName("localhost");
            DatagramPacket dpe = new DatagramPacket(respuesta.getBytes(), respuesta.length(), addr, dp.getPort());
            System.out.println("Respuesta enviada");
            //IMPORTANTE !! el socket envia el DatagramPacket
            datagramSocket.send(dpe);
        }
    }
}