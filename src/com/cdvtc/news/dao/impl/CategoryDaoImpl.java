package com.cdvtc.news.dao.impl;

import com.cdvtc.news.dao.CategoryDao;
import com.cdvtc.news.model.Category;
import com.cdvtc.news.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private Connection conn;

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();

        // 获取连接
        conn = DBUtil.getConnection();
//        Statement st = null;


        // 创建Staement
        try(Statement st = conn.createStatement()) {
            // 执行SQL，获取ResultSet
            String sql = "select * from category";
            try(ResultSet rs = st.executeQuery(sql)) {
                // 从rs中获取数据
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");

                    // 将数据写入封装对象
                    Category category = new Category();
                    category.setId(id);
                    category.setName(name);

                    //将对象往往集合类中
                    categories.add(category);
                }
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        Category category1 = new Category();
//        category1.setId(1);
//        category1.setName("时政");
//        categories.add(category1);
//
//        Category category2 = new Category();
//        category2.setId(2);
//        category2.setName("国内");
//        categories.add(category2);
//
//        Category category3 = new Category();
//        category3.setId(3);
//        category3.setName("国际");
//        categories.add(category3);

        return categories;
    }

    public static void main(String[] args) {
        CategoryDao categoryDao = new CategoryDaoImpl();
        List<Category> categoryList = categoryDao.getAllCategories();

        for(Category category: categoryList) {
            System.out.println(category);
        }
    }
}
