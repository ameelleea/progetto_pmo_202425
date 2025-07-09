package main.view;

import main.control.Controller;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Tavolo;

public class ViewImpl implements SwingView{

	private Controller controller;
    //private RistoranteFrame frame;
	private RistFrame frame;

    public ViewImpl() {
        this.controller = null;
    }
	
	@Override
    public Controller getController() {
        return this.controller;
    }
	
	@Override
    public void setController(Controller controller) {
    	this.controller = controller;
    }
	
	@Override
    public void mostraGUI() {
    	//frame = new RistoranteFrame(controller, this.observer);
		frame = new RistFrame(this.controller, this);
		frame.setVisible(true);
    }

    @Override
    public int getDurata() {
    	//return frame.getDurataInserita();
		throw new UnsupportedOperationException("Unimplemented method 'mostraMessaggio'");
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
	public void notificaSimulazioneAvviata() {
        controller.setNumeroTavoli(frame.getNumTavoli());
        controller.setNumeroClienti(frame.getNumClienti());
        controller.setDurataSimulazione(frame.getDurataInserita());
        controller.setMenuPath(frame.getPathInserito());
		controller.simula();
		frame.inizializzaTavoli();

	}

	@Override
	public void notificaSimulazioneFermata() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'notificaSimulazioneFermata'");
	}
}
