package main.balducci.interfaces;

import main.palazzetti.interfaces.Tavolo;

public interface Ristorante {

    public void apriLocale();

    public void chiudiLocale();

    public boolean isAperto();

    public String getNome();

    public Reparto getReparto(String nome);

    public Tavolo accogliClienti(GruppoClienti gruppo);

}
