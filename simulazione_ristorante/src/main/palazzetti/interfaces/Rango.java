package main.palazzetti.interfaces;

import java.util.List;
import main.balducci.interfaces.Dipendente;

public interface Rango {

    public List<Tavolo> getTavoli();

    public Dipendente getCameriere();

    public List<Tavolo> getTavoliLiberi();

    public void aggiungiTavolo(Tavolo tavolo);

    public String getId();

    public void setCameriere(Dipendente c);
}
