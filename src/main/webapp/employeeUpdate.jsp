<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>员工变更</title>
	<script type="text/javascript">
		function Check()
		{
			//1.得到文本框的值
			if ( document.getElementById("empName").value=="") {
				alert('请输入员工姓名!');
				return false;
			}else if ( document.getElementById("entryDate").value=="") {
				alert('请输入入职日期!');
				return false;
			}else if ( document.getElementById("empPhone").value=="") {
				alert('请输入联系电话!');
				return false;
			}else if ( document.getElementById("salary").value=="") {
				alert('请输入薪资!');
				return false;
			}else if ( document.getElementById("changeReason").value=="") {
				alert('请输入原因!');
				return false;
			}
			return true;
		}
	</script>
</head>
<body>
	<h1 align=center>员工变更</h1>
	<form action="employeeUpdate.do" method="post" onsubmit="return Check()">
		<input type="hidden" value="${EMP.empNo}" name="empNo">
		<table border="1" rules="groups" align="center">
			<tr>
				<td>部门名称：</td>
				<td>
					<select name="deptNo">
						<c:forEach items="${DLIST}" var="d">
							<c:if test="${EMP.deptNo == d.deptNo}">
								<option value="${d.deptNo}" selected="selected">${d.deptName}</option>
							</c:if>
							<c:if test="${EMP.deptNo != d.deptNo}">
								<option value="${d.deptNo}">${d.deptName}</option>
							</c:if>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>员工名称：</td>
				<td>
					<input type="text" class="empName" name="empName" id="empName" value="${EMP.empName}">
				</td>
			</tr>
			<tr>
				<td>员工性别：</td>
				<td>
					<input type="radio" class="empSex" name="empSex" value="1" checked>男
					<c:if test="${EMP.empSex == '0'}">
						<input type="radio" class="empSex" name="empSex" value="0"  checked>女
					</c:if>
					<c:if test="${EMP.empSex != '0'}">
						<input type="radio" class="empSex" name="empSex" value="0"  >女
					</c:if>
				</td>
			</tr>
			<tr>
				<td>入职日期：</td>
				<td>
					<input type="date" class="entryDate" name="entryDate" id="entryDate" value="${EMP.entryDate}">
				</td>
			</tr>
			<tr>
				<td>联系电话：</td>
				<td>
					<input type="text" class="empPhone" name="empPhone" id="empPhone" value="${EMP.empPhone}">
				</td>
			</tr>
			<tr>
				<td>现住址：</td>
				<td>
					<input type="text" class="empAddr" name="empAddr" id="empAddr" value="${EMP.empAddr}">
				</td>
			</tr>
			<tr>
				<td>薪资：</td>
				<td>
					<input type="text" class="salary" name="salary" id="salary" value="${EMP.salary}">￥
				</td>
			</tr>
			<tr>
				<td>变更原因：</td>
				<td>
					<textarea   cols="30"   rows="3"  class="changeReason" id="changeReason" name="changeReason"></textarea>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<input type="submit" name="submit" value="确定">&nbsp;
					<input type="reset" name="reset" value="取消" >&nbsp;
					<a href="javascript:history.go(-1);">返回</a>
					<c:if test="${mes == 3}">
						<span style="color: red;">请输入正确数字格式</span>
					</c:if>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>