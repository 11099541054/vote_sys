<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>投票列表</title>
</head>
<body>

<%@ include file="header.jsp" %>
<c:if test="${voteList == null}">
	<c:redirect url="VoteServlet?option=index"></c:redirect>
</c:if>
<div id="vote" class="wrap">
	<h2>投票列表</h2>
	<ul class="list">
	<c:forEach items="${voteList}" var ="vote" varStatus="vo">
		<li class="${vo.index%2==0?'odd':''}">
			<h4>				
				<!--  <a href="VoteServlet?option=showView&vsId=${vote.vsId}">${vote.vsTitle}</a>-->
				 <a href="VoteServlet?option=showVote&vsId=${vote.vsId}&optionCount=${vote.optionCount}&userCount=${vote.userCount}">${vote.vsTitle}</a>
			</h4>
			<div class="join"><a href="VoteServlet?option=showVote&vsId=${vote.vsId}&optionCount=${vote.optionCount}&userCount=${vote.userCount}">我要参与</a></div>
			<p class="info">共有${vote.optionCount}个选项，已有${vote.userCount}个网友参与了投票。</p>
		</li>
	</c:forEach>
		
	</ul>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
