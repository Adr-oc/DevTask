import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FormularioProyecto {

    private String nombreProyecto;
    private String lenguajes;
    private String descripcion;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormularioProyecto().GUI());
    }

    private void GUI() {

        JFrame frame = new JFrame("Formulario de Proyecto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setSize(1024, 769);
        frame.getContentPane().setBackground(Color.decode("#272830"));

        GridBagConstraints restricciones = new GridBagConstraints();
        restricciones.weightx = 0.5;
        restricciones.weighty = 0.3;
        restricciones.gridx = 0;
        restricciones.gridy = 0;
        restricciones.insets = new Insets(5, 30, 5, 30);
        restricciones.anchor = GridBagConstraints.WEST;

        JLabel tituloFormulario = new JLabel("Formulario para Nuevo Proyecto");
        tituloFormulario.setForeground(Color.decode("#a8e65f"));
        tituloFormulario.setFont(new Font("Consolas", Font.BOLD, 19));

        frame.add(tituloFormulario, restricciones);

        restricciones.gridy = 1;

        JLabel tituloNombre = new JLabel("Nombre del Proyecto:");
        tituloNombre.setForeground(Color.decode("#a8e65f"));
        tituloNombre.setFont(new Font("Consolas", Font.PLAIN, 17));
        frame.add(tituloNombre, restricciones);

        JTextField nombreTexto = new JTextField();
        nombreTexto.setForeground(Color.decode("#a8e65f"));
        nombreTexto.setBackground(Color.decode("#272830"));
        nombreTexto.setFont(new Font("Consolas", Font.PLAIN, 17));
        nombreTexto.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode("#a8e65f")));
        restricciones.fill = GridBagConstraints.HORIZONTAL;
        restricciones.gridy = 2;
        frame.add(nombreTexto, restricciones);

        restricciones.gridx = 1;
        restricciones.gridy = 1;

        JLabel tituloLenguajes = new JLabel("Lenguajes Preferidos (Java, C++, etc):");
        tituloLenguajes.setForeground(Color.decode("#a8e65f"));
        tituloLenguajes.setFont(new Font("Consolas", Font.PLAIN, 17));
        frame.add(tituloLenguajes, restricciones);

        JTextField lenguajesTexto = new JTextField();
        lenguajesTexto.setForeground(Color.decode("#a8e65f"));
        lenguajesTexto.setBackground(Color.decode("#272830"));
        lenguajesTexto.setFont(new Font("Consolas", Font.PLAIN, 17));
        lenguajesTexto.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode("#a8e65f")));
        restricciones.gridy = 2;
        frame.add(lenguajesTexto, restricciones);

        restricciones.gridx = 0;
        restricciones.gridy = 3;
        restricciones.gridwidth = 2;

        JLabel tituloDescripcion = new JLabel("Descripción del Proyecto:");
        tituloDescripcion.setForeground(Color.decode("#a8e65f"));
        tituloDescripcion.setFont(new Font("Consolas", Font.PLAIN, 17));
        frame.add(tituloDescripcion, restricciones);

        JTextArea descripcionTexto = new JTextArea(10, 20);
        descripcionTexto.setForeground(Color.decode("#a8e65f"));
        descripcionTexto.setBackground(Color.decode("#272830"));
        descripcionTexto.setFont(new Font("Consolas", Font.PLAIN, 17));
        descripcionTexto.setLineWrap(true);
        descripcionTexto.setWrapStyleWord(true);
        descripcionTexto.setBorder(new RoundedBorder(25));
        restricciones.gridy = 4;
        restricciones.gridheight = 4;
        frame.add(descripcionTexto, restricciones);

        JButton botonEnviar = new JButton("Enviar");
        botonEnviar.setForeground(Color.decode("#a8e65f"));
        botonEnviar.setBackground(Color.decode("#272830"));
        botonEnviar.setFont(new Font("Consolas", Font.PLAIN, 17));
        botonEnviar.setPreferredSize(new Dimension(200, 50));
        botonEnviar.setMaximumSize(new Dimension(200, 50));
        botonEnviar.setBorder(new RoundedBorder(25));
        restricciones.fill = GridBagConstraints.NONE;
        restricciones.gridy = 8;
        restricciones.gridheight = 1;
        restricciones.anchor = GridBagConstraints.CENTER;
        restricciones.insets = new Insets(10, 10, 10, 10);
        frame.add(botonEnviar, restricciones);

        botonEnviar.addActionListener(e -> {
            nombreProyecto = nombreTexto.getText();
            lenguajes = lenguajesTexto.getText();
            descripcion = descripcionTexto.getText();
            String nuevoProyecto = nombreProyecto + ";" + lenguajes + ";" + descripcion;
            adjuntarArchivo(nuevoProyecto);
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    private void adjuntarArchivo(String proyecto) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("src/proyectosFormulario.csv", true));

            writer.print("\n" + proyecto);

            writer.close();
            System.out.println("El nuevo proyecto se ha agregado al archivo proyectosFormulario.csv.");
        } catch (IOException e) {
            System.out.println("Error al agregar el nuevo proyecto al archivo.");
        }
    }

    static class RoundedBorder implements Border {

        private final int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

    }

}