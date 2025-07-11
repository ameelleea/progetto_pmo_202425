package test;

import main.balducci.classes.GruppoClientiImpl;
import main.balducci.classes.RistoranteImpl;
import main.balducci.interfaces.GruppoClienti;
import main.balducci.interfaces.Ristorante;
import main.palazzetti.interfaces.Tavolo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class AssegnaTavoliTest {
    @Test
    public void testAssegnazioneTavolo() {

        Ristorante ristorante = RistoranteImpl.getInstance("Test", 20, "/home/milena/Documenti/Coding/progetto_pmo_202425/simulazione_ristorante/Prodotti.json");
        ristorante.apriLocale();
        GruppoClienti gruppo1 = new GruppoClientiImpl(1, 5, ristorante);
        List<Tavolo> tavoli = ristorante.getCassa().getTavoliLiberi();

        assertTrue(tavoli.size() == 20, "Tutti i tavoli dovrebbero essere liberi");
        gruppo1.richiediTavolo(ristorante);
        try{
            synchronized (gruppo1) {
                while (gruppo1.getTavolo() == null) {
                    gruppo1.wait(); // attenzione: va dentro blocco synchronized
                }
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        tavoli = ristorante.getCassa().getTavoliLiberi();
        assertTrue(gruppo1.getTavolo().getNumeroPosti() >= gruppo1.getNumeroClienti(), "Il tavolo dovrebbe essere inizialmente libero");
        assertFalse(tavoli.size() == 20, "Un tavolo dovrebbe essere occupato");
    }
}
