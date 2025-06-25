package main.palazzetti.classes;

import java.util.ArrayList;
import java.util.List;

import main.balducci.interfaces.Reparto.TipoReparto;
import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Prodotto;
import main.palazzetti.interfaces.Prodotto.TipoProdotto;

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
    public List<Prodotto> getProdottiPerTipo(TipoProdotto tipo) {
        List<Prodotto> risultato = new ArrayList<>();
        for (Prodotto p : prodotti) {
            if (p.getTipo().equals(tipo)) {
                risultato.add(p);
            }
        }
        return risultato;
    }

    @Override
    public List<Prodotto> getProdottiPerReparto(TipoReparto r) {
        List<Prodotto> risultato = new ArrayList<>();
        for (Prodotto p : prodotti) {
            if (p.getReparto().equals(r)) {
                risultato.add(p);
            }
        }
        return risultato;
    }

  @Override
    public Prodotto getProdottoCasuale(TipoReparto reparto, TipoProdotto tipo) {
        List<Prodotto> filtrati = new ArrayList<>();
        for (Prodotto p : prodotti) {
            if (p.getReparto().equals(reparto) && p.getTipo().equals(tipo)) {
                filtrati.add(p);
            }
        }
        if (filtrati.isEmpty()) {
            return null;
        }
        int casuale = (int)(Math.random() * filtrati.size());
        return filtrati.get(casuale);
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
