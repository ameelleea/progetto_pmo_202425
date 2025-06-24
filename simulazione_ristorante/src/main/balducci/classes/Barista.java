package main.balducci.classes;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.palazzetti.interfaces.Prodotto;

public class Barista extends DipendenteImpl {

    private List<Prodotto> codaOrdini;

    Barista(LocalTime i, LocalTime f, double p) {
        super(i, f, p);
        this.codaOrdini = new ArrayList<>();
    }

    @Override
    public void lavora() {
        Iterator<Prodotto> it = this.codaOrdini.iterator();

        while(LocalTime.now().compareTo(this.getOraFineTurno()) > 0){
            while(it.hasNext()){
                Prodotto daPreparare = it.next();
                daPreparare.getTempoPreparazione();

            }
        }
    }

    @Override
    public List<Prodotto> getOrdini() {
        return this.codaOrdini;
    }
    
}
