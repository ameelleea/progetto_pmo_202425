package main.control;

import java.util.EventListener;

import main.balducci.interfaces.GruppoClienti;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Tavolo;
	
public interface ModelListener extends EventListener{
    void notificaTavoloOccupato();
    void notficaTavoloLibero();
    void notificaNuovoOrdine();
    void notificaContoRichiesto();
}
