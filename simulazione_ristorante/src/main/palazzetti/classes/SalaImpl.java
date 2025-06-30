
package main.palazzetti.classes;

import main.balducci.classes.Cameriere;
import main.balducci.classes.StipendiDipendenti;
import main.balducci.interfaces.Dipendente;
import main.balducci.interfaces.Ristorante;
import main.palazzetti.interfaces.Rango;
import main.palazzetti.interfaces.Sala;
import main.palazzetti.interfaces.Tavolo;

import java.util.*;

public class SalaImpl implements Sala {

    private Ristorante ristorante;
    private List<Rango> ranghi; 

    public SalaImpl(int numeroTavoli, int dimensioneRanghi, Ristorante ristorante) {
        this.ristorante = ristorante;
        this.ranghi = new ArrayList<>();
        int numeroParti = numeroTavoli / dimensioneRanghi;
        int resto = numeroTavoli % dimensioneRanghi;
        int idContatore = 1;

        for (int i = 1; i <= numeroParti; i++) {
            
            List<Tavolo> tavoliRango = new ArrayList<>();
            for (int j = 0; j < dimensioneRanghi; j++) {
                tavoliRango.add(new TavoloImpl(idContatore, 1 + (int)(Math.random() * 10)));
                idContatore++;
            }

            Rango rango = new RangoImpl(i, tavoliRango);
            Dipendente cameriereRango = new Cameriere(i, StipendiDipendenti.CAMERIERE.getPaga(), this.ristorante, rango);
            rango.setCameriere(cameriereRango);
            this.ranghi.add(rango);
        }

        if (resto > 0) {
            List<Tavolo> tavoliRango = new ArrayList<>();
            for (int j = 0; j < resto; j++) {
                tavoliRango.add(new TavoloImpl(idContatore, 1 + (int)(Math.random() * 10)));
                idContatore++;
            }

            Rango rango = new RangoImpl(numeroParti + 1, tavoliRango);
            Dipendente cameriereRango = new Cameriere(numeroParti+1, StipendiDipendenti.CAMERIERE.getPaga(), this.ristorante, rango);
            rango.setCameriere(cameriereRango);
            this.ranghi.add(rango);
        }
    }

    @Override
    public Rango getRangoByTavolo(Tavolo tavolo) {
        for (Rango r : ranghi) {
            if (r.getTavoli().contains(tavolo)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public Rango getRangoByCameriere(Dipendente c) {
        for (Rango r : ranghi) {
            if (r.getCameriere().equals(c)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public Rango getRango(Rango r) {
        for (Rango rango : ranghi) {
            if (rango.equals(r)) {
                return rango;
            }
        }
        return null;
    }

    @Override
    public List<Rango> getRanghi() {
        return this.ranghi;
    }
}