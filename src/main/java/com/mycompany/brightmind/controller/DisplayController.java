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
                displayPanel.setLblAvgMark(avgMarks + "");
            }
        }.execute();
    }
    
    public void runReport(String filePath,Map<String,Object> params){
        ReportGenrator reportGenrator = new ReportGenrator(filePath,params);
        Thread thread = new Thread(reportGenrator);
        thread.start();
    }
    
    public void studentReport(){
        Integer studentId = Integer.parseInt(displayPanel.getTxtStudentId().getText());
        String filePath = "/Reports/Student Report.jasper";
        Map<String,Object> params = new HashMap<>();
        params.put("student_id", studentId);
        
        runReport(filePath,params);
        displayPanel.getTxtStudentId().setText("");
    }
}
