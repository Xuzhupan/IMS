<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/include.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		$('#instock').datagrid({
			fit:false,//自适应窗口
			url:'purchasePlan_findStatus.action',
			nowrap:true,//提高加载性能
			striped:true,//斑马线效果（隔行显示不同颜色）
			pagination:true,//翻页
			rownumbers:true,//显示行号
			singleSelect:true,//单行选中
			toolbar:'#tool',
			onDblClickRow:function(rowIndex, rowData){
				openTabs(rowData);
			},
			columns:[[
				{field:'employeeId',checkbox:true},
				{field:'purchaseId',title:'单号',width:'35%'},
				{field:'employeeName',title:'计划人',width:'33%',formatter:function(data){return '<a href="#" >'+data+'</a>';}},
				{field:'createDate',title:'创建日期',width:'30%',formatter:formatterLocalTime}
			]]
			
		});
		/*----------右边入库记录--------*/
		$('#instockNote').datagrid({
			fit:false,//自适应窗口
			url:'instockNote_paginationFindAll.action',
			nowrap:true,//提高加载性能
			striped:true,//斑马线效果（隔行显示不同颜色）
			pagination:true,//翻页
			rownumbers:true,//显示行号
			singleSelect:true,//单行选中
			sortName:'createDate',
			sortOrder:'asc',
			toolbar:'#instockNoteTool',
			onDblClickRow:function(rowIndex, rowData){
				openTabs(rowData);
			},
			rowStyler:function(index,row){ 
				if(row.canRollback){
			        if (row.status==1){    
			            /* return 'background-color:yellow;color:green;font-weight:bold;'; */
			            return 'background-color:#77DDFF;color:green;';
			        }else{
			        	return 'background-color:#77DDFF;color:red;';
			        } 
				}else{
					if (row.status==1){    
			            /* return 'background-color:yellow;color:green;font-weight:bold;'; */
			            return 'background-color:#DDDDDD;color:green;';
			        }else{
			        	return 'background-color:#DDDDDD;color:red;';
			        } 
				}
		    },
			columns:[[
				{field:'instockNoteId',title:'入库记录号',width:'10%',checkbox:true},
				{field:'createDate',title:'创建时间',width:'20%',formatter:formatterToSecond},
				{field:'purchaseId',title:'单号',width:'15%'},
				{field:'plan_employeeName',title:'采购单计划人姓名',width:'10%',},
				{field:'note_employeeName',title:'入库操作人姓名',width:'10%'},
				{field:'status',title:'状态',width:'20%',formatter:function(data){
					switch(data){
					case 0: return '撤销入库';
					case 1: return '入库操作';
					}
				}}
				
			]],
			success:function(data){
				
			}
		});
		
	});
	function openTabs(rowData){
		if(!parent.$('#tt').tabs('exists',rowData.purchaseId+' |采购计划详情')){
			parent.$('#tt').tabs('add',{
				title:rowData.purchaseId+' |采购计划详情',
				selected: true,
				closable:true,
				content:'<iframe scrolling="auto" framebord="0" src="purchasePlanForm" style="width:100%;height:100%;" purchaseId="'+rowData.purchaseId+'" planName="'+rowData.planName+'" status="'+rowData.status+'"></iframe>'
				//...
			}).tabs('select');
		}else{
			/* parent.$('#tt').tabs('select',rowData.id+' |采购订单'); */
			parent.$('#tt').tabs('select',rowData.purchaseId+' |采购计划详情');
			 var currentTab = parent.$('#tt').tabs('getSelected');
			 RefreshTab(currentTab);
		}
	}
	function instock(){
		var row = $('#instock').datagrid('getSelected');
    	$.ajax({
    		url:'instockNote_instock',
    		type:'post',
    		dataType:'json',
    		data:{'purchasePlanId':row.purchaseId, 'employeeId':'${user.employee.id}'},
    		success:function(data){
    			if(data.success){
    				$.messager.alert("提示","入库成功");
    				reflush();
    			}else{
    				$.messager.alert("提示","操作失败");
    			}
    			 var currentTab = parent.$('#tt').tabs('getSelected');
    			 RefreshTab(currentTab);
    		}
    	});
	}
	function rollback(){
		var row = $('#instockNote').datagrid('getSelected');
		if(row.canRollback){
			if(row.status == 1){
				$.ajax({
		    		url:'instockNote_rollback',
		    		type:'post',
		    		dataType:'json',
		    		data:{'purchasePlanId':row.purchaseId, 'employeeId':'${user.employee.id}'},
		    		success:function(data){
		    			if(data.success){
		    				$.messager.alert("提示","撤销成功");
		    				reflush();
		    			}
		    			$('#instockNote').datagrid('reload');
		    			$('#instock').datagrid('reload');
		    		}
		    	});
			}else{
				$.messager.alert("提示","撤销操作");
			}
		}else{
			$.messager.alert("提示","该记录不能被撤消");
		}
	}
	
	function reflush(){
		// 如果打开了库存管理标签，则刷新它 
			if(parent.$('#tt').tabs('exists',"库存管理")){
				var currentTab = parent.$('#tt').tabs('getTab',"库存管理");
				RefreshTab(currentTab);
			}
			// 如果打开了采购计划标签，则刷新它 
   			if(parent.$('#tt').tabs('exists',"采购计划管理")){
   				var currentTab = parent.$('#tt').tabs('getTab',"采购计划管理");
   				RefreshTab(currentTab);
   			}
	}
</script>
</head>
<body>
	<div id="cc" class="easyui-layout" style="width:98%;height:96%;">
    	<div id="instock" data-options="region:'west'," style="width:30%;"></div>
    	<div id="instockNote" data-options="region:'center'," style="padding:0px;background:#eee;"></div>
	</div>
	<div id="tool">
		
		<a class="easyui-linkbutton" iconCls="icon-add" onclick="instock()">整单入库</a>
		
	
	</div>
	<div id="instockNoteTool">
		<a class="easyui-linkbutton" iconCls="icon-undo" onclick="rollback()">撤销操作</a>
	</div>
</body>
</html>