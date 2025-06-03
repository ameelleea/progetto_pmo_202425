package main.balducci.classes;

import java.util.HashMap;
import java.util.Map;

import main.palazzetti.interfaces.Prodotto;
import main.palazzetti.interfaces.Tavolo;
import main.balducci.interfaces.*;

public class OrdineImpl implements Ordine {

    private final Tavolo tav;
    private Map<Prodotto, Integer> ordine;

    OrdineImpl(Tavolo t, Map<Prodotto, Integer> o){
        this.tav = t;
        this.ordine = o;
    }

    //+getTavolo(): Tavolo
    public Tavolo getTavolo(){
        return this.tav;
    }

    //+getProdotti(): List<Prodotto>
    public Map<Prodotto, Integer> getProdotti(){
        return this.ordine;
    }

    //equals()
    
}