package main.balducci.classes;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

import main.balducci.interfaces.Reparto;
import main.palazzetti.interfaces.Prodotto;

public class Preparatore extends DipendenteImpl{

    private Reparto repartoAppartenenza;
    private int numTavoloOrdine;
    private Optional<Prodotto> ordineCorrente;
    private Optional<Integer> quantità;
    private volatile boolean disponibile;

    public Preparatore(int id, double stipendioOra, Reparto reparto){
        super("Preparatore " + id + " " + reparto.getTipoReparto(), stipendioOra);
        System.out.println(reparto);
        this.repartoAppartenenza = reparto;
        this.disponibile = true;
        this.numTavoloOrdine = 0;
        this.ordineCorrente = Optional.empty();
        this.quantità = Optional.empty();
    }

    @Override
    public void lavora() {
        System.out.println(this.getIdDipendente() + " avviato");
        while(this.repartoAppartenenza.isAperto()){
            if(this.ordineCorrente.isPresent()){
                this.disponibile = false;

                System.out.println(this.getIdDipendente() + " prepara " + quantità.get() + " " + ordineCorrente.get().getNome());
                try{
                    Thread.sleep(Duration.ofSeconds(ordineCorrente.get().getTempoPreparazione() * quantità.get()).toMillis());
                }catch(InterruptedException e){
                    System.out.println(e);
                }
                System.out.println(this.getIdDipendente() + " ha terminato la preparazione.");
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
