package Servicios.Sockets.SP19203AU04E02ServidorMultihilo;

import java.io.Serializable;

public class SP19203AU04E02_operacio_damia_febrer implements Serializable{
    int operando1;
    String operador;
    int operando2;
    int resultado;

    public SP19203AU04E02_operacio_damia_febrer(int operando1, int operando2, String operador){
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operador = operador;
    }
}