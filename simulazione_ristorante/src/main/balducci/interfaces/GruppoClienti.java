package main.balducci.interfaces;

import java.util.List;
import java.util.Optional;

import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Tavolo;

public interface GruppoClienti {

    public void richiediTavolo(Ristorante r);

    public Ordine getOrdineGruppo(Optional<String> giro);

    public void richiediConto();

    public int getNumeroClienti();

    public List<Cliente> getClienti();

    public Tavolo getTavolo();

    public boolean haOrdinato(String giro);

    public void setTavoloAssegnato(Tavolo tavolo); //Assegna il tavolo e notifica i clienti in attesa.
}