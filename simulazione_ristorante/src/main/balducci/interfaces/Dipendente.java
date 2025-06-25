package main.balducci.interfaces;

import java.time.LocalTime;
import java.util.List;

import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Prodotto;
/*10. Dipendente (Interfaccia)
Definita sopra.




13. Cameriere (Estende Lavoratore o Dipendente, implementa Runnable)

: Costruttore.
*/
public interface Dipendente {

    public void lavora();

    public LocalTime getOraInizioTurno();

    public LocalTime getOraFineTurno();

    public Double getPaga();

    public List<Prodotto> getOrdini();

    public boolean isDisponibile();
    public void setOrdineCorrente(Ordine ordine); //Assegna un ordine.

    /*Continua a prelevare ordini dalla coda del Reparto.
    Quando preleva un ordine, lo contrassegna come "in preparazione" e simula il tempo di preparazione.
    Al termine, notifica l'ordine che il prodotto è pronto e si rende nuovamente disponibile.*/
    public void run();

}