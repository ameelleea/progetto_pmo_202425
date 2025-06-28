package main.balducci.classes;

import java.util.ArrayList;
import java.util.List;

import main.balducci.interfaces.Cassa;
import main.balducci.interfaces.GruppoClienti;
import main.balducci.interfaces.Reparto;
import main.balducci.interfaces.Ristorante;
import main.balducci.interfaces.Reparto.TipoReparto;
import main.palazzetti.classes.MenuImpl;
import main.palazzetti.classes.SalaImpl;
import main.palazzetti.interfaces.Menu;
import main.palazzetti.interfaces.Sala;
import main.palazzetti.interfaces.Tavolo;

public class RistoranteImpl implements Ristorante {

    private final String nome;
    private final Sala sala;
    private final Menu menu;
    private final Cassa cassa;
    private final List<Reparto> reparti;
    private List<GruppoClienti> gruppiInAttesa;
    private Maitre maitre;
    private boolean isAperto;

    public RistoranteImpl(String nome){
        this.nome = nome;
        this.reparti = new ArrayList<>();
        for(TipoReparto t : TipoReparto.values()){
            this.reparti.add(new RepartoImpl(t));
        }
        this.sala = new SalaImpl();
        this.menu = new MenuImpl();
        this.cassa = new CassaImpl(this.sala, this.menu, this.reparti);

        this.isAperto = true;
    }

    @Override
    public void apriLocale() {
        this.reparti.forEach(r -> r.avviaLavoratori());

        this.sala.getRanghi().forEach(r -> {
            r.getCameriere().start();
        });

        this.maitre.start();

        this.isAperto = true;
    }

    @Override
    public void chiudiLocale() {
        this.reparti.forEach(r -> {
            r.getDipendenti().forEach(d -> {
                d.interrupt();
            });
        });

        this.sala.getRanghi().forEach(r -> {
            r.getCameriere().interrupt();
        });

        this.maitre.interrupt();

        this.isAperto = true;
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
        return this.gruppiInAttesa.remove(0);
    }
}
