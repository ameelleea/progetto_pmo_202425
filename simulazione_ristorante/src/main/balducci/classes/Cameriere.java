package main.balducci.classes;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;

import main.palazzetti.interfaces.Tavolo.StatoTavolo;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Tavolo;

public class Cameriere extends DipendenteImpl {

    private Map<Integer, StatoTavolo> tavoliServiti;


    Cameriere(LocalTime i, LocalTime f, double p, List<Tavolo> tavoli) {
        super(i, f, p);
        this.tavoliServiti = new HashMap<>();
        tavoli.forEach(t -> this.tavoliServiti.put(t.getNumero(), StatoTavolo.NON_ORDINATO));
    }

    @Override
    public void lavora() {
        while(true){
            
        }


        
    }

    @Override
    public List<Ordine> getOrdini() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrdini'");
    }
}
