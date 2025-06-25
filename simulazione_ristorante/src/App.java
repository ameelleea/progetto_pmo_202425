import main.balducci.classes.RistoranteImpl;
import main.balducci.interfaces.Ristorante;

public class App {
    public static void main(String[] args) throws Exception {
        Ristorante ristorante = new RistoranteImpl("Gamerland");
        
    }

    /*Logica di Flusso e Interazioni
1. Inizio della Giornata (Metodo apriRistorante() in Ristorante)
Vengono istanziati tutti gli oggetti: Sala con Rango e Tavolo, Menu con Prodotto, Cassa, Reparti con i loro Lavoratori, Maitre, Camerieri.
I thread per Maitre, Camerieri, Lavoratori (nei reparti) vengono avviati.
Inizia la generazione dei GruppiClienti (ognuno con il proprio thread) che arrivano e si mettono in attesa nel Ristorante.
2. Arrivo Clienti e Assegnazione Tavolo
Un thread GruppoClienti viene creato e si avvia. Chiama ristorante.aggiungiGruppoInAttesa(this).
Il Maitre (nel suo thread) ciclicamente controlla ristorante.getProssimoGruppoInAttesa().
Se un gruppo è in attesa, il Maitre chiama cassa.getTavoliLiberi(numeroPostiRichiesti) per trovare un tavolo adatto.
Se un tavolo viene trovato, il Maitre chiama tavolo.occupa(gruppo) e gruppo.setTavoloAssegnato(tavolo).
Una volta che il GruppoClienti ha il tavolo assegnato, notifica il Cameriere del Rango a cui appartiene il tavolo che il tavolo è occupato. Il cameriere a sua volta notifica cassa.occupaTavolo(tavolo).
3. Ordinazione
Il GruppoClienti (nel suo thread) istruisce i suoi Cliente a scegliere dal Menu.
Il GruppoClienti crea un Ordine e lo riempie con i prodotti scelti.
Il GruppoClienti notifica il suo Cameriere di rango che è pronto per ordinare.
Il Cameriere raccoglie l'ordine e lo invia a cassa.gestisciOrdinazione(ordine).
La Cassa smista i prodotti dell'ordine ai rispettivi Reparto (reparto.riceviOrdine(ordine)).
4. Preparazione Ordine
I Lavoratori all'interno di ogni Reparto (nei loro thread) prendono gli ordini dalla loro BlockingQueue<Ordine>.
Ogni Lavoratore simula il tempo di preparazione per il prodotto di sua competenza (usando Thread.sleep()).
Quando un prodotto è pronto, il Lavoratore notifica l'Ordine (ordine.notificaProdottoPronto(prodotto)) che parte di esso è pronta.
L'oggetto Ordine tiene traccia dello stato di tutti i suoi prodotti. Quando tutti i prodotti nell'ordine sono PRONTO, l'Ordine notifica la Cassa (cassa.notificaOrdineCompletato(ordine)).
5. Servizio e Consumo
Quando la Cassa riceve la notifica ordineCompletato, informa il Cameriere interessato.
Il Cameriere porta l'ordine al GruppoClienti (gruppo.notificaOrdinePronto()).
Il GruppoClienti simula il consumo (altro Thread.sleep()).
6. Ordinazione Aggiuntiva (Caffè/Dessert) e Richiesta Conto
Dopo il consumo, il GruppoClienti decide se ordinare altro (stesso processo di ordinazione, con la cassa che smista a Caffetteria e Dessert).
Al termine, il GruppoClienti notifica il Cameriere che desidera il conto.
Il Cameriere richiede il conto a cassa.richiediConto(tavolo).
La Cassa calcola il totale e lo restituisce al Cameriere.
Il Cameriere presenta il conto al GruppoClienti.
7. Pagamento e Chiusura Tavolo
Il GruppoClienti simula il pagamento.
Il Cameriere notifica la Cassa che il tavolo è pronto per essere chiuso (cassa.chiudiTavolo(tavolo)).
La Cassa registra l'incasso, libera il tavolo (tavolo.libera()) e aggiorna le statistiche.
8. Fine Turno (Metodo chiudiRistorante() in Ristorante)
Il Ristorante ferma la generazione di nuovi gruppi.
Attende che tutti gli ordini in corso siano completati e tutti i tavoli liberati.
Chiama cassa.calcolaTotaliDiFineTurno(dipendenti) per generare il report finale.
Termina i thread dei dipendenti.
Considerazioni su Thread e Sincronizzazione
synchronized: Sarà cruciale utilizzare il blocco synchronized su metodi o blocchi di codice che accedono a risorse condivise per prevenire condizioni di gara (race conditions). Esempi:
Accesso ai tavoli liberi nella Cassa.
Aggiornamento dello stato dell'Ordine.
Prelevare ordini dalla coda dei Reparti.
Occupare/liberare un Tavolo.
wait() / notify() / notifyAll(): Utili per la comunicazione tra thread. Ad esempio:
GruppoClienti può wait() finché il Maitre non chiama notify() quando il tavolo è assegnato.
Cameriere può wait() finché la Cassa non chiama notify() quando un ordine è pronto per essere servito.
Ordine può wait() finché tutti i prodotti non sono PRONTO.
BlockingQueue: Perfetta per le code degli ordini nei Reparti. Semplifica la gestione della concorrenza per produttori (Cassa che inserisce ordini) e consumatori (Lavoratori che li prelevano).
ExecutorService: Per gestire facilmente i pool di thread, specialmente per i GruppiClienti e i Lavoratori. Permette di controllare il numero massimo di thread e di gestirne la terminazione.
 */
}
