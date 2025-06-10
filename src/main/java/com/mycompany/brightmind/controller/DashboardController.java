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
    private MarksController marksController;
    private DisplayController displayController;

    public DashboardController(Dashboard dashboard, StudentController studentController, SubjectController subjectController, MarksController marksController, DisplayController displayController) {
        this.dashboard = dashboard;
        this.studentController = studentController;
        this.subjectController = subjectController;
        this.marksController = marksController;
        this.displayController = displayController;
        
        this.dashboard.getBtnStudentMng().addActionListener(e-> loadStudentMng());
        this.dashboard.getBtnSubjectMng().addActionListener(e ->loadSubjectMng());
        this.dashboard.getBtnMarksMng().addActionListener(e -> loadMarksMng());
        this.dashboard.getBtnDashboard().addActionListener(e -> loadDisplayMng());
        
    }
    
    public void loadStudentMng(){
        dashboard.switchView("studentPanel");
        System.out.println("Switched to Student Panel");
        studentController.loadStudents();
    }
    public void loadSubjectMng(){
        dashboard.switchView("subjectPanel");
        System.out.println("Switched to Subject Panel");
        subjectController.loadSubjects();
    }
    public void loadMarksMng(){
        dashboard.switchView("marksPanel");
        System.out.println("Switched to Marks Panel");
        marksController.loadMarks();
    }
    
    public void loadDisplayMng(){
        dashboard.switchView("displayPanel");
        System.out.println("Switched to Display Panel");
        displayController.loadDashboardSummery();
    }
}
