<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>员工变更记录</title>
<script type="text/javascript">
 function deleteE(){
	 var result=window.confirm("你确定要删除吗？");
	 if(result){
		 return true;
	 }else{
		 return false;
	 }
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
	 }else if('pageGo' == flag){
         var page = document.getElementById('pageGo').value;
         var last = Number(document.getElementById('last').value);
         if (page > last) {
             page = last;
         }else if (page < 1) {
             page = 1;
         }
         document.getElementById('page').value = page;
     }
	 document.getElementById('eForm').submit();
 }
</script>
</head>
<body>
	<h1 align=center>员工变更记录</h1>
	<div align="center">
		<form action="historySearch.do"  method="post" >
			<input type="text" class="search" name="search">
			<input type="submit" class="subSearch" name="subSearch" value="查询">
		</form>
	</div>
	<table border="1"  rules="all" align="center">
		<tr align = "center">
			<th>员工姓名</th>
			<th>部门名称</th>
			<th>薪资</th>
			<th>变更日期</th>
			<th>变更原因</th>
			<th>离职日期</th>
			<th>离职原因</th>
		</tr>
		<c:forEach items="${HLIST}" var="h">
		<tr align = "center">
			<td>${h.empName}</td>
			<td>${h.deptName}</td>
			<td>${h.salary}</td>
			<td>${h.changeDate}</td>
			<td>${h.changeReason}</td>
			<td>${h.dimissionDate}</td>
			<td>${h.dimissionReason}</td>
		</tr>
		</c:forEach>
	</table>

	<span>第${page}页&nbsp;&nbsp;共${last}页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>

	<form id = "eForm" action="historyList.do" method="post" style="float:right;">
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
        <input type="text" id="pageGo" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
        <input type="button" onclick="submitPage('pageGo')" value="Go" >
	</form>
</body>
</html>