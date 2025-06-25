package main.balducci.classes;

import java.util.List;
import java.util.Map;

import main.balducci.interfaces.*;
import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Prodotto;
import main.palazzetti.interfaces.Sala;
import main.palazzetti.interfaces.Tavolo;

public class CassaImpl implements Cassa {

    private double incassoTotaleGiornaliero;
    private Map<Tavolo, Ordine> ordiniInCorso; // Ordini attivi per ogni tavolo
    private Map<Dipendente, Double> guadagniPerDipendente; // Per calcolare i guadagni di ciascuno
    private Map<Reparto.TipoReparto, Double> guadagniPerReparto;
    private Map<Tavolo, Double> contiTavoli; // Conti parziali per tavolo
    private Sala sala; // Riferimento alla sala per conoscere i tavoli
    private Menu menu; // Riferimento al menu per i prezzi
    private List<Reparto> reparti;

    public CassaImpl(Sala sala, Menu menu, List<Reparto> reparti){

    }

    @Override
    public double calcolaConto(Tavolo t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcolaConto'");
    }

    @Override
    public void calcolaTotaliDiFineTurno(List<Dipendente> dipendenti) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcolaTotaliDiFineTurno'");
    }

    @Override
    public void apriTavolo(Tavolo t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'apriTavolo'");
    }

    @Override
    public void chiudiTavolo(Tavolo t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'chiudiTavolo'");
    }

    @Override
    public List<Tavolo> getTavoliLiberi() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTavoliLiberi'");
    }

    @Override
    public double calcolaTotaleGiornata() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcolaTotaleGiornata'");
    }

    @Override
    public void smistaOrdine(Ordine o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'smistaOrdine'");
    }

    @Override
    public Menu getMenu(String tipo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMenu'");
    }

    @Override
    public void notificaProdottoPronto(Prodotto prodotto, Ordine ordine) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'notificaProdottoPronto'");
    }

    @Override
    public void notificaOrdineCompletato(Ordine ordine) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'notificaOrdineCompletato'");
    }

    @Override
    public double richiediConto(Tavolo tavolo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'richiediConto'");
    }

    @Override
    public void registraIncasso(double importo, Tavolo tavolo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registraIncasso'");
    }

    
}