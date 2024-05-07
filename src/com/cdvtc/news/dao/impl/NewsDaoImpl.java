package com.cdvtc.news.dao.impl;

import com.cdvtc.news.dao.AdminDao;
import com.cdvtc.news.dao.NewsDao;
import com.cdvtc.news.model.Admin;
import com.cdvtc.news.model.News;
import com.cdvtc.news.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDaoImpl implements NewsDao {
    @Override
    public List<News> getAllNews() {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from news");
            //封装结果为列表数据
            List<News> newsList = new ArrayList<>();
            while (rs.next()) {
                News news = new News();

                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setImg(rs.getString("img"));
                news.setContent(rs.getString("content"));
                news.setPubDate(rs.getTimestamp("pubdate"));
                news.setStick(rs.getBoolean("stick"));
                news.setClickCount(rs.getInt("click_count"));

                /**
                 * 获取编辑信息（先获取编号，然后根据编号获取全部信息）
                 */
                int editorId = rs.getInt("editor");
                AdminDao adminDao = new AdminDaoImpl();
                Admin editor = adminDao.getAdminById(editorId);
                news.setEditor(editor);

                news.setCommentNum(this.getCommentNumByNewsId(news.getId())); // 关联查询

                newsList.add(news);
            }

            //释放连接资源
            rs.close();
            st.close();
            con.close();

            //返回结果
            return newsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 获取新闻评论数
     *
     * @param newsId
     * @return
     */
    private Integer getCommentNumByNewsId(int newsId) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("select count(*) from comment WHERE news_id =?");
            pst.setInt(1, newsId);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int num = rs.getInt(1);

                //释放连接资源
                rs.close();
                pst.close();
                con.close();

                return num;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<News> getNewsByTag(Integer tagId) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("select * from news inner join news_tag on news.id=news_tag.news_id where tag_id = ?");
            pst.setInt(1, tagId);
            ResultSet rs = pst.executeQuery();

            //封装结果为列表数据
            List<News> newsList = new ArrayList<>();
            while (rs.next()) {
                News news = new News();

                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setImg(rs.getString("img"));
                news.setContent(rs.getString("content"));
                news.setPubDate(rs.getTimestamp("pubdate"));
                news.setStick(rs.getBoolean("stick"));
                news.setClickCount(rs.getInt("click_count"));

                /**
                 * 获取编辑信息（先获取编号，然后根据编号获取全部信息）
                 */
                int editorId = rs.getInt("editor");
                AdminDao adminDao = new AdminDaoImpl();
                Admin editor = adminDao.getAdminById(editorId);
                news.setEditor(editor);

                news.setCommentNum(this.getCommentNumByNewsId(news.getId())); // 关联查询

                newsList.add(news);
            }

            //释放连接资源
            rs.close();
            pst.close();
            con.close();

            //返回结果
            return newsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<News> getNewsByCategory(Integer categoryId) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("select * from news where category_id = ?");
            pst.setInt(1, categoryId); //填充参数，注意参数编号从1开始
            ResultSet rs = pst.executeQuery();

            //封装结果为列表数据
            List<News> newsList = new ArrayList<>();
            while (rs.next()) {
                News news = new News();

                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setImg(rs.getString("img"));
                news.setContent(rs.getString("content"));
                news.setPubDate(rs.getTimestamp("pubdate"));
                news.setStick(rs.getBoolean("stick"));
                news.setClickCount(rs.getInt("click_count"));

                /**
                 * 获取编辑信息（先获取编号，然后根据编号获取全部信息）
                 */
                int editorId = rs.getInt("editor");
                AdminDao adminDao = new AdminDaoImpl();
                Admin editor = adminDao.getAdminById(editorId);
                news.setEditor(editor);

                news.setCommentNum(this.getCommentNumByNewsId(news.getId())); // 关联查询

                newsList.add(news);
            }

            //释放连接资源
            rs.close();
            pst.close();
            con.close();

            //返回结果
            return newsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<News> getStickNews(int limit) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("select * from news where stick = ? limit ?");
            pst.setBoolean(1, true);
            pst.setInt(2, limit);
            ResultSet rs = pst.executeQuery();

            //封装结果为列表数据
            List<News> newsList = new ArrayList<>();
            while (rs.next()) {
                News news = new News();

                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setImg(rs.getString("img"));
                news.setContent(rs.getString("content"));
                news.setPubDate(rs.getTimestamp("pubdate"));
                news.setStick(rs.getBoolean("stick"));
                news.setClickCount(rs.getInt("click_count"));

                /**
                 * 获取编辑信息（先获取编号，然后根据编号获取全部信息）
                 */
                int editorId = rs.getInt("editor");
                AdminDao adminDao = new AdminDaoImpl();
                Admin editor = adminDao.getAdminById(editorId);
                news.setEditor(editor);

                news.setCommentNum(this.getCommentNumByNewsId(news.getId())); // 关联查询

                newsList.add(news);
            }

            //释放连接资源
            rs.close();
            pst.close();
            con.close();

            //返回结果
            return newsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public News getNewsById(int newsId) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("select * from news where id = ?");
            pst.setInt(1, newsId); //填充参数，注意参数编号从1开始
            ResultSet rs = pst.executeQuery();

            News news = new News();
            if (rs.next()) {
                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setContent(rs.getString("content"));
                news.setPubDate(rs.getTimestamp("pubdate"));
                news.setStick(rs.getBoolean("stick"));
                news.setClickCount(rs.getInt("click_count"));

                /**
                 * 获取编辑信息（先获取编号，然后根据编号获取全部信息）
                 */
                int editorId = rs.getInt("editor");
                AdminDao adminDao = new AdminDaoImpl();
                Admin editor = adminDao.getAdminById(editorId);
                news.setEditor(editor);

                news.setCommentNum(this.getCommentNumByNewsId(news.getId())); // 关联查询
            }

            //释放连接资源
            rs.close();
            pst.close();
            con.close();

            //返回结果
            return news;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<News> getRecommendedNews(int newsId) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from news order by rand() limit 5");
            //封装结果为列表数据
            List<News> newsList = new ArrayList<>();
            while (rs.next()) {
                News news = new News();

                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setImg(rs.getString("img"));
                news.setContent(rs.getString("content"));
                news.setPubDate(rs.getTimestamp("pubdate"));
                news.setStick(rs.getBoolean("stick"));
                news.setClickCount(rs.getInt("click_count"));

                /**
                 * 获取编辑信息（先获取编号，然后根据编号获取全部信息）
                 */
                int editorId = rs.getInt("editor");
                AdminDao adminDao = new AdminDaoImpl();
                Admin editor = adminDao.getAdminById(editorId);
                news.setEditor(editor);

                news.setCommentNum(this.getCommentNumByNewsId(news.getId())); // 关联查询

                newsList.add(news);
            }

            //释放连接资源
            rs.close();
            st.close();
            con.close();

            //返回结果
            return newsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<News> getHotNews() {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from news order by pubdate desc limit 6");
            //封装结果为列表数据
            List<News> newsList = new ArrayList<>();
            while (rs.next()) {
                News news = new News();

                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setImg(rs.getString("img"));
                news.setContent(rs.getString("content"));
                news.setPubDate(rs.getTimestamp("pubdate"));
                news.setStick(rs.getBoolean("stick"));
                news.setClickCount(rs.getInt("click_count"));

                /**
                 * 获取编辑信息（先获取编号，然后根据编号获取全部信息）
                 */
                int editorId = rs.getInt("editor");
                AdminDao adminDao = new AdminDaoImpl();
                Admin editor = adminDao.getAdminById(editorId);
                news.setEditor(editor);

                news.setCommentNum(this.getCommentNumByNewsId(news.getId())); // 关联查询

                newsList.add(news);
            }

            //释放连接资源
            rs.close();
            st.close();
            con.close();

            //返回结果
            return newsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void updateClickCount(int newsId) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement("update news set click_count=click_count+1 where id=?");
            pst.setInt(1, newsId);

            pst.executeUpdate();

            //释放连接资源
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NewsDao newsDao = new NewsDaoImpl();
        List<News> newsList = newsDao.getAllNews();
        System.out.println(newsList);
    }
}
