package main.palazzetti.classes;

import main.balducci.interfaces.GruppoClienti;
import main.palazzetti.interfaces.Tavolo;

public class TavoloImpl implements Tavolo {
    private final int numero;
    private final int numeroPosti;
    private boolean occupato;
    private GruppoClienti gruppoCorrente;

    public TavoloImpl(int numero, int numeroPosti) {
        this.numero = numero;
        this.numeroPosti = numeroPosti;
        this.occupato = false;
    }

    @Override
    public int getNumero() {
        return this.numero;
    }

    @Override
    public int getNumeroPosti() {
        return this.numeroPosti;
    }

    @Override
    public boolean isOccupato() {
        return this.occupato;
    }

    @Override
    public void occupa(GruppoClienti gruppo) {
        this.gruppoCorrente = gruppo;
        this.occupato = true;
    }

    @Override
    public void libera() {
        this.gruppoCorrente = null;
        this.occupato = false;
    }

    @Override
    public GruppoClienti getGruppoCorrente() {
        return this.gruppoCorrente;
    }
}
