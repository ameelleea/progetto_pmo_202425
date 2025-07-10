package main.control;

import java.util.List;

import main.model.Model;
import main.palazzetti.interfaces.Tavolo;
import main.view.View;

public interface Controller{

	void simula();
	void simulazioneFermata();	
	void setView(View view);
	View getView();
	void setModel(Model model);
	Model getModel();
	int getDurataMinimaSimulazione();	
	int getDurataMassimaSimulazione();	
	int getNumeroTavoli();
	void setNumeroClienti(int numero);
	void setDurataSimulazione(int durata);
	void setMenuPath(String path);	
	List<Tavolo> getTavoli();
}

