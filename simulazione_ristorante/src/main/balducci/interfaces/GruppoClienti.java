package main.balducci.interfaces;

import java.util.List;

import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Tavolo;

public interface GruppoClienti {

    public void richiediTavolo(Ristorante r);

    public Ordine getOrdineGruppo(Menu m);

    public void richiediConto();

    public int getNumeroClienti();

    public List<Cliente> getClienti();

    public Tavolo getTavolo();

    public void setTavoloAssegnato(Tavolo tavolo); //Assegna il tavolo e notifica i clienti in attesa.
}