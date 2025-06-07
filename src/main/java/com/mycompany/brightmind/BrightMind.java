/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.brightmind;

import com.mycompany.brightmind.controller.DashboardController;
import com.mycompany.brightmind.controller.LoginController;
import com.mycompany.brightmind.controller.StudentController;
import com.mycompany.brightmind.model.StudentDAO;
import com.mycompany.brightmind.model.UserDAO;
import com.mycompany.brightmind.view.Dashboard;
import com.mycompany.brightmind.view.LoginView;
import com.mycompany.brightmind.view.StudentPanel;

/**
 *
 * @author Poorna
 */
public class BrightMind {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(()->{
            LoginView login = new LoginView();
            UserDAO userDAO = new UserDAO();
            LoginController loginController = new LoginController(login,userDAO);
            
            login.setVisible(true);
        });
    }
}
