<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>参与投票</title>
</head>
<body>
<%@ include file="header.jsp" %>
<div id="vote" class="wrap">
	<h2>参与投票</h2>
	<ul class="list">
		<li>
			<h4>${voteSubject.vsTitle}</h4>
			<p class="info">共有 ${param.optionCount}个选项，已有${param.userCount}个网友参与了投票。</p>
			<form method="post" action="VoteServlet?option=vote">
			    <input type="hidden" name="vsId" value="${voteSubject.vsId}"/> 
				<ol>
				    <c:forEach items="${voteSubject.options}" var="option">
				    	<li><input type="${voteSubject.vsType==1?'radio':'checkbox'}" name="options"  value="${option.voId}"/>${option.voOption}</li>
				    </c:forEach>
				</ol>
				<p class="voteView"><input type="image" src="images/button_vote.gif" /><a href="VoteServlet?option=showView&vsId=${voteSubject.vsId}"><img src="images/button_view.gif" /></a></p>
			</form>
		</li>
	</ul>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
