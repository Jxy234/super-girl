<%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2024/3/11
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! int i = 0; %>
<%
    int j = 0;
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- 该部分注释在网页中不会被显示--%>
<!-- HTML注释 -->

今天的日期是: <%= (new java.util.Date()).toLocaleString()%>
</body>
</html>
