package main.palazzetti.interfaces;

import java.util.List;

public interface Menu {

    public List<Prodotto> getProdotti();
    public List<Prodotto> getProdottiPerTipo(String tipo);
    public void aggiungiProdotto(Prodotto p);
    public void rimuoviProdotto(Prodotto p);

}
