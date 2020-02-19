import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class SPU03E01e2_2cliente_Damia_Febrer{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try{
            Socket cliente = new Socket();
            InetSocketAddress address = new InetSocketAddress("localhost", 44014);
            cliente.connect(address);

            InputStream is = cliente.getInputStream();
            OutputStream os = cliente.getOutputStream();

            boolean iterate = true;

            while(iterate){
                System.out.println("Escriba una pregunta para el servidor");
                String pregunta = sc.nextLine();
                byte [] paraServer = pregunta.getBytes();
                os.write(paraServer);                

                byte [] respuestaServidor = new byte [100];
                is.read(respuestaServidor);
                String respuesta = new String(respuestaServidor);

                System.out.println(respuesta);

                System.out.println("Desea preguntarle algo más al servidor ? S/N");
                String continuar = sc.nextLine();
                if(continuar.equals("N")){
                    iterate = false;
                }
            }
            
            cliente.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}