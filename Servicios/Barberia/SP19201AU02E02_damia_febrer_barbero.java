package Servicios.Barberia;

public class SP19201AU02E02_damia_febrer_barbero{
    String nombre;
    int BarberosLibres = 2;

    public synchronized int getbarberosLibres(){
        return BarberosLibres;
    }

    public synchronized void cortarPelo(){
        System.out.println("El barbero está cortando el pelo al cliente");
        BarberosLibres -=1;
        try{
            Thread.sleep((int)(10 * Math.random()*1000));
        }
        catch(Exception e){
            System.out.println("El barbero ha terminado de atender al cliente");
        }
    }

    public synchronized void clienteListo(){
        System.out.println("El barbero ha terminado de atender al cliente");
        BarberosLibres += 1;
    }
}