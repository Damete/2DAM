package Servicios.Sockets;

import java.io.*;
import java.net.*;


public class BufferedStreamCliente{
    public static void main(String[] args) {
        try{
            Socket socketCliente = new Socket();
            InetSocketAddress direccion = new InetSocketAddress("localhost", 5555);
            socketCliente.connect(direccion);
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            DataOutputStream outToServer = new DataOutputStream(socketCliente.getOutputStream());
            String mensaje = "Mensaje del clinete";
            outToServer.writeBytes(mensaje);
            socketCliente.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}