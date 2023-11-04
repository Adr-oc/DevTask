package model;

public class NormalUser extends User {
    

    public NormalUser(String username, String email, String password,boolean encrypt) {
        super(username, email, password,encrypt);
    }
    String myUsername = getUsername(); // Access the username
    String myEmail = getEmail();       // Access the email
}


