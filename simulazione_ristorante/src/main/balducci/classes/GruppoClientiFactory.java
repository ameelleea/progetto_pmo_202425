package main.balducci.classes;

import main.balducci.interfaces.Ristorante;

import java.util.ArrayList;
import java.util.List;

import main.balducci.interfaces.GruppoClienti;

public class GruppoClientiFactory {

    private List<GruppoClienti> gruppiAttivi;
    private int numeroClienti;
    private int gruppiCreati;

    public GruppoClientiFactory(int numeroClienti){
        this.numeroClienti = numeroClienti;
        this.gruppiCreati = 1;
        this.gruppiAttivi = new ArrayList<>();
    }

    public void generaClienti(Ristorante ristorante){
        while(this.numeroClienti > 0){

            int max = Math.min(numeroClienti, 8); 
            int dimensioneGruppo = 2 + (int)(Math.random() * (max - 1 + 1));


            GruppoClienti nuovGruppo = new GruppoClientiImpl(gruppiCreati, dimensioneGruppo, ristorante);
            this.gruppiAttivi.add(nuovGruppo);
            this.gruppiCreati++;
            this.numeroClienti -= dimensioneGruppo;
            System.out.println("Creato " + nuovGruppo.getId() + " con nr clienti " + nuovGruppo.getNumeroClienti());
        }
    }

    public List<GruppoClienti> getGruppi(){
        return this.gruppiAttivi;
    }
}
