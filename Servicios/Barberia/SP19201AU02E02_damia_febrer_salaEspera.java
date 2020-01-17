package Servicios.Barberia;

public class SP19201AU02E02_damia_febrer_salaEspera implements Runnable{
    int sillasLibres = 5;
    SP19201AU02E02_damia_febrer_barbero barbero;

    public SP19201AU02E02_damia_febrer_salaEspera(SP19201AU02E02_damia_febrer_barbero barbero){
        this.barbero = barbero;
    }

    synchronized int getSitiosLibres(){
        return sillasLibres;
    }

    @Override
    public void run() {
        int barberosLibres = barbero.getbarberosLibres();
        if(barberosLibres > 0){
            //Si hay barberos libres se sientan a cortarse el pelo
        }
        else{
            //Si no los hay se sientan en una silla, si no hay sillas libres se van de la barberia
            int libres = getSitiosLibres();
            if(libres > 0){
                System.out.println("El cliente se sienta en una silla a esperar su turno");
                try{

                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("Todos los sitios estaban ocupados así que el cliente se va de la barberia");
            }
        }
    }
}