package main.balducci.interfaces;

import java.util.List;

import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Tavolo;

public interface GruppoClienti {

    public void richiediTavolo(Ristorante r);

    public Ordine getOrdineGruppo(Menu m);

    public boolean richiediConto();

    //+getNumeroPersone(): int
    public int getNumeroPersone();

    //+getClienti(): List<Cliente>
    public List<Cliente> getClienti();

    public Tavolo getTavolo();
}