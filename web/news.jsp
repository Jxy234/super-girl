<%@ page import="com.cdvtc.news.dao.NewsDao" %>
<%@ page import="com.cdvtc.news.dao.impl.NewsDaoImpl" %>
<%@ page import="com.cdvtc.news.model.News" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2024/3/25
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 获取参数
    String id = request.getParameter("id");

    // 根据id获取新闻对象
    NewsDao newsDao = new NewsDaoImpl();
    News news = newsDao.getNewsById(Integer.parseInt(id));


    // 更新点击数(阅读数）
    newsDao.updateClickCount(Integer.parseInt(id));
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新闻详情</title>
    <link rel="stylesheet" href="./css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/main.css">
</head>
<body>
<jsp:include page="commons/header.jsp"></jsp:include>
<div class="container">
    <div class="col-xs-8">
        <h1 class="news-title"><%=news.getTitle()%></h1>
        <div class="news-status">25k阅读•35分钟前发布
            <div class="label label-default">教育</div>
            <div class="label label-default">情感</div>
        </div>
        <div class="news-content">
            <%=news.getContent()%>
        </div>
    </div>
    <div class="col-xs-4">
        <div class="side-bar-card">
            <div class="card-title">相关推荐</div>

            <div class="card-body">
                <div class="list">
                    <%
                        List<News> recommendedNewsList = newsDao.getRecommendedNews(Integer.parseInt(id));

                        for(News recommendedNews: recommendedNewsList) {
                    %>
                    <div class="item clearfix">
                        <div class="col-xs-5 no-padding-h"><img src="img/<%=recommendedNews.getImg()%>"></div>
                        <div class="col-xs-7">
                            <div class="title"><a href="news.jsp?id=<%=recommendedNews.getId()%>"><%=recommendedNews.getTitle()%></a></div>
                            <div class="desc"><%=recommendedNews.getCommentNum()%>阅读•<%=recommendedNews.getgetIntervalPubDate()%>发布</div>
                        </div>
                    </div>
                    <%}%>
                </div>
            </div>
        </div>
        <jsp:include page="commons/hotnews.jsp"></jsp:include>
    </div>
</div>
<%@include file="commons/footer.jsp"%>
</body>
</html>
