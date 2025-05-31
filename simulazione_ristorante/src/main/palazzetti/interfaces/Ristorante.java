package main.palazzetti.interfaces;

public interface Ristorante {

    public void apriLocale();

    public void chiudiLocale();

    public Tavolo accogliClienti(GruppoDiClienti gruppo);
}
