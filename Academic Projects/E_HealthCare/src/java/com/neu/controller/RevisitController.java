/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.controller;

import com.neu.model.DoctorList;
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

@WebServlet("/RevisitController")
public class RevisitController extends HttpServlet { 
    private static final long serialVersionUID = 1L;
    public RevisitController() {
        super();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String patient_id = request.getParameter("patient_id");
        String patient_name = request.getParameter("patient_name");
        
        String doctor_id = request.getParameter("doctor_id");
        String doctor_name = request.getParameter("doctor_name");
        String doctor_count = request.getParameter("doctor_count");
        
        JDBCController db = new JDBCController();
        RequestDispatcher rd = null;
        
        try {
            
            String sql = "select doctor_name from doctor;";
            List<String> ls_dname = db.databaseManagerReturnList(sql, "doctor_name");
                        
            sql = "select doctor_id from doctor;";
            List<String> ls_did = db.databaseManagerReturnList(sql, "doctor_id");

            sql = "select count from doctor;";
            List<String> ls_dcount = db.databaseManagerReturnList(sql, "count");
            
            DoctorList ds = new DoctorList(patient_name, Integer.parseInt(patient_id), ls_dname, ls_did, ls_dcount);
            rd = request.getRequestDispatcher("/new_patient.jsp");

            request.setAttribute("doctor_list", ds);
            rd.forward(request, response);

            
        }catch (ClassNotFoundException ex) {
            response.sendRedirect("errorClass.jsp");
            Logger.getLogger(RevisitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            response.sendRedirect("errorSQL.jsp");
            Logger.getLogger(RevisitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
