package main.view;

import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Tavolo;

public interface View {

    void mostraMessaggio(String messaggio);
    void aggiornaStatoTavolo(Tavolo tavolo);
    void aggiornaMenu(Menu menu);
    void aggiornaOrdine(Tavolo tavolo, Ordine ordine);
    void mostraConto(Tavolo tavolo, double importo);
    void setViewObserver(ViewObserver observer);
}


