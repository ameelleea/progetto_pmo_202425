package main.control;

import main.balducci.interfaces.GruppoClienti;
import main.model.Model;
import main.palazzetti.interfaces.Ordine;
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
    private int numeroDip;

    public ControllerImpl(Model model, View view) {
        this.model = model;
        this.view = view;
        this.gruppiInAttesa = new ArrayList<>();
    }

    public void simula(){

    }

    @Override
    public void onTavoloAssegnato(GruppoClienti gruppo, Tavolo tavolo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onTavoloAssegnato'");
    }

    @Override
    public void onOrdineInviato(Ordine ordine) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onOrdineInviato'");
    }

    @Override
    public void onOrdineServito(Tavolo tavolo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onOrdineServito'");
    }

    @Override
    public void onContoRichiesto(Tavolo tavolo, double importo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onContoRichiesto'");
    }

    @Override
    public void onLocaleAperto() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onLocaleAperto'");
    }

    @Override
    public void onLocaleChiuso() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onLocaleChiuso'");
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
    public void setNumeroDipendenti(int numero) {
        this.numeroDip = numero;
    }

    @Override
    public void setNumeroClienti(int numero) {
        this.numClienti = numero;
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