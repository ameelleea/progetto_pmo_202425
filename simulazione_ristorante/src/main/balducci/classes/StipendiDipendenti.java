package main.balducci.classes;

public enum StipendiDipendenti {
    PREPARATORE(12),
    CAMERIERE(10),
    MAITRE(11);

    private int stipendio;
    private StipendiDipendenti(int pagaoraria){
        this.stipendio = pagaoraria;
    }

    public int getPaga(){
        return this.stipendio;
    }
}
