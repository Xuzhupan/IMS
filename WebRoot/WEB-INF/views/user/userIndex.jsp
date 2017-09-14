<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/WEB-INF/c-rt.tld" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<jsp:include page="/include.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function(){
		$("#table").datagrid({
			fit:true,//自适应窗口
			url:'user_paginationFindAll.action?actionUsername=${user.username}',
			nowrap:true,//提高加载性能
			striped:true,//斑马线效果（隔行显示不同颜色）
			pagination:true,//翻页
			rownumbers:true,//显示行号
			singleSelect:true,
			toolbar:'#tab-tools',
			columns:[[
				{field:'username',title:'用户名',width:'20%'},
				{field:'employeeName',title:'员工姓名',width:'20%'},
				{field:'roleName',title:'角色',width:'20%'},
				{field:'status',title:'状态',width:'20%',formatter:function(value){if(value==1){return '<img src="css/themes/icons/ok.png">'}else{return '<img src="css/themes/icons/no.png">'}}}
			]]
		});
		
		$('#search').click(function(){
			search();
		});
	});
	
	function add(){
		$('body').append('<div id="da"></div>');
		$('#da').dialog({
			iconCls:'icon-add',
			title:"新增用户",
			width:250,
			height:300,
			cache:false,
			modal:true,
			href:'userForm',
			buttons:[{
				iconCls:'icon-save',
				text:'保存',
				handler:function(){
					$.ajax({
						type:'post',
						url:'user_save',
						data:$('#userForm').serialize(),
						dataType:'json',
						success:function(data){
							if(data.success){
								$('#da').dialog('close');
								$("#table").datagrid('reload');
							}
							$.messager.show({title:"提示",msg:"添加成功"})
						}
					});
				}
			},{
				iconCls:'icon-cancel',
				text:'关闭',
				handler:function(){
					$('#da').dialog('close');
				}
			}]
		});
	}
	function alterPassword(){
		var username= "";
		var rows = $('#table').datagrid("getSelected");
		if(rows!=null){
			username = rows.username;
		}else{
			$.messager.alert("警告","请选择行！","warning");
			return false;
		}
		$('body').append('<div id="da"></div>');
		$('#da').dialog({
			iconCls:'icon-add',
			title:"修改密码",
			width:300,
			height:150,
			cache:false,
			modal:true,
			href:'alterPassword',
			buttons:[{
				iconCls:'icon-save',
				text:'确定',
				handler:function(){
					$('#alterPassword').form('submit',{
						onSubmit:function(){
							return $(this).form('enableValidation').form('validate');
						},
						success:function(data){
							$.ajax({
								type:'post',
								url:'user_alterPassword?username='+username,
								data:$('#alterPassword').serialize(),
								dataType:'json',
								success:function(data){
									if(data.success){
										$('#da').dialog('close');
										$("#table").datagrid('reload');
										$.messager.show({title:"提示",msg:"修改成功"});
									}else{
										$.messager.show({title:"提示",msg:"修改失败"});
									}
								}
							});
						}
					});
					
				}
			},{
				iconCls:'icon-cancel',
				text:'关闭',
				handler:function(){
					$('#da').dialog('close');
				}
			}]
		});
	}
	function linkEmployee(){
		var username= "";
		var rows = $('#table').datagrid("getSelected");
		if(rows!=null){
			username = rows.username;
		}else{
			$.messager.alert("警告","请选择行！","warning");
			return false;
		}
		
		$('body').append('<div id="da"></div>');
		$('#da').dialog({
			iconCls:'icon-add',
			title:"关联新员工",
			width:300,
			height:150,
			cache:false,
			modal:true,
			href:'linkEmployee?username='+rows.username,
			buttons:[{
				iconCls:'icon-save',
				text:'确定',
				handler:function(){
					$('#linkEmployeeForm').form('submit',{
						onSubmit:function(){
							return $(this).form('enableValidation').form('validate');
						},
						success:function(data){
							$.ajax({
								type:'post',
								url:'user_linkEmployee?username='+username,
								data:$('#linkEmployeeForm').serialize(),
								dataType:'json',
								success:function(data){
									if(data.success){
										$('#da').dialog('close');
										$("#table").datagrid('reload');
										$.messager.show({title:"提示",msg:"修改成功"});
									}else{
										$.messager.show({title:"提示",msg:"修改失败"});
									}
								}
							});
						}
					});
					
				}
			},{
				iconCls:'icon-cancel',
				text:'关闭',
				handler:function(){
					$('#da').dialog('close');
				}
			}]
		});
	}
	function authorize(){
		var username= "";
		var rows = $('#table').datagrid("getSelected");
		if(rows!=null){
			username = rows.username;
		}else{
			$.messager.alert("警告","请选择行！","warning");
			return false;
		}
		
		$('body').append('<div id="dd"></div>');
		$('#dd').dialog({
			iconCls:'icon-add',
			title:"角色授权",
			width:300,
			height:150,
			cache:false,
			modal:true,
			href:'authorize?username='+username,
			buttons:[{
				iconCls:'icon-save',
				text:'确定',
				handler:function(){
					$('#authorizeForm').form('submit',{
						onSubmit:function(){
							return $(this).form('enableValidation').form('validate');
						},
						success:function(data){
							$.ajax({
								type:'post',
								url:'user_authorize?username='+username,
								data:$('#authorizeForm').serialize(),
								dataType:'json',
								success:function(data){
									if(data.success){
										$('#dd').dialog('close');
										$("#table").datagrid('reload');
										$.messager.show({title:"提示",msg:"授权成功"});
									}else{
										$.messager.show({title:"提示",msg:"授权失败"});
									}
								}
							});
						}
					});
					
				}
			},{
				iconCls:'icon-cancel',
				text:'关闭',
				handler:function(){
					$('#dd').dialog('close');
				}
			}]
		});
	}
	function alterStatus(){
		$.ajax({
			url:'user_alterStatus',
			type:'post',
			dataType:'json',
			data:{
				username:$("#table").datagrid('getSelected').username
			},
			success:function(data){
				$("#table").datagrid('reload');
			}
		});
	}
	function deleteUser(){
		var rows = $("#table").treegrid('getSelected');
		if(rows!=null){
			$.messager.confirm("系统提示","是否确定要删除该记录!",function(data){
				if(data){
					$.ajax({
						type:'post',
						url:'user_deleteUser.action',
						data:{
							username:rows.username
						},
						dataType:'json',
						success:function(data){
							if(data.success){
								$("#table").datagrid('reload');
								$.messager.show({title:"系统提示",msg:"删除成功"});
							}else{
								$.messager.show({title:"系统提示",msg:"删除失败"});
							}
						}
					});
				}			
			});
		}else{
			$.messager.alert("温馨提示","请选择删除行");
		}
	}
	
	function send(){
		$.getJSON(
			'user_search',
			$('#searchForm').serialize(),
			function(data){
				if(data){
					$('#table').datagrid('loadData',data);
				}else{
					$('#table').datagrid('loadData',{'total':0,'row':[]});
				}
			}
		);
	}
	function search(){
			send();
	}
