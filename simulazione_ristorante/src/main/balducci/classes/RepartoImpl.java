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
    private Queue<Ordine> codaOrdini;
    private TipoReparto tipoReparto;
    private volatile boolean aperto;

    public RepartoImpl(TipoReparto tipo, Ristorante ristorante, int numDipendenti){
        this.ristorante = ristorante;
        this.lavoratori = new ArrayList<>();
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

    //@Override
    //public void gestisciOrdine(Ordine ordine) {
    //    System.out.println("Reparto " + this.tipoReparto + " gestisce ordine del tavolo " + ordine.getTavoloRiferimento().getNumero());
    //    Map<Prodotto, Integer> prodotti = ordine.getProdotti();
    //    
    //    prodotti.entrySet().forEach(e -> {
    //        //this.lavoratori.stream()
    //        //    .filter(l -> l instanceof Preparatore) 
    //        //    .map(l -> (Preparatore) l)             
    //        //    .filter(Preparatore::isDisponibile)    
    //        //    .findFirst()
    //        //    .ifPresent(prep -> {
    //        //        System.out.println("Trovato preparatore libero: " +prep.getIdDipendente());
    //        //        prep.setOrdineCorrente(ordine.getTavoloRiferimento().getNumero(), e);
    //        //    }); 
    //    });
    //    this.codaOrdini.add(ordine);
    //}

    public void gestisciOrdine(Ordine ordine) {
        System.out.println("Reparto " + this.tipoReparto + " gestisce ordine del tavolo " + ordine.getTavoloRiferimento().getNumero());
        this.ristorante.addNuovoMessaggio("Reparto " + this.tipoReparto + " gestisce ordine del tavolo " + ordine.getTavoloRiferimento().getNumero());
        Map<Prodotto, Integer> prodotti = ordine.getProdotti();

        for (Map.Entry<Prodotto, Integer> entry : prodotti.entrySet()) {
            Preparatore migliorPreparatore = lavoratori.stream()
                .filter(l -> l instanceof Preparatore)
                .map(l -> (Preparatore) l)
                .min(Comparator.comparingInt(p -> p.getDimensioneCoda())) // seleziona coda più corta
                .orElse(null);

            if (migliorPreparatore != null) {
                System.out.println("Trovato preparatore libero: " + migliorPreparatore.getIdDipendente());
                migliorPreparatore.aggiungiProdottoInCoda(new Pair<Integer, Map.Entry<Prodotto, Integer>>(ordine.getTavoloRiferimento().getNumero(), entry));
            } else {
                // gestisci caso nessun preparatore disponibile (se necessario)
            }
        }
        this.codaOrdini.add(ordine);
    }
    /*for (Map.Entry<Prodotto, Integer> entry : prodotti.entrySet()) {
        boolean assegnato = false;

        while (!assegnato) {
            for (Dipendente l : this.lavoratori) {
                if (l instanceof Preparatore) {
                    Preparatore p = (Preparatore) l;
                    if (p.isDisponibile()) {
                        System.out.println("Trovato preparatore libero: " + p.getIdDipendente());
                        p.aggiungiProdottoInCoda(new Pair<Integer, Map.Entry<Prodotto, Integer>>(ordine.getTavoloRiferimento().getNumero(), entry));
                        assegnato = true;
                        break;
                    }
                }
            }

            if (!assegnato) {
                try {
                    Thread.sleep(100); // Attesa per non sovraccaricare la CPU
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    this.codaOrdini.add(ordine);
}*/
    @Override
    public void notificaProdottoPronto(Prodotto prodotto, int numT) {
        this.codaOrdini.stream()
                        .filter(o -> o.getTavoloRiferimento().getNumero() == numT)
                        .findFirst()
                        .ifPresent(o -> {
                            o.notificaProdottoPronto(prodotto);
                            if(o.isCompletato()){
                                System.out.println("Ordine " + this.tipoReparto + " tavolo " + o.getTavoloRiferimento().getNumero() + " completato");
                                this.ristorante.addNuovoMessaggio("Ordine " + this.tipoReparto + " tavolo " + o.getTavoloRiferimento().getNumero() + " completato");
                                this.ristorante.getCassa().notificaOrdineCompletato(o);
                                
                            }
                        });
    }

    @Override
    public void avviLavoratori() {
        this.lavoratori.forEach(l -> new Thread(() -> l.lavora()).start());
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

    @Override
    public Queue<Ordine> getCodaOrdini() {
        return this.codaOrdini;
    }
}