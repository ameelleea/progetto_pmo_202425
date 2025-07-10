
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
        int tavoliCreati = 0;
            
        for (int i = 1; i <= numeroParti; i++) {
        
            List<Tavolo> tavoliRango = new ArrayList<>();
            for (int j = 0; j < dimensioneRanghi; j++) {
                // Calcola il numero di posti: varia ogni 5 tavoli, da 2 a 10 a gruppi di 2
                int blocco = (tavoliCreati / 5) % 5; // 0-4 ciclicamente
                int posti = 2 + blocco * 2;
            
                tavoliRango.add(new TavoloImpl(idContatore, posti));
                idContatore++;
                tavoliCreati++;
            }
        
            Rango rango = new RangoImpl(i, tavoliRango);
            Dipendente cameriereRango = new Cameriere(i, StipendiDipendenti.CAMERIERE.getPaga(), this.ristorante, rango);
            rango.setCameriere(cameriereRango);
            this.ranghi.add(rango);
        }
        
        if (resto > 0) {
            List<Tavolo> tavoliRango = new ArrayList<>();
            for (int j = 0; j < resto; j++) {
                int blocco = (tavoliCreati / 5) % 5;
                int posti = 2 + blocco * 2;
            
                tavoliRango.add(new TavoloImpl(idContatore, posti));
                idContatore++;
                tavoliCreati++;
            }
        
            Rango rango = new RangoImpl(numeroParti + 1, tavoliRango);
            Dipendente cameriereRango = new Cameriere(numeroParti + 1, StipendiDipendenti.CAMERIERE.getPaga(), this.ristorante, rango);
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