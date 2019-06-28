<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>员工离职</title>
<script type="text/javascript">
 function deleteE(){
	 var result=window.confirm("你确定要删除吗？");
	 if(result){
		 return true;
	 }else{
		 return false;
	 }

	 function Check()
	 {
		 if ( document.getElementById("dimissionReason").value=="") {
			 alert('请输入原因!');
			 return false;
		 }
		 return true;
	 }
 }
</script>
</head>
<body>
	<h1 align=center>员工离职</h1>
	<form action="employeeLeave.do" method="post" onsubmit="return Check()">
		<table border="1"  rules="all" align="center">
			<tr align = "center">
				<th>部门名称</th>
				<th>员工姓名</th>
				<th>员工性别</th>
				<th>入职时间</th>
				<th>联系电话</th>
				<th>现住址</th>
				<th>薪资</th>
			</tr>
			<tr align = "center">
				<td>${EMP.deptName}</td>
				<td>${EMP.empName}</td>
				<td>
					<c:if test="${EMP.empSex == '0'}">
						女
					</c:if>
					<c:if test="${EMP.empSex == '1'}">
						男
					</c:if>
				</td>
				<td>${EMP.entryDate}</td>
				<td>${EMP.empPhone}</td>
				<td>${EMP.empAddr}</td>
				<td>${EMP.salary}</td>
			</tr>
		</table>
		<input type="hidden" value="${EMP.empNo}" name="empNo">
		<input type="hidden" value="${EMP.deptNo}" name="deptNo">
		<input type="hidden" value="${EMP.salary}" name="salary">
		<p align="center">
			离职原因：
			<textarea   cols="30"   rows="3"  class="dimissionReason" name="dimissionReason"></textarea>
			<input type="submit" name="submit" value="确定">&nbsp;
			<input type="reset" name="reset" value="取消" >&nbsp;
			<a href="javascript:history.go(-1);">返回</a>
		</p>
	</form>
</body>
</html>