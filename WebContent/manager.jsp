<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理投票</title>
</head>
<body>
<%@ include file="header.jsp" %>
<div id="vote" class="wrap">
	<h2>投票列表</h2>
	<ul class="list">
		<c:forEach items="${voteList}" var ="vote" varStatus="vo">
		<li class="${vo.index%2==0?'odd':''}">
			<h4>
				<em><a href="VoteServlet?option=preEdit&vsId=${vote.vsId}">维护</a></em>
				<a href="VoteServlet?option=showVote&vsId=${vote.vsId}&optionCount=${vote.optionCount}&userCount=${vote.userCount}">${vote.vsTitle}</a>
			</h4>
			<p class="info">共有${vote.optionCount}个选项，已有${vote.userCount} 个网友参与了投票。</p>
		</li>
	</c:forEach>
	</ul>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>