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
                    //updateFormFieldsFromTable(selectedRow);
                }
            }
        });
    }
    
//    private void updateFormFieldsFromTable(int row) {
//        JTable table = marksPanel.getTblMarks();
//
//        String studentId = table.getValueAt(row, 0).toString();
//        String firstName = table.getValueAt(row, 1).toString();
//        String lastName = table.getValueAt(row, 2).toString();
//        String email = table.getValueAt(row, 3).toString();
//        String dob = table.getValueAt(row, 4).toString();
//
//        marksPanel.getTxtId().setText(studentId);
//        marksPanel.getTxtFirstName().setText(firstName);
//        marksPanel.getTxtLastName().setText(lastName);
//        marksPanel.getTxtEmail().setText(email);
//        marksPanel.getTxtDob().setText(dob);
//    }
    
}
