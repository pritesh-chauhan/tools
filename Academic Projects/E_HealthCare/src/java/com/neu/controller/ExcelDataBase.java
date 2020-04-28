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
import com.neu.model.DoctorAppointment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

@WebServlet(name = "/ExcelDataBase", urlPatterns = "/excel.jsp")
public class ExcelDataBase extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public ExcelDataBase() {
        super();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        String doctor_name = request.getParameter("doctor_name");
        int doctor_id = Integer.parseInt(request.getParameter("doctor_id"));
        
        JDBCController db = new JDBCController();
        System.out.println(doctor_id+"-----------------------Doctorzzz id"+doctor_name);
        String jdbcURL = "jdbc:mysql://localhost:3306/health?characterEncoding=latin1&useConfigs=maxPerformance";
        String dbUser = "root";
        String dbPassword = "password";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            Statement statement = connection.createStatement();
            
            String sql = "select * from history where doctor_id="+doctor_id+";";
            ResultSet resultSet = statement.executeQuery(sql);
            
            HSSFWorkbook workbook = new HSSFWorkbook(); 
            HSSFSheet spreadsheet = workbook.createSheet("history db");

            HSSFRow row = spreadsheet.createRow(0);
            HSSFCell cell;
            cell = row.createCell(0);
            cell.setCellValue("Patient Id");
            cell = row.createCell(1);
            cell.setCellValue("Patient Name");
            cell = row.createCell(2);
            cell.setCellValue("Doctor Id");
            cell = row.createCell(3);
            cell.setCellValue("Doctor Name");
            cell = row.createCell(4);
            cell.setCellValue("Message");
            cell = row.createCell(5);
            cell.setCellValue("Medicine");
            cell = row.createCell(6);
            cell.setCellValue("Dose");
            cell = row.createCell(7);
            cell.setCellValue("Quantity");
            int i = 1;

            while(resultSet.next()) {
               row = spreadsheet.createRow(i);
               cell = row.createCell(0);
               cell.setCellValue(resultSet.getInt("patient_id"));
               cell = row.createCell(1);
               cell.setCellValue(resultSet.getString("patient_name"));
               cell = row.createCell(2);
               cell.setCellValue(resultSet.getString("doctor_id"));
               cell = row.createCell(3);
               cell.setCellValue(resultSet.getString("doctor_name"));
               cell = row.createCell(4);
               cell.setCellValue(resultSet.getString("message"));
               cell = row.createCell(5);
               cell.setCellValue(resultSet.getString("medicine"));
               cell = row.createCell(6);
               cell.setCellValue(resultSet.getString("dose"));
               cell = row.createCell(7);
               cell.setCellValue(resultSet.getString("quantity"));
               i++;
            }

            FileOutputStream out = new FileOutputStream(new File("C:\\Users\\prite\\Desktop\\Enterprise_Software_Design\\E_HealthCare\\doctor"+doctor_id+".xls"));
            workbook.write(out);
            out.close();
            
            String sql_n = "select patient_id from illness where doctor_name = '"+doctor_name+"';";
            List<String> patient_id = db.databaseManagerReturnList(sql_n, "patient_id");

            sql_n = "select patient_name from illness where doctor_name = '"+doctor_name+"';";
            List<String> patient_name = db.databaseManagerReturnList(sql_n, "patient_name");

            sql_n = "select message from illness where doctor_name = '"+doctor_name+"';";
            List<String> message = db.databaseManagerReturnList(sql_n, "message");
            
            DoctorAppointment doctor = new DoctorAppointment(patient_id, patient_name, message, doctor_id, doctor_name);
            
            RequestDispatcher rd = request.getRequestDispatcher("/doctor.jsp");
                    
            request.setAttribute("doctor", doctor);
            rd.forward(request, response);
            
            System.out.println("doctor.xlsx written successfully");

        }catch (ClassNotFoundException ex) {
            response.sendRedirect("errorClass.jsp");
            Logger.getLogger(ExcelDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            response.sendRedirect("errorSQL.jsp");
            Logger.getLogger(ExcelDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}