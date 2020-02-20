import java.net.*;
import java.io.*;

public class SPU03E01e4_1servidor_Damia_Febrer{
    public static void main(String[] args) throws Exception{
        //Creamos el socket
        DatagramSocket datagramSocket = new DatagramSocket(44014);

        //Hacemos el bucle para que el servidor se quede escuchando las conexiones
        byte[] buf = new byte[1024];  
        DatagramPacket dp = new DatagramPacket(buf, 1024);  
        datagramSocket.receive(dp);
        String str = new String(dp.getData(), 0, dp.getLength()); 
        System.out.println("Mensaje recibido: " + str); 

        if(str.equals("token")){
            String respuesta = "rebut";
            InetAddress addr = InetAddress.getByName("localhost");
            System.out.println("Respuesta enviada");
            DatagramPacket dpe = new DatagramPacket(respuesta.getBytes(), respuesta.length(), addr, dp.getPort()); 
            datagramSocket.send(dpe);
        }

        datagramSocket.close();
    }
}