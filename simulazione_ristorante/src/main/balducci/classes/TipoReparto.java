package main.balducci.classes;

public enum TipoReparto {
    CUCINA(5),
    PIZZERIA(3),
    BAR(1);

    private int numDipendenti;
    private TipoReparto(int num){
        this.numDipendenti = num;
    }

    public int getNumDipendenti(){
        return this.numDipendenti;
    }
}
