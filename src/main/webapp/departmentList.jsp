<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<c:if test="${msg != null}">
	<script type="text/javascript">
		alert('${msg}');
	</script>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>部门管理</title>
<script type="text/javascript">
		 function deleteE(){
			 return window.confirm("你确定要删除吗？");
		 }
		 function submitPage(flag){

			 if('first' == flag){
				 document.getElementById('page').value = 1;
			 }else if('pre' == flag){
				 var current = Number(document.getElementById('page').value);
				 if(current > 1){
					 document.getElementById('page').value = current - 1;
				 }
			 }else if('next' == flag){
				 var current = Number(document.getElementById('page').value);
				 var last = Number(document.getElementById('last').value);
				 if(current < last){
					 document.getElementById('page').value = current + 1;
				 }
			 }else if('last' == flag){
				 var last = Number(document.getElementById('last').value);
				 document.getElementById('page').value = last < 1 ? 1 : last;
			 }
			 document.getElementById('eForm').submit();
		 }
</script>
	<style>
		body{
			margin: 0 auto;
		}
		.container{
			margin:0 auto;
			width: 350px;
			height: 280px;
		}
	</style>
</head>
<body>
<div class="container">
	<h1 align=center>部门列表</h1>
	<p align=right><a href="departmentAdd.jsp">新增部门</a></p>
	<table border="1"  rules="all" align="center">
		<tr align = "center">
			<th>部门编号</th><th>部门名称</th><th>操作</th>
		</tr>
		<c:forEach items="${DLIST}" var="d">
		<tr align = "center">
			<td>${d.deptNo}</td>
			<td>${d.deptName}</td>
			<td>
				<a href="departmentUpdate.jsp?deptNo=${d.deptNo}&deptName=${d.deptName}">修改</a>
				<a href="departmentDelete.do?deptNo=${d.deptNo}" onclick="return deleteE()">删除</a>
			</td>
		</tr>
		</c:forEach>
	</table>

	<span>第${page}页&nbsp;&nbsp;共${last}页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>

	<form id = "eForm" action="departmentList.do" method="post" style="float:right;">
		<input type="hidden" id="page" name="page" value="${page}">
		<input type="hidden" id="last" name="last" value="${last}">
		<c:if test="${page == 1}">
			首页&nbsp;上一页&nbsp;
		</c:if>
		<c:if test="${page != 1}">
			<a href="javascript:void(0)" onclick="submitPage('first')">首页</a>&nbsp;
			<a href="javascript:void(0)" onclick="submitPage('pre')">上一页</a>&nbsp;
		</c:if>
		<c:if test="${page == last}">
			下一页&nbsp;尾页&nbsp;
		</c:if>
		<c:if test="${page != last}">
			<a href="javascript:void(0)" onclick="submitPage('next')">下一页</a>&nbsp;
			<a href="javascript:void(0)" onclick="submitPage('last')">尾页</a>
		</c:if>
	</form>
</div>
</body>
</html>