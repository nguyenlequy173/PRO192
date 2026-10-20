
package View;

import java.util.Scanner;
import controller.AuthController;
import model.User;

public class Main {

    private final Scanner sc;
    private final AuthController authController;

    public Main() {
        sc = new Scanner(System.in);
        authController = new AuthController();
    }

    public void showMainMenu() {
        int choice;

        do {
            System.out.println("\n===== FAMILYCONNECT =====");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 0);
    }

    private void login() {
        System.out.println("\n===== LOGIN =====");

        System.out.print("Username: ");
        String username = sc.nextLine().trim();

        System.out.print("Password: ");
        String password = sc.nextLine();

        User user = authController.login(username, password);

       
if (user != null) {
    System.out.println("Login successful!");
    System.out.println("Welcome, " + user.getFullName());

    if ("ADMIN".equalsIgnoreCase(user.getRole())) {
        showAdminMenu(user);
    } else {
        showMemberMenu(user);
    }

} else {
    System.out.println("Invalid username or password.");
}

    }

    private void register() {
        System.out.println("\n===== REGISTER =====");

        System.out.print("First name: ");
        String firstName = sc.nextLine().trim();

        System.out.print("Last name: ");
        String lastName = sc.nextLine().trim();

        System.out.print("Username: ");
        String username = sc.nextLine().trim();

        System.out.print("Password: ");
        String password = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine().trim();

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(username);
        user.setPassword(password);
        user.setRole("MEMBER");
        user.setEmail(email);
        user.setMemberId(null);

        boolean success = authController.register(user);

        if (success) {
            System.out.println("Registration successful!");
            System.out.println("You can now log in.");
        } else {
            System.out.println("Registration failed. Please check your input.");
        }
    }

    private int readInt() {
        while (true) {
            String input = sc.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    public static void main(String[] args) {
        Main view = new Main();
        view.showMainMenu();
    }
    
private void showMemberMenu(User user) {
    int choice;

    do {
        System.out.println("\n===== MEMBER MENU =====");
        System.out.println("Welcome, " + user.getFullName());
        System.out.println("1. Update Profile");
        System.out.println("2. Manage Family Members");
        System.out.println("3. View Family Tree");
        System.out.println("4. Manage Posts and Comments");
        System.out.println("5. Manage Family Events");
        System.out.println("0. Logout");
        System.out.print("Enter your choice: ");

        choice = readInt();

        switch (choice) {
            case 1:
                System.out.println("Update Profile - Coming soon.");
                break;
            case 2:
                System.out.println("Manage Family Members - Coming soon.");
                break;
            case 3:
                System.out.println("View Family Tree - Coming soon.");
                break;
            case 4:
                System.out.println("Manage Posts and Comments - Coming soon.");
                break;
            case 5:
                System.out.println("Manage Family Events - Coming soon.");
                break;
            case 0:
                System.out.println("Logged out successfully.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }

    } while (choice != 0);
}

private void showAdminMenu(User user) {
    int choice;

    do {
        System.out.println("\n===== ADMIN MENU =====");
        System.out.println("Welcome, " + user.getFullName());
        System.out.println("1. Moderate Posts");
        System.out.println("2. Manage User Accounts");
        System.out.println("3. View Community Statistics");
        System.out.println("0. Logout");
        System.out.print("Enter your choice: ");

        choice = readInt();

        switch (choice) {
            case 1:
                System.out.println("Moderate Posts - Coming soon.");
                break;
            case 2:
                System.out.println("Manage User Accounts - Coming soon.");
                break;
            case 3:
                System.out.println("Community Statistics - Coming soon.");
                break;
            case 0:
                System.out.println("Logged out successfully.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }

    } while (choice != 0);
}

}
