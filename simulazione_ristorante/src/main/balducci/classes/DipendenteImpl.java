package main.balducci.classes;

import main.balducci.interfaces.*;

public abstract class DipendenteImpl implements Dipendente {
        
    private String id;
    private double stipendioOra;


    DipendenteImpl(String id, double stipendioOra){
        this.id = id;
        this.stipendioOra = stipendioOra;
    }
    @Override
    public String getIdDipendente(){
        return this.id;
    }

    @Override
    public Double getPaga(){
        return this.stipendioOra;
    }
}