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
    private SubjectController subjectController;

    public DashboardController(Dashboard dashboard, StudentController studentController, SubjectController subjectController) {
        this.dashboard = dashboard;
        this.studentController = studentController;
        this.subjectController = subjectController;
        
        this.dashboard.getBtnStudentMng().addActionListener(e-> loadStudentMng());
        this.dashboard.getBtnSubjectMng().addActionListener(e ->loadSubjectMng());
        
    }
    
    public void loadStudentMng(){
        dashboard.switchView("studentPanel");
        System.out.println("Switched to Student Panel");
        studentController.loadStudents();
    }
    public void loadSubjectMng(){
        dashboard.switchView("subjectPanel");
        System.out.println("Switched to Subject Panel");
    }
}
