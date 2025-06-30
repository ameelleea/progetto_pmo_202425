package main.balducci.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import main.balducci.interfaces.*;
import main.palazzetti.classes.OrdineImpl;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Prodotto;
import main.palazzetti.interfaces.Tavolo;

public class GruppoClientiImpl implements GruppoClienti {

    private String id;
    private int numeroClienti;
    private List<Cliente> clienti;
    private Tavolo tavoloAssegnato;
    private Ristorante ristorante; // Riferimento al ristorante
    private Ordine primoGiro;
    private Ordine secondoGiro;
    private boolean haOrdinatoPrimoGiro;
    private boolean haOrdinatoSecondoGiro;
    private boolean haRichiestoConto;

    public GruppoClientiImpl(int id, int numClienti, Ristorante ristorante){
        this.id = "Gruppo clienti " + id;
        this.ristorante = ristorante;
        this.numeroClienti = numClienti;
        this.clienti = new ArrayList<>();

        for(int i=0; i < numClienti; i++){
            this.clienti.add(new ClienteImpl(i, this));
        }
    }

    @Override
    public void cena() {
        Stream<List<Prodotto>> liste = this.clienti.stream()
                                            .map(c -> c.ordina(this.ristorante.getMenu(), 1));

        Map<Prodotto, Integer> ordiniPrimoGiro = liste
            .flatMap(List::stream)
            .collect(Collectors.toMap(
                p -> p,
                p -> 1,
                Integer::sum
            ));

        this.primoGiro = new OrdineImpl(tavoloAssegnato, ordiniPrimoGiro);
        this.haOrdinatoPrimoGiro = true;
        while(!this.primoGiro.isCompletato());

        if(new Random().nextInt() > 0){
            liste = this.clienti.stream()
                                .map(c -> c.ordina(this.ristorante.getMenu(), 2));

            Map<Prodotto, Integer> ordiniSecondoGiro = liste
                .flatMap(List::stream)
                .collect(Collectors.toMap(
                    p -> p,
                    p -> 1,
                    Integer::sum
                ));
            this.secondoGiro = new OrdineImpl(tavoloAssegnato, ordiniSecondoGiro);
            while(!this.secondoGiro.isCompletato());
        }

        this.haRichiestoConto = true;
    }

    @Override
    public String getId(){
        return this.id;
    }
    @Override
    public int getNumeroClienti(){
        return this.numeroClienti;
    }

    public List<Cliente> getClienti(){
        return this.clienti;
    }

    @Override
    public void richiediTavolo(Ristorante r) {
        System.out.println(this.id + " richiede tavolo.");
        r.accogliClienti(this);
    }

    @Override
    public Ordine getOrdineGruppo(String giro) {
        if("primogiro".equals(giro)){
            return this.primoGiro;
        }else if("secondogiro".equals(giro)){
            return this.secondoGiro;
        }else{
            Map<Prodotto, Integer> ordiniTotali = new HashMap<>(this.primoGiro.getProdotti());

            secondoGiro.getProdotti().forEach((p, q) -> 
                ordiniTotali.merge(p, q, Integer::sum)
            );

            return new OrdineImpl(tavoloAssegnato, ordiniTotali);
        }
    }

    @Override
    public boolean richiedeConto() {
        return this.haRichiestoConto;
    }

    @Override
    public Tavolo getTavolo() {
        return this.tavoloAssegnato;
    }

    @Override 
    public boolean haOrdinato(String giro){
        if (giro.equals("primogiro")){
            return this.haOrdinatoPrimoGiro;
        }else if(giro.equals("secondogiro")){
            return this.haOrdinatoSecondoGiro;
        }else{
            throw new IllegalArgumentException(giro);
        }
    }
    @Override
    public void setTavoloAssegnato(Tavolo tavolo) {
        this.tavoloAssegnato = tavolo;
    }
}