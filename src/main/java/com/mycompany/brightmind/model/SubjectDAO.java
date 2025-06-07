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
public class SubjectDAO {
    /**
     * 
     * @param subject
     * @return true or false based on affected rows
     */
    public boolean createSubject(Subject subject){
        String sql = "INSERT INTO subjects (subject_code,subject_name,description,credit_hours) VALUES (?,?,?,?);";
        try(Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, subject.getSubjectCode());
            stmt.setString(2, subject.getSubjectName());
            stmt.setString(3, subject.getDescription());
            stmt.setInt(4, subject.getCreditPoints());
            
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
     * @param subject
     * @return true or false based on affected rows 
     */
    public boolean updateSubject(Subject subject){
        String sql = "UPDATE subjects SET subject_code=?,subject_name=?,description=?,credit_hours=? WHERE subject_id=?;";
        try(Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, subject.getSubjectCode());
            stmt.setString(2, subject.getSubjectName());
            stmt.setString(3, subject.getDescription());
            stmt.setInt(4, subject.getCreditPoints());
            stmt.setInt(5, subject.getSubjectId());
            
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
     * @param subjectId
     * @return true or false based of affected rows 
     */
    public boolean deleteSubject(int subjectId){
        String sql = "DELETE FROM subjects WHERE subject_id=?;";
        try(Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, subjectId);
            int rowA = stmt.executeUpdate();
            
            return rowA == 1;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    /**
     * 
     * @return all the subjects as an array list
     */
    public List<Subject> getAllSubject(){
        String sql = "SELECT * FROM subjects;";
        List<Subject> subjectList = new ArrayList();
        try(Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()){
            
            while(rs.next()){
                subjectList.add(new Subject(
                        rs.getInt("subject_id"),
                        rs.getString("subject_code"),
                        rs.getString("subject_name"),
                        rs.getString("description"),
                        rs.getInt("credit_hours")
                ));
            }
            return subjectList;
        }catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * 
     * @param code
     * @return return the subject of related subject code
     */
    public Subject viewSubjectByCode(String code){
        String sql = "SELECT * FROM subjects WHERE subject_code LIKE ?;";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, code);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Subject subject = new Subject();
                    subject.setSubjectId(rs.getInt("subject_id"));
                    subject.setSubjectCode(rs.getString("subject_code"));
                    subject.setSubjectName(rs.getString("subject_name"));
                    subject.setDescription(rs.getString("description"));
                    subject.setCreditPoints(rs.getInt("credit_hours"));
                    return subject;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
