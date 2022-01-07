<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试</title>
</head>
<body>
	<table border=1>
		<tr>
			<td>编号</td>
			<td>姓名</td>
			<td>手机号</td>
		</tr>
		<tr>
			<td>${UserTest.id}</td>
			<td>${UserTest.username}</td>
			<td>${UserTest.user_phone}</td>
		</tr>
	</table>
</body>
</html>