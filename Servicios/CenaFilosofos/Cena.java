package Servicios.CenaFilosofos;

public class Cena{  
    Tenedor tenedores[];  
    int parametro = 5;         //numero de filosofos y tenedores  
   public static void main(String args[]){  
    System.out.println("Cena de los Filosofos");  
    Cena cena = new Cena();  
    for(int i=0; i<5; i++){  
     Filosofo pensante = new Filosofo(i, cena);  
    }  
   }  
   public Cena(){  
    tenedores = new Tenedor[parametro];  
    for(int i=0; i<5; i++){  
     tenedores[i] = new Tenedor(i);  
    }  
   }  
   public Tenedor getTenedor(int x){  
    return tenedores[x];  
    }  
   public int getTenedorDer(int x){  
    return (x+1)%parametro;  
    }  
   public int getTenedorIzq(int x){  
    return x;  
    }  
}  