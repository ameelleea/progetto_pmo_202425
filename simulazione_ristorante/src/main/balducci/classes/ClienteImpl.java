package main.balducci.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.balducci.interfaces.*;
import main.palazzetti.classes.TipoProdotto;
import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Prodotto;

public class ClienteImpl implements Cliente {

    private List<Prodotto> ordineBevandeEPiatto;
    private List<Prodotto> ordineCaffeEDessert;
    
    public ClienteImpl(int id, GruppoClienti gruppo){
        this.ordineBevandeEPiatto = new ArrayList<>();
        this.ordineCaffeEDessert = new ArrayList<>();
    }

    @Override
    public List<Prodotto> ordina(Menu m, int giro) {

        if(giro == 1){
            ordineBevandeEPiatto.add(m.getProdottoCasuale(TipoProdotto.BEVANDA));
            ordineBevandeEPiatto.add(m.getProdottoCasuale(TipoProdotto.PORTATA));

            return this.ordineBevandeEPiatto;
        }else if(giro == 2){
            ordineCaffeEDessert.add(m.getProdottoCasuale(TipoProdotto.CAFFETTERIA));
            ordineCaffeEDessert.add(m.getProdottoCasuale(TipoProdotto.DESSERT));

            return this.ordineCaffeEDessert;
        }
        else {
            return Collections.emptyList();
        }
    }    
}