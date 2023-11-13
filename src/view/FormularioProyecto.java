package view;
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
    private String Green = "#a8e65f";
    private String Red = "#ff0000";
    private String Gray = "#CCCCCC";
    private String bgColor = "#191A23";
    private boolean nombreBol, lenguajeBol, descrBol = true;


    private void setFocusListeners(JTextField nombreTexto, JLabel tituloNombre) {
        nombreTexto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nombreTexto.setBorder(new MatteBorder(0, 0, 2, 0, Color.decode(Green)));
                nombreTexto.setForeground(Color.decode(Green));
                tituloNombre.setForeground(Color.decode(Green));
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                nombreTexto.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Gray)));
                nombreTexto.setForeground(Color.decode(Gray));
                tituloNombre.setForeground(Color.decode(Gray));
            }
        });
    }

    public void GUI() {

        JFrame frame = new JFrame("Formulario de Proyecto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setSize(1024, 620);
        frame.getContentPane().setBackground(Color.decode(bgColor));

        GridBagConstraints restricciones = new GridBagConstraints();
        restricciones.weightx = 0.5;
        restricciones.weighty = 0.3;
        restricciones.gridx = 0;
        restricciones.gridy = 0;
        restricciones.insets = new Insets(5, 50, 5, 50);
        restricciones.anchor = GridBagConstraints.WEST;
        JLabel tituloFormulario = new JLabel("Formulario para Nuevo Proyecto");
        tituloFormulario.setForeground(Color.decode("#8B85C1"));
        tituloFormulario.setFont(new Font("Consolas", Font.BOLD, 28));

        frame.add(tituloFormulario, restricciones);
        restricciones.gridy = 1;

        JLabel tituloNombre = new JLabel("Nombre del Proyecto:");
        tituloNombre.setForeground(Color.decode(Gray));
        tituloNombre.setFont(new Font("Consolas", Font.PLAIN, 17));
        frame.add(tituloNombre, restricciones);

        JTextField nombreTexto = new JTextField();
        nombreTexto.setForeground(Color.decode(Gray));
        nombreTexto.setBackground(Color.decode(bgColor));
        nombreTexto.setFont(new Font("Consolas", Font.PLAIN, 17));
        nombreTexto.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Gray)));
        setFocusListeners(nombreTexto, tituloNombre);
        restricciones.fill = GridBagConstraints.HORIZONTAL;
        restricciones.gridy = 2;
        frame.add(nombreTexto, restricciones);

        restricciones.gridx = 1;
        restricciones.gridy = 1;

        JLabel tituloLenguajes = new JLabel("Lenguajes Preferidos (Java, C++, etc):");
        tituloLenguajes.setForeground(Color.decode(Gray));
        tituloLenguajes.setFont(new Font("Consolas", Font.PLAIN, 17));
        frame.add(tituloLenguajes, restricciones);

        JTextField lenguajesTexto = new JTextField();
        lenguajesTexto.setForeground(Color.decode(Gray));
        lenguajesTexto.setBackground(Color.decode(bgColor));
        lenguajesTexto.setFont(new Font("Consolas", Font.PLAIN, 17));
        lenguajesTexto.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Gray)));
        restricciones.gridy = 2;
        setFocusListeners(lenguajesTexto, tituloLenguajes);
        frame.add(lenguajesTexto, restricciones);

        restricciones.gridx = 0;
        restricciones.gridy = 3;
        restricciones.gridwidth = 2;

        //titulo descripcion
        JLabel tituloDescripcion = new JLabel("Descripción del Proyecto:");
        tituloDescripcion.setForeground(Color.decode(Gray));
        tituloDescripcion.setFont(new Font("Consolas", Font.PLAIN, 17));
        frame.add(tituloDescripcion, restricciones);

        //descripcion
        JTextArea descripcionTexto = new JTextArea(10, 20);
        descripcionTexto.setForeground(Color.decode(Gray));
        descripcionTexto.setBackground(Color.decode(bgColor));
        descripcionTexto.setFont(new Font("Consolas", Font.PLAIN, 17));
        descripcionTexto.setLineWrap(true);
        descripcionTexto.setWrapStyleWord(true);
        descripcionTexto.setBorder(new RoundedBorder(25));
        restricciones.gridy = 4;
        restricciones.gridheight = 4;
        //cambiar color cuando sea seleccionado
        descripcionTexto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                descripcionTexto.setForeground(Color.decode(Green));
                tituloDescripcion.setForeground(Color.decode(Green));
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                descripcionTexto.setForeground(Color.decode(Gray));
                tituloDescripcion.setForeground(Color.decode(Gray));
            }
        });
        frame.add(descripcionTexto, restricciones);

        //boton enviar
        JButton botonEnviar = new JButton("Enviar");
        botonEnviar.setForeground(Color.decode(Gray));
        botonEnviar.setBackground(Color.decode(bgColor));
        botonEnviar.setFont(new Font("Consolas", Font.PLAIN, 17));
        botonEnviar.setPreferredSize(new Dimension(200, 50));
        botonEnviar.setMaximumSize(new Dimension(200, 50));
        botonEnviar.setBorder(new RoundedBorder(25));
        restricciones.fill = GridBagConstraints.NONE;
        restricciones.gridy = 8;
        restricciones.gridheight = 1;
        restricciones.anchor = GridBagConstraints.CENTER;
        restricciones.insets = new Insets(10, 10, 10, 10);
        botonEnviar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonEnviar.setForeground(Color.decode(Green));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonEnviar.setForeground(Color.decode(Gray));
            }
        });
        frame.add(botonEnviar, restricciones);

        botonEnviar.addActionListener(e -> {
            nombreProyecto = nombreTexto.getText();
            lenguajes = lenguajesTexto.getText();
            descripcion = descripcionTexto.getText();
            
            if (nombreProyecto == null || nombreProyecto.equals("")) {
                tituloNombre.setForeground(Color.decode(Red));
                nombreTexto.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Red)));
                nombreBol = false;
            } else {
                nombreBol = true;
            }
            if (lenguajes == null || lenguajes.equals("")) {
                tituloLenguajes.setForeground(Color.decode(Red));
                lenguajesTexto.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Red)));
            }else {
                lenguajeBol = true;
            }
            if (descripcion == null || descripcion.equals("")) {
                tituloDescripcion.setForeground(Color.decode(Red));
                descripcionTexto.setForeground(Color.decode(Red));
                descrBol = false;
            }else {
                descrBol = true;
            }

            if (nombreBol && lenguajeBol && descrBol) {
                String nuevoProyecto = nombreProyecto + ";" + lenguajes + ";" + descripcion;
                adjuntarArchivo(nuevoProyecto);
            }
            
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