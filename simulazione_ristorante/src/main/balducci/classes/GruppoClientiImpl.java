package main.balducci.classes;

import java.util.List;
import main.balducci.interfaces.*;
import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Tavolo;

public class GruppoClientiImpl implements GruppoClienti {

    private List<Cliente> clienti;

    //+getNumeroPersone(): int
    public int getNumeroPersone(){
        return this.clienti.size();
    }

    //+getClienti(): List<Cliente>
    public List<Cliente> getClienti(){
        return this.clienti;
    }

    @Override
    public void richiediTavolo(Ristorante r) {
        r.accogliClienti(this);
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

    @Override
    public Tavolo getTavolo() {
        return null;
    }
}