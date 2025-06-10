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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Poorna
 */
public class DisplayDAO {
    public int getCountStudents(){
        String sql = "SELECT COUNT(*) FROM students;";
        try(Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs  = stmt.executeQuery()){
            if(rs.next()){
                return rs.getInt(1);
            }
        }
        catch(SQLException sx){
            Logger.getLogger(DisplayDAO.class.getName()).log(Level.SEVERE, null, sx);
        }
        catch(Exception ex){
            Logger.getLogger(DisplayDAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return -1;
    }
    
    public int getCountSubjects(){
        String sql = "SELECT COUNT(subject_code) FROM subjects;";
        try(Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()){
            if(rs.next()){
                return rs.getInt(1);
            }
        }
        catch(SQLException sx){
            Logger.getLogger(DisplayDAO.class.getName()).log(Level.SEVERE, null, sx);
        }
        catch(Exception ex){
            Logger.getLogger(DisplayDAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return -1;
    }
    
    public double getAverageMarks(){
        String sql = "SELECT AVG(marks_obtained) FROM marks;";
        try(Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()){
            if(rs.next()){
                return rs.getDouble(1);
            }
        }
        catch(Exception ex){
            Logger.getLogger(DisplayDAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return -1;
    }
}
