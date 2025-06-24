package main.balducci.classes;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Semaphore;

import main.balducci.interfaces.*;
import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Rango;
import main.palazzetti.interfaces.Sala;
import main.palazzetti.interfaces.Tavolo;

public class CassaImpl implements Cassa {
    private Sala sala;
    private final Menu menuPasto;
    private final Menu menuDolci;

    CassaImpl(){

    }

    @Override
    public double calcolaConto(Tavolo t) {
        
    }

    @Override
    public double calcolaTotaleCameriere(Dipendente c) {
        this.ranghi.stream()
                    .map(r -> r.getCameriere())
                    .mapToDouble(c -> c.)
    }

    @Override
    public void apriTavolo(Tavolo t, GruppoClienti g) {
        
        this.sala.stream()
                .filter(r -> r.getTavoli().contains(t))
                .map(r -> r.getTavoli())
                .filter(tav -> tav == t)
                .findAny()
                .ifPresent(t -> t.occupa(g));
    }

    @Override
    public void chiudiTavolo(Tavolo t) {
        
    }

    @Override
    public Collection<Tavolo> getTavoliLiberi() {
        
    }

    @Override
    public double calcolaTotaleGiornata() {
        
    }

    @Override
    public void smistaOrdine(Ordine o) {
        
    }

    @Override
    public Menu getMenu(String tipo) {
        
    }
}