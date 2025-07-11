package main.balducci.classes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import main.balducci.interfaces.Cassa;
import main.balducci.interfaces.GruppoClienti;
import main.balducci.interfaces.Reparto;
import main.balducci.interfaces.Ristorante;
import main.palazzetti.classes.MenuImpl;
import main.palazzetti.classes.SalaImpl;
import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Sala;

public class RistoranteImpl implements Ristorante {

    public static volatile Ristorante instance;
    private final String nome;
    private final Sala sala;
    private final Menu menu;
    private final Cassa cassa;
    private final List<Reparto> reparti;
    private Queue<GruppoClienti> gruppiInAttesa;
    private Maitre maitre;
    private Thread maitreThread;
    private List<Thread> camerieriThreads;
    private volatile boolean isAperto;
    private String messaggi;

    private RistoranteImpl(String nome, int numTavoli, String menuPath) {
        this.nome = nome;
        this.reparti = new ArrayList<>();
        for(TipoReparto t : TipoReparto.values()){
            this.reparti.add(new RepartoImpl(t, this, t.getNumDipendenti()));
        }
        this.sala = SalaImpl.getInstance(numTavoli, 4, this);
        this.menu = MenuImpl.fromJson(menuPath);
        this.cassa = CassaImpl.getInstance(this.sala, this.reparti);
        this.maitre = new Maitre("Maitre", StipendiDipendenti.MAITRE.getPaga(), this);
        this.camerieriThreads = new ArrayList<>();
        this.gruppiInAttesa = new LinkedList<>();
        this.isAperto = true;
        this.messaggi = "";
    }

    public static Ristorante getInstance(String nome, int numTavoli, String menuPath) {
      if (instance == null) {
        synchronized(Ristorante.class) {
          if (instance == null) {
            instance = new RistoranteImpl(nome, numTavoli, menuPath);
          }
        }
      }
      return instance;
    }

    public static void resetInstance(){
        instance = null;
    }

    @Override
    public void apriLocale() {
        System.out.println("Avvio maitre...");
        maitreThread = new Thread(() -> this.maitre.lavora());
        maitreThread.start();

        System.out.println("Avvio camerieri...");
        this.sala.getRanghi().forEach(r -> {
            System.out.println(" -> Cameriere di rango " + r.getId());
            Thread ct = new Thread(() -> {
                try{
                    r.getCameriere().lavora();
                }catch(InterruptedException e){
                    System.out.println(r.getCameriere().getIdDipendente() + " fermato");
                }});
            ct.start();
            this.camerieriThreads.add(ct);
        });

        System.out.println("Avvio reparti...");
        this.reparti.forEach(r -> r.apriReparto());

        this.isAperto = true;
        System.out.println("Locale aperto.");
    }

    @Override
    public void chiudiLocale() {
        this.reparti.forEach(Reparto::chiudiReparto);
        this.isAperto = false;
        this.maitreThread.interrupt();
        this.camerieriThreads.forEach(Thread::interrupt);
        this.cassa.calcolaTotalePerDipendente();
        this.cassa.calcolaTotalePerReparto();
        CassaImpl.resetInstance();
        SalaImpl.resetInstance();
        RistoranteImpl.resetInstance();
    }

    @Override
    public boolean isAperto() {
        return this.isAperto;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public Reparto getReparto(TipoReparto tipo) {
        return this.reparti.stream()
                        .filter(r -> r.getTipoReparto() == tipo)
                        .findAny()
                        .get();
    }

    @Override
    public void accogliClienti(GruppoClienti gruppo) {
        System.out.println(gruppo.getId() + "passato al maitre");
        this.gruppiInAttesa.add(gruppo);
        this.maitre.nuovoGruppo();
    }

    @Override
    public Menu getMenu() {
        return this.menu;
    }

    @Override
    public Cassa getCassa() {
        return this.cassa;
    }

    @Override
    public Sala getSala() {
        return this.sala;
    }

    @Override
    public GruppoClienti getProssimoGruppo() {
        return this.gruppiInAttesa.poll();
    }

    @Override
    public String getMessaggi() {
        return this.messaggi;
    }

    @Override
    public void addNuovoMessaggio(String messaggio){
        this.messaggi += messaggio + "\n\n";
    }
}
