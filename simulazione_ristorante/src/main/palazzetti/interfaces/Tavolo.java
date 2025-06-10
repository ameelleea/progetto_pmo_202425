package main.palazzetti.interfaces;

import main.balducci.interfaces.GruppoClienti;

public interface Tavolo {

     public int getNumPosti();

     public Boolean isOccupato();

     public Rango getRango();

     public int getNumero();  

     public void occupa(GruppoClienti g);
 
     public void libera();

}
