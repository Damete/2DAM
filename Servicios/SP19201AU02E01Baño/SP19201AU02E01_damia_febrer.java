package Servicios.SP19201AU02E01Baño;

public class SP19201AU02E01_damia_febrer{

    static class Baño{

        public boolean ocupado = false;

        public synchronized boolean getEstadoBaño(){
            return ocupado;
        }

        public synchronized void irAlBaño(String nombre){
            if(ocupado){
                try{                    
                    Thread.sleep((int)(10 * Math.random()*1000));
                    ocupado = false;
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            System.out.println(nombre + " ha salido del baño");
            notifyAll();
        }

    }

    static class Persona implements Runnable{
        String nombre;
        Baño baño;

        public Persona(String nombre, Baño baño){
            this.nombre = nombre;
            this.baño = baño;
        }

        public void run(){
            boolean iterate = true;

            do{
                boolean ocupado = baño.getEstadoBaño();
                if(!ocupado){
                    System.out.println(nombre + " ha entrado en el baño");
                    baño.irAlBaño(this.nombre);                    
                }
                else{
                    try{
                        this.wait();
                        boolean estado = baño.getEstadoBaño();
                        if(!estado){
                            iterate = false;
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            while(!iterate);
        }

    }
    public static void main(String args[]){
        Baño baño = new Baño();
        Persona p1 = new Persona("Tofol", baño);
        Persona p2 = new Persona("Biel", baño);
        Persona p3  = new Persona("Pep", baño);
        p1.run();
        p2.run();
        p3.run();
    }
}