package main.palazzetti.classes;

import java.util.ArrayList;
import java.util.List;

import main.balducci.interfaces.Dipendente;
import main.palazzetti.interfaces.Rango;
import main.palazzetti.interfaces.Tavolo;

public class RangoImpl implements Rango {
    private String id;
    private Dipendente cameriereAssegnato;
    private List<Tavolo> tavoli;

    public RangoImpl(int id, List<Tavolo> tavoli) {
        this.tavoli = tavoli;
        this.id = "Rango " + id;
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
        return cameriereAssegnato;
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

    @Override
    public String getId(){
        return this.id;
    }

    @Override
    public void setCameriere(Dipendente c) {
        this.cameriereAssegnato = c;
    }
}
