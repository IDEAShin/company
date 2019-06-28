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
<title>员工管理</title>
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
	<h1 align=center>员工管理</h1>
	<p align=right><a href="employeeToAdd.do">新增员工</a></p>
	<table border="1"  rules="all" align="center">
		<tr align = "center">
			<th>员工编号</th>
			<th>部门名称</th>
			<th>员工姓名</th>
			<th>员工性别</th>
			<th>入职时间</th>
			<th>联系电话</th>
			<th>员工住址</th>
			<th>薪资</th>
			<th>离职时间</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${ELIST}" var="e">
			<tr align = "center">
				<td>${e.empNo}</td>
				<td>${e.deptName}</td>
				<td>${e.empName}</td>
				<td>
                    <c:if test="${e.empSex == '0'}">
                        女
                    </c:if>
                    <c:if test="${e.empSex == '1'}">
                        男
                    </c:if>
                </td>
				<td>${e.entryDate}</td>
				<td>${e.empPhone}</td>
				<td>${e.empAddr}</td>
				<td>${e.salary}</td>
				<td>${e.leaveDate}</td>

				<td>
					<c:if test="${e.state == 1}">
						<a href="employeeToUpdate.do?empNo=${e.empNo}">变更</a>
						<a href="employeeToLeave.do?empNo=${e.empNo}">离职</a>
					</c:if>
					<a href="employeeDelete.do?empNo=${e.empNo}&deptNo=${e.deptNo}&salary=${e.salary}" onclick="return deleteE()">减员</a>

				</td>
			</tr>
		</c:forEach>
	</table>
	<span>第${page}页&nbsp;&nbsp;共${last}页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>

	<form id = "eForm" action="employeeList.do" method="post" style="float:right;">
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
        <input type="button" onclick="submitPage('pageGo')" value="Go">
	</form>
</body>
</html>