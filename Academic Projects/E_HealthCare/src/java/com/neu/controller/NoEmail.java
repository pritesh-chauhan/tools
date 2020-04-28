package com.neu.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * @author prite
 * 
 */

@WebServlet("/NoEmailSendingServlet")
public class NoEmail extends HttpServlet{
 
    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;
    private static final long serialVersionUID = 1L;
    public NoEmail() {
        super();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String doctor_name = request.getParameter("doctor_name");
        String doctor_id = request.getParameter("d_id");
        String patient_name = request.getParameter("patient_name");
        String doctor_email = request.getParameter("email");
        String jdbcURL = "jdbc:mysql://localhost:3306/health?characterEncoding=latin1&useConfigs=maxPerformance";
        String dbUser = "root";
        String dbPassword = "password";

        
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            Statement statement = connection.createStatement();
            String sql_c = "SELECT medicine, dose, quantity from medicine where patient_name = '"+patient_name+"';";
            ResultSet resultSet = statement.executeQuery(sql_c);
            
            sql_c = "update doctor set count = count - 1 where doctor_name = '"+doctor_name+"';";
            statement.executeUpdate(sql_c);
            
            sql_c = "delete from illness where patient_name = '"+patient_name+"' and doctor_name = '"+doctor_name+"';";
            statement.executeUpdate(sql_c);
            
            sql_c = "update history set medicine = '',dose='' ,quantity = '' where patient_name = '"+patient_name+"' and doctor_name = '"+doctor_name+"';";
            statement.executeUpdate(sql_c);
            
            response.sendRedirect("noemail.jsp");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            response.sendRedirect("errorClass.jsp");
            Logger.getLogger(NoEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            response.sendRedirect("errorSQL.jsp");
            Logger.getLogger(NoEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}