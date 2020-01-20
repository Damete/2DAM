package Servicios.Barberia;

public class SP19201AU02E02_damia_febrer_salaEspera{
    int sillasLibres = 5;
    SP19201AU02E02_damia_febrer_barbero barbero;

    public SP19201AU02E02_damia_febrer_salaEspera(SP19201AU02E02_damia_febrer_barbero barbero){
        this.barbero = barbero;
    }

    synchronized int getSitiosLibres(){
        return sillasLibres;
    }

    synchronized void entrarBarberia(){
        int barberosLibres = barbero.getbarberosLibres();
        boolean barberosOcupados = true;

        if(barberosLibres > 0){
            barbero.cortarPelo();
            barbero.clienteListo();           
        }
        else{
            int libres = getSitiosLibres();
            if(libres > 0){
                System.out.println("El cliente se sienta en una silla a esperar su turno");
                sillasLibres -=1;
                try{
                    this.wait();
                    do{
                        int barberos = barbero.getbarberosLibres();
                        if(barberos > 0){
                            barberosOcupados = false;
                        }
                    }
                    while(barberosOcupados);

                    if(!barberosOcupados){
                        notifyAll();
                        sillasLibres += 1;
                    }
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