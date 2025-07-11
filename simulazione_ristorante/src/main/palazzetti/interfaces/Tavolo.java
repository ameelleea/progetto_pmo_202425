package main.palazzetti.interfaces;

import main.balducci.interfaces.GruppoClienti;

public interface Tavolo {

     public enum StatoTavolo{
        LIBERO, NON_ORDINATO, ORDINATO_PRIMO_GIRO, ORDINATO_SECONDO_GIRO, ORDINE_PRONTO, SERVITO, RICHIESTA_CONTO, CONTO_CONSEGNATO
     }

     public int getNumero();
     public int getNumeroPosti();
     public boolean isOccupato();
     public void occupa(GruppoClienti gruppo);
     public void libera();
     public GruppoClienti getGruppoCorrente();
     public StatoTavolo getStatoTavolo();
     public void setStatoTavolo(StatoTavolo stato);
}
