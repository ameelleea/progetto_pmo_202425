package main.control;

import main.model.Model;
import main.view.View;

public interface Controller{

	void setView(View view);
	
	View getView();
	
	void setModel(Model model);
	
	Model getModel();
		
	int getDurataSimulazione();
	
	int getDurataMinimaSimulazione();

	int getDurataMassimaSimulazione();
	
	void setNumeroTavoli(int numero);
    
	void setNumeroDipendenti(int numero);
    
	void setNumeroClienti(int numero);
    
	void setDurataSimulazione(int durata);
    
	void setMenuPath(String path);
	
	String getMenuPath();
}

