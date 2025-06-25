package main.balducci.classes;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;

import main.palazzetti.interfaces.Tavolo.StatoTavolo;
import main.balducci.interfaces.Cassa;
import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Rango;
import main.palazzetti.interfaces.Tavolo;

public class Cameriere extends DipendenteImpl {

    private String id;
    private String nome;
    private double stipendioOra;
    private Rango rangoAppartenenza;
    private Cassa cassa;
    private Menu menu;


    public Cameriere(String id, String nome, double stipendioOra, Rango rango, Cassa cassa, Menu menu) {
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

    public void run():
    /*Ciclo continuo:
    Notifica Occupazione Tavolo: Quando un GruppoClienti prende posto, notifica questo cameriere. Il cameriere notifica la Cassa.
    Prende Ordine: Riceve la richiesta di ordinazione da un GruppoClienti.
    Crea un oggetto Ordine.
    Invia l'ordine alla Cassa (cassa.gestisciOrdinazione(ordine)).
    Servizio Ordine: Attende che la Cassa o i reparti notifichino che l'ordine è pronto.
    Quando l'ordine è pronto, lo porta al tavolo (gruppo.notificaOrdinePronto()).
    Richiesta Conto: Quando il gruppo richiede il conto, il cameriere lo richiede alla Cassa (cassa.richiediConto(tavolo)). */
}
