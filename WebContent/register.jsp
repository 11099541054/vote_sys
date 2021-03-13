<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注   册</title>
<script type="text/javascript" src="jq/jquery-1.11.1.js"></script>
<script type="text/javascript">
var flag1 = false;
var flag2 = false;
$(function() {
	//检查输入的用户名
	$("[name=username]").blur(function(){
		//获取用户名输入框的值
		var username = $(this).val();
		//如果输入不为空串，则需要检查商品名称是否已经存在
		if(username.trim() != ''){
			//发送ajax请求
			$.get('UserServlet?option=checkName',{username:username},function(response){
				console.info(response);
				if(response == 'true'){
					$("#promptPrdName").css("color","green");
					$("#promptPrdName").html("（用户名名输入正确）");
					flag1=true;
				}else{
					$("#promptPrdName").css("color","red");
					$("#promptPrdName").html("（该用户名已被使用）");
				}
			});	
		}else{
			$("#promptPrdName").css("color","red");
			$("#promptPrdName").html("（用户名不能为空或输入空格）");
		}	
	});
	//检查两次密码是否一致
	$("[name=password]").keyup(function(){
		var password = $("[name=password]").val();
		if(password.trim() !=''&& password.length >= 6){
			$("#promptPassword").css("color","green");
			$("#promptPassword").html("（密码输入正确）");
			$("[name=confirmPassword]").keyup(function(){
			var confirmPassword = $(this).val();
				if(password == confirmPassword ){
					$("#promptConfirmPassword").css("color","green");
					$("#promptConfirmPassword").html("（两次输入密码一致）");
					flag2=true;
				}else{
					$("#promptConfirmPassword").css("color","red");
					$("#promptConfirmPassword").html("（两次密码输入不一致）");
				}
			});
		}else{
			$("#promptPassword").css("color","red");
			$("#promptPassword").html("（密码长度不能小于6位输入空格）");
		}
	});
})
function checkInput(){
	if(!(flag1&&flag2)){
		confirm("你的输入有误!");
	}
	return flag1&&flag2;
}
</script>
</head>
<body>
<div id="header" class="wrap">
	<img src="images/logo.gif" />
</div>
<div id="navbar" class="wrap">
	<div class="search">
	<!--  
		<form method="get" action="index.html">
			<input type="text" name="keywords" class="input-text" /><input type="submit" name="submit" class="input-button" value="" />
		</form>
	-->	
	</div>
</div>
<div id="register" class="box">
	<h2>新用户注册</h2>
	<div class="content">
	   <form method="post" action="UserServlet?option=register" onsubmit="return checkInput();">
			<dl>
				<!-- 
				<dt>用户ID：</dt>
				<dd><input type="text" class="input-text" name="userId"/></dd>  
				-->
				
				<dt>用户名：</dt>
				<dd><input type="text" class="input-text" name="username"/>
				<span id="promptPrdName"></span>
				</dd>
				<dt>密码：</dt>
				<dd><input type="password" class="input-text" name="password"/>
				<span id="promptPassword"></span>
				</dd>
				<dt>确认密码：</dt>
				<dd><input type="password" class="input-text" name="confirmPassword"/>
				<span id="promptConfirmPassword"></span>
				</dd>
				<dt></dt>
				<dd><input type="submit" class="input-button" name="submit" value="" /></dd>
			</dl>
		</form>
		<div class="error"></div>
	</div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
