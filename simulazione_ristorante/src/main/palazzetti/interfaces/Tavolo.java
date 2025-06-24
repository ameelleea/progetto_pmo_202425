package main.palazzetti.interfaces;

import main.balducci.interfaces.GruppoClienti;

public interface Tavolo {

     public enum StatoTavolo{
        NON_ORDINATO, ORDINE_PRONTO, SERVITO, RICHIESTA_CONTO
    }

     public int getNumPosti();

     public Boolean isOccupato();

     public Rango getRango();

     public int getNumero();  

     public void occupa(GruppoClienti g);
 
     public void libera();

     public StatoTavolo getStato();

     public void setStato(StatoTavolo s);
}
