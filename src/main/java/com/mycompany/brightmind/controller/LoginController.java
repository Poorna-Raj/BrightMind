/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brightmind.controller;

import com.mycompany.brightmind.model.MarksDAO;
import com.mycompany.brightmind.model.StudentDAO;
import com.mycompany.brightmind.model.SubjectDAO;
import com.mycompany.brightmind.model.User;
import com.mycompany.brightmind.model.UserDAO;
import com.mycompany.brightmind.view.Dashboard;
import com.mycompany.brightmind.view.LoginView;
import com.mycompany.brightmind.view.MarksPanel;
import com.mycompany.brightmind.view.StudentPanel;
import com.mycompany.brightmind.view.SubjectPanel;

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
            
            SubjectPanel subjectPanel = new SubjectPanel();
            SubjectDAO subjectDAO = new SubjectDAO();
            SubjectController subjectController = new SubjectController(subjectPanel, subjectDAO);
            
            MarksPanel marksPanel = new MarksPanel();
            MarksDAO marksDAO = new MarksDAO();
            MarksController marksController = new MarksController(marksPanel,marksDAO);
            
            Dashboard dashboard = new Dashboard(studentPanel,subjectPanel,marksPanel);
            DashboardController dashboardController = new DashboardController(dashboard, studentController,subjectController,marksController);

            dashboard.setVisible(true);
            view.hide();
        });
        }
        else{
            view.txtMessage.setText("Login Failed");
        }
    }
    
    
}
