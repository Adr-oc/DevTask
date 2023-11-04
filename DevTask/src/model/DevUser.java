package model;

public class DevUser extends User {


    public DevUser(String username, String email, String password,boolean encrypt) {
        super(username, email, password,encrypt);
    }
    
    String myUsername = getUsername(); // Access the username
    String myEmail = getEmail();       // Access the email
}
