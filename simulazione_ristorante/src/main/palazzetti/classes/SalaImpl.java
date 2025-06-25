
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRangoByTavolo'");
    }

    @Override
    public Rango getRandoByCameriere(Dipendente c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRandoByCameriere'");
    }

    @Override
    public Rango getRango(Rango r) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRango'");
    }
}

