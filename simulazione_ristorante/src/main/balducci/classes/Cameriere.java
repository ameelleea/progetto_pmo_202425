package main.balducci.classes;

import java.util.LinkedList;
import java.util.List;

import main.balducci.interfaces.Cassa;
import main.balducci.interfaces.GruppoClienti;
import main.balducci.interfaces.Ristorante;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Rango;
import main.palazzetti.interfaces.Tavolo;
import main.palazzetti.interfaces.Tavolo.StatoTavolo;

public class Cameriere extends DipendenteImpl {

    private Ristorante ristorante;
    private Rango rangoAppartenenza;
    private List<Ordine> ordiniRaccolti;

    public Cameriere(int id, double stipendioOra, Ristorante ristorante, Rango rango) {
        super("Cameriere " + id, stipendioOra);
        this.ristorante = ristorante;
        this.rangoAppartenenza = rango;
        this.ordiniRaccolti = new LinkedList<>();
    }

    @Override
    public void lavora(){
        while(this.ristorante.isAperto()){
            Cassa cassa = this.ristorante.getCassa();

            for (Tavolo t : rangoAppartenenza.getTavoli()) {
                if (!t.isOccupato()) continue;

                GruppoClienti gruppo = t.getGruppoCorrente();

                if (gruppo.haOrdinato(1) && !this.ordiniRaccolti.contains(gruppo.getOrdineGruppo(1))) {
                    System.out.println(this.getIdDipendente() + " raccoglie ordine dal " + gruppo.getId());
                    Ordine ordinePrimo = gruppo.getOrdineGruppo(1);
                    System.out.println("Ordine tavolo " +t.getNumero() + ":\n" +ordinePrimo.toString());
                    this.ordiniRaccolti.add(ordinePrimo);
                    cassa.smistaOrdine(ordinePrimo);
                }else if (gruppo.haOrdinato(2) && !this.ordiniRaccolti.contains(gruppo.getOrdineGruppo(2))) {
                    Ordine ordineSecondo = gruppo.getOrdineGruppo(2);
                    System.out.println("Ordine tavolo " +t.getNumero() + ":\n" + ordineSecondo.toString());
                    this.ordiniRaccolti.add(ordineSecondo);
                    cassa.smistaOrdine(ordineSecondo);
                }else if (gruppo.richiedeConto()) {
                    double conto= cassa.calcolaConto(t);
                    System.out.println("Il tavolo " + t.getNumero() + " paga " + conto + " euro.");
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println(t.getGruppoCorrente().getId() + " ha pagato e lascia il locale.");
                    t.libera();
                }

                if (t.getStatoTavolo() == StatoTavolo.ORDINE_PRONTO) {
                    t.setStatoTavolo(StatoTavolo.SERVITO);
                }
            }
        }
    }
}

