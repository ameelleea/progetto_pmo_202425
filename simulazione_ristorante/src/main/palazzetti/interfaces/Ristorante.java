package main.palazzetti.interfaces;

import main.balducci.interfaces.GruppoDiClienti;

public interface Ristorante {

    public void apriLocale();

    public void chiudiLocale();

    public Tavolo accogliClienti(GruppoDiClienti gruppo);
}
