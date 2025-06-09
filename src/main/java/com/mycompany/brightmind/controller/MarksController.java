/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brightmind.controller;

import com.mycompany.brightmind.model.ExamType;
import com.mycompany.brightmind.model.Marks;
import com.mycompany.brightmind.model.MarksDAO;
import com.mycompany.brightmind.view.MarksPanel;
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
            System.out.println("Subject list is empty");
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
    
    public void createMarks(){
        try{
            int studentId = Integer.parseInt(marksPanel.getTxtStudentId().getText());
            int subjectId = Integer.parseInt(marksPanel.getTxtSubjectId().getText());
            String selectedType = (String) marksPanel.getCmbExamtype().getSelectedItem();
            ExamType type = ExamType.valueOf(selectedType); 
            int obtainedMarks = Integer.parseInt(marksPanel.getTxtMarks().getText());
            int max = Integer.parseInt(marksPanel.getTxtMax().getText());
            
            marks = new Marks(studentId, subjectId, type, obtainedMarks, max);
            boolean success = marksDAO.createMarks(marks);
            if(success){
                JOptionPane.showMessageDialog(marksPanel, "Marks Added Successfully.", "Operation Complete!", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(marksPanel, "Marks addition Failed.", "Operation Failed!", JOptionPane.ERROR_MESSAGE);
            }
            loadMarks();
            clearFields();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(marksPanel, ex.getMessage(), "Operation Failed!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void updateMarks(){
        try{
            int marksId = Integer.parseInt(marksPanel.getTxtMarksId().getText());
            int studentId = Integer.parseInt(marksPanel.getTxtStudentId().getText());
            int subjectId = Integer.parseInt(marksPanel.getTxtSubjectId().getText());
            String selectedType = (String) marksPanel.getCmbExamtype().getSelectedItem();
            ExamType type = ExamType.valueOf(selectedType); 
            int obtainedMarks = Integer.parseInt(marksPanel.getTxtMarks().getText());
            int max = Integer.parseInt(marksPanel.getTxtMax().getText());
            
            marks = new Marks(marksId,studentId, subjectId, type, obtainedMarks, max);
            boolean success = marksDAO.updateMarks(marks);
            if(success){
                JOptionPane.showMessageDialog(marksPanel, "Marks Updated Successfully.", "Operation Complete!", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(marksPanel, "Marks Updation Failed.", "Operation Failed!", JOptionPane.ERROR_MESSAGE);
            }
            loadMarks();
            clearFields();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(marksPanel, ex.getMessage(), "Operation Failed!", JOptionPane.ERROR_MESSAGE);
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
                }
            }
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(marksPanel, e.getMessage(), "Operation Failed!", JOptionPane.WARNING_MESSAGE);
        }
    }
}
