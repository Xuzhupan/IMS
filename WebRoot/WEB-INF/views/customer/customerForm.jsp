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
	<form id="customerForm">
		<table>
			<tr>
				<td><input type="hidden" class="easyui-textbox" name="id" value="${customer.id }"/></td>
			</tr>
			<tr>
				<td class="tdTitle"><label>客户名称：</label></td>
				<td><input class="easyui-textbox" name="name" value="${customer.name }" data-options="required:true"/></td>
			</tr>
			<tr>
				<td class="tdTitle"><label>联系人：</label></td>
				<td><input class="easyui-validatebox" name="messenger" value="${customer.messenger }" data-options="validType:'name'"/></td>
			</tr>
			<tr>
				<td class="tdTitle"><label>联系方式：</label></td>
				<td><input class="easyui-validatebox" name="tel" value="${customer.tel }" data-options="required:true,validType:'tel'"/></td>
			</tr>
			<tr>
				<td class="tdTitle"><label>备注信息：</label></td>
				<td><input class="easyui-textbox" name="information" value="${customer.information }"/></td>
			</tr>
			<tr>
				<td><input type="hidden" class="easyui-textbox" name="createDate" value="${customer.createDate }"/></td>
			</tr>
			<tr>
				<td><input type="hidden" class="easyui-textbox" name="status" value="${customer.status }"/></td>
			</tr>
		</table>
	</form>
</body>
</html>