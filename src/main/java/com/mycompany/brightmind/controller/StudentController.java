/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brightmind.controller;

import com.mycompany.brightmind.model.Student;
import com.mycompany.brightmind.model.StudentDAO;
import com.mycompany.brightmind.view.StudentPanel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Poorna
 */
public class StudentController {
    private StudentPanel studentView;
    private StudentDAO studentDAO;
    private Student student;

    public StudentController(StudentPanel studentView, StudentDAO studentDAO) {
        this.studentView = studentView;
        this.studentDAO = studentDAO;
        setupRowSelectionListener();
        this.studentView.getBtnCreate().addActionListener(e -> createStudent());
        this.studentView.getBtnUpdate().addActionListener(e -> updateStudent());
        this.studentView.getBtnDelete().addActionListener(e -> deleteStudent());
        this.studentView.getBtnSearch().addActionListener(e -> searchStudent());
    }
    
    public void loadStudents(){
        List<Student> studentList = studentDAO.getAllStudent();
        if(studentList == null){
            System.out.println("The student list is null");
        }
        else{
            System.out.println("Student list size: " + studentList.size());
        }
        studentView.updateTable(studentList);
    }
    
    public void clearFields(){
        studentView.setTxtFirstName("");
        studentView.setTxtLastName("");
        studentView.setTxtEmail("");
        studentView.setTxtDob("");
    }
    
    private void setupRowSelectionListener() {
        studentView.getTblStudent().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = studentView.getTblStudent().getSelectedRow();
                if (selectedRow != -1) {
                    updateFormFieldsFromTable(selectedRow);
                }
            }
        });
    }
    
    private void updateFormFieldsFromTable(int row) {
        JTable table = studentView.getTblStudent();

        String studentId = table.getValueAt(row, 0).toString();
        String firstName = table.getValueAt(row, 1).toString();
        String lastName = table.getValueAt(row, 2).toString();
        String email = table.getValueAt(row, 3).toString();
        String dob = table.getValueAt(row, 4).toString();

        studentView.getTxtId().setText(studentId);
        studentView.getTxtFirstName().setText(firstName);
        studentView.getTxtLastName().setText(lastName);
        studentView.getTxtEmail().setText(email);
        studentView.getTxtDob().setText(dob);
    }


    
    public void createStudent() {
        try {
            String firstName = studentView.getTxtFirstName().getText().trim();
            String lastName = studentView.getTxtLastName().getText().trim();
            String email = studentView.getTxtEmail().getText().trim();
            String dobTxt = studentView.getTxtDob().getText().trim();

            if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || dobTxt.isBlank()) {
                JOptionPane.showMessageDialog(studentView,"All fields are required. Please fill in all details.","Validation Error",JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
                JOptionPane.showMessageDialog(studentView,"Please enter a valid email address.","Invalid Email",JOptionPane.WARNING_MESSAGE);
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            Date dob = sdf.parse(dobTxt);
            Date today = new Date();

            if (dob.after(today)) {
                JOptionPane.showMessageDialog(studentView,"Date of birth cannot be in the future.","Invalid Date",JOptionPane.WARNING_MESSAGE);
                return;
            }

            student = new Student(firstName, lastName, email, new java.sql.Date(dob.getTime()));
            boolean success = studentDAO.createStudent(student);

            if (success) {
                JOptionPane.showMessageDialog(studentView,"Student profile created successfully.","Operation Complete",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(studentView,"Student profile creation failed. Please try again.","Operation Failed",JOptionPane.ERROR_MESSAGE);
            }

            loadStudents();
            clearFields();

        } 
        catch (ParseException pex) {
            JOptionPane.showMessageDialog(studentView,"Please enter the date of birth in the correct format (yyyy-MM-dd).","Invalid Date Format",JOptionPane.ERROR_MESSAGE);
        } 
        catch (Exception ex) {
            JOptionPane.showMessageDialog(studentView,"An unexpected error occurred: " + ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    
    public void updateStudent(){
        try{
            String idText = studentView.getTxtId().getText().trim();
            String firstName = studentView.getTxtFirstName().getText().trim();
            String lastName = studentView.getTxtLastName().getText().trim();
            String email = studentView.getTxtEmail().getText().trim();
            String dobTxt = studentView.getTxtDob().getText().trim();

            if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || dobTxt.isBlank() || idText.isBlank()) {
                JOptionPane.showMessageDialog(studentView,"All fields are required. Please fill in all details.","Validation Error",JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
                JOptionPane.showMessageDialog(studentView,"Please enter a valid email address.","Invalid Email",JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dob = sdf.parse(dobTxt);
            int studentId = Integer.parseInt(idText);
            
            student = new Student(studentId,firstName, lastName, email, new java.sql.Date(dob.getTime()));
            boolean success = studentDAO.updateStudent(student);
            if(success){
                JOptionPane.showMessageDialog(studentView, "Student Profile Updated Successfully.", "Operation Complete!", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(studentView, "Student Profile Updation Failed.", "Operation Failed!", JOptionPane.ERROR_MESSAGE);
            }
            loadStudents();
            clearFields();
        }
        catch (ParseException pex) {
            JOptionPane.showMessageDialog(studentView,"Please enter the date of birth in the correct format (yyyy-MM-dd).","Invalid Date Format",JOptionPane.ERROR_MESSAGE);
        } 
        catch (Exception ex) {
            JOptionPane.showMessageDialog(studentView,"An unexpected error occurred: " + ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void deleteStudent(){
        try{
            int studentId = Integer.parseInt((studentView.getTxtId().getText()));
            
            boolean success = studentDAO.deleteStudent(studentId);
            if(success){
                JOptionPane.showMessageDialog(studentView, "Student Profile Delete Successfully.", "Operation Complete!", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(studentView, "Student Profile Deletion Failed.", "Operation Failed!", JOptionPane.ERROR_MESSAGE);
            }
            loadStudents();
            clearFields();
        }
        catch(NumberFormatException nex){
            JOptionPane.showMessageDialog(studentView, "Please select a record.", "Operation Failed!", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(studentView, e.getMessage(), "Operation Failed!", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void searchStudent(){
        try{
            String email = studentView.getTxtEmail().getText();
            Student success = studentDAO.viewStudentByEmail(email);
            
            if(success == null){
                JOptionPane.showMessageDialog(studentView, "No student were found.", "Operation Complete!", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            }
            else{
                studentView.getTxtId().setText(String.valueOf(success.getStudentId()));
                studentView.getTxtFirstName().setText(success.getFirstName());
                studentView.getTxtLastName().setText(success.getLastName());
                studentView.getTxtEmail().setText(success.getEmail());
                studentView.getTxtDob().setText(success.getDateOfBirth().toString());
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(studentView, e.getMessage(), "Operation Failed!", JOptionPane.WARNING_MESSAGE);
        }
    }
}
