
package service;

import java.util.ArrayList;
import java.util.List;
import model.Post;
import model.User;
import util.FileService;

public class AdminService {

    private final FileService fileService;
    private final AuthService authService;

    private final String POST_FILE = "data/posts.txt";

    public AdminService() {
        fileService = new FileService();
        authService = new AuthService();
    }

    // Get all user accounts
    public List<User> getAllUsers() {
        return authService.getAllUsers();
    }

    // Update the status of a post
    public boolean updatePostStatus(long postId, String newStatus) {

        if (newStatus == null
                || !(newStatus.equalsIgnoreCase("ACTIVE")
                || newStatus.equalsIgnoreCase("HIDDEN"))) {
            System.out.println("Status must be ACTIVE or HIDDEN.");
            return false;
        }

        List<String> lines = fileService.readFile(POST_FILE);
        List<String> updatedLines = new ArrayList<>();

        boolean found = false;

        for (String line : lines) {
            String[] parts = line.split("\\|", -1);

            if (parts.length != 7) {
                updatedLines.add(line);
                continue;
            }

            try {
                long currentPostId = Long.parseLong(parts[0]);

                if (currentPostId == postId) {
                    parts[5] = newStatus.toUpperCase();
                    line = String.join("|", parts);
                    found = true;
                }

            } catch (NumberFormatException e) {
                // Keep invalid data unchanged
            }

            updatedLines.add(line);
        }

        if (!found) {
            System.out.println("Post not found.");
            return false;
        }

        return fileService.writeFile(POST_FILE, updatedLines);
    }
}
