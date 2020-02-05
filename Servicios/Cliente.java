import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.*;
import hava.net.*;

public class Cliente{
    public static void main(String[] args) {
        try{
            Socket cliente = new Socket();
            InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
            cliente.connect(addr);
            InputStream is = cliente.getInputStream();
            String msg = "mensaje del cliente";
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}