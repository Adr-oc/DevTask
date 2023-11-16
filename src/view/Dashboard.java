package view;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import java.awt.*;
import controller.UserController;
import controller.ProjectController;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.util.Arrays;
import java.util.List;


public class Dashboard extends JFrame {

    public ProjectController projectController = new ProjectController();

    public void GUI() {
        JFrame frame = new JFrame("Proyectos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(1200, 700));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizar la ventana
        frame.setExtendedState(JFrame.NORMAL); // Reducir el tamaño de la ventana
        frame.pack();

        List<Post> posts = Arrays.asList(
                new Post("usuarioX", "Titulo1", "Java, C+",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."),
                new Post("usuarioC", "Titulo2", "C#", "Description 2"),
                new Post("usuarioV", "Titulo3", "Javascript", "Description 3"),
                new Post("usuarioN", "Titulo4", "Python", "Description 4"),
                new Post("usuarioB", "Titulo5", "Python", "Description 5"),
                new Post("usuarioM", "Titulo6", "Python", "Description 6"),
                new Post("usuarioM", "Titulo7", "Python", "Description 7"),
                new Post("usuarioM", "Titulo8", "Python", "Description 8"),
                new Post("usuarioM", "Titulo9", "Python", "Description 9"),
                new Post("usuarioM", "Titulo10", "Python", "Description 10"),
                new Post("usuarioM", "Titulo11", "Python", "Description 11"));

        CardLayout cardLayout = new CardLayout();
        JPanel MainPanel = new JPanel();
        MainPanel.setLayout(cardLayout);
        MainPanel.setLayout(new GridLayout(0, 2)); 
        MainPanel.setBackground(Color.decode(Colors.bgColor));
        MainPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 10, 50));


        // Crear los paneles que se mostrarán en MainPanel
        JPanel scrollPanel = new JPanel(new GridLayout(0, 2));
        scrollPanel.setBackground(Color.decode(Colors.bgColor));
        scrollPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 10, 50));

        for (Post post : posts) {
            MainPanel.add(new PostPanel(post));
        }

        JPanel newPanel = new JPanel(); // Este es tu nuevo JPanel
        newPanel.setLayout(new GridLayout(0, 2)); // Establecer layout
        newPanel.setBackground(Color.decode(Colors.bgColor)); // Establecer color de fondo
        newPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 10, 50)); // Establecer borde

        // projectsPanel
        JScrollPane projectsPanel = new JScrollPane(MainPanel);
        Dimension preferredSize = MainPanel.getPreferredSize();
        projectsPanel.setPreferredSize(preferredSize);
        projectsPanel.getVerticalScrollBar().setUnitIncrement(16); // Ajusta la velocidad de desplazamiento
        projectsPanel.setBackground(Color.decode(Colors.bgColor));
        projectsPanel.setBorder(BorderFactory.createEmptyBorder());

        Dimension d = new Dimension(12, projectsPanel.getVerticalScrollBar().getPreferredSize().height);
        projectsPanel.getVerticalScrollBar().setPreferredSize(d);
        projectsPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.decode((Colors.ScrollbarColor)); // Cambia el color del pulgar
                this.trackColor = Color.decode((Colors.ScrollbarThumbColor)); // Cambia el color de la pista
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createInvisibleButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createInvisibleButton();
            }

            private JButton createInvisibleButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
        });


        JPanel barraLateral = getjPanel(frame);
        barraLateral.setPreferredSize(new Dimension(200, frame.getHeight()));
        frame.add(barraLateral, BorderLayout.LINE_START);
        frame.add(projectsPanel, BorderLayout.CENTER);
        frame.getContentPane().setBackground(Color.decode(Colors.bgColor));
        frame.setVisible(true);
    }

    public static class Post {
        private String username;
        private String title;
        private String languages;
        private String description;

        public Post(String username, String title, String languages, String description) {
            this.username = username;
            this.title = title;
            this.languages = languages;
            this.description = description;
        }

        public String getUsername() {
            return username;
        }

        public String getTitle() {
            return title;
        }

        public String getLanguages() {
            return languages;
        }

        public String getDescription() {
            return description;
        }
    }


    public static class PostPanel extends JPanel {
        public PostPanel(Post post) {
            this.setBackground(Color.decode(Colors.bgColor));
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            JPanel panelLabels = new JPanel();
            panelLabels.setLayout(new BoxLayout(panelLabels, BoxLayout.Y_AXIS));
            panelLabels.setOpaque(false);

            JLabel labelNombreProyecto = new JLabel(post.getTitle());
            labelNombreProyecto.setFont(new Font("Consolas", Font.PLAIN, 30));
            labelNombreProyecto.setForeground(Color.decode(Colors.Primary));

            JLabel labelLenguajes = new JLabel(post.getLanguages());
            labelLenguajes.setFont(new Font("Consolas", Font.PLAIN, 20));
            labelLenguajes.setForeground(Color.decode(Colors.Secondary));

            JLabel labelUsuario = new JLabel(post.getUsername());
            labelUsuario.setFont(new Font("Consolas", Font.PLAIN, 17));
            labelUsuario.setForeground(Color.decode(Colors.Primary));


            panelLabels.add(labelNombreProyecto);
            panelLabels.add(labelLenguajes);
            panelLabels.add(labelUsuario);


            JButton botonAplicar = new JButton("Ver proyecto");

        //!================CAMBIAR ESTO POR EL ID DEL PROYECTO============
            botonAplicar.setName(post.getTitle());
        //!===============================================================

            botonAplicar.setAlignmentX(Component.CENTER_ALIGNMENT);
            botonAplicar.setForeground(Color.decode(Colors.TextColor));
            botonAplicar.setBackground(Color.decode(Colors.bgCard));
            botonAplicar.setFont(new Font("Consolas", Font.PLAIN, 17));
            botonAplicar.setBorder(new RoundedBorder(8));
            botonAplicar.setCursor(new Cursor(Cursor.HAND_CURSOR));
            botonAplicar.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    botonAplicar.setForeground(Color.decode(Colors.Primary));
                }
            
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    botonAplicar.setForeground(Color.decode(Colors.TextColor));
                }
            });
            botonAplicar.addActionListener(e -> {
                System.out.println(botonAplicar.getName());
            });
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(0, 3)); 
            buttonPanel.setOpaque(false);
            buttonPanel.add(botonAplicar);

            PanelRound contentPanel = new PanelRound();
            int roundInt = 30;
            contentPanel.setRoundBottomRight(roundInt);
            contentPanel.setLayout(new BorderLayout());
            contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            contentPanel.add(panelLabels, BorderLayout.PAGE_START);
            contentPanel.add(buttonPanel, BorderLayout.PAGE_END);
            contentPanel.setRoundTopLeft(roundInt);
            contentPanel.setRoundTopRight(roundInt);
            contentPanel.setRoundBottomLeft(roundInt);
            contentPanel.setBackground(Color.decode(Colors.bgCard));
            panelLabels.setPreferredSize(new Dimension(contentPanel.getWidth(), panelLabels.getPreferredSize().height));
            buttonPanel.setPreferredSize(new Dimension(contentPanel.getWidth(), buttonPanel.getPreferredSize().height));
            int bordersInt = 8;
            this.setBorder(BorderFactory.createEmptyBorder(bordersInt, bordersInt, bordersInt, bordersInt));
            this.add(contentPanel);
        }
    }

    private static JPanel getjPanel(JFrame proyectos) {
        PanelRound barraLateral = new PanelRound();
        barraLateral.setRoundTopRight(30);
        barraLateral.setRoundBottomRight(30);
        barraLateral.setLayout(new BoxLayout(barraLateral, BoxLayout.Y_AXIS));
        barraLateral.setPreferredSize(new Dimension(200, proyectos.getHeight()));
        barraLateral.setBackground(Color.decode(Colors.bgCard));

        String nombredeUsuario = UserController.getUsername();
        if (nombredeUsuario == null) {
            nombredeUsuario = "NombreDeUsuario";
        }
        
        JLabel barraNombreUsuario = new JLabel(nombredeUsuario);
        barraNombreUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        barraNombreUsuario.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
        barraNombreUsuario.setForeground(Color.decode("#C6BFD6"));
        barraNombreUsuario.setFont(new Font("Consolas", Font.PLAIN, 20));

        String userType = UserController.getUserType();
        //verificar si esta lleno el userType
        if (userType != null) {
            JLabel barraTipoUsuario = new JLabel(userType);
            barraTipoUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
            barraTipoUsuario.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
            barraTipoUsuario.setForeground(Color.decode("#C6BFD6"));
            barraTipoUsuario.setFont(new Font("Consolas", Font.ITALIC, 14));
            barraLateral.add(barraNombreUsuario);
            barraLateral.add(barraTipoUsuario);
        }

        if (userType.equals("Normal")){
            JButton botonCrearProyecto = new JButton("Crear proyecto");
            botonCrearProyecto.setMaximumSize(new Dimension(Integer.MAX_VALUE, botonCrearProyecto.getMinimumSize().height));
            botonCrearProyecto.setAlignmentX(Component.CENTER_ALIGNMENT);
            botonCrearProyecto.setForeground(Color.decode("#4B3B6E"));
            botonCrearProyecto.setBackground(Color.decode("#C6BFD6"));
            botonCrearProyecto.setFont(new Font("Consolas", Font.PLAIN, 14));
            botonCrearProyecto.addActionListener(e -> {
                System.out.println("Crear proyecto");
            });
            barraLateral.add(botonCrearProyecto);
        }
        
        JButton botonHome = new JButton("Home");
        botonHome.setMaximumSize(new Dimension(Integer.MAX_VALUE, botonHome.getMinimumSize().height));
        botonHome.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonHome.setForeground(Color.decode("#4B3B6E"));
        botonHome.setBackground(Color.decode("#C6BFD6"));
        botonHome.setFont(new Font("Consolas", Font.PLAIN, 14));
        botonHome.addActionListener(e -> {
            System.out.println("Home");
        });
        barraLateral.add(botonHome);

        JButton botonVerMisPosts = new JButton("Ver mis proyectos");
        botonVerMisPosts.setMaximumSize(new Dimension(Integer.MAX_VALUE, botonVerMisPosts.getMinimumSize().height));
        botonVerMisPosts.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonVerMisPosts.setForeground(Color.decode("#4B3B6E"));
        botonVerMisPosts.setBackground(Color.decode("#C6BFD6"));
        botonVerMisPosts.setFont(new Font("Consolas", Font.PLAIN, 14));
        barraLateral.add(botonVerMisPosts);

        JButton PerfilButton = new JButton("Perfil");
        PerfilButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, PerfilButton.getMinimumSize().height));
        PerfilButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        PerfilButton.setForeground(Color.decode("#4B3B6E"));
        PerfilButton.setBackground(Color.decode("#C6BFD6"));
        PerfilButton.setFont(new Font("Consolas", Font.PLAIN, 14));
        PerfilButton.addActionListener(e -> {
            System.out.println("PerfilButton");
        });
        barraLateral.add(PerfilButton);

        JButton botonCerrarSesion = new JButton("Cerrar sesión");
        botonCerrarSesion.setMaximumSize(new Dimension(Integer.MAX_VALUE, botonCerrarSesion.getMinimumSize().height));
        botonCerrarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonCerrarSesion.setForeground(Color.decode("#4B3B6E"));
        botonCerrarSesion.setBackground(Color.decode("#C6BFD6"));
        botonCerrarSesion.setFont(new Font("Consolas", Font.PLAIN, 14));
        botonCerrarSesion.addActionListener(e -> {
            int confirm = JOptionPane.showOptionDialog(
                null, "¿Estás seguro de que quieres cerrar la sesión?", 
                "Confirmación", JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE,null, null, null);
            if (confirm == 0) {
                System.out.println("Cerrar sesión");
                UserController.logout();
                proyectos.dispose();
                new SingUpVista().GUI();
            }
        });
        barraLateral.add(botonCerrarSesion, BorderLayout.PAGE_END);


        return barraLateral;
    }



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
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
}

