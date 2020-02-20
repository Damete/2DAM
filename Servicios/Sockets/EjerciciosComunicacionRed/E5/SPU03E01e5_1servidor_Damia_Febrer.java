import java.net.*;
import java.io.*;

public class SPU03E01e5_1servidor_Damia_Febrer{
    public static void main(String[] args) {
        try{
            ServerSocket servidor = new ServerSocket(44014);

            int conexiones = 0;

            while(true){
                Socket segundo = servidor.accept();

                OutputStream os = segundo.getOutputStream();

                conexiones ++;

                System.out.println("He recibido la conexion " + conexiones);

                String respuesta = String.valueOf(conexiones);
                byte [] enviar = respuesta.getBytes();
                os.write(enviar); 
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}