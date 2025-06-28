package main.balducci.classes;

import main.balducci.interfaces.Reparto;
import main.palazzetti.interfaces.Prodotto;

public class Preparatore extends DipendenteImpl{

    private Reparto repartoAppartenenza;
    private Prodotto ordineCorrente;
    private boolean disponibile;

    public Preparatore(int id, double stipendioOra, Reparto reparto){
        super(id, stipendioOra);
        this.repartoAppartenenza = reparto;
        this.disponibile = true;
    }

    @Override
    public void run(){
        

    }

    public boolean isDisponibile(){
        return this.disponibile;
    }

    public void setOrdineCorrente(Prodotto ordinato){
        this.ordineCorrente = ordinato;
    } 
    
}
