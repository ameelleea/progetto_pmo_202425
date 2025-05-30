package main.palazzetti.interfaces;

import java.time.Duration;

public interface Prodotto {
   public  String getNome();
   public  Duration getTempoPreparazione();
   public  Reparto getReparto();
   public  double getPrezzo();
   public  void setPrezzo(Double prezzo);

}