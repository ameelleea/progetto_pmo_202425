package main.balducci.interfaces;

import java.util.List;

import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Ordine;

public interface GruppoClienti {

    public void richiediTavolo(Ristorante r);

    public Ordine getOrdineGruppo(Menu m);

    public boolean richiediConto();

    //+getNumeroPersone(): int
    public int getNumeroPersone();

    //+getClienti(): List<Cliente>
    public List<Cliente> getClienti();
}