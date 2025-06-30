package main.balducci.classes;

import main.balducci.interfaces.*;

public abstract class DipendenteImpl implements Dipendente {
        
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
}