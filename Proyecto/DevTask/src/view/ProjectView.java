package view;

import javax.swing.*;

import controller.ProjectController;
import controller.UserController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class ProjectView extends JFrame {

    public static void GUI(String IDProyect, String name, String description, String email, String authortext, String devs, String[] languagestext, String userType) throws IOException {
        JFrame frame = new JFrame("Project Name");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());
        frame.setSize(1024, 620);
        frame.setMinimumSize(new Dimension(1024, 620));
        frame.getContentPane().setBackground(Color.decode(Colors.bgColor));
    
        // Agregar un WindowListener
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                Dashboard dashboard = new Dashboard();
                dashboard.GUI();
            }
        });

        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel projectName = new JLabel(name);
        projectName.setFont(new Font("Consolas", Font.BOLD, 30));
        projectName.setForeground(Color.decode("#b9f979"));

        topPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 0, 50));
        topPanel.add(projectName, BorderLayout.PAGE_START);
        topPanel.setBackground(Color.decode(Colors.bgCard));

        JButton returnButton = new JButton("Volver");
        returnButton.setFocusPainted(false);
        returnButton.setForeground(Color.decode("#a8e0b7"));
        returnButton.setBackground(Color.decode(Colors.bgCard));
        returnButton.setFont(new Font("Consolas", Font.PLAIN, 17));
        returnButton.setBorder(new RoundedBorder(20));
        returnButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        returnButton.setPreferredSize(new Dimension(100, 40));
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Dashboard dashboard = new Dashboard();
                dashboard.GUI();
            }
        });
        topPanel.add(returnButton, BorderLayout.EAST);
        frame.add(topPanel, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.decode(Colors.bgCard));

        PanelRound panelRound = new PanelRound();
        panelRound.setBackground(Color.decode(Colors.bgPrimary));
        panelRound.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        panelRound.setLayout(new BorderLayout());
        int borderRadiuspan = 20;
        panelRound.setRoundTopLeft(borderRadiuspan);
        panelRound.setRoundTopRight(borderRadiuspan);
        panelRound.setRoundBottomLeft(borderRadiuspan);
        panelRound.setRoundBottomRight(borderRadiuspan);
        topPanel.add(panelRound, BorderLayout.BEFORE_LINE_BEGINS);

        JLabel author = new JLabel(authortext);
        author.setFont(new Font("Consolas", Font.BOLD, 21));
        author.setForeground(Color.decode(Colors.TextColorPrimary));
        panelRound.add(author, BorderLayout.PAGE_START);

        JPanel languagesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String[] languages = languagestext;
        for (String language : languages) {
            PanelRound languagePanel = new PanelRound();
            languagePanel.setPreferredSize(new Dimension(100, 40));
            int borderRadius = 40;
            languagePanel.setRoundTopLeft(borderRadius);
            languagePanel.setRoundTopRight(borderRadius);
            languagePanel.setRoundBottomLeft(borderRadius);
            languagePanel.setRoundBottomRight(borderRadius);
            languagePanel.setBackground(Color.decode(Colors.bgSecondary));
            languagePanel.setBorder(BorderFactory.createEmptyBorder(8, 10, 0, 10));
            
            JLabel languageLabel = new JLabel(language);
            languageLabel.setForeground(Color.decode(Colors.TextColorSecondary));
            languageLabel.setFont(new Font("Consolas", Font.PLAIN, 17));
            
            languagePanel.add(languageLabel);
            languagesPanel.add(languagePanel);
        }
        languagesPanel.setBackground(Color.decode(Colors.bgCard));
        languagesPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        topPanel.add(languagesPanel, BorderLayout.AFTER_LAST_LINE);



        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new GridBagLayout());
        descriptionPanel.setBackground(Color.decode(Colors.bgCard));

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.insets = new Insets(0, 0, 10, 0);

        JLabel descriptionTitle = new JLabel("Descripcion");
        descriptionTitle.setFont(new Font("Consolas", Font.BOLD, 20));
        descriptionTitle.setForeground(Color.decode("#7980bf"));
        descriptionTitle.setBackground(Color.decode(Colors.bgCard));
        descriptionTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1.0;
        c.insets = new Insets(0, 0, 10, 0);
        descriptionTitle.setHorizontalAlignment(SwingConstants.CENTER);
        descriptionPanel.add(descriptionTitle, c);

        JTextArea descriptionBody = new JTextArea(description);
        descriptionBody.setFont(new Font("Consolas", Font.PLAIN, 16));
        descriptionBody.setForeground(Color.decode("#e6e8ee"));
        descriptionBody.setBackground(Color.decode(Colors.bgCard));
        descriptionBody.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        descriptionBody.setLineWrap(true);
        descriptionBody.setWrapStyleWord(true);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.gridheight = 1;
        descriptionPanel.add(descriptionBody, c);

        if(userType.equals("dev")){
        JButton applyButton = new JButton();
        ProjectController projectController = new ProjectController();
        if(projectController.isDevAssignedToProject(IDProyect, UserController.getEmail())) {
            applyButton.setText("Ya estas asignado");
            applyButton.setForeground(Color.decode(Colors.Succes));
        } else {
            applyButton.setText("Aplicar");
            applyButton.setForeground(Color.decode(Colors.Primary));
        }

        applyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (applyButton.getText().equals("Ya estas asignado")) {
                    applyButton.setForeground(Color.decode(Colors.Error));
                } else {
                    applyButton.setForeground(Color.decode(Colors.Primary));
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (applyButton.getText().equals("Ya estas asignado")) {
                    applyButton.setForeground(Color.decode(Colors.Succes));
                } else {
                    applyButton.setForeground(Color.decode(Colors.TextColor));
                }
            }
        });

        applyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String buttonText = ((JButton) e.getSource()).getText();

                if (buttonText.equals("Aplicar")) {
                    try {
                        ProjectController projectController = new ProjectController();
                        projectController.addDevToProject(IDProyect, UserController.getEmail());
                        JOptionPane.showMessageDialog(null, "Desarrollador asignado al proyecto.");
                        frame.dispose();
                        Dashboard dashboard = new Dashboard();
                        dashboard.GUI();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else if (buttonText.equals("Ya estas asignado")) {
                    try {
                        String password = JOptionPane.showInputDialog("Ingrese su contraseña:");
                        if (password != null && UserController.verify(UserController.getEmail(),password)){ 
                            ProjectController projectController = new ProjectController();
                            projectController.removeDevFromProject(IDProyect, UserController.getEmail());
                            JOptionPane.showMessageDialog(null, "Desarrollador eliminado del proyecto.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Contraseña incorrecta. No se pudo eliminar al desarrollador del proyecto.");
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        applyButton.setFocusPainted(false);
        applyButton.setForeground(Color.decode("#e8eeef"));
        applyButton.setBackground(Color.decode(Colors.bgCard));
        applyButton.setFont(new Font("Consolas", Font.PLAIN, 17));
        applyButton.setBorder(new RoundedBorder(20));
        applyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));



        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        c.gridheight = 1;
        descriptionPanel.add(applyButton, c);
        }


        descriptionPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 50));
        centerPanel.add(descriptionPanel, BorderLayout.CENTER);

        frame.add(centerPanel, BorderLayout.CENTER);

        frame.getContentPane().setBackground(Color.decode(Colors.bgCard));
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

}