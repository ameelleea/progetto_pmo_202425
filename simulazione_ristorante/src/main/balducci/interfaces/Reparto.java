package main.balducci.interfaces;

import java.util.List;

public interface Reparto {

    //+getDipendenti(): Dipendente[]
    public List<Dipendente> getDipendenti();
    
    //+aggiungiDipendente(Dipendente): void
    public void aggiungiDipendente(Dipendente d);

    //+aggiungiOrdinazione(Prodotto): void
    public void aggiungiOrdinazione(Ordine o);

    //+rimuoviDipendente(Dipendente): void
    public void rimuoviDipendente(Dipendente d);

    //+ordinePronto(Tavolo): boolean
    public boolean ordinePronto(Object t);

}