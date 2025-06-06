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
        LoginView view = new LoginView();
        view.setVisible(true);
        UserDAO userDAO = new UserDAO();
        LoginController controller = new LoginController(view,userDAO);
    }
}
