import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SPU03E01e2_2cliente_Damia_Febrer{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try{
            Socket cliente = new Socket();
            InetSocketAddress address = new InetSocketAddress("localhost", 44014);
            cliente.connect(address);

            InputStream is = cliente.getInputStream();
            OutputStream os = cliente.getOutputStream();

            System.out.println("Escriba una pregunta para el servidor");
            String pregunta = sc.nextLine();

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}