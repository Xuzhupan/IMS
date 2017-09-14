<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.teTitle{
		width:30%;
	}
</style>
</head>
<body>
	<form id="alterPassword" class="easyui-form" >
		<%-- <input type="hidden" id="username" value="${user.username }"> --%>
		<table>
			<tr>
				<td class="tdTitle"><label>新密码:</label></td>
				<td><input class="easyui-validatebox" id="password" name="password" required="true" type="password"></td>
			</tr>
			<tr>
				<td class="tdTitle"><label>确认新密码:</label></td>
				<td><input type="password" name="repassword" id="repassword" required="true" class="easyui-validatebox"  validType="equalTo['#password']" invalidMessage="密码不一致"/></td>
			</tr>
		</table>
	</form>
</body>
</html>