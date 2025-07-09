package main.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
		this.ristorante = new RistoranteImpl("Borgo", numTavoli, menuPath);
		this.generatoreClienti = new GruppoClientiFactory(numClienti);
		LocalDateTime tempoInizio = LocalDateTime.now();
		this.ristorante.apriLocale();
		this.generatoreClienti.generaClienti(ristorante);

		//while(LocalDateTime.now().isBefore(tempoInizio.plusMinutes((long) durata))) {
		//	
		//
		//}
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
	public void notificaTavoloOccupato() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'notificaTavoloOccupato'");
	}

	@Override
	public void notficaTavoloLibero() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'notficaTavoloLibero'");
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
