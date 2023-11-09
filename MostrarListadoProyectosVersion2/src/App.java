import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class App extends JFrame {
    public static void main(String[] args) {
        JFrame ventanaProyectos = new JFrame("Proyectos");
        ventanaProyectos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaProyectos.setSize(1024, 768);

        List<Post> posts = Arrays.asList(
                new Post("usuarioX", "Titulo1", "Java, C+",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."),
                new Post("usuarioC", "Titulo2", "C#", "Description 2"),
                new Post("usuarioV", "Titulo3", "Javascript", "Description 3"),
                new Post("usuarioN", "Titulo4", "Python", "Description 4"),
                new Post("usuarioB", "Titulo5", "Python", "Description 5"),
                new Post("usuarioM", "Titulo6", "Python", "Description 6"));

        JPanel postListPanel = new JPanel();
        postListPanel.setLayout(new BoxLayout(postListPanel, BoxLayout.Y_AXIS));
        for (Post post : posts) {
            postListPanel.add(new PostPanel(post));
        }
        postListPanel.setBackground(Color.decode("#272830"));

        JScrollPane scrollPane = new JScrollPane(postListPanel);
        scrollPane.setPreferredSize(new Dimension(824, 768));
        scrollPane.setBackground(Color.decode("#272830"));

        JPanel barraLateral = getjPanel(ventanaProyectos);

        ventanaProyectos.add(barraLateral, BorderLayout.WEST);
        ventanaProyectos.add(scrollPane, BorderLayout.CENTER);
        ventanaProyectos.getContentPane().setBackground(Color.decode("#272830"));
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
            setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 0));
            setLayout(new BorderLayout());
            JPanel panelLabels = new JPanel();
            panelLabels.setLayout(new BoxLayout(panelLabels, BoxLayout.Y_AXIS));
            panelLabels.setOpaque(false);
            panelLabels.setBackground(Color.decode("#272830"));

            JLabel labelUsuario = new JLabel(post.getUsername());
            labelUsuario.setFont(new Font("Consolas", Font.PLAIN, 17));
            labelUsuario.setForeground(Color.decode("#a8e65f"));

            JLabel labelNombreProyecto = new JLabel(post.getTitle());
            labelNombreProyecto.setFont(new Font("Consolas", Font.PLAIN, 17));
            labelNombreProyecto.setForeground(Color.decode("#1BFFFF"));

            JLabel labelLenguajes = new JLabel(post.getLanguages());
            labelLenguajes.setFont(new Font("Consolas", Font.PLAIN, 17));
            labelLenguajes.setForeground(Color.decode("#BC67FF"));

            panelLabels.add(labelUsuario);
            panelLabels.add(labelNombreProyecto);
            panelLabels.add(labelLenguajes);

            JTextArea textoDescripcion = new JTextArea(post.getDescription());
            textoDescripcion.setOpaque(true);
            textoDescripcion.setBackground(Color.decode("#272830"));
            textoDescripcion.setLineWrap(true);
            textoDescripcion.setWrapStyleWord(true);
            textoDescripcion.setFont(new Font("Consolas", Font.PLAIN, 17));
            textoDescripcion.setForeground(Color.decode("#D6CCEC"));

            JButton botonAplicar = new JButton("Aplicar");
            botonAplicar.setForeground(Color.decode("#0D3300"));
            botonAplicar.setBackground(Color.decode("#A1D6A1"));
            botonAplicar.setFont(new Font("Consolas", Font.PLAIN, 14));
            botonAplicar.addActionListener(e -> System.out.println("botonAplicar"));
            botonAplicar.setSize(300, 200);
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            buttonPanel.add(botonAplicar);
            buttonPanel.setBackground(Color.decode("#272830"));

            JPanel contentPanel = new JPanel(new BorderLayout());
            contentPanel.add(panelLabels, BorderLayout.PAGE_START);
            contentPanel.add(textoDescripcion, BorderLayout.CENTER);
            contentPanel.add(buttonPanel, BorderLayout.PAGE_END);
            contentPanel.setBackground(Color.decode("#272830"));

            JScrollPane scrollPane = new JScrollPane(contentPanel);
            scrollPane.setBackground(Color.decode("#272830"));
            scrollPane.setBorder(null);
            setLayout(new BorderLayout());
            add(scrollPane, BorderLayout.CENTER);
            setBackground(Color.decode("#272830"));

        }
    }

    private static JPanel getjPanel(JFrame proyectos) {
        JPanel barraLateral = new JPanel();
        barraLateral.setLayout(new BoxLayout(barraLateral, BoxLayout.Y_AXIS));
        barraLateral.setPreferredSize(new Dimension(200, proyectos.getHeight()));
        barraLateral.setBackground(Color.decode("#4B3B6E"));

        JLabel barraNombreUsuario = new JLabel("NombreDeUsuario");
        barraNombreUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        barraNombreUsuario.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        barraNombreUsuario.setForeground(Color.decode("#C6BFD6"));
        barraNombreUsuario.setFont(new Font("Consolas", Font.PLAIN, 17));

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
