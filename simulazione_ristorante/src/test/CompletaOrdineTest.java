package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import main.balducci.classes.TipoReparto;
import main.palazzetti.classes.OrdineImpl;
import main.palazzetti.classes.ProdottoImpl;
import main.palazzetti.classes.TipoProdotto;
import main.palazzetti.interfaces.Prodotto;
import main.palazzetti.interfaces.Tavolo;

public class CompletaOrdineTest {

    @Test
    void testCompletamentoOrdine() {
        Tavolo tavoloMock = mock(Tavolo.class);

        // Crea i prodotti
        Prodotto dolce = new ProdottoImpl("Tiramisù", 4.0, TipoReparto.CUCINA, 1, TipoProdotto.DESSERT);
        Prodotto primo = new ProdottoImpl("Carbonara", 7.0, TipoReparto.CUCINA, 5, TipoProdotto.PORTATA);

        // Inseriscili in una mappa
        Map<Prodotto, Integer> prodotti = new HashMap<>();
        prodotti.put(dolce, 1);
        prodotti.put(primo, 1);

        // Crea ordine con i prodotti
        OrdineImpl ordine = new OrdineImpl(tavoloMock, prodotti);

        // Inizialmente l'ordine non è completato
        assertFalse(ordine.isCompletato(), "L'ordine non dovrebbe essere completato all'inizio");

        // Notifica primo prodotto pronto
        ordine.notificaProdottoPronto(dolce);
        assertFalse(ordine.isCompletato(), "L'ordine non è ancora completo, manca un prodotto");

        // Notifica secondo prodotto pronto
        ordine.notificaProdottoPronto(primo);
        assertTrue(ordine.isCompletato(), "L'ordine dovrebbe essere completato ora che tutti i prodotti sono pronti");
    }
}
