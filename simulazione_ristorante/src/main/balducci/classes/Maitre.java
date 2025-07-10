package main.balducci.classes;

import java.util.Optional;

import main.balducci.interfaces.GruppoClienti;
import main.balducci.interfaces.Ristorante;
import main.palazzetti.interfaces.Tavolo;

public class Maitre extends DipendenteImpl{
    private Ristorante ristorante;
    private int gruppiDaGestire;

    public Maitre(String id, double stipendioOra, Ristorante ristorante){
        super(id, stipendioOra);
        this.ristorante = ristorante;  
        this.gruppiDaGestire = 0;  
    }

    @Override
    public void lavora(){
        while(this.ristorante.isAperto()){
            if(this.gruppiDaGestire > 0){
                System.out.println("Assegnazione gruppo...");
                GruppoClienti prossimo = this.ristorante.getProssimoGruppo();
                Optional<Tavolo> libero = Optional.empty();
                
            libero = this.ristorante.getCassa()
                .getTavoliLiberi()
                .stream()
                .filter(t -> t.getNumeroPosti() >= prossimo.getNumeroClienti()
                          && t.getNumeroPosti() <= prossimo.getNumeroClienti() + 1)
                .findAny();

                if(libero.isPresent()){
                    libero.ifPresent(t -> {
                        System.out.println("Assegnazione tavolo...");
                        prossimo.setTavoloAssegnato(t);
                        System.out.println("Occupazione tavolo...");
                        t.occupa(prossimo);
                        System.out.println("Gruppo " +prossimo.getId() + " assegnato al tavolo " + t.getNumero());
                        this.ristorante.addNuovoMessaggio("Gruppo " +prossimo.getId() + " assegnato al tavolo " + t.getNumero());
                    });
                }else{
                    try{
                        Thread.sleep(2000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    ristorante.accogliClienti(prossimo);
                }

                this.gruppiDaGestire--;
            }else{
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void nuovoGruppo(){
        this.gruppiDaGestire++;
    }
}
