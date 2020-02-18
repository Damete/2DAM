import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class SPU03E01e1_2cliente_Damia_Febrer{
    public static void main(String[] args){
        try{
            //Creamos el socket del cliente y le especificamos la dirección a la que va a atacar
            Socket cliente = new Socket();
            InetSocketAddress addr = new InetSocketAddress("localhost", 44014);
            cliente.connect(addr);

            String rutaFichero = "C:\\Users\\dfebrer\\Projects\\2DAM_Java\\Servicios\\Sockets\\EjerciciosComunicacionRed\\E1\\ejemplo.dat";
            
            //Creamos el Stream para enviar los datos al servidor
            OutputStream os = cliente.getOutputStream();

            //Leer el contenido del fichero
            File file = new File(rutaFichero);  //Creamos un objeto del tipo file con la ruta del fichero
            byte[] fileContent = Files.readAllBytes(file.toPath()); //Llenamos una array de bytes con el contenido del fichero mediante el metodo readAllBytes y la ruta del fichero

            //Enviamos el mensaje al servidor
            os.write(fileContent);
            System.out.println("Mensaje enviado al servidor");

            //Cerramos el socket del cliente
            cliente.close();
            
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}