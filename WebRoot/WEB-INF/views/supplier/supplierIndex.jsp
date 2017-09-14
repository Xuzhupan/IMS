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
		$('#supplier').datagrid({
			fit:true,//自适应窗口
			url:'supplier_paginationFindAll.action',
			nowrap:true,//提高加载性能
			striped:true,//斑马线效果（隔行显示不同颜色）
			pagination:true,//翻页
			rownumbers:true,//显示行号
			singleSelect:true,//单行选中
			toolbar:'#tool',
			columns:[[
				{field:'name',title:'供应商名称',width:'10%'},
				{field:'messenger',title:'联系人',width:'9%'},
				{field:'tel',title:'联系方式',width:'9%'},
				{field:'taxpayerIdentity',title:'纳税识别码',width:'9%'},
				{field:'bankName',title:'开户银行名称',width:'9%'},
				{field:'bank_account',title:'银行帐号',width:'9%'},
				{field:'registerTel',title:'注册电话',width:'9%'},
				{field:'registerAddress',title:'注册地址',width:'9%'},
				{field:'createDate',title:'创建日期',width:'9%',formatter:formatterLocalTime},
				{field:'information',title:'备注信息',width:'9%'},
				{field:'status',title:'状态',width:'9%',formatter:function(value){if(value==1){return '<img src="css/themes/icons/ok.png">'}else{return '<img src="css/themes/icons/no.png">'}}},
			]]
			
		});
		
		$('#search').click(function(){
			search();
		});
	});
	function addOrUpdateSupplier(type,title){
		var rows = $("#supplier").treegrid('getSelected');
	  	var href = "supplierForm.action";
		if('edit'==type){
			if(rows!=null){
				href = "supplierForm.action?id="+rows.id;
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
			height:400,
			cache:false,
			modal:true,
			href:href,
			buttons:[{
				iconCls:'icon-save',
				text:'保存',
				handler:function(){
					$('#supplierForm').form('submit',{
						onSubmit:function(){
							return $(this).form('enableValidation').form('validate');
						},
						success:function(data){	
							$.ajax({
								type:'post',
								url:'supplier_saveOrUpdate.action',
								data:$('#supplierForm').serialize(),
								dataType:'json',
								success:function(data){
									if(data.success){
										$('#da').dialog('close');
										$("#supplier").datagrid('reload');
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
			url:'supplier_alterStatus',
			type:'post',
			dataType:'json',
			data:{
				id:$("#supplier").treegrid('getSelected').id,
				name:$("#supplier").treegrid('getSelected').name,
				messenger:$("#supplier").treegrid('getSelected').messenger,
				tel:$("#supplier").treegrid('getSelected').tel,
				taxpayerIdentity:$("#supplier").treegrid('getSelected').taxpayerIdentity,
				bankName:$("#supplier").treegrid('getSelected').bankName,
				bankAccount:$("#supplier").treegrid('getSelected').bankAccount,
				registerTel:$("#supplier").treegrid('getSelected').registerTel,
				registerAddress:$("#supplier").treegrid('getSelected').registerAddress,
				information:$("#supplier").treegrid('getSelected').information,
				status:($("#supplier").treegrid('getSelected').status==1)?0:1
				},
			success:function(data){
				$("#supplier").datagrid('reload');
			}
		});
	}
	
	function deleteRecord(){
		var rows = $("#supplier").datagrid('getSelected');
		if(rows!=null){
			$.messager.confirm("系统提示","你的操作将会从数据库里删除数据，有可能带来无法挽回的后果，如果不确认该数据是否还被需要，可将其状态设置为不可用!",function(data){
				if(data){
					$.ajax({
						type:'post',
						url:'supplier_delete.action',
						data:{
							id:rows.id
						},
						dataType:'json',
						success:function(data){
							if(data.success){
								$("#supplier").datagrid('reload');
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
			'supplier_search',
			$('#searchForm5').serialize(),
			function(data){
				if(data){
					$('#supplier').datagrid('loadData',data);
				}else{
					$('#supplier').datagrid('loadData',{'total':0,'row':[]});
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
	<div id="supplier"></div>
	<div id="tool">
	<form id="searchForm5">
		<table>
			<tr>
				<td><label>供应商名称：</label></td>
				<td><input  class="easyui-textbox" name="searchSupplierName" data-options=" onChange:function(){send();}" /></td>
				<td><label>纳税识别码：</label></td>
				<td><input  class="easyui-textbox" name="searchSupplierTax" data-options=" onChange:function(){send();}"/></td>
				<td><label>开户银行：</label></td>
				<td><input  class="easyui-textbox" name="searchBankName" data-options=" onChange:function(){send();}"/></td>
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
		<c:if test="${permission.addSupplier }">
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="addOrUpdateSupplier('add','添加供应商信息')">添加供应商信息</a>
		</c:if>
		<c:if test="${permission.alterSupplier }">
			<a class="easyui-linkbutton" iconCls="icon-edit" onclick="addOrUpdateSupplier('edit','修改供应商信息')" >修改供应商信息</a>
		</c:if>
		<c:if test="${permission.setStatus2Delete4Supplier }">
			<a class="easyui-linkbutton" iconCls="icon-edit" onclick="alterStatus()">改变状态</a>
		</c:if>
		<c:if test="${permission.deleteSupplier }">
			<a class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteRecord()">删除供应商信息</a>
		</c:if>
	</div>
</body>
</html>