<%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2024/4/8
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登陆</title>
    <link rel="stylesheet" href="./css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/main.css">
</head>
<body>
<jsp:include page="commons/header.jsp"></jsp:include>
<div class="container container-small">
    <h1>登录
        <small>没有帐号？<a href="signup.html">注册</a></small>
    </h1>
    <form action="login" method="post">
        <div class="form-group">
            <label>账户/手机/邮箱</label>
            <input type="text" class="form-control" name="account">
        </div>
        <div class="form-group">
            <label>密码</label>
            <input type="password" class="form-control" name="password">
        </div>
        <p class="text-danger"><%
            String error = (String)request.getAttribute("error");
            if(error != null){%><%=error%><%}%></p>
        <div class="form-group">
            <button class="btn btn-primary btn-block" type="submit">登录</button>
        </div>
        <div class="form-group">
            <a href="#">忘记密码？</a>
        </div>
    </form>
</div>
<%@include file="commons/footer.jsp"%>
</body>
</html>