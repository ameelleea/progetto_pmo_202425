package main.balducci.classes;

import main.balducci.interfaces.Ristorante;

import java.util.List;

import main.balducci.interfaces.GruppoClienti;

public class GruppoClientiFactory {

    private List<GruppoClienti> gruppiAttivi;
    private int numeroClienti;
    private int gruppiCreati;

    GruppoClientiFactory(int numeroClienti){
        this.numeroClienti = numeroClienti;
        this.gruppiCreati = 0;
    }

    public void generaClienti(Ristorante ristorante){
        while(this.numeroClienti > 0){
            try{
                Thread.sleep(30000);
            }catch(InterruptedException e){
                System.out.println(e);
            }

            int dimensioneGruppo = (int)(Math.random() * 9) + 2;
            new GruppoClientiImpl(gruppiCreati+1, dimensioneGruppo, ristorante).richiediTavolo(ristorante);

            this.numeroClienti -= dimensioneGruppo;
        }
    }

    public List<GruppoClienti> getGruppi(){
        return this.gruppiAttivi;
    }
}
