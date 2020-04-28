/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author prite
 */
public class JDBCController {
    
    private final String jdbcURL = "jdbc:mysql://localhost:3306/health?characterEncoding=latin1&useConfigs=maxPerformance";
    private final String dbUser = "root";
    private final String dbPassword = "password";
    
    public void databaseManager(String stmt) throws ClassNotFoundException, SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            String sql = stmt;
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(JDBCController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String databaseManagerReturn(String stmt, String value) throws ClassNotFoundException, SQLException{
        String name = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            String sql = stmt;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                name += resultSet.getString(value);
            }
            statement.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(JDBCController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }
    
    public List<String> databaseManagerReturnList(String stmt, String value) throws ClassNotFoundException, SQLException{
        List<String> name = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            String sql = stmt;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                name.add(resultSet.getString(value));
            }
            statement.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(JDBCController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }
    
}
