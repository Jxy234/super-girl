package com.cdvtc.news.web;

import com.cdvtc.news.dao.UserDao;
import com.cdvtc.news.dao.impl.UserDaoImpl;
import com.cdvtc.news.model.User;
import com.cdvtc.news.util.Md5Util;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取参数
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        UserDao userDao = new UserDaoImpl();

        User user = userDao.login(account, Md5Util.md5(password));

        // 验证账户密码
        if(user != null) { // 验证成功

            // 设置当前登陆用户昵称
            HttpSession session = request.getSession();  //通过reqeust获取session对象
            session.setAttribute("user", user); // 设置昵称
            response.sendRedirect("index.jsp"); // 页面跳转，进入首页, Redirect,重定向， 两次request

        } else { //验证失败
            request.setAttribute("error", "账户或密码错误。"); // 设置error信息
            request.getRequestDispatcher("login.jsp").forward(request, response); // 页面跳转，返回登陆页面，forward前转，一次request
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
    }
}
