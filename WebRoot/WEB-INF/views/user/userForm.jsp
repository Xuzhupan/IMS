<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="userForm">
		<table>
			<tr>
				<td><label>用户名：</label></td>
				<td><input class="easyui-textbox" id="username" name="username" data-options="required:true"></td>
			</tr>
			<tr>
				<td><label>密码：</label></td>
				<td><input class="easyui-passwordbox" id="username" name="password" data-options="required:true"></td>
			</tr>
			<tr>
				<td><label>员工：</label></td>
				<td><input class="easyui-combogrid" id="employeeId" name="employeeId" data-options="
				    required:true,
				    panelWidth:450,
				     pagination:true,
				    idField:'employeeId',
				    textField:'employeeName',
				    url:'employee_paginationFindAll',
				    columns:[[
				    {field:'employeeName',title:'Name',width:100},
				    ]]
				"></td>
			</tr>
			<tr>
				<td>角色:</td>
				<td><input class="easyui-combogrid" name="roleId"  data-options="
					required:true,
				    panelWidth:300,
				    pagination:true,
				    idField:'id',
				    textField:'name',
				    url:'role_paginationFindAction?actionRoleId=${user.role.id }',
				    columns:[[
				    {field:'name',title:'角色名称',width:100},
				    ]]
				"
				/></td>
			</tr>
			
		</table>
	</form>
</body>
</html>