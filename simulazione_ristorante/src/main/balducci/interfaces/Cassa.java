package main.balducci.interfaces;

import java.util.List;

import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Prodotto;
import main.palazzetti.interfaces.Tavolo;

public interface Cassa {

    public double calcolaConto(Tavolo t);

    //Calcola l'incasso totale, i guadagni netti per dipendente e per reparto, sottraendo gli stipendi.
    public void calcolaTotaliDiFineTurno();

    public void apriTavolo(Tavolo t);

    //Libera il tavolo, aggiorna l'incasso e resetta il conto del tavolo.
    public void chiudiTavolo(Tavolo t);

    public List<Tavolo> getTavoliLiberi();

    public double totaleGiornata();

    //Riceve un ordine dal cameriere.
    //Aggiunge l'ordine alla mappa ordiniInCorso.
    //Smista i prodotti dell'ordine ai rispettivi Reparto (aggiungendoli alle loro code).
    public void smistaOrdine(Ordine o);

    //Chiamato da un Reparto quando un prodotto è pronto. Aggiorna lo stato dell'ordine.
    public void notificaProdottoPronto(Prodotto prodotto, Ordine ordine); 
    
    //Chiamato dall'Ordine stesso quando tutti i suoi prodotti sono pronti. La cassa notifica il cameriere che l'ordine è pronto per essere servito.    
    public void notificaOrdineCompletato(Ordine ordine); 

    //Aggiunge l'importo all'incasso totale e al conto del tavolo.
    public void registraIncasso(double importo, Tavolo tavolo);  

}