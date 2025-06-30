package main.balducci.interfaces;

import java.util.List;

import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Prodotto;

public interface Cliente {
    public List<Prodotto> ordina(Menu m, int giro);
    
}