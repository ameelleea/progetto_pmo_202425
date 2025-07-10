package main.view;

import java.util.List;

import main.balducci.interfaces.GruppoClienti;
import main.control.Controller;
import main.palazzetti.interfaces.Ordine;

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
	public void aggiornaStatoTavoli() {
		this.frame.aggiornaTavoli();
	}

	@Override
	public void aggiornaOrdini(List<Ordine> ordini) {
		this.frame.printListaOrdini(ordini);
	}

	@Override
	public void aggiornaRichiesteConto(List<GruppoClienti> richieste) {
		this.frame.printRichiesteConto(richieste);
	}

	@Override
	public void simula(){
        controller.setNumeroClienti(frame.getNumClienti());
        controller.setDurataSimulazione(frame.getDurataInserita());
        controller.setMenuPath(frame.getPathInserito());
		controller.simula();
	}

	@Override
	public void notificaSimulazioneAvviata() {
		frame.inizializzaTavoli();
	}

	@Override
	public void notificaSimulazioneFermata() {
		this.controller.simulazioneFermata();
	}

	@Override
	public void aggiornaGruppiInAttesa(List<GruppoClienti> gruppiInAttesa) {
		this.frame.printGruppiInAttesa(gruppiInAttesa);
	}
}
