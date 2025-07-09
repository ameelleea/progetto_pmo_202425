import main.control.*;
import main.view.*;
import main.model.*;
import main.control.ControllerImpl;

import java.awt.EventQueue;

public class MainApp {
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        Model model = new ModelImpl(4);
        View view = new ViewImpl();
        Controller controller = new ControllerImpl(model, view);
        view.setController(controller);

        EventQueue.invokeLater(() -> view.mostraGUI());
    }
}
