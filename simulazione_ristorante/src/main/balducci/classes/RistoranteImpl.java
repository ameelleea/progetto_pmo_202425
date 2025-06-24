package main.balducci.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

import main.balducci.interfaces.Cassa;
import main.balducci.interfaces.GruppoClienti;
import main.balducci.interfaces.Reparto;
import main.balducci.interfaces.Ristorante;
import main.palazzetti.interfaces.Tavolo;

public class RistoranteImpl implements Ristorante {

    private final String nome;
    private final Cassa cassa;
    private final List<Reparto> reparti;
    private boolean isAperto;

    RistoranteImpl(String nome){
        this.nome = nome;
        this.cassa = new CassaImpl();
        this.reparti = new ArrayList<>();
        this.reparti.add(new RepartoImpl("CUCINA"));
        this.reparti.add(new RepartoImpl("BAR"));
        this.reparti.add(new RepartoImpl("PIZZERIA"));

        this.isAperto = true;
    }

    @Override
    public void apriLocale() {
        this.isAperto = true;
    }

    @Override
    public void chiudiLocale() {
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
    public Reparto getReparto(String nome) {
        return this.reparti.stream()
                            .filter(r -> r.getNome() == nome)
                            .findAny()
                            .get();
    }

    @Override
    public Tavolo accogliClienti(GruppoClienti gruppo) {
        Tavolo tav = this.cassa.getTavoliLiberi()
                            .stream()
                            .filter(t -> t.getNumPosti() >= gruppo.getNumeroPersone())
                            .findAny()
                            .get();
        tav.occupa(gruppo);
        return tav;
    }
}
