package view;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import model.Project;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import controller.UserController;
import controller.ProjectController;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.util.ArrayList;
import java.util.List;


public class Dashboard extends JFrame {

    public static UserController userController = new UserController();

    public void GUI() {
        JFrame frame = new JFrame("Proyectos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(1200, 700));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizar la ventana
        frame.setExtendedState(JFrame.NORMAL); // Reducir el tamaño de la ventana
        frame.pack();

        

        CardLayout cardLayout = new CardLayout();
        JPanel MainPanel = new JPanel();
        MainPanel.setLayout(cardLayout);
        MainPanel.setBackground(Color.decode(Colors.bgColor));
        MainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        List<Project> projects;
        if(UserController.getUserType().equals("dev")){
            ProjectController projectController = new ProjectController();
            projects = projectController.getAllProjects();
        }else{
            ProjectController projectController = new ProjectController();
            projects = projectController.getUserProjects(UserController.getEmail());
        }
        ProjectsPanel scrollPanel = new ProjectsPanel(projects, cardLayout, MainPanel);
        ProjectsScrollPane projectsPanel = new ProjectsScrollPane(scrollPanel);
        

        PanelRound barraLateral = new PanelRound();
        barraLateral.setRoundTopRight(30);
        barraLateral.setRoundBottomRight(30);
        barraLateral.setLayout(new BoxLayout(barraLateral, BoxLayout.Y_AXIS));
        barraLateral.setPreferredSize(new Dimension(200, frame.getHeight()));
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

        //verificar si esta lleno el userType
        if (UserController.getUserType() != null) {
            JLabel barraTipoUsuario = new JLabel(UserController.getUserType());
            barraTipoUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
            barraTipoUsuario.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
            barraTipoUsuario.setForeground(Color.decode("#C6BFD6"));
            barraTipoUsuario.setFont(new Font("Consolas", Font.ITALIC, 14));
            barraLateral.add(barraNombreUsuario);
            barraLateral.add(barraTipoUsuario);
        }

        
        if(UserController.getUserType().equals("dev")){
            JButton botonHome = new JButton("Buscar Proyectos");
            botonHome.setMaximumSize(new Dimension(Integer.MAX_VALUE, botonHome.getMinimumSize().height));
            botonHome.setAlignmentX(Component.CENTER_ALIGNMENT);
            botonHome.setForeground(Color.decode("#4B3B6E"));
            botonHome.setBackground(Color.decode("#C6BFD6"));
            botonHome.setFont(new Font("Consolas", Font.PLAIN, 14));
            botonHome.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
                    ProjectController projectController = new ProjectController();
                    List<Project> projects = projectController.getAllProjects();
                    ProjectsPanel scrollPanel = new ProjectsPanel(projects, cardLayout, MainPanel);
                    ProjectsScrollPane projectsPanel = new ProjectsScrollPane(scrollPanel);
                    MainPanel.removeAll(); // Elimina el contenido actual de MainPanel
                    MainPanel.add(projectsPanel); // Añade un JPanel vacío a MainPanel
                    MainPanel.revalidate(); // Informa al MainPanel que su contenido ha cambiado
                    MainPanel.repaint(); // Redibuja el MainPanel
                }
            });
            barraLateral.add(botonHome);
        }
 

        JButton botonVerMisPosts = new JButton("Ver mis proyectos");
        botonVerMisPosts.setMaximumSize(new Dimension(Integer.MAX_VALUE, botonVerMisPosts.getMinimumSize().height));
        botonVerMisPosts.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonVerMisPosts.setForeground(Color.decode("#4B3B6E"));
        botonVerMisPosts.setBackground(Color.decode("#C6BFD6"));
        botonVerMisPosts.setFont(new Font("Consolas", Font.PLAIN, 14));
        if(UserController.getUserType().equals("dev")){
                botonVerMisPosts.setText("Ver mis proyectos");
                botonVerMisPosts.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
                ProjectController projectController = new ProjectController();
                List<Project> projects = projectController.getDevProjects(UserController.getEmail());
                ProjectsPanel scrollPanel = new ProjectsPanel(projects, cardLayout, MainPanel);
                ProjectsScrollPane projectsPanel = new ProjectsScrollPane(scrollPanel);
                MainPanel.removeAll(); // Elimina el contenido actual de MainPanel
                MainPanel.add(projectsPanel); // Añade un JPanel vacío a MainPanel
                MainPanel.revalidate(); // Informa al MainPanel que su contenido ha cambiado
                MainPanel.repaint(); // Redibuja el MainPanel
                }});
            }
            else{
                botonVerMisPosts.setText("Ver mis posts");
                botonVerMisPosts.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
                ProjectController projectController = new ProjectController();
                List<Project> projects = projectController.getUserProjects(UserController.getEmail());
                ProjectsPanel scrollPanel = new ProjectsPanel(projects, cardLayout, MainPanel);
                ProjectsScrollPane projectsPanel = new ProjectsScrollPane(scrollPanel);
                MainPanel.removeAll(); // Elimina el contenido actual de MainPanel
                MainPanel.add(projectsPanel); // Añade un JPanel vacío a MainPanel
                MainPanel.revalidate(); // Informa al MainPanel que su contenido ha cambiado
                MainPanel.repaint(); // Redibuja el MainPanel
                }});
            }

        barraLateral.add(botonVerMisPosts);

        JButton PerfilButton = new JButton("Perfil");
        PerfilButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, PerfilButton.getMinimumSize().height));
        PerfilButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        PerfilButton.setForeground(Color.decode("#4B3B6E"));
        PerfilButton.setBackground(Color.decode("#C6BFD6"));
        PerfilButton.setFont(new Font("Consolas", Font.PLAIN, 14));
        PerfilButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainPanel.removeAll(); // Elimina el contenido actual de MainPanel
                MainPanel.add(new ProfileView()); // Añade un JPanel vacío a MainPanel
                MainPanel.revalidate(); // Informa al MainPanel que su contenido ha cambiado
                MainPanel.repaint(); // Redibuja el MainPanel
            }
        });
        barraLateral.add(PerfilButton);


         if (UserController.getUserType().equals("Normal")){
            JButton botonCrearProyecto = new JButton("Crear proyecto");
            botonCrearProyecto.setMaximumSize(new Dimension(Integer.MAX_VALUE, botonCrearProyecto.getMinimumSize().height));
            botonCrearProyecto.setAlignmentX(Component.CENTER_ALIGNMENT);
            botonCrearProyecto.setForeground(Color.decode("#4B3B6E"));
            botonCrearProyecto.setBackground(Color.decode("#C6BFD6"));
            botonCrearProyecto.setFont(new Font("Consolas", Font.PLAIN, 14));
            botonCrearProyecto.addActionListener(e -> {
                System.out.println("Crear proyecto");
                NewProjectForm newProjectForm = new NewProjectForm();
                frame.dispose();
                newProjectForm.GUI();
            });
            barraLateral.add(botonCrearProyecto);
        }

        
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
                frame.dispose();
                new SingUpVista().GUI();
            }
        });

        barraLateral.add(botonCerrarSesion, BorderLayout.PAGE_END);
        barraLateral.setPreferredSize(new Dimension(200, frame.getHeight()));

        frame.add(barraLateral, BorderLayout.LINE_START);
        MainPanel.add(projectsPanel);
        frame.add(MainPanel, BorderLayout.CENTER);
        frame.getContentPane().setBackground(Color.decode(Colors.bgColor));
        frame.setVisible(true);
    }


    public static class PostPanel extends JPanel {
        public PostPanel(Project project, JPanel MainPanel) {
            this.setBackground(Color.decode(Colors.bgColor));
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            JPanel panelLabels = new JPanel();
            panelLabels.setLayout(new BoxLayout(panelLabels, BoxLayout.Y_AXIS));
            panelLabels.setOpaque(false);

            JLabel labelNombreProyecto = new JLabel(project.getProjectName());
            labelNombreProyecto.setFont(new Font("Consolas", Font.PLAIN, 30));
            labelNombreProyecto.setForeground(Color.decode(Colors.Primary));

         
            String lenguajes = project.getLanguages().replace(";", ",");
            JLabel labelLenguajes = new JLabel(lenguajes);
            labelLenguajes.setFont(new Font("Consolas", Font.PLAIN, 20));
            labelLenguajes.setForeground(Color.decode(Colors.Secondary));

            JLabel labelUsuario = new JLabel(project.getAuthor());
            labelUsuario.setFont(new Font("Consolas", Font.PLAIN, 17));
            labelUsuario.setForeground(Color.decode(Colors.Primary));


            panelLabels.add(labelNombreProyecto);
            panelLabels.add(labelLenguajes);
            panelLabels.add(labelUsuario);


            JButton botonAplicar = new JButton("Ver proyecto");
            botonAplicar.setName(project.getId());
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

            botonAplicar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(botonAplicar);
                    frame.dispose();
                    String[] lenguajes = project.getLanguages().split(";");
                    try {
                        ProjectView.GUI(project.getId(),project.getProjectName(), project.getDescription(), project.getEmail(), project.getAuthor(), project.getDevs(), lenguajes, UserController.getUserType());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                  
                }
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


    public class NewProjectForm {
        private String nombreProyecto;
        private String lenguajes;
        private String descripcion;
        private boolean nombreBol, lenguajeBol, descrBol = true;
    
        public NewProjectForm() {
        }
    
        private void setFocusListeners(JTextField nombreTexto, JLabel tituloNombre) {
            nombreTexto.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    nombreTexto.setBorder(new MatteBorder(0, 0, 2, 0, Color.decode(Colors.Primary)));
                    nombreTexto.setForeground(Color.decode(Colors.Primary));
                    tituloNombre.setForeground(Color.decode(Colors.Primary));
                }
    
                public void focusLost(java.awt.event.FocusEvent evt) {
                    nombreTexto.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Colors.TextColor)));
                    nombreTexto.setForeground(Color.decode(Colors.TextColor));
                    tituloNombre.setForeground(Color.decode(Colors.TextColor));
                }
            });
        }
    
        public void GUI() {
    

            JFrame frame = new JFrame("Formulario de Proyecto");
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.setLayout(new GridBagLayout());
            frame.setSize(1024, 620);
            frame.setMinimumSize(new Dimension(1024, 620));
            frame.getContentPane().setBackground(Color.decode(Colors.bgColor));
        
            // Agregar un WindowListener
            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    int confirm = JOptionPane.showOptionDialog(
                        frame,
                        "¿Estás seguro que deseas salir?",
                        "Confirmación de Salida",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, null, null);
                    if (confirm == 0) {
                        frame.dispose();
                        Dashboard dashboard = new Dashboard();
                        dashboard.GUI();
                    }
                }
            });
    
            GridBagConstraints restricciones = new GridBagConstraints();
            restricciones.weightx = 0.5;
            restricciones.weighty = 0.3;
            restricciones.gridx = 0;
            restricciones.gridy = 0;
            restricciones.insets = new Insets(5, 50, 5, 50);
            restricciones.anchor = GridBagConstraints.WEST;
            JLabel tituloFormulario = new JLabel("Formulario para Nuevo Proyecto");
            tituloFormulario.setForeground(Color.decode("#8B85C1"));
            tituloFormulario.setFont(new Font("Consolas", Font.BOLD, 23));
    
            frame.add(tituloFormulario, restricciones);
            restricciones.gridy = 1;
    
            JLabel tituloNombre = new JLabel("Nombre del Proyecto:");
            tituloNombre.setForeground(Color.decode(Colors.TextColor));
            tituloNombre.setFont(new Font("Consolas", Font.PLAIN, 17));
            frame.add(tituloNombre, restricciones);
    
            JTextField nombreTexto = new JTextField();
            nombreTexto.setForeground(Color.decode(Colors.TextColor));
            nombreTexto.setBackground(Color.decode(Colors.bgColor));
            nombreTexto.setFont(new Font("Consolas", Font.PLAIN, 17));
            nombreTexto.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Colors.TextColor)));
            setFocusListeners(nombreTexto, tituloNombre);
            restricciones.fill = GridBagConstraints.HORIZONTAL;
            restricciones.gridy = 2;
            frame.add(nombreTexto, restricciones);
    
    
            JLabel lenguajesTitle = new JLabel("Lenguajes de Programación:");
            lenguajesTitle.setForeground(Color.decode(Colors.TextColor));
            lenguajesTitle.setFont(new Font("Consolas", Font.PLAIN, 17));
            restricciones.gridx = 1;
            restricciones.gridy = 1;
            frame.add(lenguajesTitle, restricciones);
    
            String[] lenguajesDips = {"Java", "C++", "Python", "JavaScript", "C#"};
            PanelRound panelLenguajes = new PanelRound();
            panelLenguajes.setBackground(Color.decode(Colors.bgCard));
            int border = 30;
            panelLenguajes.setRoundTopRight(border);
            panelLenguajes.setRoundTopLeft(border);
            panelLenguajes.setRoundBottomRight(border);
            panelLenguajes.setRoundBottomLeft(border);
            List<JCheckBox> checkBoxes = new ArrayList<>();
            for (String lenguaje : lenguajesDips) {
                JCheckBox checkBox = new JCheckBox(lenguaje);
                checkBox.setOpaque(true);
                checkBox.setBackground(Color.decode(Colors.bgCard));
                checkBox.setForeground(Color.decode(Colors.TextColor));
                checkBox.setFont(new Font("Consolas", Font.PLAIN, 17));
                checkBoxes.add(checkBox);
                panelLenguajes.add(checkBox);
                checkBox.addChangeListener(e -> {
                    if (checkBox.isSelected()) {
                        checkBox.setForeground(Color.decode(Colors.Primary));
                        lenguajesTitle.setForeground(Color.decode(Colors.TextColor)); 
                    } else {
                        checkBox.setForeground(Color.decode(Colors.TextColor));
                    }
                });
                panelLenguajes.add(checkBox);
            }
            restricciones.gridx = 1;
            restricciones.gridy = 2;
            frame.add(panelLenguajes, restricciones);
    
    
    
            restricciones.gridx = 0;
            restricciones.gridy = 3;
            restricciones.gridwidth = 2;
            //titulo descripcion
            JLabel tituloDescripcion = new JLabel("Descripción del Proyecto:");
            tituloDescripcion.setForeground(Color.decode(Colors.TextColor));
            tituloDescripcion.setFont(new Font("Consolas", Font.PLAIN, 17));
            frame.add(tituloDescripcion, restricciones);
    
            //descripcion
            JTextArea descripcionTexto = new JTextArea(10, 20);
            descripcionTexto.setForeground(Color.decode(Colors.TextColor));
            descripcionTexto.setBackground(Color.decode(Colors.bgColor));
            descripcionTexto.setFont(new Font("Consolas", Font.PLAIN, 17));
            descripcionTexto.setLineWrap(true);
            descripcionTexto.setWrapStyleWord(true);
            descripcionTexto.setBorder(new RoundedBorder(25));
            restricciones.gridy = 4;
            restricciones.gridheight = 4;
            //cambiar color cuando sea seleccionado
            descripcionTexto.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    descripcionTexto.setForeground(Color.decode(Colors.Primary));
                    tituloDescripcion.setForeground(Color.decode(Colors.Primary));
                }
    
                public void focusLost(java.awt.event.FocusEvent evt) {
                    descripcionTexto.setForeground(Color.decode(Colors.TextColor));
                    tituloDescripcion.setForeground(Color.decode(Colors.TextColor));
                }
            });
            frame.add(descripcionTexto, restricciones);
    
            //boton enviar
            JButton botonCrearProjecto = new JButton("Crear Projecto");
            botonCrearProjecto.setForeground(Color.decode(Colors.TextColor));
            botonCrearProjecto.setBackground(Color.decode(Colors.bgColor));
            botonCrearProjecto.setFont(new Font("Consolas", Font.PLAIN, 17));
            botonCrearProjecto.setPreferredSize(new Dimension(200, 50));
            botonCrearProjecto.setMaximumSize(new Dimension(200, 50));
            botonCrearProjecto.setBorder(new RoundedBorder(25));
            botonCrearProjecto.setCursor(new Cursor(Cursor.HAND_CURSOR));
            restricciones.fill = GridBagConstraints.NONE;
            restricciones.gridy++;
            restricciones.gridheight = 1;
            restricciones.anchor = GridBagConstraints.CENTER;
            restricciones.insets = new Insets(60, 10, 10, 10);
            botonCrearProjecto.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    botonCrearProjecto.setForeground(Color.decode(Colors.Primary));
                }
    
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    botonCrearProjecto.setForeground(Color.decode(Colors.TextColor));
                }
            });
            restricciones.fill = GridBagConstraints.NONE;
            restricciones.gridy = 8;
            restricciones.gridheight = 1;
            restricciones.anchor = GridBagConstraints.CENTER;
            restricciones.insets = new Insets(10, 10, 10, 10);
            frame.add(botonCrearProjecto, restricciones);
    
    
            botonCrearProjecto.addActionListener(e -> {
                nombreProyecto = nombreTexto.getText();
                List<String> lenguajesSeleccionados = new ArrayList<>();
                for (JCheckBox checkBox : checkBoxes) {
                    if (checkBox.isSelected()) {
                        lenguajesSeleccionados.add(checkBox.getText());
                    }
                }
                lenguajes = String.join(";", lenguajesSeleccionados);
                descripcion = descripcionTexto.getText();
                
                if (nombreProyecto == null || nombreProyecto.equals("")) {
                    tituloNombre.setForeground(Color.decode(Colors.Error));
                    nombreTexto.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Colors.Error)));
                    nombreBol = false;
                } else {
                    nombreBol = true;
                }
    
                if (lenguajesSeleccionados.isEmpty()) {
                    lenguajesTitle.setForeground(Color.decode(Colors.Error));
                    lenguajeBol = false;
                } else {
                    lenguajeBol = true;
                }
    
                if (descripcion == null || descripcion.equals("")) {
                    tituloDescripcion.setForeground(Color.decode(Colors.Error));
                    descripcionTexto.setForeground(Color.decode(Colors.Error));
                    descrBol = false;
                }else {
                    descrBol = true;
                }
    
                if (nombreBol && lenguajeBol && descrBol) {
                    //LOL
                    try {
                        ProjectController projectController = new ProjectController();
                        projectController.newProject(nombreProyecto, descripcion, UserController.getEmail(), UserController.getUsername(), " ", lenguajes);
                        //mostrar mensaje
                        JOptionPane.showMessageDialog(null, "Proyecto creado exitosamente.");
                        frame.dispose();
                        Dashboard dashboard = new Dashboard();
                        dashboard.GUI();
                    }
                    catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                    
                }
                
            });
            
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
    
        }
    
    }
    

    public class ProfileView extends JPanel{
        public ProfileView() {
            this.setLayout(new GridLayout(2, 1));
            this.setBackground(Color.decode(Colors.Error));
            this.setSize(500, 500);
        }
    }


    public class ProjectsScrollPane extends JScrollPane {
        public ProjectsScrollPane(JPanel scrollPanel) {
            super(scrollPanel);
            configureScrollPane();
        }

        private void configureScrollPane() {
            this.setPreferredSize(new Dimension(500, 500));
            this.getVerticalScrollBar().setUnitIncrement(16);
            this.setBackground(Color.decode(Colors.bgColor));
            this.setBorder(BorderFactory.createEmptyBorder());

            Dimension d = new Dimension(12, this.getVerticalScrollBar().getPreferredSize().height);
            this.getVerticalScrollBar().setPreferredSize(d);
            this.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
                @Override
                protected void configureScrollBarColors() {
                    this.thumbColor = Color.decode((Colors.ScrollbarColor));
                    this.trackColor = Color.decode((Colors.ScrollbarThumbColor));
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
        }
    }


    public class ProjectsPanel extends JPanel {
        private List<Project> projects;
        private CardLayout cardLayout;

        public ProjectsPanel(List<Project> projects, CardLayout cardLayout, JPanel MainPanel) {
            this.projects = projects;
            this.cardLayout = cardLayout;
            configurePanel(MainPanel);
        }

        private void configurePanel(JPanel MainPanel) {
            this.setLayout(cardLayout);
            this.setLayout(new GridLayout(0, 2)); 
            this.setBackground(Color.decode(Colors.bgColor));
            this.setBorder(BorderFactory.createEmptyBorder(40, 20, 10, 50));
            for (Project project : projects) {
                this.add(new PostPanel(project, MainPanel));
            }
        }
    }

}
