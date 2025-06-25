package main.balducci.classes;

import main.balducci.interfaces.*;
import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Ordine;

public class ClienteImpl implements Cliente {

    private int id;
    private GruppoClienti gruppoAppartenenza;
    private Ordine ordineBevandeEPiatto;
    private Ordine ordineCaffeEDessert;
    private Ristorante ristorante;
    
    public ClienteImpl(int id, GruppoClienti gruppo, Ristorante ristorante){
        this.id = id;
        this.gruppoAppartenenza = gruppo;
        this.ristorante = ristorante;
    }

    @Override
    public Ordine ordina(Menu m, String tipo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ordina'");
    }    
}