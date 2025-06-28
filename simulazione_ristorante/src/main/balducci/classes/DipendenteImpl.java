package main.balducci.classes;

import main.balducci.interfaces.*;

public abstract class DipendenteImpl extends Thread implements Dipendente {
        
    private int idDipendente;
    private double stipendioOra;


    DipendenteImpl(int id, double stipendioOra){
        this.idDipendente = id;
        this.stipendioOra = stipendioOra;
    }
    @Override
    public int getIdDipendente(){
        return this.idDipendente;
    }

    @Override
    public Double getPaga(){
        return this.stipendioOra;
    }

    @Override
    public void start(){
        super.start();
    }

    @Override
    public void interrupt(){
        super.interrupt();
    }
}