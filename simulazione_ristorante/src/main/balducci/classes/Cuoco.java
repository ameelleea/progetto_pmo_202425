package main.balducci.classes;

import java.time.LocalTime;
import java.util.List;

import main.palazzetti.interfaces.Ordine;

public class Cuoco extends DipendenteImpl {

    Cuoco(LocalTime i, LocalTime f, double p) {
        super(i, f, p);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void lavora() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lavora'");
    }

    @Override
    public List<Ordine> getOrdini() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrdini'");
    }
    
}
