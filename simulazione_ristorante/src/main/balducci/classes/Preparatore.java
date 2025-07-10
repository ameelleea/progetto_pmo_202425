package main.balducci.classes;

import java.time.Duration;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import main.balducci.interfaces.Reparto;
import main.palazzetti.interfaces.Prodotto;

public class Preparatore extends DipendenteImpl{

    private Reparto repartoAppartenenza;
    private Queue<Pair<Integer, Map.Entry<Prodotto, Integer>>> codaProdotti;

    public Preparatore(int id, double stipendioOra, Reparto reparto){
        super("Preparatore " + id + " " + reparto.getTipoReparto(), stipendioOra);
        this.repartoAppartenenza = reparto;
        this.codaProdotti = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void lavora() {
        System.out.println(this.getIdDipendente() + " avviato");
        while(this.repartoAppartenenza.isAperto()){
            if(this.codaProdotti.size() > 0){
                Pair<Integer, Map.Entry<Prodotto, Integer>> ordineCorrente = this.codaProdotti.poll();
                Prodotto prodotto = ordineCorrente.getY().getKey();
                Integer quantità = ordineCorrente.getY().getValue();
                Integer numTavoloOrdine = ordineCorrente.getX();
                System.out.println(this.getIdDipendente() + " prepara " + quantità + " " + prodotto.getNome());
                try{
                    Thread.sleep(Duration.ofSeconds(prodotto.getTempoPreparazione() * quantità).toMillis());
                }catch(InterruptedException e){
                    System.out.println(e);
                }
                System.out.println(this.getIdDipendente() + " ha terminato la preparazione.");
                repartoAppartenenza.notificaProdottoPronto(prodotto, numTavoloOrdine);

            }

            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public int getDimensioneCoda(){
        return this.codaProdotti.size();
    }

    public void aggiungiProdottoInCoda(Pair<Integer, Map.Entry<Prodotto, Integer>> prodotto){
        this.codaProdotti.add(prodotto);
    } 
}
