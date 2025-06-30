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
                        ((Cameriere) cameriere).nuovoGruppo();
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
}
