<%@ page import="com.cdvtc.news.model.News" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cdvtc.news.dao.NewsDao" %>
<%@ page import="com.cdvtc.news.dao.impl.NewsDaoImpl" %><%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2024/4/1
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="side-bar-card">
    <div class="card-title">24小时热闻</div>
    <div class="card-body">
        <div class="list">
            <%
                NewsDao newsDao = new NewsDaoImpl();
                List<News> hotNewsList = newsDao.getHotNews();
                for(News hotNews: hotNewsList) {
            %>
            <div class="item">
                <a class="title" href="news.jsp?id=<%=hotNews.getId()%>"><%=hotNews.getTitle()%></a>
                <div class="desc"><%=hotNews.getClickCount()%>阅读　<%=hotNews.getCommentNum()%>评论</div>
            </div>
            <%}%>
        </div>
    </div>
</div>
