<%@ page import="com.cdvtc.news.model.Category" %>
<%@ page import="com.cdvtc.news.dao.CategoryDao" %>
<%@ page import="com.cdvtc.news.dao.impl.CategoryDaoImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cdvtc.news.dao.TagDao" %>
<%@ page import="com.cdvtc.news.dao.impl.TagDaoImpl" %>
<%@ page import="com.cdvtc.news.model.Tag" %>
<%@ page import="com.cdvtc.news.dao.impl.NewsDaoImpl" %>
<%@ page import="com.cdvtc.news.dao.NewsDao" %>
<%@ page import="com.cdvtc.news.model.News" %><%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2024/3/11
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>极客开发者</title>
  <link rel="stylesheet" href="./css/bootstrap.min.css">
  <link rel="stylesheet" href="./css/main.css">
</head>
<body>
<jsp:include page="commons/header.jsp"></jsp:include>
<div class="container">
  <div class="row">
    <div class="col-sm-2">
      <div class="list-group side-bar hidden-xs">
        <%
          String tid = request.getParameter("tid");
          TagDao tagDao = new TagDaoImpl();
          List<Tag> tags = tagDao.getAllTags();
          for(Tag tag: tags){ %>
        <a href="index.jsp?tag=<%=tag.getId()%>" class="list-group-item<%if(tid!=null && Integer.parseInt(tid)==tag.getId()){%> active<%}%>"><%=tag.getName()%></a>
        <%
          }
        %>
<%--        <a href="#" class="list-group-item active">综合</a>--%>
<%--        <a href="#" class="list-group-item">综合</a>--%>
<%--        <a href="#" class="list-group-item">综合</a>--%>
<%--        <a href="#" class="list-group-item">综合</a>--%>
<%--        <a href="#" class="list-group-item">综合</a>--%>
<%--        <a href="#" class="list-group-item">综合</a>--%>
<%--        <a href="#" class="list-group-item">综合</a>--%>
<%--        <a href="#" class="list-group-item">综合</a>--%>
      </div>
    </div>
    <div class="col-sm-7">
      <div class="news-list">
        <%
          // 获取参数
          String id = request.getParameter("id");
//          System.out.println(id);

          NewsDao newsDao = new NewsDaoImpl();
          List<News>  newsList;
          if(id != null) {
            newsList = newsDao.getNewsByCategory(Integer.parseInt(id));
          } else if (tid != null) {
            newsList = newsDao.getNewsByTag(Integer.parseInt(tid));
          } else {
//            newsList = newsDao.getAllNews();
            newsList = newsDao.getStickNews(5);
          }
          for(News news: newsList) {%>
        <div class="news-list-item clearfix">
          <div class="col-xs-5">
            <img src="img/<%=news.getImg()%>">
          </div>
          <div class="col-xs-7">
            <a href="news.jsp?id=<%=news.getId()%>" class="title"><%=news.getTitle()%></a>
            <div class="info">
              <span class="avatar"><img src="img/logo.png"></span>
              <span><%=news.getEditor().getName()%></span>•
              <span><%=news.getCommentNum()%>评论</span>•
              <span><%=news.getgetIntervalPubDate()%>前</span>
            </div>
          </div>
        </div>
        <%}%>
      </div>
    </div>
    <div class="col-sm-3">
      <div class="search-bar">
        <input type="search" class="form-control" placeholder="搜一下">
      </div>
      <div class="side-bar-card flag clearfix">
        <div class="col-xs-5">
          <img src="img/1-1.png">
        </div>
        <div class="col-xs-7">
          <div class="text-lg">有害信息举报专区</div>
          <div>举报电话：12377</div>
        </div>
      </div>
      <jsp:include page="commons/hotnews.jsp"></jsp:include>
    </div>
  </div>
</div>
<%@include file="commons/footer.jsp"%>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
