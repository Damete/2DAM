import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;

public class SPU03E01e2_1servidor_Damia_Febrer{
    public static void main(String[] args) {
        try{
            ServerSocket servidor = new ServerSockeT(44014);            

            while(true){
                Socket escucha = servidor.accept();

                InputStream is = escucha.getInputStream();
                OutputStream os = escucha.getOutputStream();

                byte[] delCliente = new Byte[100];
                is.read(delCliente);
                String pregunta = new String(delCliente);

                for (int i = 0; i<pregunta.length(); i++){
                    if(pregunta.charAt(i) == 63){
                        if(pregunta.equals("Com et dius?")){
                            String respuesta = "Em dic Exercici 2";
                            byte [] contestacion = respuesta.readAllBytes();
                            os.write(contestacion);
                        }
                        else if(pregunta.equals("Quantes línies de codi tens?")){
                            String respuesta = "40";
                            byte [] contestacion = respuesta.readAllBytes();
                            os.write(contestacion);
                        }
                        else{
                            String respuesta = "No he entès la pregunta";
                            byte [] contestacion = respuesta.readAllBytes();
                            os.write(contestacion);
                        }
                    }
                }
            }

            escucha.close();
            servidor.close();


        } catch(IOException e){
            e.printStackTrace();
        }
    }
}