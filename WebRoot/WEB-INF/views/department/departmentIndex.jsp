<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/include.jsp"></jsp:include>
<%@ taglib uri="/WEB-INF/c-rt.tld" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function(){
	$('#department').datagrid({
		fit:true,//自适应窗口
		url:'department_paginationFindAll.action',
		nowrap:true,//提高加载性能
		striped:true,//斑马线效果（隔行显示不同颜色）
		pagination:true,//翻页
		rownumbers:true,//显示行号
		singleSelect:true,//单行选中
		toolbar:'#tool',
		columns:[[
			{field:'name',title:'部门名称',width:'10%'},
			{field:'manager',title:'部门负责人',width:'9%'},
			{field:'tel',title:'联系方式',width:'9%'},
			{field:'status',title:'状态',width:'9%',formatter:function(value){if(value==1){return '<img src="css/themes/icons/ok.png">'}else{return '<img src="css/themes/icons/no.png">'}}},
		]]
		
	});
	$('#search').click(function(){
		search();
	});
});
function addOrUpdateDepartment(type,title){
	var rows = $("#department").treegrid('getSelected');
  	var href = "departmentForm.action";
	if('edit'==type){
		if(rows!=null){
			href = "departmentForm.action?id="+rows.id;
		}else{
			$.messager.alert("温馨提示","请选择编辑行");
			return false;
		}
	}
	$('body').append('<div id="da"></div>');
	$('#da').dialog({
		iconCls:'icon-add',
		title:title,
		width:320,
		height:300,
		cache:false,
		modal:true,
		href:href,
		buttons:[{
			iconCls:'icon-save',
			text:'保存',
			handler:function(){
				$('#departmentForm').form('submit',{
					onSubmit:function(){
						return $(this).form('enableValidation').form('validate');
					},
					success:function(data){	
						$.ajax({
							type:'post',
							url:'department_saveOrUpdate.action',
							data:$('#departmentForm').serialize(),
							dataType:'json',
							success:function(data){
								if(data.success){
									$('#da').dialog('close');
									$("#department").datagrid('reload');
								}
								$.messager.alert("系统提示",data.message);
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

function alterStatus(){
	$.ajax({
		url:'department_alterStatus',
		type:'post',
		dataType:'json',
		data:{
			id:$("#department").treegrid('getSelected').id,
			name:$("#department").treegrid('getSelected').name,
			manager:$("#department").treegrid('getSelected').manager,
			tel:$("#department").treegrid('getSelected').tel,
			status:($("#department").treegrid('getSelected').status==1)?0:1
			},
		success:function(data){
			$("#department").datagrid('reload');
		}
	});
}

function deleteRecord(){
	var rows = $("#department").datagrid('getSelected');
	if(rows!=null){
		$.messager.confirm("系统提示","你的操作将会从数据库里删除数据，有可能带来无法挽回的后果，如果不确认该数据是否还被需要，可将其状态设置为不可用!",function(data){
			if(data){
				$.ajax({
					type:'post',
					url:'department_delete.action',
					data:{
						id:rows.id
					},
					dataType:'json',
					success:function(data){
						if(data.success){
							$("#department").datagrid('reload');
						}
						$.messager.alert("系统提示",data.message);
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
		'department_search',
		$('#searchForm2').serialize(),
		function(data){
			if(data){
				$('#department').datagrid('loadData',data);
			}else{
				$('#department').datagrid('loadData',{'total':0,'row':[]});
			}
		}
	);
}
function search(){
		send();
}
</script>
</head>
<body>
	<div id="department">
	<div id="tool">
		<form id="searchForm2">
		<table>
			<tr>
				<td><label>部门名称：</label></td>
				<td><input  class="easyui-textbox" name="searchDeptName" data-options="onChange:function(){
						send();
					},"/></td>
				<td><label>状态：</label></td>
				<td><input  class="easyui-combobox" name="searchStatus"  data-options="
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
		<c:if test="${permission.addDepartment }">
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="addOrUpdateDepartment('add','添加部门信息')">添加部门信息</a>
		</c:if>
		<c:if test="${permission.alterDepartment }">
			<a class="easyui-linkbutton" iconCls="icon-edit" onclick="addOrUpdateDepartment('edit','修改部门信息')" >修改部门信息</a>
		</c:if>
		<c:if test="${permission.setStatus2Delete4Department }">
			<a class="easyui-linkbutton" iconCls="icon-edit" onclick="alterStatus()">改变状态</a>
		</c:if>
		<c:if test="${permission.deleteDepartment }">
			<a class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteRecord()">删除部门信息</a>
		</c:if>
	</div>
</body>
</html>