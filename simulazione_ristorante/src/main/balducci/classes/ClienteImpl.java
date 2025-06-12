package main.balducci.classes;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import main.balducci.interfaces.*;
import main.palazzetti.classes.OrdineImpl;
import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Prodotto;

public class ClienteImpl implements Cliente {

    private Random rnd = new Random();
    
    ClienteImpl(){

    }

    @Override
    public Ordine ordina(Menu m){
        Map<Prodotto, Integer> prodotti = new HashMap<>();

        Prodotto bevanda = m.getProdotti().stream()
                                        .filter(p -> p.getReparto().getNome() == "BAR")
                                        .findAny()
                                        .orElse(null);
        
        Prodotto pasto = m.getProdotti().stream()
                                        .filter(p -> p.getReparto().getNome() == "CUCINA" || p.getReparto().getNome() == "PIZZERIA")
                                        .findAny()
                                        .orElse(null);

        prodotti.put(bevanda, 1);
        prodotti.put(pasto, 1);

        return new OrdineImpl(prodotti);
    }
    
}