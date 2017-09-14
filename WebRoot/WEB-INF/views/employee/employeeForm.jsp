<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c-rt.tld" prefix="c" %>
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
	<form id="employeeForm">
		<table>
			<tr>
				<td><input type="hidden" class="easyui-textbox" name="id" value="${employee.id }"/></td>
			</tr>
			<tr>
				<td class="tdTitle"><label>名称：</label></td>
				<td><input class="easyui-textbox" name="name" value="${employee.name }" data-options="required:true"/></td>
			</tr>
			<tr>
				<td width="30%">性别:</td>
				<td>
					<input type="radio" name="sex" value="1"  <c:if test="${employee.sex==1 or empty employee.id }">checked</c:if>/>男
					<input type="radio" name="sex" value="0"  <c:if test="${employee.sex==0 }">checked</c:if>/>女
				</td>
			</tr>
			<tr>
				<td class="tdTitle"><label>生日：</label></td>
				<td><input class="easyui-datebox" name="birthday" value="${employee.birthday }" data-options="required:true"/></td>
			</tr>
			<tr>
				<td class="tdTitle"><label>入职时间：</label></td>
				<td><input class="easyui-datebox" name="entryday" value="${employee.entryday }" data-options="required:true"/></td>
			</tr>
			<tr>
				<td class="tdTitle"><label>联系方式：</label></td>
				<td><input class="easyui-validatebox" name="tel" value="${employee.tel }" data-options="validType:['tel','length[0,12]']"/></td>
			</tr>
			<tr>
				<td>部门:</td>
				<td><input class="easyui-combogrid" name="deptId" value="${employee.department.id }" style="width: 150px"  data-options="
					required:true,
				    panelWidth:450,
				    pagination:true,
				    idField:'id',
				    textField:'name',
				    url:'department_paginationFindAction',
				    columns:[[
				    {field:'name',title:'部门名称',width:100},
				    {field:'manager',title:'负责人',width:100},
				    {field:'tel',title:'联系方式',width:150}
				    ]]
				"
				/></td>
			</tr>
			<tr>
				<td><input type="hidden" class="easyui-textbox" name="status" value="${employee.status }"/></td>
			</tr>
		</table>
	</form>
</body>
</html>