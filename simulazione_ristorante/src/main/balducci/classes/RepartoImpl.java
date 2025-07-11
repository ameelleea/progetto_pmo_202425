package main.balducci.classes;

import java.util.ArrayList;
import java.util.Comparator;
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
    private List<Thread> threadLavoratori;
    private Queue<Ordine> codaOrdini;
    private TipoReparto tipoReparto;
    private volatile boolean aperto;

    public RepartoImpl(TipoReparto tipo, Ristorante ristorante, int numDipendenti){
        this.ristorante = ristorante;
        this.lavoratori = new ArrayList<>();
        this.threadLavoratori = new ArrayList<>();
        this.tipoReparto = tipo;
        for(int i = 1; i <= numDipendenti; i++){
            this.lavoratori.add(new Preparatore(i, StipendiDipendenti.PREPARATORE.getPaga(), this));
        }
        this.codaOrdini = new LinkedList<>();
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

    public void gestisciOrdine(Ordine ordine) {
        System.out.println(this.tipoReparto + " gestisce ordine del tavolo " + ordine.getTavoloRiferimento().getNumero());
        this.ristorante.addNuovoMessaggio(this.tipoReparto + " gestisce ordine del tavolo " + ordine.getTavoloRiferimento().getNumero());
        Map<Prodotto, Integer> prodotti = ordine.getProdotti();
        ordine.getStatoProdotti();
        for (Map.Entry<Prodotto, Integer> entry : prodotti.entrySet()) {
            Preparatore migliorPreparatore = lavoratori.stream()
                .filter(l -> l instanceof Preparatore)
                .map(l -> (Preparatore) l)
                .min(Comparator.comparingInt(p -> p.getDimensioneCoda()))
                .orElse(null);

            if (migliorPreparatore == null) {
                try{
                    Thread.sleep(10000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            } else {
                System.out.println("Trovato preparatore libero: " + migliorPreparatore.getIdDipendente());
                migliorPreparatore.aggiungiProdottoInCoda(new Pair<Integer, Map.Entry<Prodotto, Integer>>(ordine.getTavoloRiferimento().getNumero(), entry));
            }
        }
        this.codaOrdini.add(ordine);
    }

    @Override
    public void notificaProdottoPronto(Prodotto prodotto, int numT) {
        this.codaOrdini.stream()
                        .filter(o -> o.getTavoloRiferimento().getNumero() == numT)
                        .findFirst()
                        .ifPresent(o -> {
                            o.notificaProdottoPronto(prodotto);
                            o.getStatoProdotti();
                            if(o.isCompletato()){
                                System.out.println("Ordine " + this.tipoReparto + " tavolo " + o.getTavoloRiferimento().getNumero() + " completato");
                                this.ristorante.addNuovoMessaggio("Ordine " + this.tipoReparto + " tavolo " + o.getTavoloRiferimento().getNumero() + " completato");
                                this.ristorante.getCassa().notificaOrdineCompletato(o);
                                this.codaOrdini.remove(o);
                                
                            }
                        });
    }

    @Override
    public void avviLavoratori() {
        this.lavoratori.forEach(l -> {
            Thread lavT = new Thread(() -> {
                try{
                    l.lavora();
                }catch(InterruptedException e){
                    System.out.println(l.getIdDipendente() + " fermato");
                }});
            lavT.start();
            this.threadLavoratori.add(lavT);
        });
    }

    @Override
    public void apriReparto() {
        this.aperto = true;
        this.avviLavoratori();
    }

    @Override
    public void chiudiReparto() {
        this.aperto = false;
        this.threadLavoratori.forEach(Thread::interrupt);
    }

    @Override
    public Queue<Ordine> getCodaOrdini() {
        return this.codaOrdini;
    }
}