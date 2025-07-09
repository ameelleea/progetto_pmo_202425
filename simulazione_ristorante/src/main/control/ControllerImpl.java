package main.control;

import main.balducci.interfaces.GruppoClienti;
import main.model.Model;
import main.palazzetti.interfaces.Tavolo;
import main.view.View;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller, ModelListener {

    private static final int DURATA_MASSIMA = 50; // durata massima della simulazione in numero di giorni
	private static final int DURATA_MINIMA = 1; // durata minima della simulazione in numero di giorni
	private Model model;
	private View view;
	private int durata;
    private final List<GruppoClienti> gruppiInAttesa;
    private String menuPath;
    private int numClienti;
    private int numeroTavoli;

    public ControllerImpl(Model model, View view) {
        this.model = model;
        this.view = view;
        this.gruppiInAttesa = new ArrayList<>();
    }

    public void simula(){
        this.model.setMenuPath(menuPath);
        this.model.setNumClienti(numClienti);
        this.model.setNumeroTavoli(numeroTavoli);
        this.model.setDurataSimulazione(durata);
        this.model.simula();
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
    public int getDurataSimulazione() {
        return this.durata;
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
    public void setNumeroTavoli(int numero) {
        this.numeroTavoli = numero;
    }

    @Override
    public void setNumeroClienti(int numero) {
        this.numClienti = numero;
        this.model.setNumClienti(numero);
    }

    @Override
    public void setDurataSimulazione(int durata) {
        this.durata = durata;
    }

    @Override
    public void setMenuPath(String path) {
        this.menuPath = path;
    }

    @Override
    public String getMenuPath() {
        return this.menuPath;
    }

    @Override
    public List<Tavolo> getTavoli() {
        return this.model.getTavoli();
    }

    @Override
    public int getNumeroClienti() {
        return this.numClienti;
    }

    @Override
    public int getNumeroTavoli() {
        return this.numeroTavoli;
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
}