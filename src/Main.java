//version 1.0
import model.UserManager;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import view.FormularioProyecto;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormularioProyecto().GUI());

    }
}