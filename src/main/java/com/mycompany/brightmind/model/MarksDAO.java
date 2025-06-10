/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brightmind.model;

import com.mycompany.brightmind.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Poorna
 */
public class MarksDAO {
    /**
     * 
     * @param marks
     * @return true or false based on affected rows
     */
    public boolean createMarks(Marks marks){
        String sql = "INSERT INTO marks (student_id,subject_id,exam_type,marks_obtained,max_marks) VALUES (?,?,?,?,?);";
        try(Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, marks.getStudentId());
            stmt.setInt(2, marks.getSubjectId());
            stmt.setString(3, marks.getExamType().toString());
            stmt.setInt(4, marks.getMarksObtained());
            stmt.setInt(5, marks.getMaxMarks());
            
            int rowA = stmt.executeUpdate();
            return rowA == 1;
        } 
        catch (SQLException ex) {
            Logger.getLogger(MarksDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    /**
     * 
     * @param student
     * @return true or false based on affected rows 
     */
    public boolean updateMarks(Marks marks){
        String sql = "UPDATE marks SET student_id=?,subject_id=?,exam_type=?,marks_obtained=?,max_marks=? WHERE mark_id=?;";
        try(Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, marks.getStudentId());
            stmt.setInt(2, marks.getSubjectId());
            stmt.setString(3, marks.getExamType().toString());
            stmt.setInt(4, marks.getMarksObtained());
            stmt.setInt(5, marks.getMaxMarks());
            stmt.setInt(6, marks.getMarkId());
            
            int rowA = stmt.executeUpdate();
            return rowA ==1;
        } catch (SQLException ex) {
            Logger.getLogger(MarksDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    /**
     * 
     * @param markId
     * @return true or false based on affected rows 
     */
    public boolean deleteMarks(int markId){
        String sql = "DELETE FROM marks WHERE mark_id=?;";
        try(Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, markId);
            int rowA = stmt.executeUpdate();
            
            return rowA == 1;
        } catch (SQLException ex) {
            Logger.getLogger(MarksDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    /**
     * 
     * @param studentId
     * @param subjectId
     * @return a list of marks where student id and subject id match
     */
    public List<Marks> viewMarksBy(int studentId, int subjectId) {
        String sql = "SELECT * FROM marks WHERE student_id =? AND subject_id = ?";
        List<Marks> marksList = new ArrayList();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            stmt.setInt(2, subjectId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                Marks mark = new Marks(
                    rs.getInt("mark_id"),
                    rs.getInt("student_id"),
                    rs.getInt("subject_id"),
                    ExamType.valueOf(rs.getString("exam_type")),
                    rs.getInt("marks_obtained"),
                    rs.getInt("max_marks")
                );
                    marksList.add(mark);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarksDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Marks> getAllMarks(){
        String sql = "SELECT * FROM marks;";
        List<Marks> marksList = new ArrayList<>();
        try(Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()){
      
            while (rs.next()) {
                marksList.add(new Marks(
                    rs.getInt("mark_id"),
                    rs.getInt("student_id"),
                    rs.getInt("subject_id"),
                    ExamType.valueOf(rs.getString("exam_type")),
                    rs.getInt("marks_obtained"),
                    rs.getInt("max_marks")
            ));
}
            return marksList;
        } catch (SQLException ex) {
            Logger.getLogger(MarksDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
