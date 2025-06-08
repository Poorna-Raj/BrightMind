/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brightmind.controller;

import com.mycompany.brightmind.model.Marks;
import com.mycompany.brightmind.model.MarksDAO;
import com.mycompany.brightmind.view.MarksPanel;
import java.util.List;
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
    
}
