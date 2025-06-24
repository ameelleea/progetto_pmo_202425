package main.balducci.interfaces;

import java.time.LocalTime;
import java.util.List;

import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Prodotto;

public interface Dipendente {

    public void lavora();

    public LocalTime getOraInizioTurno();

    public LocalTime getOraFineTurno();

    public Double getPaga();

    public List<Prodotto> getOrdini();
}