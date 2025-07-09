package test;

import main.palazzetti.classes.TavoloImpl;
import main.balducci.interfaces.GruppoClienti;
import main.palazzetti.interfaces.Rango;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.Duration;
import java.util.*;

public class AssegnaTavoliTest {
    @Test
    public void testAssegnazioneTavolo() {
        // Crea un mock per Rango e GruppoClienti
        Rango rangoMock = mock(Rango.class);
        GruppoClienti gruppoMock = mock(GruppoClienti.class);

        TavoloImpl tavolo = new TavoloImpl(1, 4);

        assertTrue(!tavolo.isOccupato(), "Il tavolo dovrebbe essere inizialmente libero");

        tavolo.occupa(gruppoMock);

        assertFalse(tavolo.isOccupato(), "Il tavolo dovrebbe essere occupato dopo la chiamata a occupa()");
        assertEquals(gruppoMock, tavolo.getGruppoCorrente(), "Il gruppo clienti assegnato non corrisponde");
    }
}
