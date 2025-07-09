package main.view;

import main.control.Controller;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Tavolo;

public interface View {

    void mostraMessaggio(String messaggio);
    void aggiornaStatoTavolo(Tavolo tavolo);
    void aggiornaOrdine(Tavolo tavolo, Ordine ordine);
    void mostraConto(Tavolo tavolo, double importo);
    void mostraGUI();
    void setController(Controller c);
    Controller getController();
    int getDurata();
    void notificaSimulazioneAvviata();
    void notificaSimulazioneFermata();
}


