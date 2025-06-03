package main.balducci.interfaces;

import java.util.Map;

import main.palazzetti.interfaces.Prodotto;
import main.palazzetti.interfaces.Tavolo;

public interface Ordine {

    //+getTavolo(): Tavolo
    public Tavolo getTavolo();

    //+getProdotti(): List<Prodotto>
    public Map<Prodotto, Integer> getProdotti();
    
}