package main.model;

import java.time.LocalDate;
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
	
	public ModelImpl(final int durata) {
		this.durata = durata;
		this.ristorante = new RistoranteImpl(null);
		this.generatoreClienti = new GruppoClientiFactory();
	}
	
	public void setDurata(int durata) {
		this.durata = durata;
	}
	
	public void simula() {
		LocalDateTime tempoInizio = LocalDateTime.now();
		while(LocalDateTime.now().isBefore(tempoInizio.plusMinutes((long) durata))) {
			this.ristorante.apriLocale();
			this.generatoreClienti.generaClienti(ristorante);
		}
	}

	@Override
	public void apriLocale() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'apriLocale'");
	}

	@Override
	public void chiudiLocale() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'chiudiLocale'");
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
		this.ristorante.g
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
	public void getOrdiniInCors() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getOrdiniInCors'");
	}
}
