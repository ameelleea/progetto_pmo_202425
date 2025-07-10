package main.palazzetti.interfaces;

import java.util.Map;

public interface Ordine {

    public enum StatoProdotto { IN_ATTESA, PRONTO }

    public Map<Prodotto, Integer> getProdotti();
    
    public Tavolo getTavoloRiferimento();
    
    public boolean isCompletato(); //Controlla se tutti i prodotti sono pronti.
    
    //Chiamato da un reparto quando un prodotto è pronto. Se tutti i prodotti sono pronti, notifica l'ordine come completato.
    public void notificaProdottoPronto(Prodotto prodotto); 

    public void getStatoProdotti();
}
