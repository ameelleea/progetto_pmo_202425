import main.control.*;
import main.view.*;
import main.model.*;
import main.balducci.interfaces.Ristorante;
import main.balducci.classes.RistoranteImpl;
import main.control.ControllerImpl;
import main.view.RistoranteFrame;

import java.awt.EventQueue;

import javax.swing.SwingUtilities;

public class MainApp {
    public static void main(String[] args) {
        		// Crea model, view e controller
        Model model = new ModelImpl(4);
        View view = new ViewImpl();
        ControllerImpl controller = new ControllerImpl(model, view);

        // Collega il controller alla view
        view.setController(controller);
        
        view.setObserver(controller);

        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Mostra la GUI
			        view.mostraGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

        SwingUtilities.invokeLater(() -> {
            Ristorante model = new RistoranteImpl("Ristorante MVC");
            RistoranteFrame view = new RistoranteFrame();
            new ControllerImpl(model, view);

            view.setVisible(true);
        });
    }
}
