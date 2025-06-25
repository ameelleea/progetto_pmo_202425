package main.balducci.classes;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import main.palazzetti.interfaces.Ordine;
import main.balducci.interfaces.*;

public class RepartoImpl implements Reparto {
    
    private String nome;
    private List<Dipendente> lavoratori; // Lista di lavoratori specifici del reparto
    private BlockingQueue<Ordine> codaOrdini; // Coda per gli ordini in arrivo
    private TipoReparto tipoReparto;

    public RepartoImpl(String nome, TipoReparto tipo){

    }

    @Override
    public List<Dipendente> getDipendenti() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDipendenti'");
    }

    @Override
    public void aggiungiDipendente(Dipendente d) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'aggiungiDipendente'");
    }

    @Override
    public void aggiungiOrdinazione(Ordine o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'aggiungiOrdinazione'");
    }

    @Override
    public void rimuoviDipendente(Dipendente d) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rimuoviDipendente'");
    }

    @Override
    public TipoReparto getTipoReparto() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTipoReparto'");
    }

    @Override
    public void riceviOrdine(Ordine ordine) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'riceviOrdine'");
    }

    @Override
    public void gestisciOrdine(Ordine ordine, Dipendente lavoratore) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gestisciOrdine'");
    }

    @Override
    public void avviaLavoratori() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'avviaLavoratori'");
    }
}