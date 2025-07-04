package main.balducci.classes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Prodotto;
import main.balducci.interfaces.*;

public class RepartoImpl implements Reparto {
    
    private Ristorante ristorante;
    private List<Dipendente> lavoratori;
    private Queue<Ordine> codaOrdini;
    private TipoReparto tipoReparto;
    private boolean aperto;

    public RepartoImpl(TipoReparto tipo, Ristorante ristorante, int numDipendenti){
        this.ristorante = ristorante;
        this.lavoratori = new ArrayList<>();
        for(int i = 1; i <= numDipendenti; i++){
            this.lavoratori.add(new Preparatore(i, StipendiDipendenti.PREPARATORE.getPaga(), this));
        }
        this.codaOrdini = new LinkedList<>();
        this.tipoReparto = tipo;
        this.aperto = false;
    }

    @Override 
    public boolean isAperto(){
        return this.aperto;
    }

    @Override
    public List<Dipendente> getDipendenti() {
        return this.lavoratori;
    }

    @Override
    public void aggiungiDipendente(Dipendente d) {
        this.lavoratori.add(d);
    }

    @Override
    public void aggiungiOrdinazione(Ordine o) {
        this.codaOrdini.add(o);
    }

    @Override
    public void rimuoviDipendente(Dipendente d) {
        this.lavoratori.remove(d);
    }

    @Override
    public TipoReparto getTipoReparto() {
        return this.tipoReparto;
    }

    @Override
    public void gestisciOrdine(Ordine ordine) {
        Map<Prodotto, Integer> prodotti = ordine.getProdotti();
        
        prodotti.entrySet().forEach(e -> {
            this.lavoratori.stream()
                .filter(l -> l instanceof Preparatore) 
                .map(l -> (Preparatore) l)             
                .filter(Preparatore::isDisponibile)    
                .findFirst()
                .ifPresent(prep -> {
                    prep.setOrdineCorrente(ordine.getTavoloRiferimento().getNumero(), e);
                }); 
        });
    }

    @Override
    public void notificaProdottoPronto(Prodotto prodotto, int numT) {
        this.codaOrdini.stream()
                        .filter(o -> o.getTavoloRiferimento().getNumero() == numT)
                        .findFirst()
                        .ifPresent(o -> {
                            o.notificaProdottoPronto(prodotto);
                            if(o.isCompletato()){
                                this.ristorante.getCassa().notificaOrdineCompletato(o);
                            }
                        });
    }

    @Override
    public void avviLavoratori() {
        this.lavoratori.forEach(l -> l.lavora());
    }

    @Override
    public void apriReparto() {
        this.aperto = true;
        this.avviLavoratori();
    }

    @Override
    public void chiudiReparto() {
        this.aperto = false;
    }
}