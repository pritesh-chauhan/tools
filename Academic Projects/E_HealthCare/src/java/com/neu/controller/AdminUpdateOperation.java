/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author prite
 */
public class AdminUpdateOperation extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public AdminUpdateOperation() {
        super();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String oid = request.getParameter("old_id");
        String id = request.getParameter("id");
        String eid = request.getParameter("email");
        String pwd = request.getParameter("password");
        String ofname = request.getParameter("old_fullname");
        String fname = request.getParameter("fullname");
        String role = request.getParameter("role");
        
        System.out.println("Inside Admin Update Operations => "+id+" "+id+" "+pwd);
        JDBCController db = new JDBCController();
        
        try {
            String sql = "UPDATE users set email = '" + eid + "', password = '" + pwd + "', fullname = '" + fname + "', role = '" + role + "' where id = " + id + ";";
            db.databaseManager(sql);

            if(role.equals("patient")){

                sql = "UPDATE patient set patient_name = '"+fname+"' where patient_id = "+id+";";
                db.databaseManager(sql);

                sql = "UPDATE illness set patient_name = '"+fname+"' where patient_id = "+id+";";
                db.databaseManager(sql);

            }
            else if(role.equals("doctor")){

                sql = "UPDATE doctor set doctor_name = '"+fname+"' where doctor_id = "+id+";";
                db.databaseManager(sql);

                sql = "UPDATE patient set doctor_name = '"+fname+"' where doctor_name = '"+ofname+"';";
                db.databaseManager(sql);

                sql = "UPDATE illness set doctor_name = '"+fname+"' where doctor_id = "+id+";";
                db.databaseManager(sql);

            }

            request.getRequestDispatcher("adminUpdate.jsp").forward(request, response);

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
