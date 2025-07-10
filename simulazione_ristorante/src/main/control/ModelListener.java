package main.control;

import java.util.EventListener;
import java.util.List;

import main.balducci.interfaces.GruppoClienti;
import main.palazzetti.interfaces.Ordine;
	
public interface ModelListener extends EventListener{
    void notificaSimulazioneAvviata();
    void notificaSimulazioneFermata();
    void notificaStatoTavoloCambiato();
    void notificaGruppiInAttesaCambiati(List<GruppoClienti> gruppiInAttesa);
    void notificaNuovoOrdine(List<Ordine> ordini);
    void notificaRichiesteContoCambiate(List<GruppoClienti> richieste);
}
