package main.balducci.interfaces;

import java.util.Collection;

import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Tavolo;

public interface Cassa {

    //+calcolaConto(Tavolo): Double
    public double calcolaConto(Tavolo t);

    //+calcolaTotaleCameriere(Dipendente): Double
    public double calcolaTotaleCameriere(Dipendente c);

    //+apriTavolo(Tavolo): void
    public void apriTavolo(Tavolo t);

    //+chiudiTavolo(Tavolo): void
    public void chiudiTavolo(Tavolo t);

    //+getTavoliLiberi(): List<Tavolo>
    public Collection<Tavolo> getTavoliLiberi();

    //+calcolaTotaleGiornata(): Double
    public double calcolaTotaleGiornata();

    //+smistaOrdine(Ordine): void
    public void smistaOrdine(Ordine o);

    public Menu getMenu(String tipo);

}