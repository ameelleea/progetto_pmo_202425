package main.balducci.classes;

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

    public Cameriere(int id, double stipendioOra, Ristorante ristorante, Rango rango) {
        super("Cameriere " + id, stipendioOra);
        this.ristorante = ristorante;
        this.rangoAppartenenza = rango;
    }

    @Override
    public void lavora(){
        while(this.ristorante.isAperto()){
            Cassa cassa = this.ristorante.getCassa();

            for (Tavolo t : rangoAppartenenza.getTavoli()) {
                if (!t.isOccupato()) continue;

                GruppoClienti gruppo = t.getGruppoCorrente();

                if (gruppo.haOrdinato(1)) {
                    Ordine ordinePrimo = gruppo.getOrdineGruppo(1);
                    cassa.smistaOrdine(ordinePrimo);
                }else if (gruppo.haOrdinato(2)) {
                    Ordine ordineSecondo = gruppo.getOrdineGruppo(2);
                    cassa.smistaOrdine(ordineSecondo);
                }else if (gruppo.richiedeConto()) {
                    cassa.calcolaConto(t);
                }

                if (t.getStatoTavolo() == StatoTavolo.ORDINE_PRONTO) {
                    t.setStatoTavolo(StatoTavolo.SERVITO);
                }
            }
        }
    }
}

