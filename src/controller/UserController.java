package controller;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.UserManager;


public class UserController {
    
    protected String username;
    protected String email;
    protected String password;
    protected String userType;
    private static List<String> currentUser;
    private static UserManager userManager = new UserManager();

    public UserController() {
        UserController.userManager = new UserManager();
    }

    //#region Validations
    public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9]+([.-_]?[A-Za-z0-9]+)*@[A-Za-z0-9]+([.-]?[A-Za-z0-9]+)*\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public boolean login(String email, String enteredPassword) {
        if (userManager.login(email, enteredPassword)) {
           currentUser = userManager.getUserData();
           return true;
        } else {
           return false;
        }
    }
    
    public boolean signUp(String username, String email, String password, String userType) {
        if (userManager.signUp(username, email, password, userType)) {
            return true;
        } else {
            return false;
        }
    }

    public int handleLogin(String email, String password) {
        boolean emailBol = isValidEmail(email);
        if (emailBol) {
            if (password.equals("") || password.length() < 8) {
                return 3; //! código de error: contraseña no válida
            } else if (login(email, password)) {
                return 0; //* código de éxito
            } else {
                return 1; //! código de error: usuario o contraseña incorrectos
            }
        } else {
            return 2; //! código de error: email no válido
        }
    }

    public int handleSignUp(String username, String email, String password, String userType) {
        boolean usernameBol = !username.isEmpty() && username.matches("[a-zA-Z ]*"); // verifica si el campo username está lleno y solo contiene letras y espacios
        boolean emailBol = isValidEmail(email); // verifica si el email es válido
        boolean passwordBol = !password.isEmpty() && password.length() >= 8; // verifica si el campo password está lleno y tiene al menos 8 caracteres
        boolean userTypeBol = userType != null && !userType.isEmpty();
        
        // cambia el valor de userType a "norm" o "dev" si es "Normal" o "Desarrollador" respectivamente
        if ("Normal".equals(userType)) {
            userType = "normal";
        } else if ("Desarrollador".equals(userType)) {
            userType = "dev";
        }

        if (!usernameBol) {
            System.out.println("Error: username no válido");
            return 1; // código de error: username no válido
        } else if (!emailBol) {
            System.out.println("Error: email no válido");
            return 2; // código de error: email no válido
        } else if (!passwordBol) {
            System.out.println("Error: contraseña no válida");
            return 3; // código de error: contraseña no válida
        } else if (!userTypeBol) {
            System.out.println("Error: userType no válido");
            return 4; // código de error: userType no válido
        } else if (signUp(username, email, password, userType)) {
            System.out.println("Éxito: usuario creado");
            return 0; // código de éxito total
        } else {
            System.out.println("Error: el usuario ya existe");
            return 5; // código de error: el usuario ya existe
        }
    }
    
    //logout
    public static void logout(){
        currentUser = null;
    }
    //#endregion


    //#region Getters and Setters
    public static String getUsername() {
        return currentUser.get(0);
    }
    public static String getEmail() {
        return currentUser.get(1);
    }
    public static String getUserType() {
        return currentUser.get(2);
    }
    public static boolean verify(String email, String password) {
        return userManager.login(email, password);
    }
    //#endregion

}
