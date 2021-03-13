<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
<div id="header" class="wrap">
	<img src="images/logo.gif" />
</div>
<div id="navbar" class="wrap">
	<div class="profile">
		<span class="user"><a href="login.jsp"><c:if test="${user.username==null}">登录</c:if></a></span>
		<span class="user"><c:if test="${user.username!=null}">${user.username}</c:if></span>
		<span class="return"><a href="VoteServlet?option=index">返回列表</a></span>
		<span class="addnew"><c:if test="${user.flag == 0}"><a href="add.jsp">添加新投票</a></c:if></span>
		<span class="modify"><c:if test="${user.flag == 0}"><a href="#">维护</a></c:if></span>
		<span class="exit"><c:if test="${user.flag == 0}"><a href="UserServlet?option=exit">退出</a></c:if></span>
		
	</div>
	<div class="search">
		<form method="post" action="VoteServlet?option=search">
			<input type="text" name="vsTitle" class="input-text" value="${vsTitle}"/>
			<input type="submit" name="submit" class="input-button" value="" />
		</form>
	</div>
</div>
</body>
</html>