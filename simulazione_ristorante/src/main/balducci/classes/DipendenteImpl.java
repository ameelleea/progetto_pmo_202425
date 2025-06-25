package main.balducci.classes;

import main.balducci.interfaces.*;
import main.palazzetti.interfaces.Ordine;

public abstract class DipendenteImpl implements Dipendente, Runnable {
        
    private int id;
    private String nome;
    private double stipendioOra;
    private Reparto repartoAppartenenza;
    private Ordine ordineCorrente; // L'ordine che sta preparando
    private boolean disponibile;

    DipendenteImpl(int id, String nome, double stipendioOra, Reparto reparto){

    }

    @Override
    public void run(){
        while(true){
            this.lavora();
        }
    }
    
    //@Override
    //public LocalTime getOraInizioTurno() {
    //    
    //    return this.inizioTurno;
    //}
//
    //@Override
    //public LocalTime getOraFineTurno() {
    //    
    //    return this.fineTurno;
    //}

    @Override
    public Double getPaga() {
        
        return null;
    }
}