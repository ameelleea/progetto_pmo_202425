package main.palazzetti.interfaces;

import main.balducci.interfaces.Dipendente;

public interface Sala {

    public Rango getRango(int num);

    public void aggiungiTavolo(Rango r);

    public void rimuoviTavolo(Rango r, Tavolo t);

    public void modificaCameriere(Rango r, Dipendente c);

}
