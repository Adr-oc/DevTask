package view;

import javax.swing.*;
import java.awt.*;

import static view.Colors.Primary;
import static view.Colors.bgColor;

public class VistaMostrarProyecto extends JFrame {
    public static void Vista() {
        JFrame frame = new JFrame("Project Name");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(1200, 700));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizar la ventana
        frame.setExtendedState(JFrame.NORMAL); // Reducir el tamaño de la ventana
        frame.setBackground(Color.decode(Colors.bgCard));

        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel projectName = new JLabel("Project Name");
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
        topPanel.add(returnButton, BorderLayout.EAST);
        frame.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.decode(Colors.bgCard));

        JLabel author = new JLabel("@Author");
        author.setFont(new Font("Consolas", Font.BOLD, 21));
        author.setForeground(Color.decode("#d1ddc5"));
        topPanel.add(author, BorderLayout.BEFORE_LINE_BEGINS);

        JPanel languagesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String[] languages = {"Python", "Java", "C++"};
        for (String language : languages) {
            JButton languageButton = new JButton(language);
            languageButton.setFocusPainted(false);
            languageButton.setForeground(Color.decode("#E0A8D1"));
            languageButton.setBackground(Color.decode(Colors.bgCard));
            languageButton.setFont(new Font("Consolas", Font.PLAIN, 17));
            languageButton.setBorder(new RoundedBorder(20));
            languageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            languageButton.setPreferredSize(new Dimension(100, 40));
            languagesPanel.add(languageButton);
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

        JLabel descriptionTitle = new JLabel("Description Title");
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

        JTextArea descriptionBody = new JTextArea("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
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

        JButton applyButton = new JButton("Aplicar");
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

        descriptionPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 50));
        centerPanel.add(descriptionPanel, BorderLayout.CENTER);

        frame.add(centerPanel, BorderLayout.CENTER);

        frame.getContentPane().setBackground(Color.decode(Colors.bgCard));
        frame.pack();
        frame.setVisible(true);
    }

}
