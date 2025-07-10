package main.palazzetti.classes;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Prodotto;
import main.palazzetti.interfaces.Tavolo;

public class OrdineImpl implements Ordine {

    private Tavolo tavoloRiferimento;
    private Map<Prodotto, Integer> prodottiOrdinati;
    private Map<Prodotto, StatoProdotto> statoProdotti; 
    private volatile boolean completato;

    public OrdineImpl(Tavolo tavolo, Map<Prodotto, Integer> prodotti){
        this.tavoloRiferimento = tavolo;
        this.prodottiOrdinati = prodotti;
        this.completato = false;
        this.statoProdotti = new HashMap<>();
        this.prodottiOrdinati.keySet().stream().forEach(p -> statoProdotti.put(p, StatoProdotto.IN_ATTESA));
    }

    @Override
    public Map<Prodotto, Integer> getProdotti() {
        return prodottiOrdinati;
    }

    @Override
    public Tavolo getTavoloRiferimento() {
        return tavoloRiferimento;
    }

    @Override
    public boolean isCompletato() {
        return this.completato;
    }

    @Override
    public synchronized void notificaProdottoPronto(Prodotto prodotto) {
        this.statoProdotti.put(prodotto, StatoProdotto.PRONTO);

        if(this.statoProdotti.values().stream().allMatch(p -> p == StatoProdotto.PRONTO)){
            this.completato = true;
        }
    }

    @Override
    public String toString(){
        return this.prodottiOrdinati.entrySet()
                .stream()
                .map(e -> e.getKey().getNome() + ", " + e.getValue())
                .collect(Collectors.joining("\n"));
    }

    public void getStatoProdotti(){
        System.out.println(this.statoProdotti.entrySet()
                .stream()
                .map(e->e.getKey().getNome() +": " + e.getValue())
                .collect(Collectors.joining("\n")));
    }
}
   