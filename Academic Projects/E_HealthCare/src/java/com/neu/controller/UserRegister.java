/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.controller;

/**
 *
 * @author prite
 */

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

@WebServlet(name = "registerServlet", urlPatterns = "/reg.jsp")
public class UserRegister extends HttpServlet { 
    private static final long serialVersionUID = 1L;
    public UserRegister() {
        super();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String eid = request.getParameter("eid");
        String pwd = request.getParameter("pass");
        String fname = request.getParameter("fname");
        String role = request.getParameter("role");
        int insno = Integer.parseInt(request.getParameter("insno"));
        
        String jdbcURL = "jdbc:mysql://localhost:3306/health?characterEncoding=latin1&useConfigs=maxPerformance";
        String dbUser = "root";
        String dbPassword = "password";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            Statement statement = connection.createStatement();
            String sql_c = "SELECT count(*) from users where id ="+insno+";";
            ResultSet resultSet = statement.executeQuery(sql_c);
            int count = 1;
            while (resultSet.next()) {
                count = resultSet.getInt("count(*)");
            }
            if(role.equals("doctor")){
                String sql = "INSERT INTO doctor (doctor_id, doctor_name, count) VALUES ('" + insno + "', '" + fname + "', '" + 0 + "')";
                statement.executeUpdate(sql);
            }
            if(count == 0){
                String sql = "INSERT INTO users (id, email, password, fullname, role) VALUES ('" + insno + "', '" + eid + "', '" + pwd + "', '" + fname + "', '" + role + "')";
                statement.executeUpdate(sql);
                response.sendRedirect("login.jsp");
            }
            else{
                response.sendRedirect("error.jsp");
            }
            statement.close();
            
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            response.sendRedirect("errorClass.jsp");
            Logger.getLogger(UserRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            response.sendRedirect("errorSQL.jsp");
            Logger.getLogger(UserRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}