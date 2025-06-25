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

/*GruppoClienti (Implementa Runnable)

Arrivo: Il gruppo arriva e si mette in attesa di un tavolo. Notifica il Maitre.
Assegnazione Tavolo: Attende che il Maitre assegni un tavolo.
Prende Posto: Notifica il cameriere del rango che il tavolo è occupato.
Ordinazione Iniziale: Ciascun cliente sceglie prodotti, e il gruppo consolida l'ordine per il cameriere.
Consumo: Attende che l'ordine sia completo e servito, poi simula il consumo.
Ordinazione Aggiuntiva (Caffè/Dessert): Se decide, ordina.
Richiesta Conto: Richiede il conto al cameriere.
Pagamento: Simula il pagamento e lascia il tavolo.
public synchronized void notificaOrdinePronto(): Chiamato dal cameriere quando l'ordine è servito. */
public class GruppoClientiImpl implements GruppoClienti, Runnable {

    private int id;
    private int numeroClienti;
    private List<Cliente> clienti;
    private Tavolo tavoloAssegnato;
    private Ristorante ristorante; // Riferimento al ristorante
    private boolean haOrdinatoPrimoGiro;
    private boolean haOrdinatoSecondoGiro;
    private boolean haPagato;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    public int getNumeroClienti(){
        return this.numeroClienti;
    }

    public List<Cliente> getClienti(){
        return this.clienti;
    }

    @Override
    public void richiediTavolo(Ristorante r) {
        this.tavoloAssegnato = r.accogliClienti(this);
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
        return new OrdineImpl(tavoloAssegnato, items);

    }

    @Override
    public void richiediConto() {
        this.t.setStato(StatoTavolo.RICHIESTA_CONTO);

    }

    @Override
    public Tavolo getTavolo() {
        return this.t;
    }

    @Override
    public void setTavoloAssegnato(Tavolo tavolo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTavoloAssegnato'");
    }
}