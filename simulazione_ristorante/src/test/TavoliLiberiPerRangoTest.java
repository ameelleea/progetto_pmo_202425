package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.balducci.interfaces.Dipendente;
import main.balducci.interfaces.GruppoClienti;
import main.palazzetti.classes.RangoImpl;
import main.palazzetti.classes.TavoloImpl;
import main.palazzetti.interfaces.Tavolo;

public class TavoliLiberiPerRangoTest {

    @Test
    void testTavoliLiberiNelRango() {
        // Mock necessari
        Dipendente cameriereMock = mock(Dipendente.class);
        GruppoClienti gruppoMock = mock(GruppoClienti.class);

        // Crea tavoli
        TavoloImpl t1 = new TavoloImpl(1, 2);
        TavoloImpl t2 = new TavoloImpl(2, 2);

        // Aggiungi i tavoli a una lista
        List<Tavolo> tavoli = new ArrayList<>();
        tavoli.add(t1);
        tavoli.add(t2);

        // Crea il rango passando la lista
        RangoImpl rango = new RangoImpl(1, tavoli);

        // Occupa uno dei due tavoli
        t1.occupa(gruppoMock);

        // Verifica i tavoli liberi
        List<Tavolo> liberi = rango.getTavoliLiberi();

        assertEquals(1, liberi.size(), "Dovrebbe esserci un solo tavolo libero");
        assertTrue(liberi.contains(t2), "Il tavolo 2 dovrebbe essere libero");
    }
}
