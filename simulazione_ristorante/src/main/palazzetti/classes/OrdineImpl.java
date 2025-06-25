package main.palazzetti.classes;

import java.util.Map;

import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Prodotto;
import main.palazzetti.interfaces.Tavolo;

public class OrdineImpl implements Ordine {

    private int id;
    private Tavolo tavoloRiferimento;
    private Map<Prodotto, Integer> prodottiOrdinati;
    private Map<Prodotto, StatoProdotto> statoProdotti; 
    //private long tempoInizioPreparazione;
    private boolean completato;

    public OrdineImpl(int id, Tavolo tavolo, Map<Prodotto, Integer> prodotti){
        this.id = id;
        this.tavoloRiferimento = tavolo;
        this.prodottiOrdinati = prodotti;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Tavolo getTavolo() {
        return tavoloRiferimento;
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
    public void setStatoProdotto(Prodotto prodotto, StatoProdotto stato) {
        this.statoProdotti.put(prodotto, stato);
        
    }

    @Override
    public boolean isCompletato() {
        return this.completato;
    }

    @Override
    public void notificaProdottoPronto(Prodotto prodotto) {
        this.statoProdotti.put(prodotto, StatoProdotto.PRONTO);
        
    }
}
   