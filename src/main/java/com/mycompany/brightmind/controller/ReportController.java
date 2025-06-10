/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brightmind.controller;

import com.mycompany.brightmind.DBUtil;
import com.mycompany.brightmind.model.ReportGenrator;
import com.mycompany.brightmind.view.DisplayPanel;
import java.sql.Connection;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Poorna
 */
public class ReportController{

    private String filePath;
    private DisplayPanel displayPanel;

    public ReportController(String filePath,DisplayPanel displayPanel) {
        this.filePath = filePath;
        this.displayPanel = displayPanel;
    }
    
    public void runReport(String filePath,Map<String,Object> params){
        ReportGenrator reportGenrator = new ReportGenrator(filePath,params);
        Thread thread = new Thread(reportGenrator);
        thread.start();
    }
    
    public void studentReport(){
        
    }
    
}
