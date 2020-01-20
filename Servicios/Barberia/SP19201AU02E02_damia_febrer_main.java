package Servicios.Barberia;

public class SP19201AU02E02_damia_febrer_main{
    public static void main(String [] args){
        SP19201AU02E02_damia_febrer_barbero barbero = new SP19201AU02E02_damia_febrer_barbero();
        SP19201AU02E02_damia_febrer_salaEspera sala = new SP19201AU02E02_damia_febrer_salaEspera(barbero);
        
        // SP19201AU02E02_damia_febrer_cliente cliente1 = new SP19201AU02E02_damia_febrer_cliente("Pepe", barbero, sala);
        // SP19201AU02E02_damia_febrer_cliente cliente2 = new SP19201AU02E02_damia_febrer_cliente("Juan", barbero, sala);
        // SP19201AU02E02_damia_febrer_cliente cliente3 = new SP19201AU02E02_damia_febrer_cliente("Tofol", barbero, sala);

        Thread[] clientes = new Thread[10];

        for(int i = 0; i < 10; i++){
            clientes[i] = new Thread(new SP19201AU02E02_damia_febrer_cliente(barbero, sala));
            clientes[i].start();
        }        
    }
}