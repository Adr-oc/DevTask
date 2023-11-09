import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    public static void main(String[] args) {

        JFrame ventanaProyectos = new JFrame("Proyectos");
        //Aqui se pone el tamaño de la ventana
        ventanaProyectos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaProyectos.setSize(1024, 768);

        DefaultListModel<Post> postListModel = new DefaultListModel<>();
        JList<Post> listaDeProyectos = new JList<>(postListModel);
        listaDeProyectos.setLayout(new BoxLayout(listaDeProyectos, BoxLayout.Y_AXIS));
        listaDeProyectos.setCellRenderer(new PostListCellRenderer());
        listaDeProyectos.setBackground(Color.decode("#272830"));
        postListModel.addElement(new Post("usuarioX", "Titulo1", "Java, C+", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
        postListModel.addElement(new Post("usuarioC", "Titulo2", "C#", "This is the description of the second post"));
        postListModel.addElement(new Post("usuarioV", "Titulo3", "Javascript", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
        postListModel.addElement(new Post("usuarioN", "Titulo4", "Python", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
        JPanel barraLateral = getjPanel(ventanaProyectos, postListModel, listaDeProyectos);

        JScrollPane panelDeProyectos = new JScrollPane(listaDeProyectos);
        panelDeProyectos.setOpaque(true);
        panelDeProyectos.getViewport().setOpaque(true);
        panelDeProyectos.setBackground(Color.decode("#272830"));
        panelDeProyectos.getViewport().setBackground(Color.decode("#272830"));
        panelDeProyectos.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        ventanaProyectos.add(barraLateral, BorderLayout.WEST);
        ventanaProyectos.add(panelDeProyectos, BorderLayout.CENTER);
        ventanaProyectos.getContentPane().setBackground(Color.decode("#272830"));
        ventanaProyectos.setVisible(true);
    }


    private static JPanel getjPanel(JFrame proyectos, DefaultListModel<Post> postListModel, JList<Post> listadoProyectos) {
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

        JButton botonVerMisPosts = getjButton(postListModel, listadoProyectos);
        botonVerMisPosts.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonVerMisPosts.setForeground(Color.decode("#4B3B6E"));
        botonVerMisPosts.setBackground(Color.decode("#C6BFD6"));
        botonVerMisPosts.setFont(new Font("Consolas", Font.PLAIN, 14));
        barraLateral.add(botonVerMisPosts);

// Esto esta aquí para agregar margen a los botones de la barra. No borrar.
        JLabel textoEnBlanco = new JLabel("");
        textoEnBlanco.setAlignmentX(Component.CENTER_ALIGNMENT);
        textoEnBlanco.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        textoEnBlanco.setForeground(Color.decode("#C6BFD6"));
        textoEnBlanco.setFont(new Font("Consolas", Font.PLAIN, 17));
        barraLateral.add(textoEnBlanco);

        JButton botonCrearUnProyecto = new JButton("Crear un Proyecto");
        botonCrearUnProyecto.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonCrearUnProyecto.setForeground(Color.decode("#4B3B6E"));
        botonCrearUnProyecto.setBackground(Color.decode("#C6BFD6"));
        botonCrearUnProyecto.setFont(new Font("Consolas", Font.PLAIN, 14));
        botonCrearUnProyecto.addActionListener(e -> {
            // Aquí agregar lo que hace el boton.
            System.out.println("botonCrearUnProyecto");
        });


        barraLateral.add(botonCrearUnProyecto);

        return barraLateral;
    }


    private static JButton getjButton(DefaultListModel<Post> postListModel, JList<Post> listadoProyectos) {
        JButton botonVerMisPosts = new JButton("Ver mis proyectos");
        botonVerMisPosts.addActionListener(e -> {
            // Filtra los posts para solo mostrar los que hagan match con tu username.
            DefaultListModel<Post> listaFiltrada = new DefaultListModel<>();
            for (int i = 0; i < postListModel.getSize(); i++) {
                Post post = postListModel.getElementAt(i);
                if (post.usuario().equals("usuarioN")) {
                    listaFiltrada.addElement(post);
                }
            }
            listadoProyectos.setModel(listaFiltrada);
        });
        return botonVerMisPosts;
    }

    public record Post(String usuario, String nombre, String lenguajesPreferidos, String descripcion) {

        @Override
        public String toString() {
            return "Usuario: " + usuario + ", Nombre:" + nombre + ", Lenguajes preferidos: " + lenguajesPreferidos + ", Descripción: " + descripcion;
        }
    }

    // Esta es la clase que modifica como se despliegan los posts de Proyectos. También contiene la configuration para los colores y alignment.
    public static class PostListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                                                      boolean cellHasFocus) {
            Post post = (Post) value;
            JPanel panel = new JPanel(new BorderLayout());
            panel.setOpaque(true);
            panel.setBackground(Color.decode("#272830"));

            JPanel panelLabels = new JPanel();
            panelLabels.setLayout(new BoxLayout(panelLabels, BoxLayout.Y_AXIS));
            panelLabels.setOpaque(false);

            JLabel labelUsuario = new JLabel(post.usuario());
            labelUsuario.setFont(new Font("Consolas", Font.PLAIN, 17));
            labelUsuario.setForeground(Color.decode("#a8e65f"));

            JLabel labelNombreProyecto = new JLabel(post.nombre());
            labelNombreProyecto.setFont(new Font("Consolas", Font.PLAIN, 17));
            labelNombreProyecto.setForeground(Color.decode("#1BFFFF"));

            JLabel labelLenguajes = new JLabel(post.lenguajesPreferidos());
            labelLenguajes.setFont(new Font("Consolas", Font.PLAIN, 17));
            labelLenguajes.setForeground(Color.decode("#BC67FF"));

            panelLabels.add(labelUsuario);
            panelLabels.add(labelNombreProyecto);
            panelLabels.add(labelLenguajes);

            JTextArea textoDescripcion = new JTextArea(post.descripcion());
            textoDescripcion.setOpaque(true);
            textoDescripcion.setBackground(Color.decode("#272830"));
            textoDescripcion.setLineWrap(true);
            textoDescripcion.setWrapStyleWord(true);
            textoDescripcion.setFont(new Font("Consolas", Font.PLAIN, 17));
            textoDescripcion.setForeground(Color.decode("#D6CCEC"));
            textoDescripcion.setSize(textoDescripcion.getPreferredSize().width + 500, textoDescripcion.getPreferredSize().height);

            panel.add(panelLabels, BorderLayout.PAGE_START);
            panel.add(textoDescripcion, BorderLayout.PAGE_END);

            panel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

            return panel;


        }
    }


}
