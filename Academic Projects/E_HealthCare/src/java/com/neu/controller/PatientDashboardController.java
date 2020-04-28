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

import com.neu.model.RePatientDetails;
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

@WebServlet("/PatientDashboardServlet")
public class PatientDashboardController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public PatientDashboardController() {
        super();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("patient_id");
        String pname = request.getParameter("patient_name");
        String did = request.getParameter("doctor_id");
        String dname = request.getParameter("doctor_name");
        
        System.out.println("Message string: "+pid+" "+pname+" "+did+" "+dname);
        
        JDBCController db = new JDBCController();
        try {

            String doctor_name = dname;

            
            int doctor_id = Integer.parseInt(did);

            String sql = "select count from doctor where doctor_name = '"+doctor_name+"' and doctor_id = "+doctor_id+";";
            int doctor_count = Integer.parseInt(db.databaseManagerReturn(sql, "count"));

            sql = "select count(*) from illness where doctor_name = '"+doctor_name+"' and doctor_id = "+doctor_id+" and patient_id="+pid+";";
            int count_illness = Integer.parseInt(db.databaseManagerReturn(sql, "count(*)"));

            sql = "select count(*) from medicine where doctor_name = '"+doctor_name+"' and doctor_id = "+doctor_id+" and patient_id="+pid+";";
            int count_medicine = Integer.parseInt(db.databaseManagerReturn(sql, "count(*)"));
            
            RePatientDetails repat = new RePatientDetails(pname, Integer.parseInt(pid), doctor_name, doctor_id, doctor_count, count_illness, count_medicine);
            RequestDispatcher rd = request.getRequestDispatcher("/re_patient.jsp");

            request.setAttribute("re_patient", repat);
            rd.forward(request, response);
                        
        }catch (ClassNotFoundException ex) {
            response.sendRedirect("errorClass.jsp");
            Logger.getLogger(UserRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            response.sendRedirect("errorSQL.jsp");
            Logger.getLogger(UserRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
}