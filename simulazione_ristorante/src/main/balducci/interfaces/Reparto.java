package main.balducci.interfaces;

import java.util.List;
import java.util.Queue;

import main.balducci.classes.TipoReparto;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Prodotto;

public interface Reparto {

    public boolean isAperto();
    public void apriReparto();
    public void chiudiReparto();
    public List<Dipendente> getDipendenti();
    public void aggiungiDipendente(Dipendente d);
    public void aggiungiOrdinazione(Ordine o);
    public void rimuoviDipendente(Dipendente d);
    public TipoReparto getTipoReparto();
    public void gestisciOrdine(Ordine ordine);
    public void notificaProdottoPronto(Prodotto p, int numT); 
    public void avviLavoratori();
    public Queue<Ordine> getCodaOrdini();
}