package main.balducci.interfaces;

import java.util.List;

import main.palazzetti.interfaces.Prodotto;
import main.palazzetti.interfaces.Tavolo;

public interface Dipendente {

    //+getTavolo(): Tavolo
    public Tavolo getTavolo();

    //+getProdotti(): List<Prodotto>
    public List<Prodotto> getProdotti();
}