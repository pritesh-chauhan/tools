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
import javax.servlet.http.HttpSession;

/**
 *
 * @author prite
 */
@WebServlet(name = "loginServlet",urlPatterns = "/log.jsp")
public class UserLogin extends HttpServlet { 
    private static final long serialVersionUID = 1L;
    public UserLogin() {
        super();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eid = request.getParameter("email");
        String pwd = request.getParameter("password");
        String jdbcURL = "jdbc:mysql://localhost:3306/health?characterEncoding=latin1&useConfigs=maxPerformance";
        String dbUser = "root";
        String dbPassword = "password";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            String sql_c = "select count(*) from users where email='"+eid+"' AND password='"+pwd+"';";
            String sql = "select role from users where email='"+eid+"' AND password='"+pwd+"';";
            System.out.print("SQL statement 0: "+sql);
            HttpSession session = request.getSession(true);
            Statement statement = connection.createStatement();
            System.out.print("SQL statement 1: "+sql);
            ResultSet resultSet = statement.executeQuery(sql_c);
            int count = -1;
            while (resultSet.next()) {
                count = resultSet.getInt("count(*)");
            }
            if(count > 0){
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String role = resultSet.getString("role");
                    session.setAttribute("role", role);
                    
                    String sql_p = "select id from users where email='"+eid+"' AND password='"+pwd+"';";
                    resultSet = statement.executeQuery(sql_p);
                    int id = -1;
                    while (resultSet.next()) {
                        id = resultSet.getInt("id");
                    }
                    session.setAttribute("id", id);
                    String sql_n = "select fullname from users where email='"+eid+"' AND password='"+pwd+"';";
                    resultSet = statement.executeQuery(sql_n);
                    String name = "";
                    while (resultSet.next()) {
                        name = resultSet.getString("fullname");
                    }
                    session.setAttribute("id", ""+id);
                    session.setAttribute("fullname", name);
                    session.setAttribute("email", eid);
                    System.out.println("\nID => "+ id + " Fullname => "+name+"\n");
                    System.out.println("\n"+ role + "\n");
                    if(role.equals("admin"))
                        response.sendRedirect("admin.jsp");  
                    else if(role.equals("doctor")){
                        response.sendRedirect("doctor.jsp");  
                    }
                    else if(role.equals("patient")){
                        String sql_p1 = "select count(*) from patient where patient_id = (select id from users where email= '"+eid+"' AND password = '"+pwd+"');";
                        resultSet = statement.executeQuery(sql_p1);
                        count = -1;
                        while (resultSet.next()) {
                            count = resultSet.getInt("count(*)");
                        }
                        System.out.println("Count of patient "+count);
                        if(count > 0){
                            response.sendRedirect("re_patient.jsp");  
                        }
                        else{
                            sql = "INSERT INTO patient (patient_id, patient_name, doctor_name) VALUES ('" + id + "', '" + name + "', '')";
                            statement.executeUpdate(sql);
                            response.sendRedirect("new_patient.jsp");  
//                            request.getRequestDispatcher("new_patient.jsp").forward(request, response); 
                        }
                    }
                    else if(role.equals("pharmacist"))
                        response.sendRedirect("pharmacist.jsp");  
                    else
                        response.sendRedirect("error.jsp");
                    session.setAttribute("role", role);
                }  
            }
            else{
                response.sendRedirect("error.jsp");
            }
            
            statement.close();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            response.sendRedirect("errorClass.jsp");
            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            response.sendRedirect("errorSQL.jsp");
            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}