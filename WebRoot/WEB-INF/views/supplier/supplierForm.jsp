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
	<form id="supplierForm">
		<table>
			<tr>
				<td><input type="hidden" class="easyui-textbox" name="id" value="${supplier.id }"/></td>
			</tr>
			<tr>
				<td class="tdTitle"><label>名称：</label></td>
				<td><input class="easyui-textbox" name="name" value="${supplier.name }" data-options="required:true"/></td>
			</tr>
			<tr>
				<td class="tdTitle"><label>联系人：</label></td>
				<td><input class="easyui-textbox" name="messenger" value="${supplier.messenger }"/></td>
			</tr>
			<tr>
				<td class="tdTitle"><label>联系方式：</label></td>
				<td><input class="easyui-validatebox" name="tel" value="${supplier.tel }" data-options="required:true,validType:['tel','length[0,12]']"/></td>
			</tr>
			<tr>
				<td class="tdTitle"><label>纳税识别号：</label></td>
				<td><input class="easyui-textbox" name="taxpayerIdentity" value="${supplier.taxpayerIdentity }"/></td>
			</tr>
			<tr>
				<td class="tdTitle"><label>开户银行：</label></td>
				<td><input class="easyui-textbox" name="bankName" value="${supplier.bankName }"/></td>
			</tr>
			<tr>
				<td class="tdTitle"><label>银行帐号：</label></td>
				<td><input class="easyui-textbox" name="bankAccount" value="${supplier.bankAccount }"/></td>
			</tr>
			<tr>
				<td class="tdTitle"><label>注册电话：</label></td>
				<td><input class="easyui-validatebox" name="registerTel" value="${supplier.registerTel }" data-options="validType:['phone','length[0,12]']"/></td>
			</tr>
			<tr>
				<td class="tdTitle"><label>注册地址：</label></td>
				<td><input class="easyui-textbox" name="registerAddress" value="${supplier.registerAddress }"/></td>
			</tr>
			<tr>
				<td class="tdTitle"><label>备注信息：</label></td>
				<td><input class="easyui-textbox" name="information" value="${supplier.information }"/></td>
			</tr>
			<tr>
				<td><input type="hidden" class="easyui-textbox" name="createDate" value="${supplier.createDate }"/></td>
			</tr>
			<tr>
				<td><input type="hidden" class="easyui-textbox" name="status" value="${supplier.status }"/></td>
			</tr>
		</table>
	</form>
</body>
</html>