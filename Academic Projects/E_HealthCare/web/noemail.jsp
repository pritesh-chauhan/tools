<%-- 
    Document   : noemail
    Created on : Apr 17, 2020, 3:39:16 AM
    Author     : prite
--%>

<%@page import="com.neu.model.DoctorAppointment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="d" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="e" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@include file = "header.html"%>
<%@include file = "sessionInvalidate.jsp"%>
<form action="LogoutServlet" method="post">
        <input type="submit" value="Logout" style="text-align: justify; float: right;"/>
    </form>
</div>
<hr><br><br>

<%    
    DoctorAppointment docapp = new DoctorAppointment();
    docapp = (DoctorAppointment)request.getAttribute("doctor");
    List patient_id = (ArrayList)docapp.getPatient_id();
    List patient_name = (ArrayList)docapp.getPatient_name();
    List message = (ArrayList)docapp.getMessage();
    int doctor_id = docapp.getDoctor_id();
    String doctor_name = (String)docapp.getDoctor_name();
%>

<div class="container">
    <h1 class="blink-one"><i>!!!!!!!!!No Medicines not prescribed!!!!!!!!!</i></h1>
    <!--<h2>Please click below to go back to dashboard</h2>-->
    <a href="doctor.jsp">Go to Dashboard</a>
</div>
<%@include file = "footer.html"%>


