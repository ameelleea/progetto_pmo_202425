package main.palazzetti.interfaces;

import main.balducci.classes.TipoReparto;
import main.palazzetti.classes.TipoProdotto;

public interface Prodotto {

   public String getNome();
   public int getTempoPreparazione();
   public TipoReparto getReparto();
   public TipoProdotto getTipo();
   public double getPrezzo();
   public void setPrezzo(Double prezzo);

}