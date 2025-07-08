package main.view;

import java.time.LocalDate;
import java.util.List;

import main.control.Controller;
import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Tavolo;

public class ViewImpl implements SwingView{

	private Controller controller;
    private RistoranteFrame frame;
    private ViewObserver observer;

    public ViewImpl() {
        this.controller = null;
    }

    public void setObserver(ViewObserver observer) {
    	this.observer = observer;
    }

    public Controller getController() {
        return this.frame.getController();
    }

    public void setController(Controller controller) {
    	this.controller = controller;
    }

    public void mostraGUI() {
    	frame = new SupermercatoFrame(controller, this.observer);
		frame.setVisible(true);
    }
    
    public int getDurata() {
    	return frame.getDurataInserita();
    }

	@Override
	public void mostraMessaggio(String messaggio) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'mostraMessaggio'");
	}

	@Override
	public void aggiornaStatoTavolo(Tavolo tavolo) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'aggiornaStatoTavolo'");
	}

	@Override
	public void aggiornaMenu(Menu menu) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'aggiornaMenu'");
	}

	@Override
	public void aggiornaOrdine(Tavolo tavolo, Ordine ordine) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'aggiornaOrdine'");
	}

	@Override
	public void mostraConto(Tavolo tavolo, double importo) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'mostraConto'");
	}

	@Override
	public void setViewObserver(ViewObserver observer) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'setViewObserver'");
	}
}
