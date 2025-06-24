package main.palazzetti.interfaces;

import java.util.Map;

public interface Ordine {
    public enum StatoOrdine {
        IN_ATTESA, PRONTO, CONSEGNATO, CONSUMATO;
    }

    //+getTavolo(): Tavolo
    public Tavolo getTavolo();

    //+getProdotti(): List<Prodotto>
    public Map<Prodotto, Integer> getProdotti();

    public StatoOrdine getStato();

    public void setStato(StatoOrdine s);
}