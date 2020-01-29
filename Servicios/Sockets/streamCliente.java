import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class streamCliente{
    public static void main(String[] args) {
        try{
            Socket clientSocket = new Socket();
            InetSocketAddress address = new InetSocketAddress("localhost", 5555);
            clientSocket.connect(address);
            InputStream is = clientSocket.getInputStream();
            OutputStream os = clientSocket.getOutputStream();
            String missatge = "missatge des del client";
            os.write(missatge.getBytes());
            clientSocket.close();
        }   
        catch(IOException e){
            e.printStackTrace();
        } 
    }
}