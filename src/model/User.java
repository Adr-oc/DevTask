package model;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private String username;
    private String email;
    private String password;

    public String getPassword() {
        return password;
    }

    //Constructor
    public User(String username, String email, String password, boolean encrypt) {
        this.username = username;
        this.email = email;
        if (encrypt)
            this.password = encryptPassword(password);
        else
            this.password = password;
    }

    //Login
    public boolean login(String enteredPassword) {

        // Hash the entered password for comparison
        String hashedEnteredPassword = encryptPassword(enteredPassword);
        
        // Compare the hashedEnteredPassword with the stored password
        boolean loginResult = this.password.equals(hashedEnteredPassword);
        return loginResult;
    }
    

    // Encripta la contra con SHA-256
    private String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hashedPassword) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

}