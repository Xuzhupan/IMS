<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/include.jsp"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册页面</title>
</head>
<body>
	<form id="regForm">
		<table>
			<tr>
				<td width="25%" align="right">用户名：</td>
				<td width="30%">
					<input class="easyui-textbox" name="name" 
					data-options="prompt:'用户名',required:true,validType:'chm'" 
					style="width: 150px">
				</td>
				<td width="15%" align="right">登录帐号:</td>
				<td width="30%">
					<input class="easyui-textbox" name="username" 
					data-options="prompt:'请输入有效的邮箱地址',required:true,validType:'email'"  
					style="width: 150px">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">密码：</td>
				<td width="30%">
					<input class="easyui-textbox" type="passwrod" name="password" id="pwd"
					data-options="prompt:'密码',required:true" 
					style="width: 150px">
				</td>
				<td width="15%" align="right">确认密码:</td>
				<td width="30%">
					<input class="easyui-textbox" type="passwrod" name="password_" 
					data-options="prompt:'确认密码',required:true" validType="equals['#pwd']"
					style="width: 150px">
				</td>
			</tr>	
			<tr>
				<td><input></td>
			</tr>
			<tr>
				<td width="15%" align="right">性别：</td>
				<td width="30%">
					<input type="radio" name="sex" value="1" checked>男
					<input type="radio" name="sex" value="2">女
				</td>
				<td width="15%" align="right">出生日期:</td>
				<td width="30%">
					<input class="easyui-datebox" name="birthday" 
					 data-options="prompt:'出生日期',required:true" 	
					 style="width: 150px">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">入职时间：</td>
				<td width="30%" colspan="3">
					<input class="easyui-datebox" name="entryDate" 
					data-options="prompt:'入职时间',required:true" 
					style="width: 150px">
				</td>
				
			</tr>	
		</table>
	</form>
</body>
</html>