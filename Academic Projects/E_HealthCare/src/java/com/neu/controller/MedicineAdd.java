/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author prite
 * 
 */

@WebServlet(name = "medicineUpdateOpnServlet",urlPatterns = "/med_up_opn.jsp")
public class MedicineAdd extends HttpServlet{
    private static final long serialVersionUID = 1L;
    public MedicineAdd() {
        super();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String medname = request.getParameter("medname");
        String dose = request.getParameter("dose");
        String quantity = request.getParameter("quantity");
        String p_id = request.getParameter("patient_id");
        String p_name = request.getParameter("patient_name");
        String d_id = request.getParameter("doctor_id");
        String d_name = request.getParameter("doctor_name");
        
        System.out.println("Inside Medicine Add Operations => "+medname+" "+dose+" "+quantity+" "+p_id+" "+p_name);
        
        String jdbcURL = "jdbc:mysql://localhost:3306/health?characterEncoding=latin1&useConfigs=maxPerformance";
        String dbUser = "root";
        String dbPassword = "password";
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            Statement statement = connection.createStatement();
            String sql = "insert into medicine values ("+p_id+",'"+p_name+"',"+d_id+",'"+d_name+"','"+medname+"',"+dose+","+quantity+");";
            statement.executeUpdate(sql);   
            statement.close();
            response.sendRedirect("prescribe.jsp");
                
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            response.sendRedirect("errorClass.jsp");
            Logger.getLogger(MedicineAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            response.sendRedirect("errorSQL.jsp");
            Logger.getLogger(MedicineAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

