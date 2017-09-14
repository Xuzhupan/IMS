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
	$('#employee').datagrid({
		fit:true,//自适应窗口
		url:'employee_paginationFindAll?actionEmployeeId=${user.employee.id}',
		nowrap:true,//提高加载性能
		striped:true,//斑马线效果（隔行显示不同颜色）
		pagination:true,//翻页
		rownumbers:true,//显示行号
		singleSelect:true,//单行选中
		toolbar:'#tool',
		columns:[[
			{field:'employeeId',title:'id',width:'10%',hidden:true},
			{field:'employeeName',title:'姓名',width:'9%'},
			{field:'sex',title:'性别',width:'9%',formatter:function(value){if(value==1){return '男'};if(value==2){return '女'}}},
			{field:'birthday',title:'生日',width:'9%',formatter:formatterLocalTime},
			{field:'entryday',title:'入职时间',width:'9%',formatter:formatterLocalTime},
			{field:'tel',title:'联系方式',width:'9%'},
			{field:'departmentName',title:'部门',width:'9%'},
			{field:'status',title:'状态',width:'9%',formatter:function(value){if(value==1){return '<img src="css/themes/icons/ok.png">'}else{return '<img src="css/themes/icons/no.png">'}}},
		]]
		
	});
	
	$('#search').click(function(){search();});
});
function addOrUpdateEmployee(type,title){
	var rows = $("#employee").datagrid('getSelected');
  	var href = "employeeForm.action";
	if('edit'==type){
		if(rows!=null){
			href = "employeeForm.action?id="+rows.employeeId;
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
		height:320,
		cache:false,
		modal:true,
		href:href,
		buttons:[{
			iconCls:'icon-save',
			text:'保存',
			handler:function(){
				$('#employeeForm').form('submit',{
					onSubmit:function(){
						return $(this).form('enableValidation').form('validate');
					},
					success:function(data){
						$.ajax({
							type:'post',
							url:'employee_saveOrUpdate.action',
							data:$('#employeeForm').serialize(),
							dataType:'json',
							success:function(data){
								if(data.success){
									$('#da').dialog('close');
									$("#employee").datagrid('reload');
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
		url:'employee_alterStatus',
		type:'post',
		dataType:'json',
		data:{
			id:$("#employee").datagrid('getSelected').employeeId
		},
		success:function(data){
			$("#employee").datagrid('reload');
		}
	});
}

function deleteRecord(){
	var rows = $("#employee").datagrid('getSelected');
	if(rows!=null){
		$.messager.confirm("系统提示","你的操作将会从数据库里删除数据，有可能带来无法挽回的后果，如果不确认该数据是否还被需要，可将其状态设置为不可用!",function(data){
			if(data){
				$.ajax({
					type:'post',
					url:'employee_delete.action',
					data:{
						id:rows.employeeId
					},
					dataType:'json',
					success:function(data){
						if(data.success){
							$("#employee").datagrid('reload');
						}
						$.messager.alert("系统提示","删除成功");
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
		'employee_search',
		$('#searchForm1').serialize(),
		function(data){
			if(data){
				$('#employee').datagrid('loadData',data);
			}else{
				$('#employee').datagrid('loadData',{'total':0,'row':[]});
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
	<div id="employee"></div>
	<div id="tool">
		<form id="searchForm1">
		<table>
			<tr>
				<td><label>姓名：</label></td>
				<td><input  class="easyui-textbox" name="searchEmployeeName" data-options="onChange:function(){
						send();
					},"/></td>
				<td><label>部门：</label></td>
				<td><input  class="easyui-combobox" name="searchDept"  data-options="
					width:'80px',
					url:'department_combobox',
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
					 	'actionUsername':'${user.username }'
					}
					
					"/></td>
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
				<td><input type="hidden" name="actionEmployeeId" value="${user.employee.id }"></td>
			</tr>
		</table>
	</form>
		<c:if test="${permission.addEmployee }">
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="addOrUpdateEmployee('add','添加员工信息')">新员工登记</a>
		</c:if>
		<c:if test="${permission.alterEmployee }">
			<a class="easyui-linkbutton" iconCls="icon-edit" onclick="addOrUpdateEmployee('edit','修改员工信息')" >修改员工信息</a>
		</c:if>
		<c:if test="${permission.setStatus4Employee }">
			<a class="easyui-linkbutton" iconCls="icon-edit" onclick="alterStatus()">改变状态</a>
		</c:if>
		<c:if test="${permission.deleteEmployee }">
			<a class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteRecord()">删除员工信息</a>
		</c:if>
	</div>
</body>
</html>