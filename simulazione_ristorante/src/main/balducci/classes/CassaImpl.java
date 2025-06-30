package main.balducci.classes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import main.balducci.interfaces.*;
import main.balducci.interfaces.Reparto.TipoReparto;
import main.palazzetti.classes.OrdineImpl;
import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Prodotto;
import main.palazzetti.interfaces.Sala;
import main.palazzetti.interfaces.Tavolo;
import main.palazzetti.interfaces.Tavolo.StatoTavolo;

public class CassaImpl implements Cassa {

    private double incassoTotaleGiornaliero;
    private List<Ordine> ordiniInCorso; // Ordini attivi per ogni tavolo
    private Map<Dipendente, Double> guadagniPerDipendente; // Per calcolare i guadagni di ciascuno
    private Map<Reparto.TipoReparto, Double> guadagniPerReparto;
    private Map<Tavolo, Double> contiTavoli; // Conti parziali per tavolo
    private Sala sala; // Riferimento alla sala per conoscere i tavoli
    private List<Reparto> reparti;

    public CassaImpl(Sala sala, Menu menu, List<Reparto> reparti){

    }

    @Override
    public double calcolaConto(Tavolo t) {

        double totaleTavolo =  t.getGruppoCorrente()
                                .getOrdineGruppo(null)
                                .getProdotti()
                                .entrySet()
                                .stream()
                                .mapToDouble(p -> p.getKey().getPrezzo() * p.getValue())
                                .sum();

        this.incassoTotaleGiornaliero += totaleTavolo;
        this.contiTavoli.put(t, totaleTavolo);
        return totaleTavolo;
    }

    @Override
    public void calcolaTotaliDiFineTurno() {
        this.guadagniPerDipendente = this.contiTavoli.entrySet().stream()
            .collect(Collectors.groupingBy(
                e -> this.sala.getRangoByTavolo(e.getKey()).getCameriere(),
                Collectors.summingDouble(Map.Entry::getValue)               
            ));
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
            reparto.aggiungiOrdinazione(ordineReparto);
        });

        this.ordiniInCorso.add(o);
    }

    @Override
    public void notificaProdottoPronto(Prodotto prodotto, Ordine ordine) {
        this.ordiniInCorso.stream()
                        .filter(o -> o.getTavoloRiferimento().equals(ordine.getTavoloRiferimento()))
                        .findFirst()
                        .ifPresent(o -> o.notificaProdottoPronto(prodotto));
    }

    @Override
    public void notificaOrdineCompletato(Ordine ordine) {
        Ordine ordineOriginale = this.ordiniInCorso.stream()
                        .filter(o -> o.getTavoloRiferimento().getNumero() == ordine.getTavoloRiferimento().getNumero())
                        .findFirst()
                        .orElse(null);
        
        ordine.getProdotti().entrySet()
                            .stream()
                            .forEach(p -> ordineOriginale.notificaProdottoPronto(p.getKey()));

        if(ordineOriginale.isCompletato()){
            ordineOriginale.getTavoloRiferimento().setStatoTavolo(StatoTavolo.ORDINE_PRONTO);
        }
    }

    @Override
    public void registraIncasso(double importo, Tavolo tavolo) {
        
    }
}