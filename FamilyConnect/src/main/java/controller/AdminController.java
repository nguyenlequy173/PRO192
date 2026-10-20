
package controller;

import java.util.List;
import model.User;
import service.AdminService;

public class AdminController {

    private final AdminService adminService;

    public AdminController() {
        adminService = new AdminService();
    }

    // Get all user accounts
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    // Update the status of a post
    public boolean updatePostStatus(long postId, String newStatus) {
        return adminService.updatePostStatus(postId, newStatus);
    }
}
