<%-- 
    Document   : message_sent
    Created on : Mar 31, 2020, 10:07:01 PM
    Author     : prite
    
    Patient message sent success page.

--%>

<%@page import="com.neu.model.RePatientDetails"%>
<%@page import="com.neu.model.DoctorSelect"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@include file = "header.html"%>  
<%@include file = "sessionInvalidate.jsp"%>
<form action="LogoutServlet" method="post">
        <input type="submit" value="Logout" style="text-align: justify; float: right;"/>
</form>
<hr><br><br>
<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"  url="jdbc:mysql://localhost:3306/health?characterEncoding=latin1&useConfigs=maxPerformance"  user="root"  password="password"/>  
<%    
        RePatientDetails rep = new RePatientDetails();
        rep = (RePatientDetails)request.getAttribute("re_patient");
        
        String patient_name = rep.getPatient_name();
        int patient_id = rep.getPatient_id();
        String doctor_name = rep.getDoctor_name();
        int doctor_id = rep.getDoctor_id();
        int doctor_count = rep.getDoctor_count();
        int count_illness = rep.getCount_illness();
        int count_medicine = rep.getCount_medicine();
    %>


<h1>
    Message has been successfully sent to <%= doctor_name %>. <br>
    Please wait for the further update.
    <form action="PatientDashboardServlet" method="post">
        <input type="hidden" name="doctor_name" value= "<%= doctor_name%>" />
        <input type="hidden" name="doctor_id" value= "<%= doctor_id%>" />
        <input type="hidden" name="patient_id" value= "<%= patient_id%>" />
        <input type="hidden" name="patient_name" value= "<%= patient_name%>" />
        <input type="submit" value="Go to Dashboard" />
    </form>
</h1>
    
<%@include file = "footer.html"%>
    
