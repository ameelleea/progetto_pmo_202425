package main.control;

import main.balducci.interfaces.GruppoClienti;
import main.model.Model;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Tavolo;
import main.view.View;

import java.util.List;

public class ControllerImpl implements Controller, ModelListener {

    private static final int DURATA_MASSIMA = 50; // durata massima della simulazione in numero di giorni
	private static final int DURATA_MINIMA = 1; // durata minima della simulazione in numero di giorni
	private Model model;
	private View view;
    private boolean simulazioneAttiva = false;
    private Thread executor;
    

    public ControllerImpl(Model model, View view) {
        this.model = model;
        this.view = view;
        this.model.aggiungiModelListener(this);
    }

    public void simula(){        
        if(this.simulazioneAttiva == false){
            this.simulazioneAttiva = true;
        executor = new Thread(() -> {
            try{
		    this.model.simula();
            }catch (Exception e) {
            e.printStackTrace();
            }
		});
        executor.start();
    }   
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public View getView() {
        return this.view;
    }

    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public Model getModel() {
        return this.model;
    }

    @Override
    public int getDurataMinimaSimulazione() {
        return DURATA_MINIMA;
    }

    @Override
    public int getDurataMassimaSimulazione() {
        return DURATA_MASSIMA;
    }

    @Override
    public int getNumeroTavoli(){
        return this.model.getNumeroTavoli();
    }
    @Override
    public void setNumeroClienti(int numero) {
        this.model.setNumClienti(numero);
    }

    @Override
    public void setDurataSimulazione(int durata) {
        this.model.setDurataSimulazione(durata);
    }

    @Override
    public void setMenuPath(String path) {
        this.model.setMenuPath(path);
    }

    @Override
    public List<Tavolo> getTavoli() {
        return this.model.getTavoli();
    }

    @Override
    public void notificaStatoTavoloCambiato() {
        this.view.aggiornaStatoTavoli();
    }

    @Override
    public void notificaNuovoOrdine(List<Ordine> ordini) {
        this.view.aggiornaOrdini(ordini);
    }

    @Override
    public void notificaRichiesteContoCambiate(List<GruppoClienti> richieste) {
        this.view.aggiornaRichiesteConto(richieste);
    }

    @Override
    public void notificaSimulazioneAvviata() {
        this.view.notificaSimulazioneAvviata();
    }

    @Override
    public void notificaGruppiInAttesaCambiati(List<GruppoClienti> gruppiInAttesa) {
        this.view.aggiornaGruppiInAttesa(gruppiInAttesa);
    }

    @Override
    public void simulazioneFermata() {
        if(this.simulazioneAttiva = true){
            this.model.fermaSimulazione();
            executor.interrupt();
            this.simulazioneAttiva = false;
        }
    }

    @Override
    public void notificaNuovoMessaggio(String messaggio) {
        this.view.mostraMessaggi(messaggio);
    }

    @Override
    public void notificaTotaliCambiati(String totali) {
        this.view.aggiornaTotali(totali);
    }

    @Override
    public void notificaSimulazioneTerminata() {
        this.view.notificaSimulazioneTerminata();
        this.simulazioneAttiva = false;
    }
}