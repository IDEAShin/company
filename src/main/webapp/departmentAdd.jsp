<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增部门</title>
	<script type="text/javascript">
		function Check()
		{
			//1.得到文本框的值
			if ( document.getElementById("deptName").value=="")
			{
				alert('请输入部门名称!');
				return false;
			}
			return true;
		}
	</script>
</head>
<body>
	<h1 align=center>新增部门</h1>
	<form action="departmentAdd.do" method="post" onsubmit="return Check()">
		<table border="1" rules="groups" align="center">
			<tr>
				<td>部门名称：</td>
				<td>
					<input type="text" class="deptName" name="deptName" id="deptName" value="${param.deptName}">
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<input type="submit" name="submit" value="增加">&nbsp;
					<input type="reset" name="reset" value="取消" >&nbsp;
					<a href="javascript:history.go(-1);">返回</a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>