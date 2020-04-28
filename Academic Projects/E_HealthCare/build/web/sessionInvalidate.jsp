<%-- 
    Document   : sessionInvalidate
    Created on : Apr 18, 2020, 10:41:00 PM
    Author     : prite
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
%>