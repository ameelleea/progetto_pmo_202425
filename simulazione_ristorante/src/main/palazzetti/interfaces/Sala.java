package main.palazzetti.interfaces;

import main.balducci.interfaces.Dipendente;

public interface Sala {
    
    //Trova il rango a cui appartiene un tavolo.
    public Rango getRangoByTavolo(Tavolo tavolo); 

    public Rango getRandoByCameriere(Dipendente c);

    public Rango getRango(Rango r);
}
