package view;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ProyectView {


    public void GUI() {

        JFrame frame = new JFrame("Formulario de Proyecto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setSize(1024, 620);
        frame.getContentPane().setBackground(Color.decode(Colors.bgColor));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}