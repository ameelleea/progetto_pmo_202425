package main.balducci.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.balducci.interfaces.*;
import main.palazzetti.classes.TipoProdotto;
import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Prodotto;

public class ClienteImpl implements Cliente {

    //private int id;
    //private GruppoClienti gruppoAppartenenza;
    private List<Prodotto> ordineBevandeEPiatto;
    private List<Prodotto> ordineCaffeEDessert;
    
    public ClienteImpl(int id, GruppoClienti gruppo){
        //this.id = id;
        //this.gruppoAppartenenza = gruppo;
        this.ordineBevandeEPiatto = new ArrayList<>();
        this.ordineCaffeEDessert = new ArrayList<>();
    }

    @Override
    public List<Prodotto> ordina(Menu m, int giro) {

        if(giro == 1){
            ordineBevandeEPiatto.add(m.getProdottoCasuale(TipoReparto.BAR, TipoProdotto.BEVANDA));
            m.getProdottiPerTipo(TipoProdotto.PORTATA)
            .stream()
            .findAny()
            .ifPresent(p -> ordineBevandeEPiatto.add(p));

            return this.ordineBevandeEPiatto;
        }else if(giro == 2){
            ordineBevandeEPiatto.add(m.getProdottoCasuale(TipoReparto.BAR, TipoProdotto.CAFFETTERIA));
            m.getProdottiPerTipo(TipoProdotto.PORTATA)
            .stream()
            .findAny()
            .ifPresent(p -> ordineBevandeEPiatto.add(p));

            ordineBevandeEPiatto.add(m.getProdottoCasuale(TipoReparto.BAR, TipoProdotto.DESSERT));
            m.getProdottiPerTipo(TipoProdotto.PORTATA)
            .stream()
            .findAny()
            .ifPresent(p -> ordineBevandeEPiatto.add(p));

            return this.ordineCaffeEDessert;
        }
        else {
            return Collections.emptyList();
        }
    }    
}