<%--个人信息修改--%>
<%@ page import="com.cdvtc.news.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人信息</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<jsp:include page="commons/header.jsp"> </jsp:include>
<div class="container">
    <div class="container">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">
                    修改个人信息
                </h3>
            </div>
            <div class="panel-body" style="padding: 20px;">
                <form action="UserInfoEditServlet" class="form-horizontal" role="form" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="account" class="col-sm-2 control-label">账户：</label>
                        <div class="col-sm-10">
<%--                            readonly 不允许编辑。--%>
                            <input type="text" readonly class="form-control" name="account" id="account" placeholder="请输入账户" value="${sessionScope.user.account}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="nickname" class="col-sm-2 control-label">昵称：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="nickname" id="nickname" placeholder="请输入昵称" value="<%=((User)session.getAttribute("user")).getNickname()%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="birthday" class="col-sm-2 control-label">生日：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="birthday" id="birthday" placeholder="请选择您的出生日期"
                                   value="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">电子邮件：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="email" id="email" placeholder="请输入电子邮件" value="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="mobile" class="col-sm-2 control-label">手机号码：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="mobile" id="mobile" placeholder="请输入手机号码" value="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="photo" class="col-sm-2 control-label">头像：</label>
                        <div class="col-sm-10">
                            <input type="file" class="form-control" name="photo" id="photo" placeholder="请选择头像照片" accept="image/jpg,image/jpeg,image/png" value="">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">确定</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@include file="commons/footer.jsp"%>
<%--引入 JavaScript 文件的标签--%>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-datepicker.min.js"></script>
<script src="js/bootstrap-datepicker.zh-CN.min.js"></script>
<script>
    $().ready(function () {
        // 设置日期选择器 Bootstrap 的日期选择器（datepicker）
        $('#birthday').datepicker({
            language: 'zh-CN', //语言
            clearBtn: true,//清除按钮
            format: "yyyy-mm-dd"//日期格式
        });
    });
</script>
</body>
</html>

