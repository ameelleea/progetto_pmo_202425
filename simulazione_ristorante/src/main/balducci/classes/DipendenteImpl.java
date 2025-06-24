package main.balducci.classes;

import java.time.LocalTime;

import main.balducci.interfaces.*;

public abstract class DipendenteImpl extends Thread implements Dipendente {
        
    private LocalTime inizioTurno, fineTurno;
    private double pagaOraria;

    DipendenteImpl(LocalTime i, LocalTime f, double p){
        this.inizioTurno = i;
        this.fineTurno = f;
        this.pagaOraria = p;
    }

    @Override
    public void run(){
        while(true){
            this.lavora();
        }
    }
    
    @Override
    public LocalTime getOraInizioTurno() {
        
        return this.inizioTurno;
    }

    @Override
    public LocalTime getOraFineTurno() {
        
        return this.fineTurno;
    }

    @Override
    public Double getPaga() {
        
        return this.pagaOraria;
    }
}