package main.palazzetti.interfaces;

import java.util.Map;

public interface Ordine {

    //+getTavolo(): Tavolo
    public Tavolo getTavolo();

    //+getProdotti(): List<Prodotto>
    public Map<Prodotto, Integer> getProdotti();
    
}