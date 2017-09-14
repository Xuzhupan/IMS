<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/include.jsp"></jsp:include>
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
		$('#sellPlan').datagrid({
			fit:true,//自适应窗口
			url:'sellPlan_paginationFindAll.action',
			nowrap:true,//提高加载性能
			striped:true,//斑马线效果（隔行显示不同颜色）
			pagination:true,//翻页
			rownumbers:true,//显示行号
			singleSelect:true,//单行选中
			toolbar:'#tool',
			
			onDblClickRow:function(rowIndex, rowData){
				console.log(rowData);
				parent.$('#tt').tabs('add',{
					title:rowData.sellPlanId+' |销售订单详情',
					selected: true,
					closable:true,
					content:'<iframe scrolling="auto" framebord="0" src="sellPlanForm" style="width:100%;height:100%;" scrolling="auto" frameborder="0" sellPlanId="'+rowData.sellPlanId+'" planName="'+rowData.planName+'" customerId="'+rowData.customerId+'" status="'+rowData.status+'"></iframe>'
					//...
				}).tabs('select');
			},
			columns:[[
				{field:'sellPlanId',title:'单号',width:'10%'},
				{field:'planName',title:'订单名称',width:'10%'},
				{field:'employeeName',title:'计划人制定人',width:'10%',formatter:function(data){return '<a href="#" >'+data+'</a>';}},
				{field:'customerName',title:'客户名称',width:'10%',formatter:function(data){return '<a href="#" >'+data+'</a>';}},
				{field:'createDate',title:'创建日期',width:'9%',formatter:formatterLocalTime},
				{field:'status',title:'状态',width:'9%',formatter:function(value){
						switch(value){
						case 0:return '<label class="ineffective">未生效</label>';
						case 1:return '<label class="executed">已生效</label>';
						case 2:return '<label class="instocked">已出库</label>';
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
			title: '销售订单详情',
			selected: true,
			closable:true,
			content:'<iframe scrolling="auto" framebord="0" src="sellPlanForm" style="width:100%;height:100%;" sellPlanId="" planName="" status="new"></iframe>'
			//...
		}).tabs('select');
	}
	function issue(){
		var row = $('#sellPlan').datagrid('getSelected');
		if(row.status==0){
			$.ajax({
				url:'sellPlan_alterStatus',
				type:'post',
				dataTypt:'json',
				data:{'action':"0to1",'id':row.sellPlanId},
				success:function(data){
					$('#sellPlan').datagrid('reload');
				}
			});
		}else{
			$.messager.alert("提示","请选择未生效的计划");
		}
	}
	function revoke(){
		var row = $('#sellPlan').datagrid('getSelected');
		if(row.status==1){
			$.ajax({
				url:'sellPlan_alterStatus',
				type:'post',
				dataTypt:'json',
				data:{'action':"1to0",'id':row.sellPlanId},
				success:function(data){
					$('#sellPlan').datagrid('reload');
				}
			});
		}else{
			$.messager.alert("提示","请选择生效的计划");
		}
	}
	function unable(){
		var row = $('#sellPlan').datagrid('getSelected');
		if(row.status==0){
			$.ajax({
				url:'sellPlan_alterStatus',
				type:'post',
				dataTypt:'json',
				data:{'action':"0to3",'id':row.sellPlanId},
				success:function(data){
					$('#sellPlan').datagrid('reload');
				}
			});
		}else{
			$.messager.alert("提示","计划已生效或已出库，请先撤回计划");
		}
	}
	function able(){
		var row = $('#sellPlan').datagrid('getSelected');
		if(row.status==3){
			$.ajax({
				url:'sellPlan_alterStatus',
				type:'post',
				dataTypt:'json',
				data:{'action':"3to0",'id':row.sellPlanId},
				success:function(data){
					$('#sellPlan').datagrid('reload');
				}
			});
		}else{
			$.messager.alert("提示","请选择作废计划");
		}
	}
	function deletePlan(){
		$.messager.confirm("警告", "删除销售订单会同时删除该订单的出库记录，如需保留出库记录，请将其设为作废状态!一定要删除吗?", function (r) {
			if(r){
				var row = $('#sellPlan').datagrid('getSelected');
				if(row.status==3){
					$.ajax({
						url:'sellPlan_delete',
						type:'post',
						dataTypt:'json',
						data:{'id':row.sellPlanId},
						success:function(data){
							//$('#sellPlan').datagrid('reload');
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
				'sellPlan_search',
				$('#searchForm9').serialize(),
				function(data){
					if(data){
						$('#sellPlan').datagrid('loadData',data);
					}else{
						$('#sellPlan').datagrid('loadData',{'total':0,'row':[]});
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
	<div id="sellPlan"></div>
	<div id="tool">
	<form id="searchForm9">
		<table>
			<tr>
				<td><label>单号：</label></td>
				<td><input  class="easyui-textbox" name="searchSellId" style="width:120px;" data-options=" onChange:function(){send();}"/></td>
				<td><label>订单名称：</label></td>
				<td><input  class="easyui-textbox" name="searchSellName" data-options=" onChange:function(){send();}"/></td>
				<td><label>订单制定人：</label></td>
				<td><input  class="easyui-textbox" name="searchSellEmployee" style="width:120px;" data-options=" onChange:function(){send();}"/></td>
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
		<c:if test="${permission.addSellPlan }">
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="openTabs()">添加销售订单</a>
		</c:if>
		<c:if test="${permission.setStatus2Delete4SellPlan }">
		<a class="easyui-linkbutton" iconCls="icon-edit" onclick="issue()" >发布订单</a>
			<a class="easyui-linkbutton" iconCls="icon-redo" onclick="revoke()">撤回订单</a>
			<a class="easyui-linkbutton" iconCls="icon-no" onclick="unable()">订单作废</a>
			<a class="easyui-linkbutton" iconCls="icon-undo" onclick="able()">取消作废</a>
		</c:if>
		<c:if test="${permission.deleteSellPlan }">
			<a class="easyui-linkbutton" iconCls="icon-remove" onclick="deletePlan()">删除销售订单</a>
		</c:if>
	</div>
</body>
</html>