/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.controller;

import com.neu.model.DoctorAppointment;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author prite
 */

@WebServlet("/DoctorDashboardServlet")
public class DoctorDashboard extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public DoctorDashboard() {
        super();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String doctor_id = (String)request.getParameter("doctor_id");
        String doctor_name = (String)request.getParameter("doctor_name");
        
        System.out.println("D ID: "+doctor_id+" Doctor_name"+doctor_name);
        
        JDBCController db = new JDBCController();
        try {
            
            String sql_n = "select patient_id from illness where doctor_name = '"+doctor_name+"';";
            List<String> patient_id = db.databaseManagerReturnList(sql_n, "patient_id");

            sql_n = "select patient_name from illness where doctor_name = '"+doctor_name+"';";
            List<String> patient_name = db.databaseManagerReturnList(sql_n, "patient_name");

            sql_n = "select message from illness where doctor_name = '"+doctor_name+"';";
            List<String> message = db.databaseManagerReturnList(sql_n, "message");
            
            DoctorAppointment doctor = new DoctorAppointment(patient_id, patient_name, message, Integer.parseInt(doctor_id), doctor_name);
            
            RequestDispatcher rd = request.getRequestDispatcher("/doctor.jsp");
                    
            request.setAttribute("doctor", doctor);
            rd.forward(request, response);
                     
            
        } catch (ClassNotFoundException ex) {
            response.sendRedirect("errorClass.jsp");
            Logger.getLogger(DoctorDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            response.sendRedirect("errorSQL.jsp");
            Logger.getLogger(DoctorDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
}
