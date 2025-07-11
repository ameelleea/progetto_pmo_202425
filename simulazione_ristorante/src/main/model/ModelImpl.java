package main.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import main.balducci.classes.GruppoClientiFactory;
import main.balducci.classes.RistoranteImpl;
import main.balducci.classes.TipoReparto;
import main.balducci.interfaces.Cassa;
import main.balducci.interfaces.Dipendente;
import main.balducci.interfaces.GruppoClienti;
import main.balducci.interfaces.Ristorante;
import main.control.ModelListener;
import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Tavolo;
import main.palazzetti.interfaces.Tavolo.StatoTavolo;

public class ModelImpl implements Model{
	
	private final int NUMERO_TAVOLI = 20;
	private List<ModelListener> listeners = new ArrayList<>();
	private int durata;
	private Ristorante ristorante;
	private GruppoClientiFactory generatoreClienti;
	private int numClienti;
	private String menuPath;
	private List<Thread> threadGruppi;
	private List<GruppoClienti> gruppiInAttesa;
	private List<GruppoClienti> gruppiSeduti;
	private ScheduledExecutorService scheduler;
	
	public ModelImpl(final int durata) {
		this.durata = durata;
		this.gruppiInAttesa = new LinkedList<>();
		this.gruppiSeduti = new LinkedList<>();
		this.threadGruppi = new ArrayList<>();
	}
	
	public void setDurata(int durata) {
		this.durata = durata;
	}
	
	public void simula() {
    	System.out.println("Simulazione: inizializzazione...");
    	this.ristorante = RistoranteImpl.getInstance("Borgo", NUMERO_TAVOLI, menuPath);
    	this.generatoreClienti = new GruppoClientiFactory(numClienti);
    	this.listeners.forEach(ModelListener::notificaSimulazioneAvviata);
    	this.ristorante.apriLocale();

    	LocalDateTime tempoInizio = LocalDateTime.now();
    	System.out.println("Simulazione: ciclo inizio, durata = " + durata + " minuti");

    	// Esegui ogni 3 secondi la creazione di un nuovo gruppo
		this.scheduler = Executors.newSingleThreadScheduledExecutor();
    	this.scheduler.scheduleAtFixedRate(() -> {
        if (generatoreClienti.getNumeroClienti() > 1 &&
            LocalDateTime.now().isBefore(tempoInizio.plusMinutes(durata))) {

            GruppoClienti nuovoGruppo = generatoreClienti.creaGruppo(ristorante);
            gruppiInAttesa.add(nuovoGruppo);
            notificaGruppoInAttesa();

            Thread ngThread = new Thread(() -> {
				//Simula attesa
				try{
					Thread.sleep(3000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
                nuovoGruppo.richiediTavolo(ristorante);

                // Aspetta finché non ha un tavolo
                synchronized (nuovoGruppo) {
                    while (nuovoGruppo.getTavolo() == null) {
                        try {
                            nuovoGruppo.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                notificaStatoTavoloCambiato();
                gruppiInAttesa.remove(nuovoGruppo);
                notificaGruppoInAttesa();

                gruppiSeduti.add(nuovoGruppo);
                    nuovoGruppo.cena();
					nuovoGruppo.richiedeConto();
					this.notificaRichiesteContoCambiate();
                    gruppiSeduti.remove(nuovoGruppo);
                    notificaStatoTavoloCambiato();
            });

			ngThread.start();
			this.threadGruppi.add(ngThread);
        }
    }, 0, 3, TimeUnit.SECONDS);

    // Ciclo principale che notifica ogni mezzo secondo lo stato della simulazione
    while (LocalDateTime.now().isBefore(tempoInizio.plusMinutes(durata))) {
        notificaOrdiniInAttesaCambiati();
        notificaRichiesteContoCambiate();
        notificaNuovoMessaggio();
        notificaTotaliCambiati();
		notificaStatoTavoloCambiato();

        try {
            Thread.sleep(500); // controllo frequente
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
	List<GruppoClienti> copiaGruppi = new ArrayList<>(this.gruppiSeduti);
	List<GruppoClienti> richiedonoConto = copiaGruppi.stream()
	    .filter(g -> g.getTavolo().getStatoTavolo() == StatoTavolo.RICHIESTA_CONTO)
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
		scheduler.shutdownNow(); // Ferma la generazione dei gruppi
		this.ristorante.chiudiLocale();
		this.notificaTotaliCambiati();
		this.gruppiInAttesa.clear();
		this.gruppiSeduti.clear();
		this.threadGruppi.forEach(Thread::interrupt);
	}

	@Override
	public void notificaNuovoMessaggio() {
		this.listeners.forEach(l -> l.notificaNuovoMessaggio(this.ristorante.getMessaggi()));
	}

	@Override
	public void notificaTotaliCambiati() {
		Cassa cassa = this.ristorante.getCassa();
		Map<Dipendente, Double> totaliDip = new HashMap<>(cassa.getTotalePerDipendente());
		Map<TipoReparto, Double> totaliReparti = new HashMap<>(cassa.getTotalePerReparto());

		String totali = "Totale giornata: " + cassa.totaleGiornata() + "\n" 
		    + "Totali per dipendente: \n" + totaliDip.entrySet()
		        .stream()
		        .map(e -> e.getKey().getIdDipendente() + ": " + String.format("%.2f", e.getValue()) + " euro")
		        .collect(Collectors.joining("\n")) + "\n"
		    + "Totali per reparto: \n" + totaliReparti.entrySet()
		        .stream()
		        .map(e -> e.getKey() + ": " + String.format("%.2f", e.getValue()) + " euro")
		        .collect(Collectors.joining("\n"));

		
		this.listeners.forEach(l -> l.notificaTotaliCambiati(totali));
	}
}
