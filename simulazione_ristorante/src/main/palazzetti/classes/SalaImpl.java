
package main.palazzetti.classes;

import main.balducci.interfaces.Dipendente;
import main.palazzetti.interfaces.Rango;
import main.palazzetti.interfaces.Sala;
import main.palazzetti.interfaces.Tavolo;

import java.util.*;

public class SalaImpl implements Sala {

    private List<Rango> ranghi; 

    public SalaImpl() {
        this.ranghi = new ArrayList<>();
    }

    @Override
    public Rango getRangoByTavolo(Tavolo tavolo) {
        for (Rango r : ranghi) {
            if (r.getTavoli().contains(tavolo)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public Rango getRangoByCameriere(Dipendente c) {
        for (Rango r : ranghi) {
            if (r.getCameriere().equals(c)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public Rango getRango(Rango r) {
        for (Rango rango : ranghi) {
            if (rango.equals(r)) {
                return rango;
            }
        }
        return null;
    }
}