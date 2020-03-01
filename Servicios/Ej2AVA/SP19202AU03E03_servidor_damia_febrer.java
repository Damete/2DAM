package Servicios.Ej2AVA;

import java.io.*;
import java.net.*;

public class SP19202AU03E03_servidor_damia_febrer{
    public static void main(String[] args) {
        try{
            ServerSocket servidor = new ServerSocket(44014);
            Zona platea = new Zona(1000);
            Zona anfiteatro = new Zona(200);
            Zona VIP = new Zona(25);


            while(true){
                Socket nuevo = servidor.accept();

                InputStream is = nuevo.getInputStream();
                OutputStream os = nuevo.getOutputStream();

                byte [] delCliente = new byte[1];
                is.read(delCliente);
                String opcion = new String(delCliente);

                if(opcion.contains("1")){
                    String preguntaZona = "¿Para que zona desea ver la cantidad restante de entradas?, Platea, Anfiteatro o VIP?";
                    byte [] preguntarZona = preguntaZona.getBytes();
                    os.write(preguntarZona);

                    byte [] zona = new byte [10];
                    is.read(zona);
                    String zonaElegida = new String(zona);

                    if(zonaElegida.contains("Platea") || zonaElegida.contains("platea")){
                        int entradas = platea.getEntradasParaVender();
                        String resultado = String.valueOf(entradas);
                        os.write(resultado.getBytes());
                    }
                    else if(zonaElegida.contains("Anfiteatro") || zonaElegida.contains("anfiteatro")){
                        int entradas = anfiteatro.getEntradasParaVender();
                        String resultado = String.valueOf(entradas);
                        os.write(resultado.getBytes());
                    }
                    else if(zonaElegida.contains("VIP") || zonaElegida.contains("vip")){
                        int entradas = VIP.getEntradasParaVender();
                        String resultado = String.valueOf(entradas);
                        os.write(resultado.getBytes());
                    }
                }
                else if(opcion.contains("2")){
                    String preguntaZona = "¿Para que zona desea comprar las entradas, Platea, Anfiteatro o VIP?";
                    byte [] preguntarZona = preguntaZona.getBytes();
                    os.write(preguntarZona);

                    byte [] zona = new byte [10];
                    is.read(zona);
                    String zonaElegida = new String(zona);

                    String preguntaCantidad = "¿Quantas entradas desea comprar?";
                    byte [] preguntarCantidad = preguntaCantidad.getBytes();
                    os.write(preguntarCantidad);

                    byte [] cantidad = new byte[10];
                    is.read(cantidad);
                    String help = new String(cantidad);
                    int cantidadEspecificada = formatearNumero(help);

                    if(zonaElegida.contains("Platea") || zonaElegida.contains("platea")){
                        String resultado = platea.vender(cantidadEspecificada);
                        os.write(resultado.getBytes());
                    }
                    else if(zonaElegida.contains("Anfiteatro") || zonaElegida.contains("anfiteatro")){
                        String resultado = anfiteatro.vender(cantidadEspecificada);
                        os.write(resultado.getBytes());
                    }
                    else if(zonaElegida.contains("VIP") || zonaElegida.contains("vip")){
                        String resultado = VIP.vender(cantidadEspecificada);
                        os.write(resultado.getBytes());
                    }
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static int formatearNumero(String numero){
        String formateado = "";

        for(int i = 0; i < numero.length(); i++){
            if(numero.charAt(i) != 00){
                formateado += numero.charAt(i);
            }
        }

        int listo = Integer.parseInt(formateado);

        return listo;
    }

    public static class Zona{
        private int entradasParaVender;
        
        public Zona(int n){
            entradasParaVender = n;
        }

        public int getEntradasParaVender(){
            return entradasParaVender;
        }

        public String vender(int n){
            String respuesta ="";

            if(this.entradasParaVender == 0){
                respuesta = ("Lo sentimos, las entradas para la zona que ha elegido están agotadas");
            } else if(this.entradasParaVender < n){
                respuesta = ("Solo quedan " + entradasParaVender + " entradas disponibles para la zona que ha seleccionado");
            }
            if(this.entradasParaVender >= n){
                entradasParaVender -= n;
                respuesta = ("Aquí tiene sus " + n + " entradas, gracias por su compra");
            }

            return respuesta;
        }
    }
}