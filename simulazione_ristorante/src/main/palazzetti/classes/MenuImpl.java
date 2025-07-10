package main.palazzetti.classes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.balducci.classes.TipoReparto;
import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Prodotto;

public class MenuImpl implements Menu {
    private List<Prodotto> prodotti;

    public MenuImpl() {
        this.prodotti = new ArrayList<>();
    }

    public static MenuImpl fromJson(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        MenuImpl menu = new MenuImpl();
        try {
            List<JsonProdotto> prodottiJson = mapper.readValue(
                new File(filePath),
                new TypeReference<List<JsonProdotto>>() {}
            );

            for (JsonProdotto p : prodottiJson) {
                Prodotto prodotto = ProdottoImpl.fromJson(p);
                menu.aggiungiProdotto(prodotto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return menu;
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
    public Prodotto getProdottoCasuale(TipoProdotto tipo) {
        List<Prodotto> filtrati = new ArrayList<>();
        for (Prodotto p : prodotti) {
            if (p.getTipo().equals(tipo)) {
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
