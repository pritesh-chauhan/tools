/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.controller;

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

/**
 *
 * @author prite
 */
@WebServlet(name = "messageSentServlet", urlPatterns = "/msg.jsp")
public class MessageSent extends HttpServlet { 
    private static final long serialVersionUID = 1L;
    public MessageSent() {
        super();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("patient_id");
        String pname = request.getParameter("patient_name");
        String did = request.getParameter("doctor_id");
        String dname = request.getParameter("doctor_name");
        String msg = request.getParameter("message");
        
        System.out.println("Message string: "+pid+" "+pname+" "+did+" "+dname+" "+msg);
        
        JDBCController db = new JDBCController();
        try {
            
            String sql_c = "insert into illness values ("+pid+", '"+pname+"',"+did+", '"+dname+"', '"+msg+"');";
            db.databaseManager(sql_c);
            
            sql_c = "insert into history values ("+pid+", '"+pname+"',"+did+", '"+dname+"', '"+msg+"','','','');";
            db.databaseManager(sql_c);
            
            String sql = "select doctor_name from  patient where patient_id = "+pid+";";
            String doctor_name = db.databaseManagerReturn(sql, "doctor_name");
            int doctor_id = Integer.parseInt(did);

            sql = "select count from doctor where doctor_name = '"+doctor_name+"' and doctor_id = "+doctor_id+";";
            int doctor_count = Integer.parseInt(db.databaseManagerReturn(sql, "count"));

            sql = "select count(*) from illness where doctor_name = '"+doctor_name+"' and doctor_id = "+doctor_id+" and patient_id="+pid+";";
            int count_illness = Integer.parseInt(db.databaseManagerReturn(sql, "count(*)"));

            sql = "select count(*) from medicine where doctor_name = '"+doctor_name+"' and doctor_id = "+doctor_id+" and patient_id="+pid+";";
            int count_medicine = Integer.parseInt(db.databaseManagerReturn(sql, "count(*)"));
            
            RePatientDetails repat = new RePatientDetails(pname, Integer.parseInt(pid), doctor_name, doctor_id, doctor_count, count_illness, count_medicine);
            RequestDispatcher rd = request.getRequestDispatcher("/message_sent.jsp");

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