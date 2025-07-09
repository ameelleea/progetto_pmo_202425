package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.List;

import main.balducci.classes.TipoReparto;
import main.palazzetti.classes.MenuImpl;
import main.palazzetti.classes.ProdottoImpl;
import main.palazzetti.classes.TipoProdotto;
import main.palazzetti.interfaces.Prodotto;

public class FiltraProdottiPerTipo {
    @Test
    void testFiltraProdottiPerTipo() {
        MenuImpl menu = new MenuImpl();

        Prodotto dolce = new ProdottoImpl("Tiramisù", 4.0, TipoReparto.CUCINA, 1, TipoProdotto.DESSERT);
        Prodotto primo = new ProdottoImpl("Carbonara", 7.0, TipoReparto.CUCINA, 5, TipoProdotto.PORTATA);

        menu.aggiungiProdotto(dolce);
        menu.aggiungiProdotto(primo);

        List<Prodotto> dessert = menu.getProdottiPerTipo(TipoProdotto.DESSERT);

        assertEquals(1, dessert.size(), "Dovrebbe esserci un solo dessert");
        assertTrue(dessert.contains(dolce), "Il tiramisù dovrebbe essere presente nella lista dei dessert");
    }
}
