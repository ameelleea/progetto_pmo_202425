package main.control;

import java.util.EventListener;
	
public interface ModelListener extends EventListener{
    void notificaSimulazioneAvviata();
    void notificaSimulazioneFermata();
    void notificaStatoTavoloCambiato();
    void notficaTavoloLibero();
    void notificaNuovoOrdine();
    void notificaContoRichiesto();
}
