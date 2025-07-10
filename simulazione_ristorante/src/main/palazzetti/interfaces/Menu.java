package main.palazzetti.interfaces;

import java.util.List;

import main.balducci.classes.TipoReparto;
import main.palazzetti.classes.TipoProdotto;

public interface Menu {

    public List<Prodotto> getProdotti();
    public List<Prodotto> getProdottiPerTipo(TipoProdotto tipo);
    public List<Prodotto> getProdottiPerReparto(TipoReparto r);
    public Prodotto getProdottoCasuale(TipoProdotto tipo);
    public void aggiungiProdotto(Prodotto p);
    public void rimuoviProdotto(Prodotto p);

}
