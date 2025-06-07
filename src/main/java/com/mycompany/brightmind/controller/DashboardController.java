/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brightmind.controller;

import com.mycompany.brightmind.view.Dashboard;
import com.mycompany.brightmind.view.StudentPanel;
import java.awt.CardLayout;

/**
 *
 * @author Poorna
 */
public class DashboardController {
    private Dashboard dashboard;
    private StudentController studentController;

    public DashboardController(Dashboard dashboard, StudentController studentController) {
        this.dashboard = dashboard;
        this.studentController = studentController;
        
        this.dashboard.getBtnStudentMng().addActionListener(e-> loadStudentMng());
    }
    
    public void loadStudentMng(){
        dashboard.switchView("studentPanel");
        System.out.println("Switched to Student Panel");
        studentController.loadStudents();
    }
}
