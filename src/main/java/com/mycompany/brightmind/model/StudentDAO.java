/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brightmind.model;

/**
 *
 * @author Poorna
 */
import com.mycompany.brightmind.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class StudentDAO {
    /**
     * 
     * @param student object which holds data to insert
     * @return true or false based on affected rows
     */
    public boolean createStudent(Student student){
        String sql = "INSERT INTO students (first_name,last_name,email,date_of_birth) VALUES (?,?,?,?);";
        try(Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, student.getFirstName());
            stmt.setString(2,student.getLastName());
            stmt.setString(3, student.getEmail());
            stmt.setDate(4, student.getDateOfBirth());
            
            int rowA = stmt.executeUpdate();
            return rowA == 1;
        } 
        catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    /**
     * 
     * @param student object which holds data to update
     * @return true or false based on affected rows
     */
    public boolean updateStudent(Student student){
        String sql = "UPDATE students SET first_name=?,last_name=?,email=?,date_of_birth=? WHERE student_id=?;";
        try(Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getEmail());
            stmt.setDate(4, student.getDateOfBirth());
            stmt.setInt(5, student.getStudentId());
            
            int rowA = stmt.executeUpdate();
            return rowA ==1;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    /**
     * 
     * @param studentId which can be used to identify the student
     * @return true or false based of affected rows
     */
    public boolean deleteStudent(int studentId){
        String sql = "DELETE FROM students WHERE student_id=?;";
        try(Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, studentId);
            int rowA = stmt.executeUpdate();
            
            return rowA == 1;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    /**
     * 
     * @param studentId which can be used to search for specific student
     * @return Student object which holds data of the student
     */
    public Student viewStudentByEmail(String email) {
        String sql = "SELECT * FROM students WHERE email LIKE ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Student student = new Student();
                    student.setStudentId(rs.getInt("student_id"));
                    student.setFirstName(rs.getString("first_name"));
                    student.setLastName(rs.getString("last_name"));
                    student.setEmail(rs.getString("email"));
                    student.setDateOfBirth(rs.getDate("date_of_birth"));
                    return student;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * 
     * @return all the students as list of student objects  
     */
    public List<Student> getAllStudent(){
        String sql = "SELECT * FROM students;";
        List<Student> studentList = new ArrayList<>();
        try(Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()){
      
            while(rs.next()){
                studentList.add(new Student(
                    rs.getInt("student_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getDate("date_of_birth")
                ));
            }
            return studentList;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
