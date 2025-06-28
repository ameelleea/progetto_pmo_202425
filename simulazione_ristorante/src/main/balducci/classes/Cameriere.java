package main.balducci.classes;

import java.util.concurrent.Semaphore;

import main.balducci.interfaces.Cassa;
import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Rango;

public class Cameriere extends DipendenteImpl {

    private Rango rangoAppartenenza;
    private Cassa cassa;
    private Menu menu;
    private double totaleGiornata;
    private Semaphore nuovoGruppo;


    public Cameriere(int id, double stipendioOra, Rango rango, Cassa cassa, Menu menu) {
        super(id, stipendioOra);
        this.rangoAppartenenza = rango;
        this.nuovoGruppo = new Semaphore(0);
    }

    @Override
    public void run(){
        try{
            while(true){
                this.nuovoGruppo.acquire();

                this.
            }
        }catch(InterruptedException e){

        }

    }

    public void nuovoGruppo(){
        this.nuovoGruppo.release();
    }
    /*Ciclo continuo:
    Notifica Occupazione Tavolo: Quando un GruppoClienti prende posto, notifica questo cameriere. Il cameriere notifica la Cassa.
    Prende Ordine: Riceve la richiesta di ordinazione da un GruppoClienti.
    Crea un oggetto Ordine.
    Invia l'ordine alla Cassa (cassa.gestisciOrdinazione(ordine)).
    Servizio Ordine: Attende che la Cassa o i reparti notifichino che l'ordine è pronto.
    Quando l'ordine è pronto, lo porta al tavolo (gruppo.notificaOrdinePronto()).
    Richiesta Conto: Quando il gruppo richiede il conto, il cameriere lo richiede alla Cassa (cassa.richiediConto(tavolo)). */
}
