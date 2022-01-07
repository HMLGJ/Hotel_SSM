<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body>
<!-- 顶部开始 -->
<div class="Home page header" style="background-color: #393D49;">
    <ul class="Home ul" id="Home-ul">
            <li data-id="index" class="layui-nav-item layui-hide-xs layui-this">
                <a class="fly-case-active" data-type="toTopNav"
                   href="/home.html">首页</a>
            </li>
            <li data-id="login" class="layui-nav-item layui-hide-xs ">
                <a class="fly-case-active" data-type="toTopNav"
                   href="/webapp/login.jsp">登录</a>
            </li>
            <li data-id="register" class="layui-nav-item layui-hide-xs ">
                <a class="fly-case-active" data-type="toTopNav"
                   href="/register.jsp">注册</a>
            </li>
            <span class="layui-nav-bar" style="left: 560px; top: 55px; width: 0px; opacity: 0;"></span>
        </ul>
</div>
<!-- 顶部结束 -->	
</body>
</html>