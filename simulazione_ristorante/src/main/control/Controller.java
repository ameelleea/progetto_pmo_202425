package main.control;

import java.util.List;

import main.model.Model;
import main.palazzetti.interfaces.Tavolo;
import main.view.View;

public interface Controller{

	void simula();

	void setView(View view);
	
	View getView();
	
	void setModel(Model model);
	
	Model getModel();
		
	int getDurataSimulazione();
	
	int getDurataMinimaSimulazione();

	int getDurataMassimaSimulazione();

	int getNumeroClienti();
	
	int getNumeroTavoli();
	
	void setNumeroTavoli(int numero);
    
	void setNumeroClienti(int numero);
    
	void setDurataSimulazione(int durata);
    
	void setMenuPath(String path);
	
	String getMenuPath();

	List<Tavolo> getTavoli();
}

