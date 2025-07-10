package main.balducci.interfaces;

import java.util.List;
import java.util.Map;

import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Tavolo;
import main.balducci.classes.TipoReparto;

public interface Cassa {

    public double calcolaConto(Tavolo t);

    public void calcolaTotalePerDipendente();

    public void calcolaTotalePerReparto();

    public Map<Dipendente, Double> getTotalePerDipendente();

    public Map<TipoReparto, Double> getTotalePerReparto();

    public void chiudiTavolo(Tavolo t);

    public List<Tavolo> getTavoliLiberi();

    public double totaleGiornata();

    public void smistaOrdine(Ordine o);
       
    public void notificaOrdineCompletato(Ordine ordine);  

    public List<Ordine> getCodaOrdini();
}