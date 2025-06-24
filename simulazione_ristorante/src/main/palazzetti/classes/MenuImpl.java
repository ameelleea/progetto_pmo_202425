package main.palazzetti.classes;

import java.util.ArrayList;
import java.util.List;

import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Prodotto;

public class MenuImpl implements Menu {
    private List<Prodotto> prodotti;

    public MenuImpl() {
        this.prodotti = new ArrayList<>();
    }

    @Override
    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    @Override
    public List<Prodotto> getProdottiPerTipo(String tipo) {
        List<Prodotto> risultato = new ArrayList<>();
        for (Prodotto p : prodotti) {
            if (p.getReparto().getNome().equalsIgnoreCase(tipo)) {
                risultato.add(p);
            }
        }
        return risultato;
    }

    @Override
    public void aggiungiProdotto(Prodotto p) {
        prodotti.add(p);
    }

    @Override
    public void rimuoviProdotto(Prodotto p) {
        prodotti.remove(p);
    }
}
