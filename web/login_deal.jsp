<%@ page import="com.cdvtc.news.dao.impl.UserDaoImpl" %>
<%@ page import="com.cdvtc.news.dao.UserDao" %>
<%@ page import="com.cdvtc.news.model.User" %>
<%@ page import="com.cdvtc.news.util.Md5Util" %><%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2024/4/8
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 获取参数
    String account = request.getParameter("account");
    String password = request.getParameter("password");

    UserDao userDao = new UserDaoImpl();

    User user = userDao.login(account, Md5Util.md5(password));

    // 验证账户密码
    if(user != null) { // 验证成功

        // 设置当前登陆用户昵称
        session.setAttribute("user", user); // 设置昵称
        response.sendRedirect("index.jsp"); // 页面跳转，进入首页, Redirect,重定向， 两次request

    } else { //验证失败
        request.setAttribute("error", "账户或密码错误。"); // 设置error信息
        request.getRequestDispatcher("login.jsp").forward(request, response); // 页面跳转，返回登陆页面，forward前转，一次request
    }

%>>
