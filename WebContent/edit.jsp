<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新投票信息</title>
<script type="text/javascript" src="jq/jquery-1.11.1.js"></script>
<script type="text/javascript">
var isIE = !!document.all;
function AddOption()
{
	var voteoptions = document.getElementById("voteoptions");
	var _p = document.createElement("p");
	var _input = document.createElement("input");
	_input.type = "text";
	_input.className = "input-text";
	_input.setAttribute("name", "options");
	_p.appendChild(_input);
	var _a = document.createElement("a");//创建一个<a>标签
	_a.className = "del";//<a class="del"></a>
	_a.setAttribute("href", "javascript:;");//<a class="del" href="javascript:;"></a>
	if(isIE) {
		_a.attachEvent("onclick", DelOption);
	} else {
		_a.addEventListener("click", DelOption, false);
	}
	_a.appendChild(document.createTextNode("删除"));
	_p.appendChild(_a);
	voteoptions.appendChild(_p);
}
function DelOption(e)
{
	if(!e) e = window.event;
	var a = e.srcElement || e.target;
	var obj = a.parentNode;
	obj.parentNode.removeChild(obj);
	
}
function delOption(a)
{
	var obj = a.parentNode;
	obj.parentNode.removeChild(obj);
}
var flag1 = true;
$(function() {
	//检查输入的用户名
	$("[name=subjectTitle]").blur(function(){
		//获取用户名输入框的值
		var subjectTitle = $(this).val();
		//如果输入不为空串，则需要检查商品名称是否已经存在
		if(subjectTitle.trim() != ''){
			//发送ajax请求
			$.get('VoteServlet?option=checkTitle',{subjectTitle:subjectTitle},function(response){
				console.info(response);
				if(response == 'true'){
					flag1 = false;
					confirm("投票的内容已存在，请重新输入！")
					
				}else{
					flag1 = true;
				}
			});	
		}else{
			flag1 = false;
			confirm("投票的内容不能为空！")
		}	
	});
})
function option(){
	var options = document.getElementsByName("options");
	for(var i=0;i<options.length;i++){
		if(options[i].value==''){
			confirm('投票的选项不能为空!');
			return false;
		}
	}
}
function check(){
	var flag2=option();
	return flag1&&flag2;
}
</script>
</head>
<body>
<%@ include file="header.jsp" %>
<div id="voteManage" class="box">
	<h2>更新投票信息</h2>
	<div class="content">
		<form method="post" action="VoteServlet?option=edit" onsubmit="return check();">
	 		<input type="hidden" name="vsId" value="${voteSubject.vsId}"/>
			<dl>
				<dt>投票内容：</dt>
				<dd>
				   <input type="text" class="input-text" name="subjectTitle" value="${voteSubject.vsTitle}"/>
				</dd>
				<dt>投票类型：</dt>
				<dd>
		  		   <input type="radio" name="subjectType" ${voteSubject.vsType==1?"checked":""} value="1"/>单选
				   <input type="radio" name="subjectType" ${voteSubject.vsType==2?"checked":""} value="2"/>多选
				</dd>
				<dt>投票选项：</dt>
				<dd id="voteoptions">
				    <c:forEach items="${voteSubject.options}" var="vo">
						<p><input type="text" class="input-text" name="options" value="${vo.voOption}"/><a class="del" href="javascript:;" onclick="delOption(this)">删除</a></p>	
					</c:forEach>
				</dd>
				<dd class="button">
					<input type="image" src="images/button_submit.gif" />
					<a href="javascript:;" onclick="AddOption()">增加选项</a>
					<a href="Subject!list.action">取消操作</a>
				</dd>
			</dl>
		</form>
	</div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>