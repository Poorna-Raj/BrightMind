/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brightmind.model;

import com.mycompany.brightmind.DBUtil;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Poorna
 */
public class ReportGenrator implements Runnable{

    private final String filePath;
    private final Map<String,Object> parameters;

    public ReportGenrator(String filePath, Map parameters) {
        this.filePath = filePath;
        this.parameters = parameters;
    }
    
    
    @Override
    public void run() {
        try (Connection conn = DBUtil.getConnection();
             InputStream reportStream = getClass().getResourceAsStream(filePath)) {

            if (reportStream == null) {
                System.err.println("Report file not found: " + filePath);
                return;
            }

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                    parameters != null ? parameters : new HashMap<>(), conn);

            System.out.println("Generated report: " + filePath + " on thread: " + Thread.currentThread().getName());

            SwingUtilities.invokeLater(() -> JasperViewer.viewReport(jasperPrint, false));

        } catch (JRException | IOException | SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ReportGenrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
