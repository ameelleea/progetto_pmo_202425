package main.palazzetti.classes;

import main.balducci.interfaces.GruppoClienti;
import main.palazzetti.interfaces.Tavolo;

public class TavoloImpl implements Tavolo {
    private final int numero;
    private final int numeroPosti;
    private GruppoClienti gruppoCorrente;
    private StatoTavolo stato;

    public TavoloImpl(int numero, int numeroPosti) {
        this.numero = numero;
        this.numeroPosti = numeroPosti;
        this.stato = StatoTavolo.LIBERO;
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
        return this.stato != StatoTavolo.LIBERO;
    }

    @Override
    public void occupa(GruppoClienti gruppo) {
        this.gruppoCorrente = gruppo;
        this.stato = StatoTavolo.NON_ORDINATO;
    }

    @Override
    public void libera() {
        this.gruppoCorrente = null;
        this.stato = StatoTavolo.LIBERO;
    }

    @Override
    public GruppoClienti getGruppoCorrente() {
        return this.gruppoCorrente;
    }

    @Override
    public void setStatoTavolo(StatoTavolo stato) {
        this.stato = stato;
    }

    @Override
    public StatoTavolo getStatoTavolo() {
        return this.stato;
    }
}
