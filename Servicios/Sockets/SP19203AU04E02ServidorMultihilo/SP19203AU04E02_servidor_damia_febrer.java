package Servicios.Sockets.SP19203AU04E02ServidorMultihilo;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SP19203AU04E02_servidor_damia_febrer implements Runnable{

    Socket csocket;

    SP19203AU04E02_servidor_damia_febrer(Socket csocket){
        this.csocket = csocket;
    }

    public void run(){
        try{            
            InputStream is = csocket.getInputStream();
            OutputStream os = csocket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            ObjectInputStream ois = new ObjectInputStream(is);

            byte[] solicitud = new byte[50];
            is.read(solicitud);
            String orden = new String(solicitud);
            orden = cleanString(orden);

            if(orden.equals("stop")){
                csocket.close();
            }
            else{    
                System.out.println("\nVoy a recibir el objeto");
                SP19203AU04E02_operacio_damia_febrer operacion = (SP19203AU04E02_operacio_damia_febrer) ois.readObject();
        
                System.out.println("\nHe recibido el objeto");
        
                if(operacion.operador.equals("+")){
                    System.out.println("\nEs una suma");
                    int resultado = operacion.operando1 + operacion.operando2;
                    operacion.resultado = resultado;
                    oos.writeObject(operacion);
                    System.out.println("\nHe escrito el resultado en el objeto");
                }
                else if(operacion.operador.equals("-")){
                    System.out.println("\nEs una resta");
                    int resultado = operacion.operando1 - operacion.operando2;
                    operacion.resultado = resultado;
                    oos.writeObject(operacion);
                    System.out.println("\nHe escrito el resultado en el objeto");
                }
                else if(operacion.operador.equals("*")){
                    System.out.println("\nEs una multiplicación");
                    int resultado = operacion.operando1 * operacion.operando2;
                    operacion.resultado = resultado;
                    oos.writeObject(operacion);
                    System.out.println("\nHe escrito el resultado en el objeto");
                }    
            }        
        }
        catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static String cleanString(String dirty){
        String clean = "";

        for(int i = 0; i < dirty.length(); i++){
            if(dirty.charAt(i) != 00){
                clean += dirty.charAt(i);
            }
        }

        return clean;
    }

    public static void main(String[] args) throws Exception{
        ServerSocket servidor = new ServerSocket(44014);

        while(true){
            Socket sock = servidor.accept();
            new Thread(new SP19203AU04E02_servidor_damia_febrer(sock)).start();
        }
    }
}