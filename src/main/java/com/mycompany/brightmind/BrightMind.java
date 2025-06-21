/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.brightmind;

import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.brightmind.controller.LoginController;
import com.mycompany.brightmind.model.UserDAO;
import com.mycompany.brightmind.view.LoginView;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Poorna
 */
public class BrightMind {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } 
        catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        UIManager.put( "Button.arc", 999 );
        UIManager.put( "Component.arc", 999 );
        UIManager.put( "ProgressBar.arc", 999 );
        UIManager.put( "TextComponent.arc", 999 );

        java.awt.EventQueue.invokeLater(()->{
            LoginView login = new LoginView();
            UserDAO userDAO = new UserDAO();
            LoginController loginController = new LoginController(login,userDAO);
            login.setTitle("BrightMind - Login Portal");
            login.setLocationRelativeTo(null);
            login.setVisible(true);
        });
    }
}
