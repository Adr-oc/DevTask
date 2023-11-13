package model;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    // Attributes
    private List<User> users;
    private String csvFileName = "src/db/users.csv";


    // Constructor (carga los archivos del csv))
    public UserManager() {
        users = new ArrayList<>();
        loadUsersFromCSV();
    }


    // Sign-up - crea un nuevo usuario
    public boolean signUp(String username, String email, String password, String userType) {
        // Verifica si el usuario ya existe si ya existe no lo crea
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                System.out.println("Username or email is already taken.");
                return false;
            }
        }
    
        // Crea el usuario dependiendo del tipo
        if (userType.equals("dev")) {
            users.add(new DevUser(username, email, password, true));
            System.out.println("Dev user created."); // Debug statement 2
        } else if (userType.equals("normal")) {
            users.add(new NormalUser(username, email, password, true));
            System.out.println("Normal user created."); // Debug statement 2
        } else {
            System.out.println("Invalid user type.");
            return false;
        }
        
        // Guarda los usuarios en el csv
        saveUserToCSV(users.get(users.size() - 1));
        System.out.println("Users saved to CSV."); // Debug statement 3
        System.out.println("Sign-up successful.");
        return true;
    }


    //Login
    public boolean login(String email, String enteredPassword){
        User loginUser = null;
        // Busca el usuario en la lista que cargo del csv
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                loginUser = user;
                break;
            }
        }

        // Verifica si el usuario existe y si la contraseña es correcta
        if (loginUser != null && loginUser.login(enteredPassword)) {
            System.out.println("Login successful.");
             return true;
        } else {
            System.out.println("Login failed.");
            return false;
        }
    }


    //!!!!Funcion temporal solo para desarrollar no implementar en el proyecto final!!!!!!!
    public List<User> getUsers() {
        return users;
    }


    // Load users from CSV file
    private void loadUsersFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userAttributes = line.split(",");
                if (userAttributes.length == 4) { // Check if there are four attributes
                    String username = userAttributes[0];
                    String email = userAttributes[1];
                    String password = userAttributes[2];
                    String userType = userAttributes[3];
                    if (userType.equals("dev"))
                        users.add(new DevUser(username, email, password,false));
                    else if (userType.equals("normal"))
                        users.add(new NormalUser(username, email, password,false));
                    else {
                        System.out.println("Invalid user type: " + userType);
                    }
                } else {
                    System.out.println("Invalid CSV line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading users from CSV file: " + e.getMessage());
        }
    }
    
    // Save users to CSV file
    private void saveUserToCSV(User user) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(csvFileName, true))) {
            if (user instanceof DevUser){
                pw.println(user.getUsername() + "," + user.getEmail() + "," + user.getPassword() + "," + "dev");
            } else if (user instanceof NormalUser){
                pw.println(user.getUsername() + "," + user.getEmail() + "," + user.getPassword() + "," + "normal");
            }
        } catch (IOException e) {
            System.out.println("Error writing user to CSV file: " + e.getMessage());
        }
    }

}
