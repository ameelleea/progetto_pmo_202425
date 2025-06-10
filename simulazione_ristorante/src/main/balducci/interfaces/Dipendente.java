package main.balducci.interfaces;

import java.time.LocalTime;

public interface Dipendente {

    public void lavora();

    public LocalTime getOraInizioTurno();

    public LocalTime getOraFineTurno();

    public Double getPaga();
}