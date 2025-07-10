package main.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import main.balducci.classes.GruppoClientiFactory;
import main.balducci.classes.RistoranteImpl;
import main.balducci.interfaces.Cassa;
import main.balducci.interfaces.GruppoClienti;
import main.balducci.interfaces.Ristorante;
import main.control.ModelListener;
import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Tavolo;

public class ModelImpl implements Model{
	
	private final int NUMERO_TAVOLI = 20;
	private List<ModelListener> listeners = new ArrayList<>();
	private int durata;
	private Ristorante ristorante;
	private GruppoClientiFactory generatoreClienti;
	private int numClienti;
	private String menuPath;
	private List<GruppoClienti> gruppiInAttesa;
	private List<GruppoClienti> gruppiSeduti;
	
	public ModelImpl(final int durata) {
		this.durata = durata;
		this.gruppiInAttesa = new LinkedList<>();
		this.gruppiSeduti = new LinkedList<>();
	}
	
	public void setDurata(int durata) {
		this.durata = durata;
	}
	
	public void simula() {
		System.out.println("Simulazione: inizializzazione...");
		this.ristorante = new RistoranteImpl("Borgo", NUMERO_TAVOLI, menuPath);
		this.generatoreClienti = new GruppoClientiFactory(numClienti);
		System.out.println("Simulazione: listener...");
		this.listeners.forEach(ModelListener::notificaSimulazioneAvviata);
		System.out.println("Simulazione: apriLocale...");
		this.ristorante.apriLocale();

		LocalDateTime tempoInizio = LocalDateTime.now();
		System.out.println("Simulazione: ciclo inizio, durata = " + durata + " minuti");

		while (LocalDateTime.now().isBefore(tempoInizio.plusMinutes((long) durata))) {
			if (generatoreClienti.getNumeroClienti() > 1) {
				GruppoClienti nuovoGruppo = generatoreClienti.creaGruppo(ristorante);
				gruppiInAttesa.add(nuovoGruppo);
				
				this.notificaGruppoInAttesa();
				nuovoGruppo.richiediTavolo(ristorante);
				// Aspetta finché non gli viene assegnato un tavolo
				try{
					Thread.sleep(Duration.ofSeconds(3).toMillis());
					synchronized (nuovoGruppo) {
					    while (nuovoGruppo.getTavolo() == null) {
					        nuovoGruppo.wait();
				    	}
					}
				}catch(InterruptedException e){
					e.printStackTrace();
				}

				this.notificaStatoTavoloCambiato();
				gruppiInAttesa.remove(nuovoGruppo);
				this.notificaGruppoInAttesa();
				gruppiSeduti.add(nuovoGruppo);
				new Thread(() -> {
					nuovoGruppo.cena();
					this.gruppiSeduti.remove(nuovoGruppo);
				}).start();
			}else{
				this.notificaGruppoInAttesa();
			}

			this.notificaOrdiniInAttesaCambiati();
			this.notificaRichiesteContoCambiate();
			this.notificaNuovoMessaggio();
			//try {
			//	Thread.sleep(10000); // attende 10 secondi
			//} catch (InterruptedException e) {
			//	System.err.println("Simulazione interrotta: " + e.getMessage());
			//	break;
			//}
		}

		this.fermaSimulazione();
		this.listeners.forEach(ModelListener::notificaSimulazioneTerminata);
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
	public void notificaGruppoInAttesa(){
		this.listeners.forEach(l -> l.notificaGruppiInAttesaCambiati(this.gruppiInAttesa));
	}

	@Override
	public void notificaStatoTavoloCambiato() {
		this.listeners.forEach(ModelListener::notificaStatoTavoloCambiato);
	}

	@Override
	public void notificaOrdiniInAttesaCambiati() {
		this.listeners.forEach(l -> l.notificaNuovoOrdine(this.ristorante.getCassa().getCodaOrdini()));
	}

	@Override
	public void notificaRichiesteContoCambiate() {
		List <GruppoClienti> richiedonoConto = this.gruppiSeduti.stream()
												.filter(g -> g.richiedeConto())
												.collect(Collectors.toList());
		this.listeners.forEach(l -> l.notificaRichiesteContoCambiate(richiedonoConto));
	}

	@Override
	public void setNumClienti(int num) {
		this.numClienti = num;
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
	public int getNumeroTavoli() {
		return this.NUMERO_TAVOLI;
	}

	@Override
	public void fermaSimulazione() {
		this.ristorante.chiudiLocale();
		this.notificaTotaliCambiati();
		this.gruppiInAttesa.clear();
		this.gruppiSeduti.clear();
	}

	@Override
	public void notificaNuovoMessaggio() {
		this.listeners.forEach(l -> l.notificaNuovoMessaggio(this.ristorante.getMessaggi()));
	}

	@Override
	public void notificaTotaliCambiati() {
		Cassa cassa = this.ristorante.getCassa();
		String totali = "Totale giornata: " + Double.valueOf(cassa.totaleGiornata()).toString() + "\n" 
		+ "Totali per dipendente: \n" + cassa.getTotalePerDipendente().entrySet()
				.stream()
				.map(e -> e.getKey().getIdDipendente() + ": " + e.getValue() + " euro\n")
            	.collect(Collectors.joining("\n"))
		+ "Totali per reparto: \n" + cassa.getTotalePerReparto().entrySet()
				.stream()
				.map(e -> e.getKey() + ": " + e.getValue() + " euro\n")
            	.collect(Collectors.joining("\n"));
		
		this.listeners.forEach(l -> l.notificaTotaliCambiati(totali));
	}
}
