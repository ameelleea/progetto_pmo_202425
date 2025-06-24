package main.balducci.classes;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import main.palazzetti.interfaces.Ordine;

public class Pizzaiolo extends DipendenteImpl {

    private List<Ordine> ordiniInAttesa;
    
    Pizzaiolo(LocalTime i, LocalTime f, double p) {
        super(i, f, p);
        this.ordiniInAttesa = new ArrayList<>();
    }

    @Override
    public void lavora() {
        while (!ordiniInAttesa.isEmpty()) {
            Ordine ordine = ordiniInAttesa.remove(0);
            // Prepara la pizza per l'ordine
        }
    }

    @Override
    public List<Ordine> getOrdini() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrdini'");
    }
}
