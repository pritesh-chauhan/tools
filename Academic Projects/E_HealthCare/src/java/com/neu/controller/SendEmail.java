package com.neu.controller;
import com.neu.model.DoctorAppointment;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * @author prite
 * 
 */

@WebServlet("/EmailSendingServlet")
public class SendEmail extends HttpServlet{
 
    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;
    private static final long serialVersionUID = 1L;
    public SendEmail() {
        super();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String doctor_name = request.getParameter("doctor_name");
        String patient_name = request.getParameter("patient_name");
        String doctor_id = request.getParameter("doctor_id");
        String doctor_email = "";
        String jdbcURL = "jdbc:mysql://localhost:3306/health?characterEncoding=latin1&useConfigs=maxPerformance";
        String dbUser = "root";
        String dbPassword = "password";        
        JDBCController db = new JDBCController();
        // Step1
        System.out.println("\n 1st ===> setup Mail Server Properties..");
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        System.out.println("Mail Server Properties have been setup successfully..");

        // Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(mailServerProperties);
        generateMailMessage = new MimeMessage(getMailSession);
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            Statement statement = connection.createStatement();
            
            
            
            String sql_e = "SELECT email from users where id = '"+doctor_id+"';";
            ResultSet resultSet = statement.executeQuery(sql_e);
            while(resultSet.next()){
                doctor_email = resultSet.getString("email");
            }
            
            System.out.println("Email servlet"+doctor_email+" "+patient_name);
            
            String sql_c = "SELECT medicine, dose, quantity from medicine where patient_name = '"+patient_name+"';";
            resultSet = statement.executeQuery(sql_c);
            
            
            String count = "";
            String med_hist = "";
            String quant_hist = "";
            String dose_hist = "";
            while (resultSet.next()) {
                count += "Medicine Name: "+resultSet.getString("medicine")+" Quantity: "+resultSet.getString("quantity")+"<br>";
                med_hist += resultSet.getString("medicine")+","; 
                quant_hist += resultSet.getString("quantity")+","; 
                dose_hist += resultSet.getString("dose")+","; 
            }
            if(med_hist.charAt(med_hist.length()-1) == ','){
                med_hist = med_hist.substring(0, med_hist.length()-1);
            }
            
            if(quant_hist.charAt(quant_hist.length()-1) == ','){
                quant_hist = quant_hist.substring(0, quant_hist.length()-1);
            }
            
            if(dose_hist.charAt(dose_hist.length()-1) == ','){
                dose_hist = dose_hist.substring(0, dose_hist.length()-1);
            }
            
            System.out.println("History items: "+med_hist+" "+quant_hist);
            generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(doctor_email));
            generateMailMessage.setSubject("Order from "+doctor_name);
            String emailBody = "Hello Pharmacist,<br><br> Order for "+ patient_name + " is as follows: <br><br>" + count + "<br><br> Regards, <br>"+doctor_name;
            generateMailMessage.setContent(emailBody, "text/html");
            System.out.println("Mail Session has been created successfully..");

            // Step3
            System.out.println("\n\n 3rd ===> Get Session and Send mail");
            Transport transport = getMailSession.getTransport("smtp");
            transport.connect("smtp.gmail.com", "rhpeople66@gmail.com", "66rhpeople");
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();
            
            sql_c = "update doctor set count = count - 1 where doctor_name = '"+doctor_name+"';";
            statement.executeUpdate(sql_c);
            
            sql_c = "delete from illness where patient_name = '"+patient_name+"' and doctor_name = '"+doctor_name+"';";
            statement.executeUpdate(sql_c);
            
            sql_c = "update history set medicine = '"+med_hist+"',dose='"+dose_hist+"' ,quantity = '"+quant_hist+"' where patient_name = '"+patient_name+"' and doctor_name = '"+doctor_name+"';";
            statement.executeUpdate(sql_c);
            
            
            String sql_n = "select patient_id from illness where doctor_name = '"+doctor_name+"';";
            List<String> patient_id = db.databaseManagerReturnList(sql_n, "patient_id");

            sql_n = "select patient_name from illness where doctor_name = '"+doctor_name+"';";
            List<String> lpatient_name = db.databaseManagerReturnList(sql_n, "patient_name");

            sql_n = "select message from illness where doctor_name = '"+doctor_name+"';";
            
            List<String> message = db.databaseManagerReturnList(sql_n, "message");
            
            DoctorAppointment doctor = new DoctorAppointment(patient_id, lpatient_name, message, Integer.parseInt(doctor_id), doctor_name);
            
            RequestDispatcher rd = request.getRequestDispatcher("/doctor_order.jsp");
                    
            request.setAttribute("doctor", doctor);
            rd.forward(request, response);
            
            
        } catch (MessagingException ex) {
            response.sendRedirect("errorMail.jsp");
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            response.sendRedirect("errorClass.jsp");
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            response.sendRedirect("errorSQL.jsp");
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}