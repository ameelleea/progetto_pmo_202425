package main.palazzetti.interfaces;

import main.balducci.interfaces.Dipendente;
public interface Rango {

    public Tavolo[] getTavoli();

    public Dipendente getCameriere();

    public Tavolo[] getTavoliLiberi();
}

