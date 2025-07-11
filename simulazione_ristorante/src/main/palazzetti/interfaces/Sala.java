package main.palazzetti.interfaces;

import java.util.List;

import main.balducci.interfaces.Dipendente;

public interface Sala {
    public Rango getRangoByTavolo(Tavolo tavolo); 

    public Rango getRangoByCameriere(Dipendente c);

    public Rango getRango(Rango r);

    public List<Rango> getRanghi();
}
