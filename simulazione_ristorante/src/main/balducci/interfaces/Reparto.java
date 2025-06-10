package main.balducci.interfaces;

import java.util.Collection;

import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Tavolo;

public interface Reparto {

    //+getDipendenti(): Dipendente[]
    public Collection<Dipendente> getDipendenti();
    
    //+aggiungiDipendente(Dipendente): void
    public void aggiungiDipendente(Dipendente d);

    //+aggiungiOrdinazione(Prodotto): void
    public void aggiungiOrdinazione(Ordine o);

    //+rimuoviDipendente(Dipendente): void
    public void rimuoviDipendente(Dipendente d);

    //+ordinePronto(Tavolo): boolean
    public boolean ordinePronto(Tavolo t);

}