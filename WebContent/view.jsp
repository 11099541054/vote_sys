<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<%@ include file="header.jsp" %>
<div id="vote" class="wrap">
	<h2>查看投票</h2>
	<ul class="list">
		<li>
			<h4>${voteBean.vsTitle}</h4>
			<p class="info">共有 ${voteBean.optionCount} 个选项，已有${voteBean.userCount}个网友参与了投票。</p>
				<ol>
					<c:forEach items="${optionBeans}" var="ob">
						<li>${ob.voOption }
							<div class="rate">
								<div class="ratebg"><div class="percent" style="width:${ob.percent}%"></div></div>
								<p>${ob.optionAmount}票<span>(<f:formatNumber value="${ob.percent}"  pattern="#,###,###,###"></f:formatNumber>%)</span></p>
								
							</div>
						</li>
					</c:forEach>
				</ol>
				<div class="goback"><a href="javascript:history.back()">返回投票列表</a></div>
		</li>
	</ul>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
