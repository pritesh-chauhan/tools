<%-- 

    Document   : login
    Created on : Mar 25, 2020, 1:32:58 PM
    Author     : prite

    This page is where doctor can prescribe the medicines to the selected patient.
    

--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.neu.model.DoctorAppointment"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file = "header.html"%>  
<%@include file = "sessionInvalidate.jsp"%>
<form action="LogoutServlet" method="post">
        <input type="submit" value="Logout" style="float: right;"/>
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
    

<div class="header">
    <h1 style="margin-left: 150px;">Order has been successfully sent to Pharmacy!</h1>
    <form action="DoctorDashboardServlet" method="post">
        <input type="hidden" name="doctor_name" value= "<%= doctor_name%>" />
        <input type="hidden" name="doctor_id" value= "<%= doctor_id%>" />
        <input type="submit" value="Go to Dashboard" />
    </form>
</div>
<%@include file = "footer.html"%>  