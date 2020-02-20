import java.io.*;
import java.net.*;

public class SPU03E01e6_1cliente_Damia_Febrer{
    public static void main(String[] args) {
        int [] puertos = new int [Integer.parseInt(args[1])];
        int puerto = 44014;

        for(int i = 0; i < puertos.length; i++){
            puertos[i] = puerto;
            puerto ++;
        }

        try{
            Socket cliente = new Socket();
            InetSocketAddress addr;

            if(Integer.parseInt(args[1]) != puertos.length){
                addr = new InetSocketAddress("localhost", puertos[Integer.parseInt(args[1])] + 1);
            } else{
                addr = new InetSocketAddress("localhost", puertos[0]);
            }            

            cliente.connect(addr);

            InputStream is = cliente.getInputStream();
            OutputStream os = cliente.getOutputStream();

            if(Integer.parseInt(args[1]) == 1){
                String mensaje = "token";
                byte [] enviar = mensaje.getBytes();
                os.write(enviar);
            } else{
              byte [] delAnterior = new byte[20];
              is.read(delAnterior);
              String recibido = new String(delAnterior);

              if(recibido.contains("token")){
                  os.write(delAnterior);
              }
              else{
                  cliente.close();
              }
            }

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}