import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SPU03E01e1_1servidor_Damia_Febrer{
    public static void main(String[] args){
        try{
        //Creamos el socket del servidor
        //InetSocketAddress addr = new InetSocketAddress(44014);
        ServerSocket servidor = new ServerSocket(44014);        
        Socket nuevo = servidor.accept();

        //Creamos el stream para recibir lo que nos viene del cliente
        InputStream is = nuevo.getInputStream();

        //Creamos el array de bytes para almacenar el mensaje del cliente
        byte[] delCliente = new byte[100];
        is.read(delCliente);

        String mensajeDelCliente = new String(delCliente);
        System.out.println(mensajeDelCliente);

        //Cerramos los sockets
        nuevo.close();
        servidor.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}