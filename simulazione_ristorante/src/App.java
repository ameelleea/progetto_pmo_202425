import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

import main.balducci.classes.GruppoClientiImpl;
import main.balducci.classes.RistoranteImpl;
import main.balducci.interfaces.GruppoClienti;
import main.balducci.interfaces.Ristorante;

public class App {
    public static void main(String[] args) throws Exception {
        Random rnd = new Random();

        Ristorante ristorante = new RistoranteImpl("Gamerland");
        ArrayList<GruppoClienti> gruppi = new ArrayList<>();

        ristorante.apriLocale();

        int durataTotale = (int) Duration.ofMinutes(2).toSeconds();
        int tick = 0;

        while (tick < durataTotale) {
            tick++;

            System.out.println("Tick " + tick);

            if (tick % 5 == 0) {
                int numClienti = rnd.nextInt(10) + 1;
                GruppoClienti nuovGruppoClienti = new GruppoClientiImpl(gruppi.size()+1, numClienti, ristorante);
                gruppi.add(nuovGruppoClienti);
                System.out.println("Creato gruppo con " + numClienti + " clienti.");
                nuovGruppoClienti.richiediTavolo(ristorante);
            }

            try {
                Thread.sleep(1000); // attende 1 secondo
            } catch (InterruptedException e) {
                System.out.println("Simulazione interrotta!");
                break;
            }
        }

        System.out.println("Simulazione terminata!");

        ristorante.chiudiLocale();
    }
}
