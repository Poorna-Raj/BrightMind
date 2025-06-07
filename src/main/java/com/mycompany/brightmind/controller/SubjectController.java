/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brightmind.controller;

import com.mycompany.brightmind.model.Subject;
import com.mycompany.brightmind.model.SubjectDAO;
import com.mycompany.brightmind.view.SubjectPanel;
import java.text.ParseException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Poorna
 */
public class SubjectController {
    private SubjectPanel subjectPanel;
    private SubjectDAO subjectDAO;
    private Subject subject;

    public SubjectController(SubjectPanel subjectPanel, SubjectDAO subjectDAO) {
        this.subjectPanel = subjectPanel;
        this.subjectDAO = subjectDAO;
        
        setupRowSelectionListener();
        
//        Set up btn controlles
        this.subjectPanel.getBtnCreate().addActionListener(e -> createSubject());
        this.subjectPanel.getBtnUpdate().addActionListener(e -> updateSubject());
        this.subjectPanel.getBtnDelete().addActionListener(e -> deleteSubject());
        this.subjectPanel.getBtnSearch().addActionListener(e -> searchSubject());
    }
    
    public void loadSubjects(){
        List<Subject> subjectList = subjectDAO.getAllSubject();
        if(subjectList == null){
            System.out.println("Subject list is empty");
        }
        subjectPanel.updateTable(subjectList);
    }
    
    private void setupRowSelectionListener() {
        subjectPanel.getTblSubject().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = subjectPanel.getTblSubject().getSelectedRow();
                if (selectedRow != -1) {
                    updateFormFieldsFromTable(selectedRow);
                }
            }
        });
    }
    
    private void updateFormFieldsFromTable(int row) {
        JTable table = subjectPanel.getTblSubject();

        String subjectId = table.getValueAt(row, 0).toString();
        String subjectCode = table.getValueAt(row, 1).toString();
        String subjectName = table.getValueAt(row, 2).toString();
        String description = table.getValueAt(row, 3).toString();
        String txtCredit = table.getValueAt(row, 4).toString();
        int credit = Integer.parseInt(txtCredit);

        subjectPanel.getTxtId().setText(subjectId);
        subjectPanel.getTxtCode().setText(subjectCode);
        subjectPanel.getTxtName().setText(subjectName);
        subjectPanel.getTxtaDes().setText(description);
        subjectPanel.getNumPoints().setValue(credit);
    }
    
    public void clearFields(){
        subjectPanel.setTxtId("");
        subjectPanel.setTxtCode("");
        subjectPanel.setTxtName("");
        subjectPanel.setTxtaDes("");
        subjectPanel.setNumPoints(0);
    }

    private void createSubject() {
        try{
            String code = subjectPanel.getTxtCode().getText();
            String name = subjectPanel.getTxtName().getText();
            String des = subjectPanel.getTxtaDes().getText();
            int points = (int) subjectPanel.getNumPoints().getValue();
            
            subject = new Subject(code,name,des,points);
            boolean success = subjectDAO.createSubject(subject);
            if(success){
                JOptionPane.showMessageDialog(subjectPanel, "Subject Created Successfully.", "Operation Complete!", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(subjectPanel, "Subject Creation Failed.", "Operation Failed!", JOptionPane.ERROR_MESSAGE);
            }
            loadSubjects();
            clearFields();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(subjectPanel, e.getMessage(), "Operation Failed!", JOptionPane.WARNING_MESSAGE);
        }
        
    }

    private void updateSubject() {
        try{
            String code = subjectPanel.getTxtCode().getText();
            String name = subjectPanel.getTxtName().getText();
            String des = subjectPanel.getTxtaDes().getText();
            int points = (int) subjectPanel.getNumPoints().getValue();
            int id = Integer.parseInt(subjectPanel.getTxtId().getText());
            
            subject = new Subject(id,code,name,des,points);
            boolean success = subjectDAO.updateSubject(subject);
            if(success){
                JOptionPane.showMessageDialog(subjectPanel, "Subject Updated Successfully.", "Operation Complete!", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(subjectPanel, "Subject Updation Failed.", "Operation Failed!", JOptionPane.ERROR_MESSAGE);
            }
            loadSubjects();
            clearFields();
        }catch(Exception e){
            JOptionPane.showMessageDialog(subjectPanel, e.getMessage(), "Operation Failed!", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteSubject() {
        try{
            int subjectId = Integer.parseInt((subjectPanel.getTxtId().getText()));
            
            boolean success = subjectDAO.deleteSubject(subjectId);
            if(success){
                JOptionPane.showMessageDialog(subjectPanel, "Subject Delete Successfully.", "Operation Complete!", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(subjectPanel, "Subject Deletion Failed.", "Operation Failed!", JOptionPane.ERROR_MESSAGE);
            }
            loadSubjects();
            clearFields();
        }
        catch(NumberFormatException nex){
            JOptionPane.showMessageDialog(subjectPanel, "Please select a record.", "Operation Failed!", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(subjectPanel, e.getMessage(), "Operation Failed!", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void searchSubject() {
        try{
            String code = subjectPanel.getTxtCode().getText();
            Subject success = subjectDAO.viewSubjectByCode(code);
            
            if(success == null){
                JOptionPane.showMessageDialog(subjectPanel, "No subject were found.", "Operation Complete!", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            }
            else{
                subjectPanel.getTxtId().setText(String.valueOf(success.getSubjectId()));
                subjectPanel.getTxtCode().setText(success.getSubjectCode());
                subjectPanel.getTxtName().setText(success.getSubjectName());
                subjectPanel.getTxtaDes().setText(success.getDescription());
                subjectPanel.getNumPoints().setValue(success.getCreditPoints());
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(subjectPanel, e.getMessage(), "Operation Failed!", JOptionPane.WARNING_MESSAGE);
        }
    }
}
