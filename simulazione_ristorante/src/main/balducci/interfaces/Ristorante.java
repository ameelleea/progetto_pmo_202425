package main.balducci.interfaces;

import main.balducci.interfaces.Reparto.TipoReparto;
import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Sala;
import main.palazzetti.interfaces.Tavolo;

public interface Ristorante {

    public void apriLocale();

    public void chiudiLocale();

    public boolean isAperto();

    public String getNome();

    public Reparto getReparto(TipoReparto tipo);

    public Tavolo accogliClienti(GruppoClienti gruppo);

    public Menu getMenu();
    
    public Cassa getCassa();
    
    public Sala getSala();

}
