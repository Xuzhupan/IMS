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
	<form id="departmentForm">
		<table>
			<tr>
				<td><input type="hidden" class="easyui-textbox" name="id" value="${department.id }"/></td>
			</tr>
			<tr>
				<td class="tdTitle"><label>名称：</label></td>
				<td><input class="easyui-textbox" name="name" value="${department.name }" data-options="required:true"/></td>
			</tr>
			<tr>
				<td class="tdTitle"><label>部门负责人：</label></td>
				<td><input class="easyui-validatebox" name="manager" value="${department.manager }" data-options="validType:'name'"/></td>
			</tr>
			<tr>
				<td class="tdTitle"><label>联系方式：</label></td>
				<td><input class="easyui-validatebox" name="tel" value="${department.tel }" data-options="validType:'tel'"/></td>
			</tr>
			<tr>
				<td><input type="hidden" class="easyui-textbox" name="status" value="${department.status }"/></td>
			</tr>
		</table>
	</form>
</body>
</html>