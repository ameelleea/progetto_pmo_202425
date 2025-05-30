package main.balducci.interfaces;

import java.util.List;

public interface Cassa {

    //+calcolaConto(Tavolo): Double
    public double calcolaConto(Object t);

    //+calcolaTotaleCameriere(Dipendente): Double
    public double calcolaTotaleCameriere(Dipendente c);

    //+apriTavolo(Tavolo): void
    public void apriTavolo(Object t);

    //+chiudiTavolo(Tavolo): void
    public void chiudiTavolo(Object t);

    //+getTavoliLiberi(): List<Tavolo>
    public List<Object> getTavoliLiberi();

    //+calcolaTotaleGiornata(): Double
    public double calcolaTotaleGiornata();

    //+smistaOrdine(Ordine): void
    public void smistaOrdine(Ordine o);
}