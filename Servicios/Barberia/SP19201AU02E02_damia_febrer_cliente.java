package Servicios.Barberia;

public class SP19201AU02E02_damia_febrer_cliente implements Runnable{
    SP19201AU02E02_damia_febrer_barbero barbero;
    SP19201AU02E02_damia_febrer_salaEspera sala;

    public SP19201AU02E02_damia_febrer_cliente(SP19201AU02E02_damia_febrer_barbero barbero, SP19201AU02E02_damia_febrer_salaEspera sala){
        this.barbero = barbero;
        this.sala = sala;
    }

    @Override
    public void run() {
        sala.entrarBarberia();
    }
}