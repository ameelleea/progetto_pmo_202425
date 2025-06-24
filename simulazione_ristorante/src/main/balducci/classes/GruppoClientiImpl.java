package main.balducci.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import main.balducci.interfaces.*;
import main.palazzetti.classes.OrdineImpl;
import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Prodotto;
import main.palazzetti.interfaces.Tavolo;
import main.palazzetti.interfaces.Tavolo.StatoTavolo;

public class GruppoClientiImpl implements GruppoClienti {

    private List<Cliente> clienti;
    private Tavolo t;

    GruppoClientiImpl(int numClienti){
        this.clienti = new ArrayList<>();
        for(int i=0; i < numClienti; i++){
            this.clienti.add(new ClienteImpl());
        }
    }

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
        this. t = r.accogliClienti(this);
    }

    @Override
    public Ordine getOrdineGruppo(Menu m) {
        Map<Prodotto, Integer> items = this.clienti.stream()
                                                .map(c -> c.ordina(m))
                                                .map(o -> o.getProdotti())
                                                .flatMap(map -> map.entrySet().stream()) 
                                                .collect(Collectors.toMap(
                                                    Map.Entry::getKey,   
                                                    Map.Entry::getValue,  
                                                    Integer::sum          
                                                ));
        return new OrdineImpl(t, items);

    }

    @Override
    public void richiediConto() {
        this.t.setStato(StatoTavolo.RICHIESTA_CONTO);

    }

    @Override
    public Tavolo getTavolo() {
        return this.t;
    }
}