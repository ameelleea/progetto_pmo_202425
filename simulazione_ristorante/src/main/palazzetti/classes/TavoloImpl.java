package main.palazzetti.implementation;

import main.balducci.interfaces.GruppoClienti;
import main.palazzetti.interfaces.Rango;
import main.palazzetti.interfaces.Tavolo;

public class TavoloImpl implements Tavolo {
    private int numero;
    private int numPosti;
    private boolean occupato;
    private GruppoClienti gruppo;
    private Rango rango;

    public TavoloImpl(int numero, int numPosti, Rango rango) {
        this.numero = numero;
        this.numPosti = numPosti;
        this.rango = rango;
        this.occupato = false;
        this.gruppo = null;
    }

    @Override
    public int getNumPosti() {
        return numPosti;
    }

    @Override
    public Boolean isOccupato() {
        return occupato;
    }

    @Override
    public Rango getRango() {
        return rango;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public void occupa(GruppoClienti g) {
        this.gruppo = g;
        this.occupato = true;
    }

    @Override
    public void libera() {
        this.gruppo = null;
        this.occupato = false;
    }
}
