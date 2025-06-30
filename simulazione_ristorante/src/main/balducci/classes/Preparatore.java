package main.balducci.classes;

import java.util.Map;
import java.util.Optional;

import main.balducci.interfaces.Reparto;
import main.palazzetti.interfaces.Prodotto;

public class Preparatore extends DipendenteImpl{

    private String id;
    private Reparto repartoAppartenenza;
    private int numTavoloOrdine;
    private Optional<Prodotto> ordineCorrente;
    private Optional<Integer> quantità;
    private boolean disponibile;

    public Preparatore(int id, double stipendioOra, Reparto reparto){
        super("Preparatore " + id, stipendioOra);
        this.repartoAppartenenza = reparto;
        this.disponibile = true;
    }

    @Override
    public void lavora() {
        while(this.repartoAppartenenza.isAperto()){
            if(this.ordineCorrente.isPresent()){
                this.disponibile = false;

                System.out.println(this.id + " prepara " + quantità + " " + ordineCorrente.get().getNome());
                try{
                    Thread.sleep(ordineCorrente.get().getTempoPreparazione() * quantità.get());
                }catch(InterruptedException e){
                    System.out.println(e);
                }

                repartoAppartenenza.notificaProdottoPronto(ordineCorrente.get(), numTavoloOrdine);
                this.ordineCorrente = Optional.empty();
                this.disponibile = true;
            }
        }
    }

    public boolean isDisponibile(){
        return this.disponibile;
    }

    public void setOrdineCorrente(int numT, Map.Entry<Prodotto, Integer> ordinato){
        this.numTavoloOrdine = numT;
        this.ordineCorrente = Optional.of(ordinato.getKey());
        this.quantità = Optional.of(ordinato.getValue());
    } 
}
