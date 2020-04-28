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
@WebServlet(name = "medicineDelOpnServlet",urlPatterns = "/med_del_opn.jsp")
public class MedicineDel extends HttpServlet{
    private static final long serialVersionUID = 1L;
    public MedicineDel() {
        super();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String medname = request.getParameter("delmedname");
        String dose = request.getParameter("deldose");
        String quantity = request.getParameter("delquantity");
        String p_id = request.getParameter("patient_id");
        String d_id = request.getParameter("doctor_id");
        
        System.out.println("Inside Medicine Delete Operations => "+medname+" "+dose+" "+quantity+" "+p_id);
        
        String jdbcURL = "jdbc:mysql://localhost:3306/health?characterEncoding=latin1&useConfigs=maxPerformance";
        String dbUser = "root";
        String dbPassword = "password";
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            Statement statement = connection.createStatement();
            String sql = "delete from medicine where patient_id = "+p_id+" and doctor_id = "+d_id+" and medicine = '"+medname+"' and dose = "+dose+" and quantity = "+quantity+";";
            System.out.println("Delte query"+sql);
            statement.executeUpdate(sql);   
            statement.close();
            response.sendRedirect("prescribe.jsp");
                
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            response.sendRedirect("errorClass.jsp");
            Logger.getLogger(MedicineDel.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            response.sendRedirect("errorSQL.jsp");
            Logger.getLogger(MedicineDel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

