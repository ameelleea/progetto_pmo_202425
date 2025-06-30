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
        this.gruppiDaGestire = 0;  
    }

    @Override
    public void lavora(){
        while(this.ristorante.isAperto()){
            if(this.gruppiDaGestire > 0){
                GruppoClienti prossimo = this.ristorante.getProssimoGruppo();
                Optional<Tavolo> libero = Optional.empty();
                
                libero = this.ristorante.getCassa()
                .getTavoliLiberi()
                .stream()
                .filter(t -> t.getNumeroPosti() >= prossimo.getNumeroClienti())
                .findAny();

                if(libero.isPresent()){
                    libero.ifPresent(t -> {
                        prossimo.setTavoloAssegnato(t);
                        t.occupa(prossimo);
                    });
                }else{
                    ristorante.accogliClienti(prossimo);
                }

                this.gruppiDaGestire--;
            }
        }
    }

    public void nuovoGruppo(){
        this.gruppiDaGestire++;
    }
}
