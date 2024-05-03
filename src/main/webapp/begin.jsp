<%@ page import="Model.taikhoan" %>
<%@ page import="java.util.UUID" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css" />
    
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/css/favicon.ico">

</head>
<body>
<%  session = request.getSession(true);
    String csrfToken = UUID.randomUUID().toString();
    session.setAttribute("csrfToken", csrfToken);%>
<div class="container">
    <div class="image">
        <img src="https://www.evn.com.vn/userfile/VH/User/huyent_tcdl/images/2021/6/hrmscuatapdoan24621(1).jpeg" style="width:700px;height:460px;" alt="">
    </div>
    <div class="form">
        <form action="<%=request.getContextPath()%>/login">
            <h1>ĐĂNG NHẬP</h1>
            <input type="hidden" name="csrfToken" value="<%= session.getAttribute("csrfToken") %>">
            <div class = "box_button_login"> <button type="submit" class="btn"><b>Đăng nhập</b></button> </div>
        </form>
    </div>
</div>
</body>
</html>