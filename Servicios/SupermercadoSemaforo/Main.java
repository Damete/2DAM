import java.util.concurrent.Semaphore;

public class Main{
    static final int numeroCajas = 10;
    static Semaphore semaforo = new Semaphore(10);

    static class Supermercado extends Thread{
        String nombreCliente = "";

        public Supermercado(String nombre){
            this.nombreCliente = nombre;
        }

        public void run(){

            try{
                System.out.println(nombreCliente + " Entra en el supermercado");
                semaforo.acquire();                

                //Enviar al cliente a una caja
                aCaja(nombreCliente);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            System.out.println(nombreCliente + " se va del supermercado");
            semaforo.release();
        }

        public void aCaja(String nombre){
            System.out.println(nombre + " esta pagando en caja");
            try{
                sleep((int)(Math.random()*3+2)*1000);
            }
            catch(Exception e){
                e.printStackTrace();
            }            
        }
    }

    public static void main(String[] args) {
            Supermercado f = new Supermercado("Pedro");      
            f.start();
            Supermercado a = new Supermercado("Manolo");
            a.start();
            Supermercado b = new Supermercado("Pepito");
            b.start();
            Supermercado c = new Supermercado("Tofol");
            c.start();
            Supermercado d = new Supermercado("Joan");
            d.start();
            Supermercado e = new Supermercado("Andreu");
            e.start();
            Supermercado g = new Supermercado("Jeroni");
            g.start();
            Supermercado aa = new Supermercado("Maria");
            aa.start();
            Supermercado bb = new Supermercado("Bernat");
            bb.start();
            Supermercado cc = new Supermercado("Clara");
            cc.start();
            Supermercado dd = new Supermercado("Joana");
            dd.start();
            Supermercado aaa = new Supermercado("Biel");
            aaa.start();
    }
}