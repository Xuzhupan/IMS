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
		$('#customer').datagrid({
			fit:true,//自适应窗口
			url:'customer_paginationFindAll.action',
			nowrap:true,//提高加载性能
			striped:true,//斑马线效果（隔行显示不同颜色）
			pagination:true,//翻页
			rownumbers:true,//显示行号
			singleSelect:true,//单行选中
			toolbar:'#tool',
			columns:[[
				{field:'name',title:'客户名称',width:'10%'},
				{field:'messenger',title:'联系人',width:'9%'},
				{field:'tel',title:'联系方式',width:'9%'},
				{field:'createDate',title:'创建日期',width:'9%',formatter:formatterLocalTime},
				{field:'information',title:'备注信息',width:'9%'},
				{field:'status',title:'状态',width:'9%',formatter:function(value){if(value==1){return '<img src="css/themes/icons/ok.png">'}else{return '<img src="css/themes/icons/no.png">'}}},
			]]
			
		});
		
		$('#search').click(function(){
			search();
		});
		
	});
	function addOrUpdatecustomer(type,title){
		var rows = $("#customer").treegrid('getSelected');
	  	var href = "customerForm.action";
		if('edit'==type){
			if(rows!=null){
				href = "customerForm.action?id="+rows.id;
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
					$('#customerForm').form('submit',{
						onSubmit:function(){
							return $(this).form('enableValidation').form('validate');
						},
						success:function(data){	
							$.ajax({
								type:'post',
								url:'customer_saveOrUpdate.action',
								data:$('#customerForm').serialize(),
								dataType:'json',
								success:function(data){
									if(data.success){
										$('#da').dialog('close');
										$("#customer").datagrid('reload');
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
			url:'customer_alterStatus',
			type:'post',
			dataType:'json',
			data:{
				id:$("#customer").treegrid('getSelected').id,
				name:$("#customer").treegrid('getSelected').name,
				messenger:$("#customer").treegrid('getSelected').messenger,
				tel:$("#customer").treegrid('getSelected').tel,
				information:$("#customer").treegrid('getSelected').information,
				status:($("#customer").treegrid('getSelected').status==1)?0:1
				},
			success:function(data){
				$("#customer").datagrid('reload');
			}
		});
	}
	
	function deleteRecord(){
		var rows = $("#customer").datagrid('getSelected');
		if(rows!=null){
			$.messager.confirm("系统提示","你的操作将会从数据库里删除数据，有可能带来无法挽回的后果，如果不确认该数据是否还被需要，可将其状态设置为不可用!",function(data){
				if(data){
					$.ajax({
						type:'post',
						url:'customer_delete.action',
						data:{
							id:rows.id
						},
						dataType:'json',
						success:function(data){
							if(data.success){
								$("#customer").datagrid('reload');
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
			'customer_search',
			$('#searchForm4').serialize(),
			function(data){
				if(data){
					$('#customer').datagrid('loadData',data);
				}else{
					$('#customer').datagrid('loadData',{'total':0,'row':[]});
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
	<div id="customer"></div>
	<div id="tool">
	<form id="searchForm4">
		<table>
			<tr>
				<td><label>客户名称：</label></td>
				<td><input  class="easyui-textbox" name="searchCustomerName" data-options=" onChange:function(){send();}"/></td>
				<td><label>状态：</label></td>
				<td><input  class="easyui-combobox" name="searchStatus"  data-options="
					width:'80px',
					valueField: 'id',
					textField: 'text',
					onChange:function(){send();},
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
		<c:if test="${permission.addCustomer }">
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="addOrUpdatecustomer('add','添加客户信息')">添加客户信息</a>
		</c:if>
		<c:if test="${permission.alterCustomer }">
			<a class="easyui-linkbutton" iconCls="icon-edit" onclick="addOrUpdatecustomer('edit','修改客户信息')" >修改客户信息</a>
		</c:if>
		<c:if test="${permission.setStatus2Delete4Customer }">
			<a class="easyui-linkbutton" iconCls="icon-edit" onclick="alterStatus()">改变状态</a>
		</c:if>
		<c:if test="${permission.deleteCustomer }">
			<a class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteRecord()">删除客户信息</a>
		</c:if>
	</div>
</body>
</html>