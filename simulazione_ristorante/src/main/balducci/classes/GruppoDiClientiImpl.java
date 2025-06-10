package main.balducci.classes;

import java.util.List;
import main.balducci.interfaces.*;
import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Ordine;

public class GruppoDiClientiImpl implements GruppoClienti {

    //+getNumeroPersone(): int
    public int getNumeroPersone(){
        
    }

    //+getClienti(): List<Cliente>
    public List<Cliente> getClienti(){
        
    }

    @Override
    public void richiediTavolo(Ristorante r) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'richiediTavolo'");
    }

    @Override
    public Ordine getOrdineGruppo(Menu m) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrdineGruppo'");
    }

    @Override
    public boolean richiediConto() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'richiediConto'");
    }
}