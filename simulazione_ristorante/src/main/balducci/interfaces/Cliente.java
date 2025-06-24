package main.balducci.interfaces;

import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Ordine;

public interface Cliente {
    public Ordine ordina(Menu m);
    
}