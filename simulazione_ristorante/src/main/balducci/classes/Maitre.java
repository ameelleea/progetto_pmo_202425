package main.balducci.classes;

import java.util.Optional;
import java.util.concurrent.Semaphore;

import main.balducci.interfaces.Dipendente;
import main.balducci.interfaces.GruppoClienti;
import main.balducci.interfaces.Ristorante;
import main.palazzetti.interfaces.Tavolo;

public class Maitre extends DipendenteImpl{
    private Ristorante ristorante;
    private Semaphore gruppiDaGestire;

    public Maitre(int id, double stipendioOra, Ristorante ristorante){
        super(id, stipendioOra);  
        this.gruppiDaGestire = new Semaphore(0);  
    }

    @Override
    public void run(){
        try{
            while(true){
                this.gruppiDaGestire.acquire();

                GruppoClienti prossimo = this.ristorante.getProssimoGruppo();
                Optional<Tavolo> libero = this.ristorante.getCassa()
                    .getTavoliLiberi()
                    .stream()
                    .filter(t -> !t.isOccupato())
                    .filter(t -> t.getNumeroPosti() >= prossimo.getNumeroClienti())
                    .findAny();

                libero.ifPresent(t -> {
                    prossimo.setTavoloAssegnato(t);
                    Dipendente cameriere = this.ristorante.getSala().getRangoByTavolo(t).getCameriere();
                    if(cameriere instanceof Cameriere){
                        (Cameriere) cameriere.nuovoGruppo();
                    }
                });
            }
        }catch(InterruptedException e){
           System.out.println("Maitre interrotto");
        }
    }

    public void nuovoGruppo(){
        this.gruppiDaGestire.release(1);
    }
    /*Ciclo continuo:
    Controlla se ci sono gruppi in attesa (ristorante.getProssimoGruppoInAttesa()).
    Accede alla Cassa per vedere i tavoli liberi (cassa.getTavoliLiberi()).
    Trova un tavolo adatto per il gruppo.
    Se trova un tavolo:
    Assegna il tavolo al gruppo (gruppo.setTavoloAssegnato(tavolo)).
    Notifica la Cassa che il tavolo è occupato (cassa.occupaTavolo(tavolo)).
    Se non trova un tavolo, il gruppo rimane in attesa.*/
}
