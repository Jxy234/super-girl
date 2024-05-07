package com.cdvtc.news.dao.impl;

import com.cdvtc.news.dao.AdminDao;
import com.cdvtc.news.model.Admin;
import com.cdvtc.news.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoImpl implements AdminDao {
    @Override
    public Admin getAdminById(int adminId) {
        //获取连接
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            //执行查询并返回结果集
            pst = con.prepareStatement("select * from admin where id = ?");
            pst.setInt(1, adminId); //填充参数，注意参数编号从1开始
            rs = pst.executeQuery();
            Admin admin = new Admin();
            if (rs.next()) {
                admin.setId(rs.getInt("id"));
                admin.setAccount(rs.getString("account"));
                admin.setName(rs.getString("name"));
                admin.setPassword(rs.getString("password"));
            }
            //返回结果
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放JDBC相关资源
            DBUtil.release(rs, pst, con);
        }
        return null;
    }
}
