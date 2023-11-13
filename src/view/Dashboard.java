package view;
import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.util.Arrays;
import java.util.List;

public class Dashboard extends JFrame {

    

    public void GUI() {
        JFrame ventanaProyectos = new JFrame("Proyectos");
        ventanaProyectos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaProyectos.setSize(1200, 700);
        ventanaProyectos.setLocationRelativeTo(null);
        ventanaProyectos.setMinimumSize(new Dimension(1200, 700));
        ventanaProyectos.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizar la ventana
        ventanaProyectos.setExtendedState(JFrame.NORMAL); // Reducir el tamaño de la ventana
        ventanaProyectos.pack();

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

        JPanel postListPanel = new JPanel();
        postListPanel.setLayout(new GridLayout(0, 2)); 
        postListPanel.setBackground(Color.decode(Colors.bgColor));
        postListPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 10, 50));

        for (Post post : posts) {
            postListPanel.add(new PostPanel(post));
        }



        // ScrollPane
        JScrollPane scrollPane = new JScrollPane(postListPanel);
        Dimension preferredSize = postListPanel.getPreferredSize();
        scrollPane.setPreferredSize(preferredSize);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Ajusta la velocidad de desplazamiento
        scrollPane.setBackground(Color.decode(Colors.bgColor));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        Dimension d = new Dimension(12, scrollPane.getVerticalScrollBar().getPreferredSize().height);
        scrollPane.getVerticalScrollBar().setPreferredSize(d);
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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

        JPanel barraLateral = getjPanel(ventanaProyectos);
        barraLateral.setPreferredSize(new Dimension(200, ventanaProyectos.getHeight()));
        ventanaProyectos.add(barraLateral, BorderLayout.LINE_START);
        ventanaProyectos.add(scrollPane, BorderLayout.CENTER);
        ventanaProyectos.getContentPane().setBackground(Color.decode(Colors.bgColor));
        ventanaProyectos.setVisible(true);
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


            JButton botonAplicar = new JButton("Aplicar");

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

        JLabel barraNombreUsuario = new JLabel("NombreDeUsuario");
        barraNombreUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        barraNombreUsuario.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
        barraNombreUsuario.setForeground(Color.decode("#C6BFD6"));
        barraNombreUsuario.setFont(new Font("Consolas", Font.PLAIN, 20));

        JLabel barraTipoUsuario = new JLabel("Desarrollador/Usuario");
        barraTipoUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        barraTipoUsuario.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        barraTipoUsuario.setForeground(Color.decode("#C6BFD6"));
        barraTipoUsuario.setFont(new Font("Consolas", Font.ITALIC, 14));
        barraLateral.add(barraNombreUsuario);
        barraLateral.add(barraTipoUsuario);

        JButton botonVerMisPosts = new JButton("Ver mis proyectos");
        botonVerMisPosts.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonVerMisPosts.setForeground(Color.decode("#4B3B6E"));
        botonVerMisPosts.setBackground(Color.decode("#C6BFD6"));
        botonVerMisPosts.setFont(new Font("Consolas", Font.PLAIN, 14));
        barraLateral.add(botonVerMisPosts);
        botonVerMisPosts.addActionListener(e -> {
            System.out.println("botonVerMisPosts");
        });

        // No borrar este texto, es el margen entre los dos botones de una manera
        // sencilla.
        JLabel textoEnBlanco = new JLabel("");
        textoEnBlanco.setAlignmentX(Component.CENTER_ALIGNMENT);
        textoEnBlanco.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        barraLateral.add(textoEnBlanco);

        JButton botonCrearUnProyecto = new JButton("Crear un Proyecto");
        botonCrearUnProyecto.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonCrearUnProyecto.setForeground(Color.decode("#4B3B6E"));
        botonCrearUnProyecto.setBackground(Color.decode("#C6BFD6"));
        botonCrearUnProyecto.setFont(new Font("Consolas", Font.PLAIN, 14));
        botonCrearUnProyecto.addActionListener(e -> {
            System.out.println("botonCrearUnProyecto");
        });
        barraLateral.add(botonCrearUnProyecto);
        return barraLateral;
    }
}
