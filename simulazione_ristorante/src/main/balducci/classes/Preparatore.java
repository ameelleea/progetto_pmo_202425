package main.balducci.classes;

import java.util.Optional;

import main.balducci.interfaces.Reparto;
import main.palazzetti.interfaces.Prodotto;

public class Preparatore extends DipendenteImpl{

    private Reparto repartoAppartenenza;
    private int numTavoloOrdine;
    private Optional<Prodotto> ordineCorrente;
    private boolean disponibile;

    public Preparatore(int id, double stipendioOra, Reparto reparto){
        super(id, stipendioOra);
        this.repartoAppartenenza = reparto;
        this.disponibile = true;
    }

    @Override
    public void lavora() {
        while(this.repartoAppartenenza.isAperto()){
            if(this.ordineCorrente.isPresent()){
                this.disponibile = false;

                int tempoPrep = ordineCorrente.get().getTempoPreparazione();
                if (tempoPrep > 0) {
                    tempoPrep--;
                }else{
                    repartoAppartenenza.notificaProdottoPronto(ordineCorrente.get(), numTavoloOrdine);
                    this.ordineCorrente = Optional.empty();
                    this.disponibile = true;
                }
            }
        }
    }

    public boolean isDisponibile(){
        return this.disponibile;
    }

    public void setOrdineCorrente(int numT, Prodotto ordinato){
        this.numTavoloOrdine = numT;
        this.ordineCorrente = Optional.of(ordinato);
    } 
}
