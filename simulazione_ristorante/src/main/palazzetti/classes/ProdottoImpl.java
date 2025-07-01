package main.palazzetti.classes;

import main.palazzetti.interfaces.Prodotto;
import main.balducci.classes.TipoReparto;

public class ProdottoImpl implements Prodotto {
    private final String nome;
    private final int tempoPreparazione;
    private final TipoReparto reparto;
    private double prezzo;
    private final TipoProdotto tipo;

    public ProdottoImpl(String nome, double prezzo, TipoReparto reparto, int tempoPreparazione, TipoProdotto tipo) {
        this.nome = nome;
        this.tempoPreparazione = tempoPreparazione;
        this.reparto = reparto;
        this.prezzo = prezzo;
        this.tipo = tipo;
    }

    public static ProdottoImpl fromJson(JsonProdotto j) {
        return new ProdottoImpl(
            j.getNome(),
            j.getPrezzo(),
            TipoReparto.valueOf(j.getReparto()),
            j.getTempoPreparazione(),
            TipoProdotto.valueOf(j.getTipo())
        );
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public int getTempoPreparazione() {
        return tempoPreparazione;
    }

    @Override
    public TipoReparto getReparto() {
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

    @Override
    public TipoProdotto getTipo() {
        return this.tipo;
    }
}
