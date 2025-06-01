package main.palazzetti.interfaces;

import java.util.List;

public interface Menu {

    public List<Object> getProdotti();
    public List<Object> getProdottiPerTipo(String tipo);
    public void aggiungiProdotto(Prodotto p);
    public void rimuoviProdotto(Prodotto p);
}
