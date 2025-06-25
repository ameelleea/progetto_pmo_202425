package main.balducci.interfaces;

import java.util.List;
import main.palazzetti.interfaces.Ordine;

public interface Reparto {

    public enum TipoReparto { CUCINA, PIZZERIA, BAR }

    //+getDipendenti(): Dipendente[]
    public List<Dipendente> getDipendenti();
    
    //Aggiunge un lavoratore.
    public void aggiungiDipendente(Dipendente d);

    //+aggiungiOrdinazione(Prodotto): void
    public void aggiungiOrdinazione(Ordine o);

    //+rimuoviDipendente(Dipendente): void
    public void rimuoviDipendente(Dipendente d);

    public TipoReparto getTipoReparto();

    //Aggiunge l'ordine alla coda.
    public void riceviOrdine(Ordine ordine); 
   
    //Metodo astratto per la logica specifica di preparazione.
    public void gestisciOrdine(Ordine ordine, Dipendente lavoratore);

    //Inizia i thread dei lavoratori.
    public void avviaLavoratori(); 

}