package controller;
import javax.swing.SwingUtilities;
import view.SingUpVista;

public class Controller {
    
    public static void start() {
        SwingUtilities.invokeLater(() -> new SingUpVista().GUI());
    }

}
