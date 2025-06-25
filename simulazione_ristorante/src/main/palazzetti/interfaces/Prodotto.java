package main.palazzetti.interfaces;

import main.balducci.interfaces.Reparto.TipoReparto;

public interface Prodotto {
   public enum TipoProdotto {PORTATA, BEVANDA, CAFFETTERIA, DOLCE}

   public String getNome();
   public int getTempoPreparazione();
   public TipoReparto getReparto();
   public TipoProdotto getTipo();
   public double getPrezzo();
   public void setPrezzo(Double prezzo);

}