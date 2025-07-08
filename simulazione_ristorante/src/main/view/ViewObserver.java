package main.view;

import main.balducci.interfaces.GruppoClienti;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Tavolo;

public interface ViewObserver {
    void onNuovoGruppo(GruppoClienti gruppo);
    void onAperturaLocale();
    void onChiusuraLocale();
    void onOrdineCreato(Tavolo tavolo, Ordine ordine);
    void onContoRichiesto(Tavolo tavolo);
}
