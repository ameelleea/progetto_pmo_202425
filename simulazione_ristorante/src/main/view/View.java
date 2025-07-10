package main.view;

import java.util.List;

import main.balducci.interfaces.GruppoClienti;
import main.control.Controller;
import main.palazzetti.interfaces.Ordine;

public interface View {

    void mostraMessaggi(String messaggio);
    void aggiornaGruppiInAttesa(List<GruppoClienti> gruppiInAttesa);
    void aggiornaStatoTavoli();
    void aggiornaOrdini(List<Ordine> ordini);
    void aggiornaRichiesteConto(List<GruppoClienti> richieste);
    void aggiornaTotali(String totali);
    void mostraGUI();
    void setController(Controller c);
    Controller getController();
    int getDurata();
    void simula();
    void notificaSimulazioneAvviata();
    void notificaSimulazioneFermata();
    void notificaSimulazioneTerminata();
}


