
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

    private static volatile Sala instance;
    private Ristorante ristorante;
    private List<Rango> ranghi; 

    private SalaImpl(int numeroTavoli, int dimensioneRanghi, Ristorante ristorante) {
        this.ristorante = ristorante;
        this.ranghi = new ArrayList<>();
        int numeroParti = numeroTavoli / dimensioneRanghi;
        int resto = numeroTavoli % dimensioneRanghi;
        int idContatore = 1;
        int tavoliCreati = 0;
            
        for (int i = 1; i <= numeroParti; i++) {
            List<Tavolo> tavoliRango = new ArrayList<>();
            for (int j = 0; j < dimensioneRanghi; j++) {
                // Ogni 4 tavoli aumenta di 2 posti, da 2 a 10
                int blocco = (tavoliCreati / 4) % 5; // 0,1,2,3,4
                int posti = 2 + blocco * 2;          // 2,4,6,8,10
            
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
                int blocco = (tavoliCreati / 4) % 5; // stesso calcolo anche qui
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

    public static Sala getInstance(int numeroTavoli, int dimensioneRanghi, Ristorante ristorante) {
      if (instance == null) {
        synchronized(Sala.class) {
          if (instance == null) {
            instance = new SalaImpl(numeroTavoli, dimensioneRanghi, ristorante);
          }
        }
      }
      return instance;
    }

    public static void resetInstance(){
        instance = null;
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