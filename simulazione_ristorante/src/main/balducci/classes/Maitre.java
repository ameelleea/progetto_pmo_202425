package main.balducci.classes;

import main.balducci.interfaces.Cassa;
import main.balducci.interfaces.Ristorante;
import main.palazzetti.interfaces.Sala;

public class Maitre extends DipendenteImpl{
    private String id;
    private String nome;
    private double stipendioOra;
    private Sala sala;
    private Cassa cassa;
    private Ristorante ristorante;

    public Maitre(String id, String nome, double stipendioOra, Sala sala, Cassa cassa, Ristorante ristorante){
        super();    
    }

    public void run():
    /*Ciclo continuo:
    Controlla se ci sono gruppi in attesa (ristorante.getProssimoGruppoInAttesa()).
    Accede alla Cassa per vedere i tavoli liberi (cassa.getTavoliLiberi()).
    Trova un tavolo adatto per il gruppo.
    Se trova un tavolo:
    Assegna il tavolo al gruppo (gruppo.setTavoloAssegnato(tavolo)).
    Notifica la Cassa che il tavolo è occupato (cassa.occupaTavolo(tavolo)).
    Se non trova un tavolo, il gruppo rimane in attesa.*/
}
