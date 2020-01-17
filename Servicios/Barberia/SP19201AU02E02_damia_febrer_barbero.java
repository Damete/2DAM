package Servicios.Barberia;

public class SP19201AU02E02_damia_febrer_barbero{
    String nombre;
    int BarberosLibres = 2;

    public synchronized int getbarberosLibres(){
        return BarberosLibres;
    }
}