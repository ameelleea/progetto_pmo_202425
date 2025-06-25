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
    private long tempoInizioPreparazione;
    private boolean completato;

    public OrdineImpl(int id, Tavolo tavolo, Map<Prodotto, Integer> prodotti){
        this.id = id;
        this.tavoloRiferimento = tavolo;
        this.prodottiOrdinati = prodotti;
    }

    @Override
    public int getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }

    @Override
    public Tavolo getTavolo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTavolo'");
    }

    @Override
    public Map<Prodotto, Integer> getProdotti() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProdotti'");
    }

    @Override
    public Tavolo getTavoloRiferimento() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTavoloRiferimento'");
    }

    @Override
    public void setStatoProdotto(Prodotto prodotto, StatoProdotto stato) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setStatoProdotto'");
    }

    @Override
    public boolean isCompletato() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isCompletato'");
    }

    @Override
    public void notificaProdottoPronto(Prodotto prodotto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'notificaProdottoPronto'");
    }
}