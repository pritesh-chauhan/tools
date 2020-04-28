/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
@WebServlet(name = "pharmacyServlet",urlPatterns = "/med_order.jsp")
public class Pharmacy extends HttpServlet{
    private static final long serialVersionUID = 1L;
    public Pharmacy() {
        super();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String medname = request.getParameter("medicine");
        String dose = request.getParameter("dose");
        String quantity = request.getParameter("quantity");
        String p_id = request.getParameter("patient_id");
        String p_name = request.getParameter("patient_name");
        String d_id = request.getParameter("doctor_id");
        String d_name = request.getParameter("doctor_name");
        
        System.out.println("Inside Pharmacy Operations => "+medname+" "+quantity+" "+p_id+" "+d_id);
        
        String jdbcURL = "jdbc:mysql://localhost:3306/health?characterEncoding=latin1&useConfigs=maxPerformance";
        String dbUser = "root";
        String dbPassword = "password";
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            Statement statement = connection.createStatement();
            String sql = "select medicine, quantity from medicine where patient_id = "+p_id+" and doctor_id = "+d_id+" and medicine = '"+medname+"';";
            ResultSet resultSet = statement.executeQuery(sql);
            String medicine = "";
            int quant = 0;
            while (resultSet.next()) {
                medicine = resultSet.getString("medicine");
                quant = resultSet.getInt("quantity");
            }
            
            
            sql = "select medicine, quantity from pharmacy;";
            resultSet = statement.executeQuery(sql);
            String med_p = "";
            int quant_p = 0;
            while (resultSet.next()) {
                if(resultSet.getString("medicine").equals(medicine)){
                    med_p = resultSet.getString("medicine");
                    quant_p = resultSet.getInt("quantity");
                }
            }
            System.out.println("Medicine: "+medicine+" == "+med_p+" , "+quant+" == "+quant_p);
            
            int diff = quant_p - quant;
            System.out.println("Difference: " +diff);
            
            if(diff < 0){
                
                //Adding the quantity that needs to be restocked
                sql = "insert into orders values('"+medicine+"',"+diff*(-1)+");";
                statement.executeUpdate(sql); 
                
                sql = "select count(*) from pharmacy where medicine='"+medicine+"';";
                resultSet = statement.executeQuery(sql); 
                int count = -1;
                while(resultSet.next()){
                    count = resultSet.getInt("count(*)");
                }
                if(count == 0){
                    sql = "insert into pharmacy values('"+medicine+"',0);";
                    statement.executeUpdate(sql); 
                }
            }
            else{
                
                //Placing the order
                sql = "delete from medicine where patient_id="+p_id+" and medicine = '"+medname+"';";
                statement.executeUpdate(sql); 
                
                //Deducting from pharmacy and placing the order
                sql = "update pharmacy set quantity ="+diff+" where medicine ='"+medname+"';";
                statement.executeUpdate(sql); 
            }
            statement.close();
            response.sendRedirect("pharmacist.jsp");
                
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            response.sendRedirect("errorClass.jsp");
            Logger.getLogger(Pharmacy.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            response.sendRedirect("errorSQL.jsp");
            Logger.getLogger(Pharmacy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

