<%--
  Created by IntelliJ IDEA.
  User: 荆哲
  Date: 2020/8/20
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>Title</title>
    <base href="<%=basePath%>">
    <link href="static/css/app.css" rel="stylesheet">
    <!-- jQuery -->
    <script type="text/javascript" src="static/jquery/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            // alert("JSP文件加载完毕");
            $.ajax({
                url:"login",
                type:"post",
                contentType:"application/json",
                data:{username:"abc",password:"123"},
                success:function (data) {

                }
            });
            $("#btn").click(function () {
                // alert("点击按钮");
            });
        });
    </script>
</head>
<body>
<form action="login">
    用户名：<input type="text" name="username" placeholder="请输入用户名"> <br>
    密码：<input type="password" name="password" placeholder="请输入密码"> <br>
    <input type="submit" value="提交">
</form>
</body>
</html>
