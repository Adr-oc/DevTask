package view;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import controller.UserController;

public class SingUpVista {

    //#region SingUpVista
    public String username,email,password,userType = null;
    private String Green = "#a8e65f";
    private String Succes = "#2ECC71";
    private String Red = "#E74C3C";
    private String Gray = "#CCCCCC";
    private String bgColor = "#191A23";
    private boolean usernameBol, emailBol, passwordBol, userTypeBol = true;
    public UserController userController = new UserController();

    private void setEmailFocusListeners(JTextField emailTxtBox, JLabel tituloEmail) {
        emailTxtBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailTxtBox.setBorder(new MatteBorder(0, 0, 2, 0, Color.decode(Green)));
                emailTxtBox.setForeground(Color.decode(Green));
                tituloEmail.setFont(new Font("Consolas", Font.BOLD, 18));
                tituloEmail.setForeground(Color.decode(Green));
                tituloEmail.setText("Email:");
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (userController.isValidEmail(emailTxtBox.getText())) {
                    emailTxtBox.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Gray)));
                    emailTxtBox.setForeground(Color.decode(Succes));
                    tituloEmail.setFont(new Font("Consolas", Font.PLAIN, 17));
                    tituloEmail.setForeground(Color.decode(Gray));
                    tituloEmail.setText("Email:");
                }else{
                    emailTxtBox.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Red)));
                    emailTxtBox.setForeground(Color.decode(Red));
                    tituloEmail.setFont(new Font("Consolas", Font.PLAIN, 17));
                    tituloEmail.setForeground(Color.decode(Red));
                    tituloEmail.setText("Email:  el email no es valido");
                }
            }
        });
    }


    private void setFocusListeners(JTextField userNameTxtBox, JLabel tituloNombre) {
        userNameTxtBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                userNameTxtBox.setBorder(new MatteBorder(0, 0, 2, 0, Color.decode(Green)));
                userNameTxtBox.setForeground(Color.decode(Green));
                tituloNombre.setFont(new Font("Consolas", Font.BOLD, 18));
                tituloNombre.setForeground(Color.decode(Green));
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (userNameTxtBox.getText().equals("") || userNameTxtBox.getText().equals(null)) {
                    userNameTxtBox.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Red)));
                    userNameTxtBox.setForeground(Color.decode(Red));
                    tituloNombre.setFont(new Font("Consolas", Font.PLAIN, 17));
                    tituloNombre.setForeground(Color.decode(Red));
                }else{
                    userNameTxtBox.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Gray)));
                    userNameTxtBox.setForeground(Color.decode(Succes));
                    tituloNombre.setFont(new Font("Consolas", Font.PLAIN, 17));
                    tituloNombre.setForeground(Color.decode(Gray));
                }
 
            }
        });
    }


    private void setFocusListenersForComboBox(JComboBox<String> userTypeComboBox, JLabel titulo) {
        userTypeComboBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                userTypeComboBox.setBorder(new MatteBorder(2, 2, 2, 2, Color.decode(Green)));
                userTypeComboBox.setForeground(Color.decode(Green));
                titulo.setFont(new Font("Consolas", Font.BOLD, 18));
                titulo.setForeground(Color.decode(Green));
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (userTypeComboBox.getSelectedItem().equals("")) {
                    userTypeComboBox.setBorder(new MatteBorder(2, 2, 2, 2, Color.decode(Red)));
                    userTypeComboBox.setForeground(Color.decode(Red));
                    titulo.setFont(new Font("Consolas", Font.PLAIN, 17));
                    titulo.setForeground(Color.decode(Red));
                }else{
                    userTypeComboBox.setBorder(new MatteBorder(2, 2, 2, 2, Color.decode(Gray)));
                    userTypeComboBox.setForeground(Color.decode(Succes));
                    titulo.setFont(new Font("Consolas", Font.PLAIN, 17));
                    titulo.setForeground(Color.decode(Gray));
                
                }
            }
        });
    }


    public void GUI() {

        JFrame frame = new JFrame("SingUp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setSize(500, 700);
        frame.getContentPane().setBackground(Color.decode(bgColor));

        GridBagConstraints restricciones = new GridBagConstraints();
        restricciones.weightx = 1.0;
        restricciones.gridx = 0;
        restricciones.gridy = 0;
        restricciones.gridwidth = 2; // Assuming you have 2 columns in your layout
        restricciones.anchor = GridBagConstraints.CENTER;
        JLabel tituloFormulario = new JLabel("Crear Cuenta", SwingConstants.CENTER);
        tituloFormulario.setForeground(Color.decode("#8B85C1"));
        tituloFormulario.setFont(new Font("Consolas", Font.BOLD, 28));
        frame.add(tituloFormulario, restricciones);

        restricciones.gridy++;
        restricciones.insets = new Insets(0, 50, 20, 60);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.decode(bgColor));
        JLabel labelPregunta = new JLabel("¿Ya tienes una cuenta?");
        labelPregunta.setForeground(Color.decode(Gray));
        labelPregunta.setFont(new Font("Consolas", Font.PLAIN, 12));

        JButton tienesCuentaButton = new JButton("Inicia Sesión");
        tienesCuentaButton.setForeground(Color.decode(Green));
        tienesCuentaButton.setBackground(Color.decode(bgColor));
        tienesCuentaButton.setFont(new Font("Consolas", Font.PLAIN, 12));
        tienesCuentaButton.setBorderPainted(false);
        tienesCuentaButton.setFocusPainted(false);
        tienesCuentaButton.setContentAreaFilled(false);
        tienesCuentaButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tienesCuentaButton.addActionListener(e -> {
            frame.dispose();
            LoginVista loginVista = new LoginVista();
            loginVista.GUI();
        });
        tienesCuentaButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tienesCuentaButton.setForeground(Color.decode("#8B85C1"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                tienesCuentaButton.setForeground(Color.decode(Green));
            }
        });

        panel.add(labelPregunta);
        panel.add(tienesCuentaButton);
        frame.add(panel, restricciones);

        // Reset to default for other components
        restricciones.gridwidth = 1;
        restricciones.weightx = 0.5;
        restricciones.gridy = 0;
        restricciones.anchor = GridBagConstraints.WEST;
        // Nombre
        restricciones.insets = new Insets(90, 50, 0, 60);
        restricciones.gridy++;
        JLabel tituloNombre = new JLabel("Nombre y Apellido:");
        tituloNombre.setForeground(Color.decode(Gray));
        tituloNombre.setFont(new Font("Consolas", Font.PLAIN, 17));
        frame.add(tituloNombre, restricciones);
        JTextField nombreTxtBox = new JTextField();
        nombreTxtBox.setForeground(Color.decode(Gray));
        nombreTxtBox.setBackground(Color.decode(bgColor));
        nombreTxtBox.setFont(new Font("Consolas", Font.PLAIN, 17));
        nombreTxtBox.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Gray)));
        setFocusListeners(nombreTxtBox, tituloNombre);
        restricciones.fill = GridBagConstraints.HORIZONTAL;
        restricciones.insets = new Insets(0, 50, 0, 60);
        restricciones.gridy++; 
        frame.add(nombreTxtBox, restricciones);

        // Email
        restricciones.insets = new Insets(30, 50, 0, 50);
        restricciones.gridy++;
        JLabel tituloEmail = new JLabel("Email:");
        tituloEmail.setForeground(Color.decode(Gray));
        tituloEmail.setFont(new Font("Consolas", Font.PLAIN, 17));
        frame.add(tituloEmail, restricciones);
        JTextField emailTxtBox = new JTextField();
        emailTxtBox.setForeground(Color.decode(Gray));
        emailTxtBox.setBackground(Color.decode(bgColor));
        emailTxtBox.setFont(new Font("Consolas", Font.PLAIN, 17));
        emailTxtBox.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Gray)));
        setEmailFocusListeners(emailTxtBox, tituloEmail);
        restricciones.fill = GridBagConstraints.HORIZONTAL;
        restricciones.gridy++;
        restricciones.insets = new Insets(0, 50, 0, 50);
        frame.add(emailTxtBox, restricciones);

        // Password
        restricciones.insets = new Insets(30, 50, 0, 50);
        restricciones.gridy++;
        JLabel tituloPassword = new JLabel("Password:");
        tituloPassword.setForeground(Color.decode(Gray));
        tituloPassword.setFont(new Font("Consolas", Font.PLAIN, 17));
        frame.add(tituloPassword, restricciones);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setForeground(Color.decode(Gray));
        passwordField.setBackground(Color.decode(bgColor));
        passwordField.setFont(new Font("Consolas", Font.PLAIN, 17));
        passwordField.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Gray)));
        setFocusListeners(passwordField, tituloPassword);
        restricciones.fill = GridBagConstraints.HORIZONTAL;
        restricciones.insets = new Insets(0, 50, 0, 50);
        restricciones.gridy++;
        frame.add(passwordField, restricciones);


        // Type of User
        restricciones.insets = new Insets(30, 50, 0, 50);
        restricciones.gridy++;
        JLabel tituloTypeOfUser = new JLabel("Tipo de Usuario:");
        tituloTypeOfUser.setForeground(Color.decode(Gray));
        tituloTypeOfUser.setFont(new Font("Consolas", Font.PLAIN, 18));
        frame.add(tituloTypeOfUser, restricciones);
        String[] userTypes = {"","Normal", "Desarrollador"};
        JComboBox<String> userTypeComboBox = new JComboBox<>(userTypes);
        userTypeComboBox.setForeground(Color.decode(Gray));
        userTypeComboBox.setBackground(Color.decode(bgColor));
        userTypeComboBox.setFont(new Font("Consolas", Font.PLAIN, 17));
       
        restricciones.fill = GridBagConstraints.HORIZONTAL;
        restricciones.insets = new Insets(10, 50, 0, 50);
        restricciones.gridy++;
        setFocusListenersForComboBox(userTypeComboBox, tituloTypeOfUser);
        frame.add(userTypeComboBox, restricciones);
        

        //boton enviar
        JButton botonCrearCuenta = new JButton("Crear Cuenta");
        botonCrearCuenta.setForeground(Color.decode(Gray));
        botonCrearCuenta.setBackground(Color.decode(bgColor));
        botonCrearCuenta.setFont(new Font("Consolas", Font.PLAIN, 17));
        botonCrearCuenta.setPreferredSize(new Dimension(200, 50));
        botonCrearCuenta.setMaximumSize(new Dimension(200, 50));
        botonCrearCuenta.setBorder(new RoundedBorder(25));
        botonCrearCuenta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        restricciones.fill = GridBagConstraints.NONE;
        restricciones.gridy++;
        restricciones.gridheight = 1;
        restricciones.anchor = GridBagConstraints.CENTER;
        restricciones.insets = new Insets(60, 10, 10, 10);
        botonCrearCuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonCrearCuenta.setForeground(Color.decode(Green));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonCrearCuenta.setForeground(Color.decode(Gray));
            }
        });
        frame.add(botonCrearCuenta, restricciones);

        botonCrearCuenta.addActionListener(e -> {
            username = nombreTxtBox.getText();
            email = emailTxtBox.getText();
            password = new String(passwordField.getPassword());
            userType = (String) userTypeComboBox.getSelectedItem();
            System.err.println(userType);
            int signUpResult = userController.handleSignUp(username, email, password, userType);
            switch (signUpResult) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Usuario creado exitosamente");
                    frame.dispose();
                    LoginVista loginVista = new LoginVista();
                    loginVista.GUI();
                    break;
                case 1:
                    tituloNombre.setForeground(Color.decode(Red));
                    nombreTxtBox.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Red)));
                    nombreTxtBox.setForeground(Color.decode(Red));
                    break;
                case 2:
                    tituloEmail.setForeground(Color.decode(Red));
                    emailTxtBox.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Red)));
                    emailTxtBox.setForeground(Color.decode(Red));
                    break;
                case 3:
                    tituloPassword.setForeground(Color.decode(Red));
                    tituloPassword.setText("Password: la contraseña > 8 caracteres");
                    passwordField.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Red)));
                    passwordField.setForeground(Color.decode(Red));
                    break;
                case 4:
                    tituloTypeOfUser.setForeground(Color.decode(Red));
                    userTypeComboBox.setBorder(new MatteBorder(2, 2, 2, 2, Color.decode(Red)));
                    userTypeComboBox.setForeground(Color.decode(Red));
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "El usuario ya existe");
                    break;
                default:
                    tituloNombre.setForeground(Color.decode(Green));
                    nombreTxtBox.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Green)));
                    tituloEmail.setForeground(Color.decode(Green));
                    emailTxtBox.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Green)));
                    tituloPassword.setForeground(Color.decode(Green));
                    passwordField.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Green)));
                    tituloTypeOfUser.setForeground(Color.decode(Green));
                    userTypeComboBox.setBorder(new MatteBorder(2, 2, 2, 2, Color.decode(Green)));
                    break;
            }

        });
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    //#endregion


    //#region LoginVista
    public class LoginVista {

        public void GUI() {

            JFrame frame = new JFrame("LogIn");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridBagLayout());
            frame.setSize(500, 700);
            frame.getContentPane().setBackground(Color.decode(bgColor));

            GridBagConstraints restricciones = new GridBagConstraints();
            restricciones.weightx = 1.0;
            restricciones.gridx = 0;
            restricciones.gridy = 0;
            restricciones.gridwidth = 2; // Assuming you have 2 columns in your layout
            restricciones.anchor = GridBagConstraints.CENTER;
            JLabel tituloFormulario = new JLabel("Iniciar Sesión", SwingConstants.CENTER);
            tituloFormulario.setForeground(Color.decode("#8B85C1"));
            tituloFormulario.setFont(new Font("Consolas", Font.BOLD, 28));
            frame.add(tituloFormulario, restricciones);

            restricciones.gridy++;
            restricciones.insets = new Insets(0, 50, 90, 60);
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            panel.setBackground(Color.decode(bgColor));
            JLabel labelPregunta = new JLabel("¿No tienes cuenta?");
            labelPregunta.setForeground(Color.decode(Gray));
            labelPregunta.setFont(new Font("Consolas", Font.PLAIN, 12));

            JButton NotienesCuentaButton = new JButton("Crear una cuenta nueva");
            NotienesCuentaButton.setForeground(Color.decode(Green));
            NotienesCuentaButton.setBackground(Color.decode(bgColor));
            NotienesCuentaButton.setFont(new Font("Consolas", Font.PLAIN, 12));
            NotienesCuentaButton.setBorderPainted(false);
            NotienesCuentaButton.setFocusPainted(false);
            NotienesCuentaButton.setContentAreaFilled(false);
            NotienesCuentaButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            NotienesCuentaButton.addActionListener(e -> {
                frame.dispose();
                SingUpVista singUpVista = new SingUpVista();
                singUpVista.GUI();
            });
            NotienesCuentaButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    NotienesCuentaButton.setForeground(Color.decode("#8B85C1"));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    NotienesCuentaButton.setForeground(Color.decode(Green));
                }
            });

            panel.add(labelPregunta);
            panel.add(NotienesCuentaButton);
            frame.add(panel, restricciones);

            // Reset to default for other components
            restricciones.gridwidth = 1;
            restricciones.weightx = 0.5;
            restricciones.gridy = 0;
            restricciones.anchor = GridBagConstraints.WEST;
 
            // Email
            restricciones.insets = new Insets(150, 50, 0, 50);
            restricciones.gridy++;
            JLabel tituloEmail = new JLabel("Email:");
            tituloEmail.setForeground(Color.decode(Gray));
            tituloEmail.setFont(new Font("Consolas", Font.PLAIN, 17));
            frame.add(tituloEmail, restricciones);
            JTextField emailTxtBox = new JTextField();
            emailTxtBox.setForeground(Color.decode(Gray));
            emailTxtBox.setBackground(Color.decode(bgColor));
            emailTxtBox.setFont(new Font("Consolas", Font.PLAIN, 17));
            emailTxtBox.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Gray)));
            setEmailFocusListeners(emailTxtBox, tituloEmail);
            restricciones.fill = GridBagConstraints.HORIZONTAL;
            restricciones.gridy++;
            restricciones.insets = new Insets(0, 50, 0, 50);
            frame.add(emailTxtBox, restricciones);

            // Password
            restricciones.insets = new Insets(30, 50, 0, 50);
            restricciones.gridy++;
            JLabel tituloPassword = new JLabel("Password:");
            tituloPassword.setForeground(Color.decode(Gray));
            tituloPassword.setFont(new Font("Consolas", Font.PLAIN, 17));
            frame.add(tituloPassword, restricciones);
            JPasswordField passwordField = new JPasswordField();
            passwordField.setForeground(Color.decode(Gray));
            passwordField.setBackground(Color.decode(bgColor));
            passwordField.setFont(new Font("Consolas", Font.PLAIN, 17));
            passwordField.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Gray)));
            setFocusListeners(passwordField, tituloPassword);
            restricciones.fill = GridBagConstraints.HORIZONTAL;
            restricciones.insets = new Insets(0, 50, 0, 50);
            restricciones.gridy++;
            frame.add(passwordField, restricciones);


            //boton enviar
            JButton botonIniciarSesion = new JButton("Iniciar Sesión");
            botonIniciarSesion.setForeground(Color.decode(Gray));
            botonIniciarSesion.setBackground(Color.decode(bgColor));
            botonIniciarSesion.setFont(new Font("Consolas", Font.PLAIN, 17));
            botonIniciarSesion.setPreferredSize(new Dimension(200, 50));
            botonIniciarSesion.setMaximumSize(new Dimension(200, 50));
            botonIniciarSesion.setBorder(new RoundedBorder(25));
            botonIniciarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR));
            restricciones.fill = GridBagConstraints.NONE;
            restricciones.gridy++;
            restricciones.gridheight = 1;
            restricciones.anchor = GridBagConstraints.CENTER;
            restricciones.insets = new Insets(100, 10, 10, 10);
            botonIniciarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    botonIniciarSesion.setForeground(Color.decode(Green));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    botonIniciarSesion.setForeground(Color.decode(Gray));
                }
            });

            frame.add(botonIniciarSesion, restricciones);

            botonIniciarSesion.addActionListener(e -> {
                email = emailTxtBox.getText();
                password = new String(passwordField.getPassword());

                int loginResult = userController.handleLogin(email, password);
                switch (loginResult) {
                    case 0:
                        JOptionPane.showMessageDialog(null, "Bienvenido");
                        frame.dispose();
                        Dashboard dashboard = new Dashboard();
                        dashboard.GUI();
                        break;
                    case 1:
                        tituloEmail.setForeground(Color.decode(Red));
                        emailTxtBox.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Red)));
                        emailTxtBox.setForeground(Color.decode(Red));
                        tituloPassword.setForeground(Color.decode(Red));
                        passwordField.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Red)));
                        passwordField.setForeground(Color.decode(Red));
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                        break;
                    case 2:
                        tituloEmail.setForeground(Color.decode(Red));
                        emailTxtBox.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Red)));
                        emailTxtBox.setForeground(Color.decode(Red));
                        break;
                    case 3:
                        tituloPassword.setForeground(Color.decode(Red));
                        passwordField.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(Red)));
                        passwordField.setForeground(Color.decode(Red));
                        break;
                }
            });
            
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        }
    }
}
    //#endregion

