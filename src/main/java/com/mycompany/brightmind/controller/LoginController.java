/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brightmind.controller;

import com.mycompany.brightmind.model.StudentDAO;
import com.mycompany.brightmind.model.User;
import com.mycompany.brightmind.model.UserDAO;
import com.mycompany.brightmind.view.Dashboard;
import com.mycompany.brightmind.view.LoginView;
import com.mycompany.brightmind.view.StudentPanel;

/**
 *
 * @author Poorna
 */
public class LoginController {
    private LoginView view;
    private User user;
    private UserDAO userDAO;

    public LoginController(LoginView view, UserDAO userDAO) {
        this.view = view;
        this.userDAO = userDAO;
        
        this.view.btnLogin.addActionListener(e -> authenticate());
    }

    private void authenticate() {
        String email = view.txtEmail.getText();
        String password = view.txtPassword.getText();
        
        user = new User(email,password);
        
        if(userDAO.validateUser(user)){
            java.awt.EventQueue.invokeLater(() -> {
            StudentPanel studentPanel = new StudentPanel();
            StudentDAO studentDAO = new StudentDAO();

            StudentController studentController = new StudentController(studentPanel, studentDAO);
            Dashboard dashboard = new Dashboard(studentPanel);
            DashboardController dashboardController = new DashboardController(dashboard, studentController);

            dashboard.setVisible(true);
            view.hide();
        });
        }
        else{
            view.txtMessage.setText("Login Failed");
        }
    }
    
    
}
