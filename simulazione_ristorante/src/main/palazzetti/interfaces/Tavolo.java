package main.palazzetti.interfaces;

import main.balducci.interfaces.GruppoDiClienti;

public interface Tavolo {

     public int getNumPosti();

     public Boolean isOccupato();

     public Rango getRango();

     public int getNumero();  

     public void occupa(GruppoDiClienti g);
 
     public void libera();
}
