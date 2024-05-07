package com.cdvtc.news.dao.impl;

import com.cdvtc.news.model.User;
import com.cdvtc.news.util.DBUtil;

import java.sql.*;

public class UserDaoImpl implements UserDao {
    /**
     * 用户登陆
     * @param account (账号/手机号/电子邮件)
     * @param password
     * @return
     */
    public User login(String account, String password) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("select * from user where (account=? or mobile=? or email=?) and password=? and forbidden=?");
            pst.setString(1, account);
            pst.setString(2, account);
            pst.setString(3, account);
            pst.setString(4, password);
            pst.setBoolean(5, false);
            ResultSet rs = pst.executeQuery();

            User user = null;
            if (rs.next()) {
                user = new User();
                user.setAccount(account);
                user.setId(rs.getInt("id"));
                user.setBirthday(rs.getDate("birthday"));
                user.setMobile(rs.getString("mobile"));
                user.setEmail(rs.getString("email"));
                user.setPhoto(rs.getString("photo"));
                user.setRegDate(rs.getTimestamp("regdate"));
                user.setNickname(rs.getString("nickname"));
            }

            //释放连接资源
            rs.close();
            pst.close();
            con.close();

            //返回结果
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 判断用户是否已经存在
     * @param key （只能是：account:账户 nickname:昵称  mobile:手机号码 email:电子邮件）
     * @param value
     * @return
     */
    @Override
    public boolean isUserExisted(String key, String value) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("select * from user where " + key + "=?");
            pst.setString(1, value);

            ResultSet rs = pst.executeQuery();

            boolean result = rs.next(); //判断是否查询到数据

            //释放连接资源
            rs.close();
            pst.close();
            con.close();

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean addUser(User user) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("insert into user(account, password, nickname, photo, birthday, email, mobile, regDate, forbidden) values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, user.getAccount());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getNickname());
            pst.setString(4, user.getPhoto());
            pst.setDate(5, new Date(user.getBirthday().getTime())); //需要将java.util.Date转化为java.sql.Date
            pst.setString(6, user.getEmail());
            pst.setString(7, user.getMobile());
            pst.setTimestamp(8, new Timestamp(new java.util.Date().getTime())); //当前时间作为注册时间
            pst.setBoolean(9, false); //默认为不禁用

            int result = pst.executeUpdate();

            //释放连接资源
            pst.close();
            con.close();

            return result > 0; //大于0表示成功创建一行
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
