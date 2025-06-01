package main.balducci.interfaces;

import java.util.List;

public interface Dipendente {

    //+getTavolo(): Tavolo
    public Tavolo getTavolo();

    //+getProdotti(): List<Prodotto>
    public List<Prodotto> getProdotti();
}