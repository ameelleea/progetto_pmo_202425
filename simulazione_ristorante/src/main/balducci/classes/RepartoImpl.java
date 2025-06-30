package main.balducci.classes;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.IntStream;

import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Prodotto;
import main.palazzetti.interfaces.Ordine.StatoProdotto;
import main.balducci.interfaces.*;

public class RepartoImpl implements Reparto {
    
    private List<Dipendente> lavoratori; // Lista di lavoratori specifici del reparto
    private Queue<Ordine> codaOrdini; // Coda per gli ordini in arrivo
    private TipoReparto tipoReparto;
    private boolean aperto;

    public RepartoImpl(TipoReparto tipo){

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
        
        prodotti.forEach((p, q) -> {
            IntStream.range(0, q).forEach(i -> {
                        this.lavoratori.stream()
                            .filter(l -> l instanceof Preparatore) 
                            .map(l -> (Preparatore) l)             
                            .filter(Preparatore::isDisponibile)    
                            .findFirst()
                            .ifPresent(prep -> {
                                prep.setOrdineCorrente(p);
                                ordine.setStatoProdotto(p, StatoProdotto.IN_PREPARAZIONE);
                            }); 
            });
        });
    }

    @Override
    public void avviLavoratori() {
        this.lavoratori.forEach(l -> l.lavora());
    }
}