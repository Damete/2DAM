package Servicios.Hilos;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SP19202AU02Repas_damia_febrer {

    static int introducido;

    public static synchronized void multiplicar(int factor){
        for(int i = 0; i <= 10; i++){
            int resultado = factor * i;
            System.out.println(factor + " por " + i + " es " + resultado);
        }
    }

    static class MiThread extends Thread{
        int factor = 0;
        int id;
        Controlador controlador;

        MiThread(int id, int factor, Controlador controlador){
            this.id = id;
            this.factor = factor;
            this.controlador = controlador;
        }

        public void run(){ 
            System.out.println("[Información] El thread con id " + id + " se ha iniciado" );
            boolean eEntrao = false;

            do{
                eEntrao = controlador.accion(id, factor);
                if(eEntrao = false){
                    try{
                        wait();
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
            while(!eEntrao);

            System.out.println("[Información] La ejecución del thread con id " + id + " ha finalizado" );
        }
    }

    static class Controlador{
        int siguiente = 0;

        public synchronized boolean accion(int id, int multiplicar){
            if(siguiente == id){
                System.out.println("\nHa entrado " + id + "\n");
                multiplicar(multiplicar);
                siguiente ++;                
                notifyAll();

                return true;
            }

            return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean comprobar = false;

        do{
            try{
                System.out.println("Introduzca un número entre 1 i 10");
                introducido = sc.nextInt();
                comprobar = false;
                sc.close();

                Controlador cont = new Controlador();
                
                for(int i = 0; i <= 3; i++){
                    MiThread hilo =  new MiThread(i, introducido, cont);
                    hilo.start();
                    introducido ++;
                }
            }
            catch(InputMismatchException ime){
                comprobar = true;
                sc.next();
            }
        }
        while(comprobar);       
    }
}