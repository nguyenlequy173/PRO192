
package service;

import model.User;
import util.FileService;
import java.util.ArrayList;
import java.util.List;

public class AuthService {

    private final FileService fileService;
    private final String USER_FILE = "data/users.txt";

    public AuthService() {
        fileService = new FileService();
    }

    // Get all users from users.txt
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        List<String> lines = fileService.readFile(USER_FILE);

        for (String line : lines) {
            try {
                String[] parts = line.split("\\|", -1);

                if (parts.length != 9) {
                    continue;
                }

                long userId = Long.parseLong(parts[0]);
                String firstName = parts[1];
                String lastName = parts[2];
                String userName = parts[3];
                String password = parts[4];
                String role = parts[5];
                String fullName = parts[6];
                String email = parts[7];

                Long memberId = null;
                if (!parts[8].isEmpty()) {
                    memberId = Long.parseLong(parts[8]);
                }

                User user = new User(
                    userId,
                    firstName,
                    lastName,
                    userName,
                    password,
                    role,
                    fullName,
                    email,
                    memberId
                );

                users.add(user);

            } catch (NumberFormatException e) {
                System.out.println("Invalid user data: " + line);
            }
        }

        return users;
    }

    // Register a new user
    public boolean register(User user) {
        if (user == null) {
            return false;
        }

        if (user.getUserName() == null
                || user.getUserName().trim().isEmpty()) {
            System.out.println("Username cannot be empty.");
            return false;
        }

        if (user.getPassword() == null
                || user.getPassword().isEmpty()) {
            System.out.println("Password cannot be empty.");
            return false;
        }

        // Check whether the username already exists
        for (User existingUser : getAllUsers()) {
            if (existingUser.getUserName()
                    .equalsIgnoreCase(user.getUserName().trim())) {
                System.out.println("Username already exists.");
                return false;
            }
        }

        // Generate a new user ID
        long newId = 1;

        for (User existingUser : getAllUsers()) {
            if (existingUser.getUserId() >= newId) {
                newId = existingUser.getUserId() + 1;
            }
        }

        user.setUserId(newId);

        // Prepare the line to save
        String memberIdText = user.getMemberId() == null
                ? ""
                : String.valueOf(user.getMemberId());

        String line = user.getUserId() + "|"
                + safe(user.getFirstName()) + "|"
                + safe(user.getLastName()) + "|"
                + user.getUserName().trim() + "|"
                + user.getPassword() + "|"
                + safe(user.getRole()) + "|"
                + safe(user.getFullName()) + "|"
                + safe(user.getEmail()) + "|"
                + memberIdText;

        // Save the new user
        List<String> lines = fileService.readFile(USER_FILE);
        lines.add(line);

        return fileService.writeFile(USER_FILE, lines);
    }

    // Login
    public User login(String userName, String password) {
        if (userName == null || password == null) {
            return null;
        }

        for (User user : getAllUsers()) {
            boolean usernameMatches = user.getUserName()
                    .equalsIgnoreCase(userName.trim());

            boolean passwordMatches = user.getPassword()
                    .equals(password);

            if (usernameMatches && passwordMatches) {
                return user;
            }
        }

        return null;
    }

    // Prevent the "|" character from breaking the file format
    private String safe(String value) {
        if (value == null) {
            return "";
        }

        return value.replace("|", "/");
    }
    
public boolean updateProfile(User updatedUser) {
    if (updatedUser == null) {
        return false;
    }

    List<String> lines = fileService.readFile(USER_FILE);
    List<String> updatedLines = new ArrayList<>();
    boolean found = false;

    for (String line : lines) {
        String[] parts = line.split("\\|", -1);

        if (parts.length != 9) {
            updatedLines.add(line);
            continue;
        }

        try {
            long currentUserId = Long.parseLong(parts[0]);

            if (currentUserId == updatedUser.getUserId()) {
                parts[1] = safe(updatedUser.getFirstName());
                parts[2] = safe(updatedUser.getLastName());
                parts[6] = safe(updatedUser.getFullName());
                parts[7] = safe(updatedUser.getEmail());

                line = String.join("|", parts);
                found = true;
            }

        } catch (NumberFormatException e) {
            // Keep invalid data unchanged
        }

        updatedLines.add(line);
    }

    if (!found) {
        System.out.println("User not found.");
        return false;
    }

    return fileService.writeFile(USER_FILE, updatedLines);
}

}
