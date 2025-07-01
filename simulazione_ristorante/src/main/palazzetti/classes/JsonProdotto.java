package main.palazzetti.classes;

public class JsonProdotto {
    private String nome;
    private int tempoPreparazione;
    private double prezzo;
    private String tipo;
    private String reparto;

  
    public JsonProdotto() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTempoPreparazione() {
        return tempoPreparazione;
    }

    public void setTempoPreparazione(int tempoPreparazione) {
        this.tempoPreparazione = tempoPreparazione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getReparto() {
        return reparto;
    }

    public void setReparto(String reparto) {
        this.reparto = reparto;
    }
}
