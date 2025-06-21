/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brightmind.controller;

import com.mycompany.brightmind.model.ExamType;
import com.mycompany.brightmind.model.Marks;
import com.mycompany.brightmind.model.MarksDAO;
import com.mycompany.brightmind.view.MarksPanel;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Poorna
 */
public class MarksController {
    private Marks marks;
    private MarksDAO marksDAO;
    private MarksPanel marksPanel;

    public MarksController(MarksPanel marksPanel,MarksDAO marksDAO) {
        this.marksDAO = marksDAO;
        this.marksPanel = marksPanel;
        setupRowSelectionListener();
        
        this.marksPanel.getBtnCreate().addActionListener(e -> createMarks());
        this.marksPanel.getBtnUpdate().addActionListener(e -> updateMarks());
        this.marksPanel.getBtnDelete().addActionListener(e -> deleteMarks());
        this.marksPanel.getBtnSearch().addActionListener(e -> searchMarks());
    }
    
    void loadMarks() {
        List<Marks> marksList = marksDAO.getAllMarks();
        if(marksList == null){
            JOptionPane.showMessageDialog(marksPanel,"Marks Table is Empty","Failed to Fetch Marks",JOptionPane.ERROR_MESSAGE);
        }
        marksPanel.updateTable(marksList);
    }
    
