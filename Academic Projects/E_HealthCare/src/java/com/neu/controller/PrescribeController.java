/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.controller;

import com.neu.model.PatientAttend;
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
@WebServlet("/PrescribeController")
public class PrescribeController extends HttpServlet { 
    private static final long serialVersionUID = 1L;
    public PrescribeController() {
        super();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        String p_id = request.getParameter("patient_id");
        String message = request.getParameter("message");
        String p_name = request.getParameter("patient_name");
        int d_id = Integer.parseInt(request.getParameter("doctor_id"));
        String d_name = request.getParameter("doctor_name");
        String medicine = request.getParameter("medicine");
        if(medicine.equals("/"))
            medicine = "medicine";
        int dose = Integer.parseInt(request.getParameter("dose"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
       
        String opn = request.getParameter("opn");
        
        
        System.out.println("OPtion=>"+opn);
        PatientAttend pt = new PatientAttend(p_id, d_id, p_name, d_name, message, medicine, dose, quantity);
        
        System.out.println("Patient and Medicine details"+pt.toString());
        
        JDBCController db = new JDBCController();
        RequestDispatcher rd;
        try {
            
            if(opn.equals("add")){
                String sql = "insert into medicine values ("+p_id+",'"+p_name+"',"+d_id+",'"+d_name+"','"+medicine+"',"+dose+","+quantity+");";
                db.databaseManager(sql);
                rd = request.getRequestDispatcher("/prescribe.jsp");   
                request.setAttribute("patient", pt);
            }
            else if(opn.equals("delete")){
                String sql = "delete from medicine where patient_id = "+p_id+" and doctor_id = "+d_id+" and medicine = '"+medicine+"' and dose = "+dose+" and quantity = "+quantity+";";
                db.databaseManager(sql);
                rd = request.getRequestDispatcher("/prescribe.jsp");
                request.setAttribute("patient", pt);
            }  
            else{
                rd = request.getRequestDispatcher("/prescribe.jsp");
                request.setAttribute("patient", pt);
            }
            
            rd.forward(request, response);
            
        } catch (ClassNotFoundException | NullPointerException ex) {
            response.sendRedirect("errorClass.jsp");
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            response.sendRedirect("errorSQL.jsp");
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

