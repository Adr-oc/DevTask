//version 1.0
import model.UserManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        //clear screen
        System.out.print("\033[H\033[2J");
        


        //Ejemplo como usar login y singUp
        //===============Inicia EJEMPLO=====================//

        //scanner, Usermanager y variables necesarias para el ejemplo
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();
        String user,email,password,userType;


        //example of Sign-up
        System.out.println("==========Sign-up=========");
        System.out.print("Enter username: ");
        user = scanner.nextLine();

        //email
        System.out.print("Enter email: ");
        email = scanner.nextLine();

        //password
        System.out.print("Enter password: ");
        password = scanner.nextLine();

        //user type
        System.out.print("Enter user type:  1.Dev   2.Normal: ");
        int userTypeint = scanner.nextInt();
        userType = "";
        switch (userTypeint) {
            case 1:
                userType = "dev";
                break;
            case 2:
                userType = "normal";
                break;
        }

        //se llama a la funcion signUp del userManager
        userManager.signUp(user, email, password, userType);


        //clear screen
        System.out.print("\033[H\033[2J");


        //example of login
        System.out.println("==========Login=========");
        System.out.print("Enter username:");
        scanner.nextLine();
        user = scanner.nextLine();
        System.out.print("Enter password:");
        password = scanner.nextLine();

        //se llama a la funcion login del userManager
        userManager.login(user, password);
        //===============Termina EJEMPLO=====================//

    }
}