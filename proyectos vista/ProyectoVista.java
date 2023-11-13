import javax.swing.*;
import java.awt.*;

public class ProyectoVista {

    String projectName = "sopas";
    String projectDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum";

    String author = "yo mismo";
    String language = "JAVA";

    public static void GUI(String projectName, String projectDescription, String author, String language) {
        Font titleFont = new Font("Consolas", Font.BOLD, 30);
        Font subtitleFont = new Font("Consolas", Font.PLAIN, 18);

        JFrame frame = new JFrame("Detalles del Proyecto");
        frame.getContentPane().setBackground(Color.decode(Colors.bgColor));
        frame.setLayout(new BorderLayout());

        // Panel para contener los componentes principales
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.decode(Colors.bgColor));
        mainPanel.setLayout(new GridLayout(5, 2));

        JLabel titleLabel = new JLabel("Nombre del Proyecto: " + projectName);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.decode(Colors.Primary));

        JLabel authorLabel = new JLabel("Autor: " + author);
        authorLabel.setFont(subtitleFont);
        authorLabel.setForeground(Color.decode(Colors.TextColor));

        JLabel langLabel = new JLabel("Lenguajes: " + language);
        langLabel.setFont(subtitleFont);
        langLabel.setForeground(Color.decode(Colors.TextColor));

        JLabel descLabel = new JLabel("Descripción del Proyecto:");
        descLabel.setFont(subtitleFont);
        descLabel.setForeground(Color.decode(Colors.TextColor));

        JTextArea descTextArea = new JTextArea(projectDescription);
        descTextArea.setBackground(Color.decode(Colors.bgCard));
        descTextArea.setForeground(Color.decode(Colors.TextColor));
        descTextArea.setLineWrap(true);
        descTextArea.setWrapStyleWord(true);
        descTextArea.setEditable(false);

        mainPanel.add(titleLabel);
        mainPanel.add(new JLabel());
        mainPanel.add(authorLabel);
        mainPanel.add(langLabel);
        mainPanel.add(descLabel);
        mainPanel.add(new JLabel());
        mainPanel.add(new JScrollPane(descTextArea));

        frame.add(mainPanel, BorderLayout.CENTER);

        // Botones en la esquina superior derecha
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.decode(Colors.bgColor));

        JButton regresarButton = new JButton("Regresar");
        regresarButton.setFont(subtitleFont);
        regresarButton.setBackground(Color.decode(Colors.Secondary));
        regresarButton.setForeground(Color.decode(Colors.TextColor));
        buttonPanel.add(regresarButton);

        frame.add(buttonPanel, BorderLayout.NORTH);

        // Botón "Integrarme al proyecto" en la esquina inferior derecha
        JButton integrarmeButton = new JButton("Integrarme al proyecto");
        integrarmeButton.setFont(subtitleFont);
        integrarmeButton.setBackground(Color.decode(Colors.Secondary));
        integrarmeButton.setForeground(Color.decode(Colors.TextColor));
        frame.add(integrarmeButton, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        ProyectoVista proyecto = new ProyectoVista();
        GUI(proyecto.projectName, proyecto.projectDescription, proyecto.author, proyecto.language);
    }
}
