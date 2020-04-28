/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.controller;

import com.neu.model.DoctorAppointment;
import com.neu.model.DoctorList;
import com.neu.model.RePatientDetails;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author prite
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet { 
    private static final long serialVersionUID = 1L;
    public LoginController() {
        super();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eid = request.getParameter("email");
        String pwd = request.getParameter("password");
        
        JDBCController db = new JDBCController();
        RequestDispatcher rd = null;
        try {
            String sql_c = "select count(*) from users where email='"+eid+"' AND password='"+pwd+"';";
            int count = Integer.parseInt(db.databaseManagerReturn(sql_c, "count(*)"));
            
            HttpSession session = request.getSession(true);

            if(count > 0){
                String sql = "select role from users where email='"+eid+"' AND password='"+pwd+"';";
                String role = db.databaseManagerReturn(sql, "role");   
                
                String sql_p = "select id from users where email='"+eid+"' AND password='"+pwd+"';";
                int id = Integer.parseInt(db.databaseManagerReturn(sql_p, "id"));
                
                String sql_n = "select fullname from users where email='"+eid+"' AND password='"+pwd+"';";
                String name = db.databaseManagerReturn(sql_n, "fullname");
                    
                if(role.equals("admin")){
                    response.sendRedirect("admin.jsp");  
                }
                else if(role.equals("doctor")){
                    
                    sql_n = "select patient_id from illness where doctor_name = '"+name+"';";
                    List<String> patient_id = db.databaseManagerReturnList(sql_n, "patient_id");
                    
                    sql_n = "select patient_name from illness where doctor_name = '"+name+"';";
                    List<String> patient_name = db.databaseManagerReturnList(sql_n, "patient_name");
                    
                    sql_n = "select message from illness where doctor_name = '"+name+"';";
                    List<String> message = db.databaseManagerReturnList(sql_n, "message");
                    
                    DoctorAppointment doctor = new DoctorAppointment(patient_id, patient_name, message, id, name);
                    
                    rd = request.getRequestDispatcher("/doctor.jsp");
                    
                    request.setAttribute("doctor", doctor);
                    rd.forward(request, response);
                    
                }
                else if(role.equals("patient")){
                    
                    //Count to know if patient is revisiting or new
                    String sql_p1 = "select count(*) from patient where patient_id = (select id from users where email= '"+eid+"' AND password = '"+pwd+"');";
                    int patient_count = Integer.parseInt(db.databaseManagerReturn(sql_p1, "count(*)"));
                    
                    System.out.println("Count of patient "+patient_count);
                    
                    if(patient_count > 0){
                        
                        sql = "select doctor_name from patient where patient_id = "+id+";";
                        String doctor_name = db.databaseManagerReturn(sql, "doctor_name");
                        
                        sql = "select id from users where fullname = '"+doctor_name+"';";
                        int doctor_id = Integer.parseInt(db.databaseManagerReturn(sql, "id"));
                        
                        sql = "select count from doctor where doctor_id = "+doctor_id+";";
                        int doctor_count = Integer.parseInt(db.databaseManagerReturn(sql, "count"));
                        
                        sql = "select count(*) from illness where doctor_name = '"+doctor_name+"' and doctor_id = "+doctor_id+" and patient_id="+id+";";
                        System.out.println("Revisit error =>>>>" +sql);
                        int count_illness = Integer.parseInt(db.databaseManagerReturn(sql, "count(*)"));
                        
                        sql = "select count(*) from medicine where doctor_name = '"+doctor_name+"' and doctor_id = "+doctor_id+" and patient_id="+id+";";
                        int count_medicine = Integer.parseInt(db.databaseManagerReturn(sql, "count(*)"));
                        
                        RePatientDetails repat = new RePatientDetails(name, id, doctor_name, doctor_id, doctor_count, count_illness, count_medicine);
                        rd = request.getRequestDispatcher("/re_patient.jsp");
                    
                        request.setAttribute("re_patient", repat);
                        rd.forward(request, response);
                        
                    }
                    else{
                        sql = "INSERT INTO patient (patient_id, patient_name, doctor_name) VALUES (" + id + ", '" + name + "', 'NA')";
                        db.databaseManager(sql);
                        
                        sql = "select doctor_name from doctor;";
                        List<String> ls_dname = db.databaseManagerReturnList(sql, "doctor_name");
                        
                        sql = "select doctor_id from doctor;";
                        List<String> ls_did = db.databaseManagerReturnList(sql, "doctor_id");
                        
                        sql = "select count from doctor;";
                        List<String> ls_dcount = db.databaseManagerReturnList(sql, "count");
                        
                        DoctorList ds = new DoctorList(name, id, ls_dname, ls_did, ls_dcount);
                        rd = request.getRequestDispatcher("/new_patient.jsp");
                    
                        request.setAttribute("doctor_list", ds);
                        rd.forward(request, response);
                    }
                }
                else if(role.equals("pharmacist"))
                    response.sendRedirect("pharmacist.jsp");  
                else
                    response.sendRedirect("error.jsp");
                session.setAttribute("role", role);    
            }
            else{
                response.sendRedirect("error.jsp");
            }
        } catch (ClassNotFoundException ex) {
            response.sendRedirect("errorClass.jsp");
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            response.sendRedirect("errorSQL.jsp");
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

