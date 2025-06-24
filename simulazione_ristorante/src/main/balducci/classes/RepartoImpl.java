package main.balducci.classes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Prodotto;
import main.palazzetti.interfaces.Tavolo;
import main.palazzetti.interfaces.Ordine.StatoOrdine;
import main.balducci.interfaces.*;

public class RepartoImpl implements Reparto {
    
    private String nome;
    private List<Dipendente> dipendenti;
    private List<Ordine> ordiniDaEvadere;

    RepartoImpl(String nome){
        this.dipendenti = new LinkedList<>();
        this.ordiniDaEvadere = new ArrayList<>();
    }

    public List<Dipendente> getDipendenti(){
     return this.dipendenti;   
    }
    
    @Override
    public void aggiungiDipendente(Dipendente d){
        this.dipendenti.add(d);
    }

    public void assegnaOrdinazione(Ordine o){
        this.dipendenti.stream()
                        .sorted(d -> d.getOrdini().size())
                        .findFirst()
                        .get();           
    }

    @Override
    public void aggiungiOrdinazione(Ordine o){
        this.ordiniDaEvadere.add(o);
        this.assegnaOrdinazione(o);
    }

    @Override
    public void rimuoviDipendente(Dipendente d){
        this.dipendenti.remove(d);
    }

    @Override
    public boolean ordinePronto(Ordine o){
        o.setStato(StatoOrdine.PRONTO);
        this.ordiniDaEvadere.remove(o);
        return true;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

}