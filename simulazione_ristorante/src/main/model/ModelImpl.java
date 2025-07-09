package main.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import main.balducci.classes.GruppoClientiFactory;
import main.balducci.classes.RistoranteImpl;
import main.balducci.interfaces.GruppoClienti;
import main.balducci.interfaces.Ristorante;
import main.control.ModelListener;
import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Tavolo;

public class ModelImpl implements Model{
	
	private List<ModelListener> listeners = new ArrayList<>();
	private int durata;
	private Ristorante ristorante;
	private GruppoClientiFactory generatoreClienti;
	private int numClienti;
	private int numTavoli;
	private String menuPath;
	
	public ModelImpl(final int durata) {
		this.durata = durata;
	}
	
	public void setDurata(int durata) {
		this.durata = durata;
	}
	
	public void simula() {
		System.out.println("Simulazione: inizializzazione...");
		this.ristorante = new RistoranteImpl("Borgo", numTavoli, menuPath);
		this.generatoreClienti = new GruppoClientiFactory(numClienti);
		System.out.println("Simulazione: listener...");
		this.listeners.forEach(ModelListener::notificaSimulazioneAvviata);
		System.out.println("Simulazione: apriLocale...");
		this.ristorante.apriLocale();
		System.out.println("Simulazione: generaClienti...");
		this.generatoreClienti.generaClienti(ristorante);

		LocalDateTime tempoInizio = LocalDateTime.now();
		System.out.println("Simulazione: ciclo inizio, durata = " + durata + " minuti");
    	Iterator<GruppoClienti> it = this.generatoreClienti.getGruppi().iterator();
		while (it.hasNext()) {
			it.next().richiediTavolo(ristorante);
		}

		while (LocalDateTime.now().isBefore(tempoInizio.plusMinutes((long) durata))) {
			this.notificaStatoTavoloCambiato();
		}
	}

	@Override
	public boolean isLocaleAperto() {
		return this.ristorante.isAperto();
	}

	@Override
	public void aggiungiModelListener(ModelListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void rimuoviModelListener(ModelListener listener) {
		this.listeners.remove(this.listeners.indexOf(listener));
	}

	@Override
	public List<GruppoClienti> getGruppiInAttesa() {
		//this.ristorante.g
		throw new UnsupportedOperationException("Unimplemented method 'notificaTavoloOccupato'");
	}

	@Override
	public List<Tavolo> getTavoli() {
		return this.ristorante.getSala().getRanghi().stream()
													.flatMap(r -> r.getTavoli().stream())
													.collect(Collectors.toList());
	}

	@Override
	public Menu getMenu() {
		return this.ristorante.getMenu();
	}

	@Override
	public void notificaStatoTavoloCambiato() {
		this.listeners.forEach(ModelListener::notificaStatoTavoloCambiato);
	}

	@Override
	public void notificaNuovoOrdine() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'notificaNuovoOrdine'");
	}

	@Override
	public void notificaContoRichiesto() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'notificaContoRichiesto'");
	}

	@Override
	public void getOrdiniInCorso() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getOrdiniInCorso'");
	}

	@Override
	public void setNumClienti(int num) {
		this.numClienti = num;
	}

	@Override
	public void setNumeroTavoli(int numero) {
		this.numTavoli = numero;
	}

	@Override
	public void setDurataSimulazione(int durata) {
		this.durata = durata;
	}

	@Override
	public void setMenuPath(String path) {
		this.menuPath = path;
	}
}
