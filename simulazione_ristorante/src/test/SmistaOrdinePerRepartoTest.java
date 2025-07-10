package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import org.junit.jupiter.api.Test;

import main.palazzetti.classes.OrdineImpl;
import main.palazzetti.classes.ProdottoImpl;
import main.palazzetti.classes.TavoloImpl;
import main.palazzetti.classes.TipoProdotto;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Prodotto;
import main.palazzetti.interfaces.Tavolo;
import main.balducci.classes.RistoranteImpl;
import main.balducci.classes.TipoReparto;
import main.balducci.interfaces.Ristorante;

public class SmistaOrdinePerRepartoTest {

    @Test
    void testSmistamentoOrdinePerReparto() {
        Tavolo tavoloMock = new TavoloImpl(1, 2);
        Ristorante r = new RistoranteImpl(null, 0, "/home/milena/Documenti/Coding/progetto_pmo_202425/simulazione_ristorante/Prodotti.json");
        r.apriLocale();

        // Creo la mappa dei prodotti con quantità
        Map<Prodotto, Integer> prodotti = new HashMap<>();

        Prodotto bevanda = new ProdottoImpl("Acqua", 1.5, TipoReparto.BAR, 1, TipoProdotto.BEVANDA);
        Prodotto pizza = new ProdottoImpl("Margherita", 1, TipoReparto.PIZZERIA, 6, TipoProdotto.PORTATA);

        prodotti.put(bevanda, 1);
        prodotti.put(pizza, 1);

        OrdineImpl ordine = new OrdineImpl(tavoloMock, prodotti);

        r.getCassa().smistaOrdine(ordine);
        Queue<Ordine> ordiniBar = r.getReparto(TipoReparto.BAR).getCodaOrdini();
        Queue<Ordine> ordiniPizzeria = r.getReparto(TipoReparto.PIZZERIA).getCodaOrdini();

        assertTrue(ordiniBar.poll().getProdotti().keySet().contains(bevanda));
        assertTrue(ordiniPizzeria.poll().getProdotti().keySet().contains(pizza));
    }
}
