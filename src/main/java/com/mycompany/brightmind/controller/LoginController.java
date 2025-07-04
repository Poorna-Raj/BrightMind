/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brightmind.controller;

import com.mycompany.brightmind.model.DisplayDAO;
import com.mycompany.brightmind.model.MarksDAO;
import com.mycompany.brightmind.model.StudentDAO;
import com.mycompany.brightmind.model.SubjectDAO;
import com.mycompany.brightmind.model.User;
import com.mycompany.brightmind.model.UserDAO;
import com.mycompany.brightmind.view.Dashboard;
import com.mycompany.brightmind.view.DisplayPanel;
import com.mycompany.brightmind.view.LoginView;
import com.mycompany.brightmind.view.MarksPanel;
import com.mycompany.brightmind.view.StudentPanel;
import com.mycompany.brightmind.view.SubjectPanel;
import javax.swing.JOptionPane;

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
        
        this.view.getBtnLogin().addActionListener(e -> authenticate());
    }

    private void authenticate() {
        String email = view.getTxtEmail().getText();
        String password = view.getTxtPassword().getText();
        
        if(email.isBlank() || email.isEmpty() || password.isBlank() || password.isEmpty()){
            JOptionPane.showMessageDialog(view,"Please enter both email and password.","Authentication Failed",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
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
            
            DisplayPanel displayPanel = new DisplayPanel();
            DisplayDAO displayDAO = new DisplayDAO();
            DisplayController displayController = new DisplayController(displayPanel,displayDAO);
            
            Dashboard dashboard = new Dashboard(studentPanel,subjectPanel,marksPanel,displayPanel);
            DashboardController dashboardController = new DashboardController(dashboard, studentController,subjectController,marksController,displayController);

            dashboard.setTitle("BrightMind - Dashboard");
            dashboard.setLocationRelativeTo(null);
            dashboard.setVisible(true);
            view.hide();
        });
        }
        else{
            JOptionPane.showMessageDialog(view,"Invalid Email or Password","Authentication Failed",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
}
