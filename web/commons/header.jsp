<%@ page import="com.cdvtc.news.dao.CategoryDao" %>
<%@ page import="com.cdvtc.news.dao.impl.CategoryDaoImpl" %>
<%@ page import="com.cdvtc.news.model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cdvtc.news.model.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <a href="index.html" class="navbar-brand"></a>
        </div>
        <!-- class="visible-xs-inline-block"：在超小屏幕上显示-->
        <label for="toggle-checkbox" id="toggle-label" class="visible-xs-inline-block">菜单</label>
        <input type="checkbox" class="hidden" id="toggle-checkbox">
        <div class="hidden-xs">
            <ul class="nav navbar-nav">
                <%
                    String id = request.getParameter("id");
                %>
                <li <%if(id==null){%> class="active" <%}%>><a href="index.jsp">首页</a></li>
                <%
                    CategoryDao categoryDao = new CategoryDaoImpl();
                    List<Category> categoryList = categoryDao.getAllCategories();
                    for(Category category: categoryList) {  %>
                <li <%if(id!=null&& Integer.parseInt(id)==category.getId()){%>class="active"<%}%>><a href="index.jsp?id=<%=category.getId()%>"><%=category.getName()%></a></li>
                <%}%>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <%
                    User user = (User)session.getAttribute("user");
                    if(user!= null) {
                %>
                <li><a href="#"><img src="${pageContext.request.contextPath}/img/photos/${sessionScope.user.photo}" class="img-circle" width="32px"></a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">${sessionScope.user.nickname}<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="userinfo.jsp">个人信息</a></li>
                        <li><a href="change_password.jsp">修改密码</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="javascript:logout()">退出</a></li>
                    </ul>
                </li>
                <%} else{%>
                <li><a href="login.jsp">登陆</a></li>
                <%}%>
                <li><a href="signup.jsp">注册</a></li>
            </ul>
        </div>
    </div>
</div>