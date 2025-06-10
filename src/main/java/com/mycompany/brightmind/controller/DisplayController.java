/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brightmind.controller;

import com.mycompany.brightmind.model.DisplayDAO;
import com.mycompany.brightmind.model.ReportGenrator;
import com.mycompany.brightmind.view.DisplayPanel;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Poorna
 */
public class DisplayController {
    private DisplayPanel displayPanel;
    private DisplayDAO displayDAO;

    public DisplayController(DisplayPanel displayPanel, DisplayDAO displayDAO) {
        this.displayPanel = displayPanel;
        this.displayDAO = displayDAO;
        
        this.displayPanel.getBtnStudent().addActionListener(e -> studentReport());
        this.displayPanel.getBtnSubject().addActionListener(e -> subjectReport());
        this.displayPanel.getBtnSbOv().addActionListener(e -> subjectOverview());
        this.displayPanel.getBtnStOv().addActionListener(e -> studentOverview());
        this.displayPanel.getBtnMaOv().addActionListener(e -> marksOverview());
    }
    
    public void loadDashboardSummery(){
        new javax.swing.SwingWorker<Void, Void>(){
            int totalStudents;
            int totalSubjects;
            double avgMarks;

            @Override
            protected Void doInBackground() throws Exception {
                totalStudents = displayDAO.getCountStudents();
                totalSubjects = displayDAO.getCountSubjects();
                avgMarks = displayDAO.getAverageMarks();
                return null;
            }
            
            @Override
            protected void done(){
                displayPanel.setLblToStudents(totalStudents + "");
                displayPanel.setLblToSubjects(totalSubjects + "");
            }
        }.execute();
    }
    
    public void runReport(String filePath,Map<String,Object> params){
        ReportGenrator reportGenrator = new ReportGenrator(filePath,params);
        Thread thread = new Thread(reportGenrator);
        thread.start();
    }
    
    public void studentReport(){
        try{
            Integer studentId = Integer.parseInt(displayPanel.getTxtStudentId().getText());
            String filePath = "/Reports/Student Report.jasper";
            Map<String,Object> params = new HashMap<>();
            params.put("student_id", studentId);
        
            runReport(filePath,params);
            displayPanel.getTxtStudentId().setText("");
        }
        catch(NumberFormatException nfe){
            JOptionPane.showMessageDialog(displayPanel, "Please Enter a student ID!","Operation Failed",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void subjectReport(){
        String type = displayPanel.getCmbType().getSelectedItem().toString();
        String filePath = "/Reports/Mark Analysis.jasper";
        Map<String,Object> params = new HashMap<>();
        params.put("exam_type", type);
        
        runReport(filePath,params);
        
    }
    
    public void subjectOverview(){
        String type = displayPanel.getCmbType().getSelectedItem().toString();
        String filePath = "/Reports/Subject Overview.jasper";
        
        runReport(filePath,null);
    }
    
    public void studentOverview(){
        String type = displayPanel.getCmbType().getSelectedItem().toString();
        String filePath = "/Reports/Student Overview.jasper";
        
        runReport(filePath,null);
    }
    
    public void marksOverview(){
        String type = displayPanel.getCmbType().getSelectedItem().toString();
        String filePath = "/Reports/Marks Overview.jasper";
        
        runReport(filePath,null);
    }
}
