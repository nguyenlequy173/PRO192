/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.User;
import service.AuthService;

/**
 *
 * @author admin
 */
public class AuthController {

    private final AuthService authService;

    public AuthController() {
        authService = new AuthService();
    }

    public User login(String username, String password) {
        return authService.login(username, password);
    }

    public boolean register(User user) {
        return authService.register(user);
    }

public boolean updateProfile(User user) {
    return authService.updateProfile(user);
}

}
