package main.model;

import java.util.List;

import main.control.ModelListener;
import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Tavolo;

public interface Model {
    boolean isLocaleAperto();
    void aggiungiModelListener(ModelListener listener);
    void rimuoviModelListener(ModelListener listener);
    List<Tavolo> getTavoli();
    int getNumeroTavoli();
    Menu getMenu();
    void simula();
    void fermaSimulazione();
    void notificaGruppoInAttesa();
    void notificaStatoTavoloCambiato();
    void notificaOrdiniInAttesaCambiati();
    void notificaRichiesteContoCambiate();
    void notificaNuovoMessaggio();
    void notificaTotaliCambiati();
    void setNumClienti(int num);
	void setDurataSimulazione(int durata);
	void setMenuPath(String path);
}

