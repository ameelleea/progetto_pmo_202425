package main.palazzetti.interfaces;

import main.balducci.interfaces.GruppoClienti;

public interface Tavolo {

     public enum StatoTavolo{
        NON_ORDINATO, ORDINE_PRONTO, SERVITO, RICHIESTA_CONTO
     }

     public int getNumero();
     public int getNumeroPosti();
     public boolean isOccupato();
     public void occupa(GruppoClienti gruppo); //Segna il tavolo come occupato e associa il gruppo.
     public void libera(); //Segna il tavolo come libero.
     public GruppoClienti getGruppoCorrente();
}
