package main.control;

import java.util.EventListener;

import main.balducci.interfaces.GruppoClienti;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Tavolo;
	
public interface ModelListener extends EventListener{
    void onTavoloAssegnato(GruppoClienti gruppo, Tavolo tavolo);
    void onOrdineInviato(Ordine ordine);
    void onOrdineServito(Tavolo tavolo);
    void onContoRichiesto(Tavolo tavolo, double importo);
    void onLocaleAperto();
    void onLocaleChiuso();
}
