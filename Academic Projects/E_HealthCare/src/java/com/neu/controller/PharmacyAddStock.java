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
 */

@WebServlet(name = "pharmacyAddStockServlet",urlPatterns = "/pharma_add.jsp")
public class PharmacyAddStock extends HttpServlet{
    private static final long serialVersionUID = 1L;
    public PharmacyAddStock() {
        super();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String medname = request.getParameter("medicine");
        String quantity = request.getParameter("quantity");
        
        System.out.println("Inside Pharmacy Update Stock Operations => "+medname+" "+quantity);
        
        String jdbcURL = "jdbc:mysql://localhost:3306/health?characterEncoding=latin1&useConfigs=maxPerformance";
        String dbUser = "root";
        String dbPassword = "password";
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            Statement statement = connection.createStatement();
            String sql = "update pharmacy set quantity = quantity +"+quantity+" where medicine = '"+medname+"';";
            statement.executeUpdate(sql);
            
            
            sql = "delete from orders where medicine = '"+medname+"';";
            statement.executeUpdate(sql);
            
            statement.close();
            response.sendRedirect("pharmacist.jsp");
                
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            response.sendRedirect("errorClass.jsp");
            Logger.getLogger(PharmacyAddStock.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            response.sendRedirect("errorSQL.jsp");
            Logger.getLogger(PharmacyAddStock.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}

