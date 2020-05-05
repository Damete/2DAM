package Servicios.Sockets.SocketsCifrados;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class SP19203AU05E05Cliente {
    public static void main(String[] args) {
        try{
            Socket cliente = new Socket();
            InetSocketAddress addr = new InetSocketAddress("localhost", 44014);
            cliente.connect(addr);

            OutputStream os = cliente.getOutputStream();
            
            String test = "Tofol";

            BASE64Encoder encoder = new BASE64Encoder();
            String encoded = encoder.encode(test.getBytes());

            os.write(encoded.getBytes());

            cliente.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}