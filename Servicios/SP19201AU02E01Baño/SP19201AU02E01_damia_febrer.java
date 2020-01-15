package Servicios.SP19201AU02E01Baño;

public class SP19201AU02E01_damia_febrer{

    class Baño{

        public boolean ocupado = false;

        public synchronized boolean getEstadoBaño(){
            return ocupado;
        }

        public synchronized void irAlBaño(){
            if(ocupado){
                try{                    
                    Thread.sleep((int)(10 * Math.random()*1000));
                    ocupado = false;
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            else{
                notifyAll();
            }
        }

    }

    class Persona implements Runnable{
        String nombre;
        Baño baño;

        public Persona(String nombre, Baño baño){
            this.nombre = nombre;
            this.baño = baño;
        }

        public void run(){
            boolean ocupado = baño.getEstadoBaño();
            do{
                if(!ocupado){
                    System.out.println(nombre + " he entrado en el baño");
                    baño.irAlBaño();                    
                }
                else{
                    try{
                        this.wait();
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            while(!ocupado);            
        }

    }
    public static void main(String args[]){
        Baño baño = new Baño();
        Persona pe = new Persona("Tofol", baño);
        pe.run();
        
    }
}