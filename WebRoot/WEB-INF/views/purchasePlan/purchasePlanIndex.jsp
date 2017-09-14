<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/include.jsp"></jsp:include>
<%@ taglib uri="/WEB-INF/c-rt.tld" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.ineffective{
		color:black;
	}
	.executed{
		color: green;
	}
	.instocked{
		color: gray;
	}
	.disabled{
		color: red;
	}
</style>
<script type="text/javascript">
$(function(){
	$('#purchasePlan').datagrid({
		fit:true,//自适应窗口
		url:'purchasePlan_paginationFindAll.action',
		nowrap:true,//提高加载性能
		striped:true,//斑马线效果（隔行显示不同颜色）
		pagination:true,//翻页
		rownumbers:true,//显示行号
		singleSelect:true,//单行选中
		toolbar:'#tool',
		onDblClickRow:function(rowIndex, rowData){
			parent.$('#tt').tabs('add',{
				title:rowData.purchaseId+' |采购计划详情',
				selected: true,
				closable:true,
				content:'<iframe scrolling="auto" framebord="0" src="purchasePlanForm" style="width:100%;height:100%;" purchaseId="'+rowData.purchaseId+'" planName="'+rowData.planName+'" status="'+rowData.status+'"></iframe>'
				//...
			}).tabs('select');
		},
		columns:[[
			{field:'purchaseId',title:'单号',width:'10%'},
			{field:'planName',title:'计划名称',width:'10%'},
			{field:'employeeName',title:'计划人',width:'10%',formatter:function(data){return '<a href="#" >'+data+'</a>';}},
			{field:'createDate',title:'创建日期',width:'9%',formatter:formatterLocalTime},
			{field:'status',title:'状态',width:'9%',formatter:function(value){
					switch(value){
					case 0:return '<label class="ineffective">未生效</label>';
					case 1:return '<label class="executed">已生效</label>';
					case 2:return '<label class="instocked">已入库</label>';
					case 3:return '<label class="disabled">作废</label>';
					}
				}
			},
		]]
		
	});
	
	$('#search').click(function(){send();});
});
function openTabs(){
		parent.$('#tt').tabs('add',{
			title: '采购订单计划详情',
			selected: true,
			closable:true,
			content:'<iframe scrolling="auto" framebord="0" src="purchasePlanForm" style="width:100%;height:100%;" purchaseId="" planName="" status="new"></iframe>'
			//...
		}).tabs('select');
}
function issue(){
	var row = $('#purchasePlan').datagrid('getSelected');
	if(row.status==0){
		$.ajax({
			url:'purchasePlan_alterStatus',
			type:'post',
			dataTypt:'json',
			data:{'action':"0to1",'id':row.purchaseId},
			success:function(data){
				$('#purchasePlan').datagrid('reload');
			}
		});
	}else{
		$.messager.alert("提示","请选择未生效的计划");
	}
}
function revoke(){
	var row = $('#purchasePlan').datagrid('getSelected');
	if(row.status==1){
		$.ajax({
			url:'purchasePlan_alterStatus',
			type:'post',
			dataTypt:'json',
			data:{'action':"1to0",'id':row.purchaseId},
			success:function(data){
				$('#purchasePlan').datagrid('reload');
			}
		});
	}else{
		$.messager.alert("提示","请选择生效的计划");
	}
}
function unable(){
	var row = $('#purchasePlan').datagrid('getSelected');
	if(row.status==0){
		$.ajax({
			url:'purchasePlan_alterStatus',
			type:'post',
			dataTypt:'json',
			data:{'action':"0to3",'id':row.purchaseId},
			success:function(data){
				$('#purchasePlan').datagrid('reload');
			}
		});
	}else{
		$.messager.alert("提示","计划已生效或入库，请先撤回计划");
	}
}
function able(){
	var row = $('#purchasePlan').datagrid('getSelected');
	if(row.status==3){
		$.ajax({
			url:'purchasePlan_alterStatus',
			type:'post',
			dataTypt:'json',
			data:{'action':"3to0",'id':row.purchaseId},
			success:function(data){
				$('#purchasePlan').datagrid('reload');
			}
		});
	}else{
		$.messager.alert("提示","请选择作废计划");
	}
}
function deletePlan(){
	$.messager.confirm("警告", "删除采购计划会同时删除该计划的入库记录，如需保留入库记录，请将其设为作废状态!一定要删除吗?", function (r) {
		if(r){
			var row = $('#purchasePlan').datagrid('getSelected');
			if(row.status==3){
				$.ajax({
					url:'purchasePlan_delete',
					type:'post',
					dataTypt:'json',
					data:{'id':row.purchaseId},
					success:function(data){
						//$('#purchasePlan').datagrid('reload');
						var currentTab = parent.$('#tt').tabs('getSelected');
		   			 	RefreshTab(currentTab);
					}
				});
			}else{
				$.messager.alert("提示","请选择作废计划");
			}
		}
	});
}

function send(){
	$.getJSON(
			'purchasePlan_search',
			$('#searchForm8').serialize(),
			function(data){
				if(data){
					$('#purchasePlan').datagrid('loadData',data);
				}else{
					$('#purchasePlan').datagrid('loadData',{'total':0,'row':[]});
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
	<div id="purchasePlan"></div>
	<div id="tool">
		<form id="searchForm8">
		<table>
			<tr>
				<td><label>单号：</label></td>
				<td><input  class="easyui-textbox" name="searchPurchaseId" style="width:120px;" data-options=" onChange:function(){send();}"/></td>
				<td><label>计划名称：</label></td>
				<td><input  class="easyui-textbox" name="searchPurchaseName" data-options=" onChange:function(){send();}"/></td>
				<td><label>计划制定人：</label></td>
				<td><input  class="easyui-textbox" name="searchPurchaseEmployee" style="width:120px;" data-options=" onChange:function(){send();}"/></td>
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
						id: '0',
						text: '未生效'
					},{
						id: '1',
						text: '已生效'
					},{
						id: '2',
						text: '已发布'
					},{
						id: '3',
						text: '作废'
					}]" /></td>
				<td><a class="easyui-linkbutton" id="search">搜索</a></td>
				<td><input type="hidden" name="actionUsername" value="${user.username }"></td>
			</tr>
		</table>
	</form>
		<c:if test="${permission.addPurchasePlan }">
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="openTabs()">添加采购计划</a>
		</c:if>
		<c:if test="${permission.setStatus2Delete4PurchasePlan }">
			<a class="easyui-linkbutton" iconCls="icon-edit" onclick="issue()" >发布采购计划</a>
			<a class="easyui-linkbutton" iconCls="icon-redo" onclick="revoke()">撤回采购计划</a>
			<a class="easyui-linkbutton" iconCls="icon-no" onclick="unable()">作废</a>
			<a class="easyui-linkbutton" iconCls="icon-undo" onclick="able()">取消作废</a>
		</c:if>
		<c:if test="${permission.deletePurchasePlan }">
			<a class="easyui-linkbutton" iconCls="icon-remove" onclick="deletePlan()">删除采购计划</a>
		</c:if>
	</div>
</body>
</html>