package main.balducci.classes;

import main.balducci.interfaces.Ristorante;

import main.balducci.interfaces.GruppoClienti;

public class GruppoClientiFactory {
    private int numeroClienti;
    private int gruppiCreati;

    public GruppoClientiFactory(int numeroClienti){
        this.numeroClienti = numeroClienti;
        this.gruppiCreati = 1;
    }

    public GruppoClienti creaGruppo(Ristorante ristorante){

        int max = Math.min(numeroClienti, 8); 
        int dimensioneGruppo = 2 + (int)(Math.random() * (max - 1 + 1));


        GruppoClienti nuovGruppo = new GruppoClientiImpl(gruppiCreati, dimensioneGruppo, ristorante);
        this.gruppiCreati++;
        this.numeroClienti -= dimensioneGruppo;
        System.out.println("Creato " + nuovGruppo.getId() + " con nr clienti " + nuovGruppo.getNumeroClienti());
        return nuovGruppo;
    }

    public int getNumeroClienti(){
        return this.numeroClienti;
    }
}
