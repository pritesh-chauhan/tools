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
 * 
 */
public class AdminDeleteOperation extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public AdminDeleteOperation() {
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
        
        System.out.println("Inside Admin Delete Operations => "+id+" "+eid+" "+pwd);
        
        JDBCController db = new JDBCController();
        
        try {
            //Deleting the user from the main users table
            
            String sql = "DELETE from users where id = "+id+";";
            db.databaseManager(sql);
            
            //Based on the role deleting is done
            
            if(role.equals("patient")){
                    
                sql = "delete from patient where patient_id = "+id+";";
                db.databaseManager(sql);

                sql = "delete from illness where patient_id = "+id+";";
                db.databaseManager(sql);

            }
            else if(role.equals("doctor")){

                sql = "delete from doctor where doctor_id = "+id+";";
                db.databaseManager(sql);

                sql = "delete from patient where doctor_name = '"+fname+"';";
                db.databaseManager(sql);

                sql = "delete from illness where doctor_id = "+id+";";
                db.databaseManager(sql);

            }
            
            request.getRequestDispatcher("adminDelete.jsp").forward(request, response);
            
        } catch (ClassNotFoundException ex) {
            response.sendRedirect("errorClass.jsp");
            Logger.getLogger(AdminDeleteOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            response.sendRedirect("errorSQL.jsp");
            Logger.getLogger(AdminDeleteOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
