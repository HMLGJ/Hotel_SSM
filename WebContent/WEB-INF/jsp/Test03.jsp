<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试</title>
<script>
	function check(){
		var username = ${"#username"}.val()
		var user_phone = ${"#user_phone"}.val()
		if(username==""||user_phone==""){
			$("#message").text("账号或密码不能为空!")
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<font color="red">
		<span id="message">${msg}</span>
	</font>
	<from action="${pageContext.request.contextPath }/Test03.action" mothod="post" onsubmit="return check()">
		用户名:<input id="username" type="text" name="username"/>
		<br /><br />
		手机号:<input id="user_phone" type="text" name="user_phone" />
		<br /><br />
		登录:<input type="submit" value="登录" />
	</from>
</body>
</html>