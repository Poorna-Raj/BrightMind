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
import java.util.logging.Level;
import java.util.logging.Logger;
public class UserDAO {
    
    public boolean validateUser(User user){
        String sql = "SELECT * FROM tblUser WHERE email = ? AND password = ?";
        try(Connection conn = DBUtil.getInstance();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,user.getEmail());
            stmt.setString(2,user.getPassword());
            
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
