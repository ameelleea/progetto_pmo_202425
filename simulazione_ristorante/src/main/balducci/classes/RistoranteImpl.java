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

    private final String nome;
    private final Sala sala;
    private final Menu menu;
    private final Cassa cassa;
    private final List<Reparto> reparti;
    private Queue<GruppoClienti> gruppiInAttesa;
    private Maitre maitre;
    private volatile boolean isAperto;

    public RistoranteImpl(String nome, int numTavoli, String menuPath){
        this.nome = nome;
        this.reparti = new ArrayList<>();
        for(TipoReparto t : TipoReparto.values()){
            this.reparti.add(new RepartoImpl(t, this, t.getNumDipendenti()));
        }
        this.sala = new SalaImpl(numTavoli, 4, this);
        this.menu = MenuImpl.fromJson(menuPath);
        this.cassa = new CassaImpl(this.sala, this.reparti);
        this.maitre = new Maitre("Maitre", StipendiDipendenti.MAITRE.getPaga(), this);
        this.gruppiInAttesa = new LinkedList<>();
        this.isAperto = true;
    }

    @Override
    public void apriLocale() {
        System.out.println("Avvio maitre...");
        new Thread(() -> this.maitre.lavora()).start();

        System.out.println("Avvio camerieri...");
        this.sala.getRanghi().forEach(r -> {
            System.out.println(" -> Cameriere di rango " + r.getId());
            new Thread(() -> r.getCameriere().lavora()).start();
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
        System.out.println("Gruppo " + gruppo.getId() + "passato al maitre");
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
}
