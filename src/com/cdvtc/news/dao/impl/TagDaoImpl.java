package com.cdvtc.news.dao.impl;

import com.cdvtc.news.dao.TagDao;
import com.cdvtc.news.model.Tag;
import com.cdvtc.news.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TagDaoImpl implements TagDao {
    @Override
    public List<Tag> getAllTags() {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from tag");

            //封装结果为列表数据
            List<Tag> tags = new ArrayList<>();
            while (rs.next()) {
                Tag tag = new Tag();

                int id = rs.getInt("id");
                String name = rs.getString("name");
                tag.setId(id);
                tag.setName(name);

                tags.add(tag);
            }

            //释放连接资源
            rs.close();
            st.close();
            con.close();

            //返回结果
            return tags;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        TagDao tagDao = new TagDaoImpl();
        System.out.println(tagDao.getAllTags());
    }
}
