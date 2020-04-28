/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.controller;

import com.neu.model.DoctorSelect;
import java.io.IOException;
import java.sql.SQLException;
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

@WebServlet(name = "appointServlet",urlPatterns = "/appoint.jsp")
public class Appointment extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public Appointment() {
        super();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String doctor_id = (String)request.getParameter("doctor_id");
        String doctor_name = (String)request.getParameter("doctor_name");
        String patient_id = (String) request.getParameter("patient_id");
        String patient_name = (String) request.getParameter("patient_name");
        
        System.out.println("Patient ID: "+patient_id+" Patient_name: "+patient_name+" D ID: "+doctor_id+" Doctor_name"+doctor_name);
        
        JDBCController db = new JDBCController();
        try {
            
            String sql_d = "update doctor set count = count - 1 where doctor_name = (select doctor_name from patient where patient_id = " +patient_id+ ");";
            db.databaseManager(sql_d);
                        
            String sql_c = "update doctor set count = count + 1 where doctor_id ="+doctor_id+" AND doctor_name ='"+doctor_name+"';";
            db.databaseManager(sql_c);
            
            System.out.println("Checking the patient update query: update patient set doctor_name = '"+doctor_name+"' where patient_id ="+patient_id+";");
            
            sql_c = "update patient set doctor_name = '"+doctor_name+"' where patient_id = "+patient_id+";";
            System.out.println("Update doctor_name in patient: "+sql_c);
            db.databaseManager(sql_c);

            
            DoctorSelect ds = new DoctorSelect(patient_name, Integer.parseInt(patient_id), doctor_name, Integer.parseInt(doctor_id));

            
            RequestDispatcher rd = request.getRequestDispatcher("/illness.jsp");
                    
            request.setAttribute("doctor_selection", ds);
            rd.forward(request, response);
            
            
        } catch (ClassNotFoundException ex) {
            response.sendRedirect("errorClass.jsp");
            Logger.getLogger(UserRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            response.sendRedirect("errorSQL.jsp");
            Logger.getLogger(UserRegister.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
}
