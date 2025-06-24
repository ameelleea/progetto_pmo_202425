
package main.palazzetti.classes;

import main.balducci.interfaces.Dipendente;
import main.palazzetti.interfaces.Rango;
import main.palazzetti.interfaces.Sala;
import main.palazzetti.interfaces.Tavolo;

import java.util.*;

public class SalaImpl implements Sala {

    private Map<Integer, Rango> ranghi; 

    public SalaImpl() {
        this.ranghi = new HashMap<>();
    }

    @Override
    public Rango getRango(int num) {
        return ranghi.get(num);
    }

    @Override
    public void aggiungiTavolo(Rango r) {
        
        //ranghi.putIfAbsent(r.getNumero(), r);
    }

    @Override
    public void rimuoviTavolo(Rango r, Tavolo t) {
        //Rango rango = ranghi.get(r.getNumero());
        //if (rango != null) {
        //    rango.rimuoviTavolo(t);
        //}
    }

    @Override
    public void modificaCameriere(Rango r, Dipendente c) {
        //Rango rango = ranghi.get(r.getNumero());
        //if (rango != null) {
        //    rango.setCameriere(c);
        //}
    }
}

