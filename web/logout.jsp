<%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2024/4/8
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //清除session中保存的登陆相关信息
//    session.removeAttribute("nickname");

    session.invalidate(); // session失效

    // 页面跳转
    response.sendRedirect("index.jsp");
%>