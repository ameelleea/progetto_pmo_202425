package main.model;

import java.util.List;

import main.balducci.interfaces.GruppoClienti;
import main.control.ModelListener;
import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Tavolo;

public interface Model {
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
    void setNumClienti(int num);
    void setNumeroTavoli(int numero);
	void setDurataSimulazione(int durata);
	void setMenuPath(String path);
}

