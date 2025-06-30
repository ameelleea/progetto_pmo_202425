package main.balducci.interfaces;

import java.util.List;

import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Tavolo;

public interface GruppoClienti {

    public void cena();
    
    public void richiediTavolo(Ristorante r);

    public Ordine getOrdineGruppo(String giro);

    public boolean richiedeConto();

    public int getNumeroClienti();

    public List<Cliente> getClienti();

    public Tavolo getTavolo();

    public boolean haOrdinato(String giro);

    public void setTavoloAssegnato(Tavolo tavolo); //Assegna il tavolo e notifica i clienti in attesa.
}