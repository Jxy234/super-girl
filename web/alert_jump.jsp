<%--//这段代码是一个JSP页面，用于显示操作提示信息并执行页面跳转。--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>操作提示</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<script>
    alert('${message}'); //调用JavaScript的alert()函数显示一个警告框，其中${message}是一个占位符，用于显示操作提示信息。
    window.location.href='${page}'; //使用JavaScript的window.location.href属性来执行页面跳转，${page}是一个占位符，用于指定跳转的目标页面。
</script>
</body>
</html>
