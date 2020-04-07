package org.simplewebapp.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.simplewebapp.beans.UserAccount;
 
public class DBUtils {
 
    public static UserAccount findUser(Connection conn, //
            String userName, String password) throws SQLException {
 
        //String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a " //
          //      + " where a.User_Name = ? ";
 
        String sql = "Select a.UserName, a.Password, a.Gender from User_Account a " //
                + " where a.UserName = '" + userName + "' and password = '" + password + "'";
        
        //PreparedStatement pstm = conn.prepareStatement(sql);
        //pstm.setString(1, userName);
        //pstm.setString(2, password);
        //ResultSet rs = pstm.executeQuery();
        
        Statement stmt = null;
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
 
        if (rs.next()) {
            String gender = rs.getString("Gender");
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);
            return user;
        }
        return null;
    }
 
    public static UserAccount findUser(Connection conn, String userName) throws SQLException {
 
        String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a "//
                + " where a.UserName = ? ";
 
        //PreparedStatement pstm = conn.prepareStatement(sql);
       // pstm.setString(1, userName);
 
        //ResultSet rs = pstm.executeQuery();
        Statement stmt = null;
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            String password = rs.getString("Password");
            String gender = rs.getString("Gender");
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);
            return user;
        }
        return null;
    }
    
    public static UserAccount addUser(Connection conn, String userName, String password, String gender) throws SQLException {
    	 
        String sql = "insert into demo.user_Account values('" + userName + "','" + password + "','" + gender + "')";;
 
        Statement stmt = null;
        stmt = conn.createStatement();
        stmt.executeUpdate(sql);
        
        return null;
    }
 
    
 
}