    private void setupRowSelectionListener() {
        marksPanel.getTblMarks().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = marksPanel.getTblMarks().getSelectedRow();
                if (selectedRow != -1) {
                   updateFormFieldsFromTable(selectedRow);
                }
            }
        });
    }
    
    private void updateFormFieldsFromTable(int row) {
        JTable table = marksPanel.getTblMarks();

        String marksId = table.getValueAt(row,0).toString();
        String studentId = table.getValueAt(row, 1).toString();
        String subjectId = table.getValueAt(row, 2).toString();
        String examType = table.getValueAt(row, 3).toString();
        String marks = table.getValueAt(row, 4).toString();
        String max = table.getValueAt(row, 5).toString();

        marksPanel.getTxtMarksId().setText(marksId);
        marksPanel.getTxtStudentId().setText(studentId);
        marksPanel.getTxtSubjectId().setText(subjectId);
        marksPanel.getCmbExamtype().setSelectedIndex(getCmbIndex(examType));
        marksPanel.getTxtMarks().setText(marks);
        marksPanel.getTxtMax().setText(max);
    }
    
    private int getCmbIndex(String type){
        if(type == "Mid"){
            return 0;
        }
        else if(type == "Final"){
            return 1;
        }
        else{
            return 2;
        }
    }
    
    public void clearFields(){
        marksPanel.setTxtMarksId("");
        marksPanel.setTxtStudentId("");
        marksPanel.setTxtSubjectId("");
        marksPanel.setCmbExamtype(0);
        marksPanel.setTxtMarks("");
        marksPanel.setTxtMax("");
    }
    
    public void createMarks() {
        try {
            String studentIdText = marksPanel.getTxtStudentId().getText();
            String subjectIdText = marksPanel.getTxtSubjectId().getText();
            String selectedType = (String) marksPanel.getCmbExamtype().getSelectedItem();
            String obtainedMarksText = marksPanel.getTxtMarks().getText();
            String maxText = marksPanel.getTxtMax().getText();

            if (studentIdText.isBlank() || subjectIdText.isBlank() || selectedType == null ||
                obtainedMarksText.isBlank() || maxText.isBlank()) {
                JOptionPane.showMessageDialog(marksPanel,"Please fill in all the fields before submitting.","Validation Error",JOptionPane.WARNING_MESSAGE);
                return;
            }

            int studentId = Integer.parseInt(studentIdText);
            int subjectId = Integer.parseInt(subjectIdText);
            int obtainedMarks = Integer.parseInt(obtainedMarksText);
            int max = Integer.parseInt(maxText);
            ExamType type = ExamType.valueOf(selectedType);

            if (obtainedMarks < 0 || obtainedMarks > max) {
                JOptionPane.showMessageDialog(marksPanel,"Obtained marks should be between 0 and the maximum marks.","Invalid Marks",JOptionPane.WARNING_MESSAGE);
                return;
            }

            marks = new Marks(studentId, subjectId, type, obtainedMarks, max);
            boolean success = marksDAO.createMarks(marks);

            if (success) {
                JOptionPane.showMessageDialog(marksPanel,"Marks added successfully.","Operation Complete",JOptionPane.INFORMATION_MESSAGE);
            } 
            else {
                JOptionPane.showMessageDialog(marksPanel,"Failed to add marks. Please try again.","Operation Failed",JOptionPane.ERROR_MESSAGE);
            }

            loadMarks();
            clearFields();
        } 
        catch(SQLIntegrityConstraintViolationException sqlEx){
            JOptionPane.showMessageDialog(marksPanel,"Please check the Student ID or Subject ID!","Invalid Input",JOptionPane.ERROR_MESSAGE);
        }
        catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(marksPanel,"Student ID, Subject ID, Obtained Marks, and Max Marks must be valid numbers.","Invalid Input",JOptionPane.ERROR_MESSAGE);
        } 
        catch (Exception ex) {
            JOptionPane.showMessageDialog(marksPanel,"An unexpected error occurred: " + ex.getMessage(),"Operation Failed",JOptionPane.ERROR_MESSAGE);
        }
    }

    
    public void updateMarks(){
        try {
            String marksIdText = marksPanel.getTxtMarksId().getText();
            String studentIdText = marksPanel.getTxtStudentId().getText();
            String subjectIdText = marksPanel.getTxtSubjectId().getText();
            String selectedType = (String) marksPanel.getCmbExamtype().getSelectedItem();
            String obtainedMarksText = marksPanel.getTxtMarks().getText();
            String maxText = marksPanel.getTxtMax().getText();

            if (marksIdText.isBlank() || studentIdText.isBlank() || subjectIdText.isBlank() ||
                selectedType == null || obtainedMarksText.isBlank() || maxText.isBlank()) {
                JOptionPane.showMessageDialog(marksPanel,"Please fill in all the fields before updating.","Validation Error",JOptionPane.WARNING_MESSAGE);
                return;
            }

            int marksId = Integer.parseInt(marksIdText);
            int studentId = Integer.parseInt(studentIdText);
            int subjectId = Integer.parseInt(subjectIdText);
            int obtainedMarks = Integer.parseInt(obtainedMarksText);
            int max = Integer.parseInt(maxText);
            ExamType type = ExamType.valueOf(selectedType);

            if (obtainedMarks < 0 || obtainedMarks > max) {
                JOptionPane.showMessageDialog(marksPanel,"Obtained marks must be between 0 and maximum marks.","Invalid Marks",JOptionPane.WARNING_MESSAGE);
                return;
            }

            marks = new Marks(marksId, studentId, subjectId, type, obtainedMarks, max);
            boolean success = marksDAO.updateMarks(marks);

            if (success) {
                JOptionPane.showMessageDialog(marksPanel,"Marks updated successfully.","Operation Complete",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(marksPanel,"Failed to update marks. Please try again.","Operation Failed",JOptionPane.ERROR_MESSAGE);
            }

            loadMarks();
            clearFields();

        } 
        catch(SQLIntegrityConstraintViolationException sqlEx){
            JOptionPane.showMessageDialog(marksPanel,"Please check the Student ID or Subject ID!","Invalid Input",JOptionPane.ERROR_MESSAGE);
        }
        catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(marksPanel,"Student ID, Subject ID, Obtained Marks, and Max Marks must be valid numbers.","Invalid Input",JOptionPane.ERROR_MESSAGE);
        } 
        catch (Exception ex) {
            JOptionPane.showMessageDialog(marksPanel,"An unexpected error occurred: " + ex.getMessage(),"Operation Failed",JOptionPane.ERROR_MESSAGE);
        }
    }

    
    
    public void deleteMarks(){
        try{
            int marksId = Integer.parseInt((marksPanel.getTxtMarksId().getText()));
            
            boolean success = marksDAO.deleteMarks(marksId);
            if(success){
                JOptionPane.showMessageDialog(marksPanel, "Marks Deleted Successfully.", "Operation Complete!", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(marksPanel, "Marks Deletion Failed.", "Operation Failed!", JOptionPane.ERROR_MESSAGE);
            }
            loadMarks();
            clearFields();
        }
        catch(NumberFormatException nex){
            JOptionPane.showMessageDialog(marksPanel, "Please select a record.", "Operation Failed!", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(marksPanel, e.getMessage(), "Operation Failed!", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void searchMarks(){
        try{
            String studentText = marksPanel.getTxtStudentId().getText().trim();
            String subjectText = marksPanel.getTxtSubjectId().getText().trim();
            List<Marks> filteredMarks = new ArrayList();
        
            if (studentText.isEmpty() || subjectText.isEmpty()) {
                loadMarks();
            }
            else{
                int studentId = Integer.parseInt(studentText);
                int subjectId = Integer.parseInt(subjectText);
                
                filteredMarks = marksDAO.viewMarksBy(studentId, subjectId);
                if(filteredMarks == null || filteredMarks.isEmpty()){
                    JOptionPane.showMessageDialog(marksPanel, "No marks has been found","Operation Completed",JOptionPane.INFORMATION_MESSAGE);
                    loadMarks();
                }
                else{
                    marksPanel.updateTable(filteredMarks);
                    clearFields();
                }
            }
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(marksPanel, e.getMessage(), "Operation Failed!", JOptionPane.WARNING_MESSAGE);
        }
    }
}
