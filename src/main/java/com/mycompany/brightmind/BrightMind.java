/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.brightmind;

import com.mycompany.brightmind.controller.LoginController;
import com.mycompany.brightmind.model.UserDAO;
import com.mycompany.brightmind.view.LoginView;

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
