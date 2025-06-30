package test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import main.balducci.classes.GruppoClientiImpl;
import main.balducci.classes.RistoranteImpl;
import main.balducci.interfaces.GruppoClienti;
import main.balducci.interfaces.Ristorante;

public class AccogliClientiTest {
    
    private Ristorante ristorante;
    private GruppoClienti gruppo1, gruppo2, gruppo3;
    
    
    @BeforeEach
    void setUp() {
        ristorante = new RistoranteImpl("Gamerland");
        gruppo1 = new GruppoClientiImpl(0, 2, ristorante);
        gruppo2 = new GruppoClientiImpl(0, 5, ristorante);
        gruppo3 = new GruppoClientiImpl(0, 10, ristorante);
    }

    @Test
    void testAddFlight() {
        ristorante.addFlight(flight1);
        assertTrue(ristorante.getFlights().contains(flight1), "Flight should be added to the ristorante.");
        Optional<Flight> foundFlight = ristorante.findFlightByCode("AB123");
        assertNotNull(foundFlight.get(), "Flight should be found by its code.");
        assertEquals("AB123", foundFlight.get().getFlightCode(), "Flight code should match.");
        
        ristorante.addFlight(flight2);
        assertEquals(2,ristorante.getFlights().size());
        
        ristorante.addFlight(flight3);
        assertEquals("LT1762",ristorante.getFlights().first().getFlightCode());
    }
}
