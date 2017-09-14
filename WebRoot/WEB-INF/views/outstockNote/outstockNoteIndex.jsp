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
		$('#outstock').datagrid({
			fit:false,//自适应窗口
			url:'sellPlan_findStatus.action',
			nowrap:true,//提高加载性能
			striped:true,//斑马线效果（隔行显示不同颜色）
			pagination:true,//翻页
			rownumbers:true,//显示行号
			singleSelect:true,//单行选中
			toolbar:'#tool',
			onDblClickRow:function(rowIndex, rowData){
				openTabs(rowData,rowData.customerId);
			},
			columns:[[
				{field:'employeeId', checkbox:true},
				{field:'sellPlanId',title:'单号',width:'15%'},
				{field:'customerName',title:'客户名称',width:'35%'},
				{field:'employeeName',title:'计划人',width:'18%',formatter:function(data){return '<a href="#" >'+data+'</a>';}},
				{field:'createDate',title:'创建日期',width:'30%',formatter:formatterLocalTime}
			]]
			
		});
		/*----------右边入库记录--------*/
		$('#outstockNote').datagrid({
			fit:false,//自适应窗口
			url:'outstockNote_paginationFindAll.action',
			nowrap:true,//提高加载性能
			striped:true,//斑马线效果（隔行显示不同颜色）
			pagination:true,//翻页
			rownumbers:true,//显示行号
			singleSelect:true,//单行选中
			sortName:'createDate',
			sortOrder:'asc',
			toolbar:'#outstockNoteTool',
			onDblClickRow:function(rowIndex, rowData){
				openTabs(rowData,rowData.sellPlanCustomerId);
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
				{field:'outstockNoteId',title:'入库记录号',width:'10%',checkbox:true},
				{field:'createDate',title:'创建时间',width:'20%',formatter:formatterToSecond},
				{field:'sellPlanId',title:'单号',width:'15%'},
				{field:'plan_employeeName',title:'采购单计划人姓名',width:'10%',},
				{field:'note_employeeName',title:'入库操作人姓名',width:'10%'},
				{field:'status',title:'状态',width:'20%',formatter:function(data){
					switch(data){
					case 0: return '撤销出库';
					case 1: return '出库操作';
					}
				}}
				
			]],
			success:function(data){
				
			}
		});
		
	});
	function openTabs(rowData,customerId){
		if(!parent.$('#tt').tabs('exists',rowData.sellPlanId+' |销售订单详情')){
			parent.$('#tt').tabs('add',{
				title:rowData.sellPlanId+' |销售订单详情',
				selected: true,
				closable:true,
				content:'<iframe scrolling="auto" framebord="0" src="sellPlanForm" style="width:100%;height:100%;" sellPlanId="'+rowData.sellPlanId+'" customerId="'+customerId+'"></iframe>'
				//...
			}).tabs('select');
		}else{
			/* parent.$('#tt').tabs('select',rowData.id+' |采购订单'); */
			parent.$('#tt').tabs('select',rowData.sellPlanId+' |销售订单详情');
			 var currentTab = parent.$('#tt').tabs('getSelected');
			 RefreshTab(currentTab);
		}
	}
	function outstock(){
		var row = $('#outstock').datagrid('getSelected');
    	$.ajax({
    		url:'outstockNote_outstock',
    		type:'post',
    		dataType:'json',
    		data:{'sellPlanId':row.sellPlanId, 'employeeId':'${user.employee.id}'},
    		success:function(data){
    			if(data.success){
    				$.messager.alert("提示","出库成功");
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
		var row = $('#outstockNote').datagrid('getSelected');
		if(row.canRollback){
			if(row.status == 1){
				$.ajax({
		    		url:'outstockNote_rollback',
		    		type:'post',
		    		dataType:'json',
		    		data:{'sellPlanId':row.sellPlanId, 'employeeId':'${user.employee.id}'},
		    		success:function(data){
		    			if(data.success){
		    				$.messager.alert("提示","撤销成功");
		    				reflush();
		    			}
		    			$('#outstockNote').datagrid('reload');
		    			$('#outstock').datagrid('reload');
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
			// 如果打开了销售订单标签，则刷新它 
   			if(parent.$('#tt').tabs('exists',"销售订单管理")){
   				var currentTab = parent.$('#tt').tabs('getTab',"销售订单管理");
   				RefreshTab(currentTab);
   			}
	}
	function RefreshTab(currentTab) {
		var url = $(currentTab.panel('options')).attr('href');
		parent.$('#tt').tabs('update', {
			tab: currentTab,
			options: {
				href: url
			}
		});
		currentTab.panel('refresh');
	}
</script>
</head>
<body>
	<div id="c" class="easyui-layout" style="width:98%;height:96%;">
    	<div id="outstock" data-options="region:'west'," style="width:30%;"></div>
    	<div id="outstockNote" data-options="region:'center'," style="padding:0px;background:#eee;"></div>
	</div>
	<div id="tool">
		
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="outstock()">整单提货</a>
		
	
	</div>
	<div id="outstockNoteTool">
		<a class="easyui-linkbutton" iconCls="icon-undo" onclick="rollback()">撤销操作</a>
	</div>
</body>
</html>