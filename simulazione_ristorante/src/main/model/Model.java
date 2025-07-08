package main.model;

import java.util.List;

import main.balducci.interfaces.GruppoClienti;
import main.control.ModelListener;
import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Tavolo;

public interface Model {
    void apriLocale();
    void chiudiLocale();
    boolean isLocaleAperto();
    void aggiungiModelListener(ModelListener listener);
    void rimuoviModelListener(ModelListener listener);
    List<GruppoClienti> getGruppiInAttesa();
    List<Tavolo> getTavoli();
    Menu getMenu();
    void simula();
    void notificaTavoloOccupato();
    void notficaTavoloLibero();
    void notificaNuovoOrdine();
    void notificaContoRichiesto();
    void getOrdiniInCorso();
}

