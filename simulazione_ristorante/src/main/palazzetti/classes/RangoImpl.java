package main.palazzetti.classes;

import java.util.ArrayList;
import java.util.Collection;

import main.balducci.interfaces.Dipendente;
import main.palazzetti.interfaces.Rango;
import main.palazzetti.interfaces.Tavolo;

public class RangoImpl implements Rango {
    private List<Tavolo> tavoli;
    private Dipendente cameriere;

    public RangoImpl(Dipendente cameriere) {
        this.tavoli = new ArrayList<>();
        this.cameriere = cameriere;
    }

    public void aggiungiTavolo(Tavolo t) {
        tavoli.add(t);
    }

    @Override
    public List<Tavolo> getTavoli() {
        return tavoli;
    }

    @Override
    public Dipendente getCameriere() {
        return cameriere;
    }

    @Override
    public List<Tavolo> getTavoliLiberi() {
        List<Tavolo> liberi = new ArrayList<>();
        for (Tavolo t : tavoli) {
            if (!t.isOccupato()) {
                liberi.add(t);
            }
        }
        return liberi;
    }
}
