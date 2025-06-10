package main.palazzetti.interfaces;

import java.util.Collection;

import main.balducci.interfaces.Dipendente;
public interface Rango {

    public Collection<Tavolo> getTavoli();

    public Dipendente getCameriere();

    public Collection<Tavolo> getTavoliLiberi();

}

