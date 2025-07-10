package main.balducci.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import main.balducci.interfaces.*;
import main.palazzetti.classes.OrdineImpl;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Prodotto;
import main.palazzetti.interfaces.Rango;
import main.palazzetti.interfaces.Sala;
import main.palazzetti.interfaces.Tavolo;
import main.palazzetti.interfaces.Tavolo.StatoTavolo;

public class CassaImpl implements Cassa {

    private double incassoTotaleGiornaliero;
    private List<Ordine> ordiniInCorso; // Ordini attivi per ogni tavolo
    private Map<Tavolo, List<Ordine>> ordiniPerTavolo; //Ordini totali di ciascun tavolo in tutta la simulazione
    private Map<Dipendente, Double> guadagniPerDipendente; // Per calcolare i guadagni di ciascuno
    private Map<TipoReparto, Double> guadagniPerReparto;
    private Sala sala; // Riferimento alla sala per conoscere i tavoli
    private List<Reparto> reparti;

    public CassaImpl(Sala sala, List<Reparto> reparti){
        this.sala = sala;
        this.reparti = reparti;
        this.incassoTotaleGiornaliero = 0;
        this.ordiniInCorso = new ArrayList<>(); 
        this.ordiniPerTavolo = new HashMap<>();
        this.guadagniPerDipendente = new HashMap<>(); 
        this.guadagniPerReparto = new HashMap<>(); 
    }

    @Override
    public double calcolaConto(Tavolo t) {

        double totaleTavolo =  t.getGruppoCorrente()
                                .getOrdineGruppo()
                                .getProdotti()
                                .entrySet()
                                .stream()
                                .mapToDouble(p -> p.getKey().getPrezzo() * p.getValue())
                                .sum();

        this.incassoTotaleGiornaliero += totaleTavolo;
        return totaleTavolo;
    }

    @Override
    public void calcolaTotalePerDipendente() {
        for (Map.Entry<Tavolo, List<Ordine>> entry : ordiniPerTavolo.entrySet()) {
            Tavolo tavolo = entry.getKey();
            List<Ordine> ordini = entry.getValue();
    
            Rango rango = sala.getRangoByTavolo(tavolo);
    
            Dipendente cameriere = rango.getCameriere();
    
            // Calcolo il totale degli ordini per quel tavolo
            double totaleOrdini = 0.0;
            for (Ordine ordine : ordini) {
                // Somma dei prezzi dei prodotti ordinati
                double totaleOrdine = ordine.getProdotti().entrySet().stream()
                    .mapToDouble(e -> e.getKey().getPrezzo() * e.getValue())
                    .sum();
                totaleOrdini += totaleOrdine;
            }
        
            guadagniPerDipendente.merge(cameriere, totaleOrdini, Double::sum);
        }
    }

    @Override
    public void calcolaTotalePerReparto(){
        for (Reparto r : this.reparti) {
            double totale = this.ordiniPerTavolo.values().stream()      
                .flatMap(List::stream)                                  
                .map(Ordine::getProdotti)                               
                .map(Map::keySet)                                       
                .flatMap(Set::stream)                                   
                .filter(p -> p.getReparto() == r.getTipoReparto())     
                .mapToDouble(Prodotto::getPrezzo)                  
                .sum();
        
            this.guadagniPerReparto.put(r.getTipoReparto(), totale);
        }
    }

    @Override
    public void chiudiTavolo(Tavolo t) {
        t.libera();
    }

    @Override
    public List<Tavolo> getTavoliLiberi() {
        return this.sala.getRanghi().stream()
                                    .map(r -> r.getTavoliLiberi())
                                    .flatMap(List::stream)
                                    .collect(Collectors.toList());
    }

    @Override
    public double totaleGiornata() {
        return this.incassoTotaleGiornaliero;
    }

    @Override
    public void smistaOrdine(Ordine o) {
        System.out.println("Cassa smista ordine del tavolo " + o.getTavoloRiferimento().getNumero());
        this.ordiniInCorso.add(o);
        Map<TipoReparto, Map<Prodotto, Integer>> mappaPerReparto =
            o.getProdotti().entrySet().stream()
                .collect(Collectors.groupingBy(
                    (Map.Entry<Prodotto, Integer> entry) -> entry.getKey().getReparto(), 
                    Collectors.toMap(
                        Map.Entry::getKey,               
                        Map.Entry::getValue             
                    )
                ));

        mappaPerReparto.entrySet().forEach(e -> {
            Ordine ordineReparto = new OrdineImpl(o.getTavoloRiferimento(), e.getValue());
            Reparto reparto = reparti.stream().filter(r -> r.getTipoReparto() == e.getKey()).findAny().orElse(null);
            reparto.gestisciOrdine(ordineReparto);
        });
    }

    @Override
    public synchronized void notificaOrdineCompletato(Ordine ordine) {
        Ordine ordineOriginale = this.ordiniInCorso.stream()
                        .filter(o -> o.getTavoloRiferimento().getNumero() == ordine.getTavoloRiferimento().getNumero())
                        .findFirst()
                        .orElse(null);
        
        ordine.getProdotti().entrySet()
                            .stream()
                            .forEach(p -> ordineOriginale.notificaProdottoPronto(p.getKey()));

        if(ordineOriginale.isCompletato()){
            ordineOriginale.getTavoloRiferimento().setStatoTavolo(StatoTavolo.ORDINE_PRONTO);
            this.ordiniInCorso.remove(ordineOriginale);
        System.out.println("Tavolo " + ordineOriginale.getTavoloRiferimento().getNumero() + " " + ordineOriginale.getTavoloRiferimento().getStatoTavolo());
        }
    }

    @Override
    public List<Ordine> getCodaOrdini(){
        return this.ordiniInCorso;
    }

    @Override
    public Map<Dipendente, Double> getTotalePerDipendente() {
        return this.guadagniPerDipendente;
    }

    @Override
    public Map<TipoReparto, Double> getTotalePerReparto() {
        return this.guadagniPerReparto;
    }
}