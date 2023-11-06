package controller;
import model.UserManager;


public class UserController {
    
    protected String username;
    protected String email;
    protected String password;
    protected String userType;
    private UserManager userManager = new UserManager();
    
    public UserController() {
        this.userManager = new UserManager();
    }

    
    public boolean login(String email, String enteredPassword) {
        if (userManager.login(email, enteredPassword)) {
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


}
