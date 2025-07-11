package main.palazzetti.interfaces;

import java.util.Map;

public interface Ordine {

    public enum StatoProdotto { IN_ATTESA, PRONTO }

    public Map<Prodotto, Integer> getProdotti();
    
    public Tavolo getTavoloRiferimento();
    
    public boolean isCompletato();
    
    public void notificaProdottoPronto(Prodotto prodotto); 

    public void getStatoProdotti();
}
