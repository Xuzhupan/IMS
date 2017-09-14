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
	<form id="goodsForm">
		<table>
			<tr>
				<td><input type="hidden" class="easyui-textbox" name="id" value="${goods.id }"/></td>
			</tr>
			<tr>
				<td class="tdTitle"><label>名称：</label></td>
				<td><input class="easyui-textbox" name="name" value="${goods.name }" data-options="required:true"/></td>
			</tr>
			<tr>
				<td class="tdTitle"><label>规格型号：</label></td>
				<td><input class="easyui-textbox" name="type" value="${goods.type }"/></td>
			</tr>
			<tr>
				<td class="tdTitle"><label>单位：</label></td>
				<td><input class="easyui-textbox" name="unit" value="${goods.unit }" data-options="required:true"/></td>
			</tr>
			<tr>
				<td class="tdTitle"><label>备注信息：</label></td>
				<td><input class="easyui-textbox" name="information" value="${goods.information }"/></td>
			</tr>
			<tr>
				<td><input type="hidden" class="easyui-textbox" name="status" value="${goods.status }"/></td>
			</tr>
		</table>
	</form>
</body>
</html>