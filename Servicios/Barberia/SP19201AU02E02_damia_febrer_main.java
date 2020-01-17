package Servicios.Barberia;

public class SP19201AU02E02_damia_febrer_main{
    public static void main(String [] args){
        SP19201AU02E02_damia_febrer_barbero barbero = new SP19201AU02E02_damia_febrer_barbero();
        
        SP19201AU02E02_damia_febrer_salaEspera sala = new SP19201AU02E02_damia_febrer_salaEspera(barbero);
    }
}