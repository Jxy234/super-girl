package com.cdvtc.news.web;

import com.cdvtc.news.model.User;
import com.cdvtc.news.service.UserService;
import com.cdvtc.news.service.impl.UserServiceImpl;
import com.cdvtc.news.util.Md5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(urlPatterns = "/signup")
public class SignupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取参数前设置编码，可解决post方式提交的中文乱码
        request.setCharacterEncoding("UTF8");

        //获取参数
        String account = request.getParameter("account");
        String nickname = request.getParameter("nickname");
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String birthday = request.getParameter("birthday");
        String photo = request.getParameter("photo");

        /**
         * 数据验证
         */
        UserService userService = new UserServiceImpl();
        String error_account = null;
        if(userService.isUserExisted("account", account)){
            error_account = "账户已经存在！";
            request.setAttribute("error_account", error_account);
        }
        String error_nickname = null;
        if(userService.isUserExisted("nickname", nickname)){
            error_nickname = "昵称已经存在！";
            request.setAttribute("error_nickname", error_nickname);
        }
        String error_mobile = null;
        if(userService.isUserExisted("mobile", mobile)){
            error_mobile = "手机号码已经存在！";
            request.setAttribute("error_mobile", error_mobile);
        }
        String error_email = null;
        if(email != null && userService.isUserExisted("email", email)){ // 注意：email为null时不验证
            error_email = "电子邮件已经存在！";
            request.setAttribute("error_email", error_email);
        }

        /**
         * 根据验证结果进行页面跳转
         */
        if(error_account==null && error_nickname==null && error_mobile==null && error_email==null){ //验证通过
            User user = new User();
            user.setNickname(nickname);
            user.setEmail(email);
            user.setMobile(mobile);
            user.setAccount(account);
            user.setPassword(Md5Util.md5(password)); //密码使用MD5加密保存
            user.setPhoto(photo);

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                user.setBirthday(df.parse(birthday));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            userService.addUser(user);

            //进入登陆页面
//        response.sendRedirect("login.jsp");
            request.setAttribute("page", "login.jsp");
            request.setAttribute("message", "账户注册成功，请使用该账户登陆系统。");
            request.getRequestDispatcher("alert_jump.jsp").forward(request, response);
        } else { //返回至注册页面
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
