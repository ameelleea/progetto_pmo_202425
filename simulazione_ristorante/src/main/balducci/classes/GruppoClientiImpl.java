package main.balducci.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import main.balducci.interfaces.*;
import main.palazzetti.classes.OrdineImpl;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Prodotto;
import main.palazzetti.interfaces.Tavolo;

public class GruppoClientiImpl implements GruppoClienti {

    private int id;
    private int numeroClienti;
    private List<Cliente> clienti;
    private Tavolo tavoloAssegnato;
    private Ristorante ristorante; // Riferimento al ristorante
    private Ordine primoGiro;
    private Ordine secondoGiro;
    private boolean haOrdinatoPrimoGiro;
    private boolean haOrdinatoSecondoGiro;

    public GruppoClientiImpl(int id, int numClienti, Ristorante ristorante){
        this.id = id;
        this.ristorante = ristorante;
        this.numeroClienti = numClienti;
        this.clienti = new ArrayList<>();

        for(int i=0; i < numClienti; i++){
            this.clienti.add(new ClienteImpl(i, this, ristorante));
        }
    }

    @Override
    public void run() {
            //Richiedi tavolo al ristorante
            this.richiediTavolo(ristorante);

            //Attendo che venga assegnato
            while(this.tavoloAssegnato == null);
            Stream<List<Prodotto>> liste = this.clienti.stream()
                                               .map(c -> c.ordina(this.ristorante.getMenu(), "primogiro"));

            Map<Prodotto, Integer> ordiniPrimoGiro = liste
                .flatMap(List::stream)
                .collect(Collectors.toMap(
                    p -> p,
                    p -> 1,
                    Integer::sum
                ));

            this.primoGiro = new OrdineImpl(this.id, tavoloAssegnato, ordiniPrimoGiro);
            this.haOrdinatoPrimoGiro = true;
            while(!this.primoGiro.isCompletato());

            if(new Random().nextInt() > 0){
                liste = this.clienti.stream()
                                               .map(c -> c.ordina(this.ristorante.getMenu(), "secondogiro"));

                Map<Prodotto, Integer> ordiniSecondoGiro = liste
                    .flatMap(List::stream)
                    .collect(Collectors.toMap(
                        p -> p,
                        p -> 1,
                        Integer::sum
                    ));
                this.secondoGiro = new OrdineImpl(this.id, tavoloAssegnato, ordiniSecondoGiro);
                while(!this.secondoGiro.isCompletato());
            }

            this.richiediConto();
    }

    public int getNumeroClienti(){
        return this.numeroClienti;
    }

    public List<Cliente> getClienti(){
        return this.clienti;
    }

    @Override
    public void richiediTavolo(Ristorante r) {
        r.accogliClienti(this);
    }

    @Override
    public Ordine getOrdineGruppo(Optional<String> giro) {
        if(Optional.of("primogiro").equals(giro)){
            return this.primoGiro;
        }else if(Optional.of("secondogiro").equals(giro)){
            return this.secondoGiro;
        }else{
            Map<Prodotto, Integer> ordiniTotali = new HashMap<>(this.primoGiro.getProdotti());

            secondoGiro.getProdotti().forEach((p, q) -> 
                ordiniTotali.merge(p, q, Integer::sum)
            );

            return new OrdineImpl(this.id, tavoloAssegnato, ordiniTotali);
        }
    }

    @Override
    public void richiediConto() {
        
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