package main.palazzetti.interfaces;

import java.util.List;

import main.balducci.classes.TipoReparto;
import main.palazzetti.classes.MenuImpl;
import main.palazzetti.interfaces.Prodotto.TipoProdotto;

public interface Menu {

    public static Menu createMenu(){
        Menu menu = new MenuImpl();

        return menu;
    }
    /*
     *     public static List<Prodotto> caricaProdottiDaFile(String percorsoFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ProdottoImpl> prodotti = objectMapper.readValue(
            new File(percorsoFile),
            objectMapper.getTypeFactory().constructCollectionType(List.class, ProdottoImpl.class)
        );
        return prodotti;
    }
     */
    public List<Prodotto> getProdotti();
    public List<Prodotto> getProdottiPerTipo(TipoProdotto tipo);
    public List<Prodotto> getProdottiPerReparto(TipoReparto r);
    public Prodotto getProdottoCasuale(TipoReparto reparto, TipoProdotto tipo);
    public void aggiungiProdotto(Prodotto p);
    public void rimuoviProdotto(Prodotto p);

}
