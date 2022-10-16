<%@ page import="com.example.webproject.models.Step" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quest game</title>
</head>
<body>
<br/>
<div class="gameBoard">
    <div class="question">
        <h2>${question}</h2>
    </div>
    <div class="firstAnswer">
        <input type="radio" onclick="window.location='/logic?choice=1'">
        ${answers.get(0)}
    </div>
    <div class="secondAnswer">
        <input type="radio" onclick="window.location='/logic?choice=2'">
        ${answers.get(1)}
    </div>
    <br>
</div>
<div>
    Statistics:
    <br>
    <%="Name : " + session.getAttribute("username")%>
    <br>
    <%

    %>
    <%="Games played : " + session.getAttribute("countOfGames")%>
    <br>
    <% String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
    %>
    <%="Players IP : " + ip%>
</div>
<%
    ServletContext context = request.getServletContext();
    Step currentStep = (Step) session.getAttribute("step");
    boolean isLastStep = currentStep.returnNumericStep() == Step.values().length;

    if (isLastStep) {
        context.setAttribute("isLastStep", "yes");
    } else {
        context.setAttribute("isLastStep", "no");
    }
%>
<%%>
</body>
</html>