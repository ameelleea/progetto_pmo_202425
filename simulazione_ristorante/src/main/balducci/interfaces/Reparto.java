package main.balducci.interfaces;

import java.util.List;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Prodotto;
import main.palazzetti.interfaces.Tavolo;

public interface Reparto {

    public enum TipoReparto { CUCINA, PIZZERIA, BAR }

    public boolean isAperto();
    public List<Dipendente> getDipendenti();
    
    //Aggiunge un lavoratore.
    public void aggiungiDipendente(Dipendente d);

    public void aggiungiOrdinazione(Ordine o);

    public void rimuoviDipendente(Dipendente d);

    public TipoReparto getTipoReparto();
   
    //Metodo astratto per la logica specifica di preparazione.
    public void gestisciOrdine(Ordine ordine);

    public void notificaProdottoPronto(Prodotto p, int numT); 
    public void avviLavoratori();
}