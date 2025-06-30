package main.balducci.classes;

import java.util.concurrent.Semaphore;

import main.balducci.interfaces.Cassa;
import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Rango;

public class Cameriere extends DipendenteImpl {

    private Rango rangoAppartenenza;
    private Cassa cassa;
    private Menu menu;
    private double totaleGiornata;
    private Semaphore nuovoGruppo;


    public Cameriere(int id, double stipendioOra, Rango rango, Cassa cassa, Menu menu) {
        super(id, stipendioOra);
        this.rangoAppartenenza = rango;
        this.nuovoGruppo = new Semaphore(0);
    }

    @Override
    public void lavora(){
        while(true){

        }
    }

    public void nuovoGruppo(){
        this.nuovoGruppo.release();
    }
}
