/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brightmind.controller;

import com.mycompany.brightmind.model.Student;
import com.mycompany.brightmind.model.StudentDAO;
import com.mycompany.brightmind.view.StudentPanel;
import java.util.List;
import javax.swing.SwingUtilities;

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
}
