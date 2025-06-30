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

        long tempo = Duration.ofMinutes(5).toMillis();

        while(tempo > 0){
            int numClienti = rnd.nextInt(10) + 1;
            GruppoClienti nuovGruppoClienti = new GruppoClientiImpl(gruppi.size()+1, numClienti, ristorante);
            nuovGruppoClienti.richiediTavolo(ristorante);
        }

        ristorante.chiudiLocale();
    }
}
