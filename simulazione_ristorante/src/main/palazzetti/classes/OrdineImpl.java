package main.palazzetti.classes;

import java.util.Map;

import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Prodotto;
import main.palazzetti.interfaces.Tavolo;

public class OrdineImpl implements Ordine {

    private Tavolo t;
    private Map<Prodotto, Integer> prodotti;

    OrdineImpl(Tavolo t, Map<Prodotto, Integer> prodotti){

        this.t = t;
        this.prodotti = prodotti;
    }

    @Override
    public Tavolo getTavolo() {
        return this.t;
    }

    @Override
    public Map<Prodotto, Integer> getProdotti() {
        return this.prodotti;
    }

    
}