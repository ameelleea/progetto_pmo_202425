package main.balducci.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.balducci.interfaces.*;
import main.balducci.interfaces.Reparto.TipoReparto;
import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Prodotto;
import main.palazzetti.interfaces.Prodotto.TipoProdotto;

public class ClienteImpl implements Cliente {

    private int id;
    private GruppoClienti gruppoAppartenenza;
    private List<Prodotto> ordineBevandeEPiatto;
    private List<Prodotto> ordineCaffeEDessert;
    private Ristorante ristorante;
    
    public ClienteImpl(int id, GruppoClienti gruppo, Ristorante ristorante){
        this.id = id;
        this.gruppoAppartenenza = gruppo;
        this.ristorante = ristorante;
        this.ordineBevandeEPiatto = new ArrayList<>();
        this.ordineCaffeEDessert = new ArrayList<>();
    }

    @Override
    public List<Prodotto> ordina(Menu m, String tipo) {

        if("primogiro".equals(tipo)){
            ordineBevandeEPiatto.add(m.getProdottoCasuale(TipoReparto.BAR, TipoProdotto.BEVANDA));
            m.getProdottiPerTipo(TipoProdotto.PORTATA)
            .stream()
            .findAny()
            .ifPresent(p -> ordineBevandeEPiatto.add(p));

            return this.ordineBevandeEPiatto;
        }else if("secondogiro".equals(tipo)){
            return this.ordineCaffeEDessert;
        }
        else {
            return Collections.emptyList(); // o lancia eccezione se preferisci
        }
    }    
}