</script>
<body>
<div id="tab-tools">
	<form id="searchForm">
		<table>
			<tr>
				<td><label>用户名：</label></td>
				<td><input id="s1" class="easyui-textbox" name="searchUsername" data-options="onChange:function(){send();}"/></td>
				<td><label>角色：</label></td>
				<td><input id="s2" class="easyui-combobox" name="searchRole"  data-options="
					width:'80px',
					url:'role_combobox',
					valueField: 'id',
					textField: 'text',
					onChange:function(){
						send();
					},
					loadFilter:function(data){
						var j = {'id':'','text':'全部'};
						data[0]=j;
						return data;
					},
					queryParams: {
					 	'actionRoleId':${user.role.id }
					}
					
					"/></td>
				<td><label>状态：</label></td>
				<td><input id="s3" class="easyui-combobox" name="searchStatus"  data-options="
					width:'80px',
					valueField: 'id',
					textField: 'text',
					onChange:function(){
						send();
					},
					data: [{
						id: '',
						text: '全部'
					},{
						id: '1',
						text: '可用'
					},{
						id: '0',
						text: '不可用'
					}]" /></td>
				<td><a class="easyui-linkbutton" id="search">搜索</a></td>
				<td><input type="hidden" name="actionUsername" value="${user.username }"></td>
			</tr>
		</table>
	</form>
	<c:if test="${permission.addUser }">
	<a class="easyui-linkbutton" plain="true" iconCls="icon-add" onclick="add()">添加用户</a>
	</c:if>
	<c:if test="${permission.alterPassword }">
	<a class="easyui-linkbutton" plain="true" iconCls="icon-edit" onclick="alterPassword()">修改密码</a>
	</c:if>
	<c:if test="${permission.linkEmployee }">
		<a class="easyui-linkbutton" plain="true" iconCls="icon-edit" onclick="linkEmployee()">关联新员工</a>
	</c:if>
	<c:if test="${permission.authorize }">
		<a class="easyui-linkbutton" plain="true" iconCls="icon-man" onclick="authorize()">用户授权</a>
	</c:if>
	<c:if test="${permission.setStatus4User }">
		<a class="easyui-linkbutton" plain="true" iconCls="icon-lock" onclick="alterStatus()">修改状态</a>
	</c:if>
	<c:if test="${permission.deleteUser }">
		<a class="easyui-linkbutton" plain="true" iconCls="icon-remove" onclick="deleteUser()">删除用户</a>
	</c:if>
</div>
<table id="table"></table>
</body>
</html>
