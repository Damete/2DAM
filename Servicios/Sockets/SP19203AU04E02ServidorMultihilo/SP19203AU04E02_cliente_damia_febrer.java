package Servicios.Sockets.SP19203AU04E02ServidorMultihilo;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SP19203AU04E02_cliente_damia_febrer{

    public static boolean comprobarOperador(String operador){
        boolean valido = false;

        if(operador.equals("+") || operador.equals("-") || operador.equals("*")){
            valido = true;
        }
        else{
            return false;
        }
        
        return valido;
    }
    public static void main(String[] args) {

        int PrimerOperando = 0;
        String operador = "";
        int SegundoOperando = 0;

        boolean iterate = true;
        Scanner menu = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        Scanner op = new Scanner(System.in);

        while(iterate){
            try{
                Socket cliente = new Socket();
                InetSocketAddress addr = new InetSocketAddress("localhost", 44014);
                cliente.connect(addr);

                InputStream is = cliente.getInputStream();
                OutputStream os = cliente.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                ObjectInputStream ois = new ObjectInputStream(is);

                System.out.println("Calculadora simple powered by Damo \n 1- Realizar operación \n 2- Salir");
                String respuesta = menu.nextLine();

                if(respuesta.equals("1")){
                    String msg = "go";
                    byte[] orden = msg.getBytes();
                    os.write(orden);

                    boolean comprobarPrimero = false;
                    boolean comprobarOperador = false;
                    boolean comprobarSegundo = false;
                    
                    do{
                        try{
                            System.out.println("\nIntroduzca el primer operando");
                            PrimerOperando = sc.nextInt();
                            comprobarPrimero = false;
                        }
                        catch(InputMismatchException ime){
                            System.out.println("\nEl valor introducido es incorrecto, por favor vuelva a introducirlo");
                            comprobarPrimero = true;
                            sc.next();
                        }
                    }
                    while(comprobarPrimero);

                    do{
                        try{
                            System.out.println("\nIntroduzca el operador");
                            operador = op.nextLine();

                            boolean checkeado = comprobarOperador(operador);
                            if(checkeado){
                                comprobarOperador = false;
                            }
                            else{
                                System.out.println("El operador introducido no es valido, por favor vuelva a introducirlo");
                                comprobarOperador = true;
                            }
                        }
                        catch(InputMismatchException ime){
                            System.out.println("\nEl valor introducido es incorrecto, por favor vuelva a introducirlo");
                            comprobarOperador = true;
                            op.next();
                        }
                    }
                    while(comprobarOperador);

                    do{
                        try{
                            System.out.println("\nIntroduzca el segundo operando");
                            SegundoOperando = sc.nextInt();
                            comprobarSegundo = false;
                        }
                        catch(InputMismatchException ime){
                            System.out.println("\nEl valor introducido es incorrecto, por favor vuelva a introducirlo");
                            comprobarSegundo = true;
                            sc.next();
                        }
                    }
                    while(comprobarSegundo);

                    System.out.println("\nVoy a enviar el objeto");

                    SP19203AU04E02_operacio_damia_febrer operacion = new SP19203AU04E02_operacio_damia_febrer(PrimerOperando, SegundoOperando, operador);

                    oos.writeObject(operacion);

                    System.out.println("\nHe enviado el objeto, ahora voy a recibir el resultado");

                    SP19203AU04E02_operacio_damia_febrer pepe = (SP19203AU04E02_operacio_damia_febrer) ois.readObject();

                    System.out.println("\nEl resultado de " + PrimerOperando + operador + SegundoOperando + " es " + pepe.resultado + "\n");
                }
                else{
                    menu.close();
                    sc.close();
                    op.close();

                    //Le decimos al server que no queremos seguir enviandole información y que cierre la conexión
                    String msg = "stop";
                    byte[] orden = msg.getBytes();
                    os.write(orden);

                    iterate = false;
                }

            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}