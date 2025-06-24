package main.palazzetti.classes;

import java.time.Duration;
import main.palazzetti.interfaces.Prodotto;
import main.balducci.interfaces.Reparto;

public class ProdottoImpl implements Prodotto {
    private String nome;
    private Duration tempoPreparazione;
    private Reparto reparto;
    private double prezzo;
    

    public ProdottoImpl(String nome, Duration tempoPreparazione, Reparto reparto, double prezzo) {
        this.nome = nome;
        this.tempoPreparazione = tempoPreparazione;
        this.reparto = reparto;
        this.prezzo = prezzo;
      
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public Duration getTempoPreparazione() {
        return tempoPreparazione;
    }

    @Override
    public Reparto getReparto() {
        return reparto;
    }

    @Override
    public double getPrezzo() {
        return prezzo;
    }

    @Override
    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }
}