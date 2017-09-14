<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/include.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	var thisTabs = parent.$('#tt').tabs('getSelected'); //当前标签页
	var tabsTitle =  thisTabs.panel('options').title; //当前标签页标题  笔记：title继承自panel，所以要.panel获得它的panel对象
	//var purchaseId = tabsTitle.split("|")[0]; //拿到订单 id
	var purchaseId = $(thisTabs.panel('options').content).attr('purchaseId'); //拿到订单 id
	var planName = $(thisTabs.panel('options').content).attr('planName'); //拿到订单 名称
	var status = $(thisTabs.panel('options').content).attr('status');
    var datagrid; //定义全局变量datagrid
    var editRow = 'undefined'; //定义全局变量：当前编辑的行
$(function () {
    datagrid = $("#purchasePlanForm").datagrid({
        url: 'purchaseInfo_findPlanInfo', //请求的数据源
        iconCls: 'icon-save', //图标
        pagination: true, //显示分页
        pageSize: 15, //页大小
        pageList: [15, 30, 45, 60], //页大小下拉选项此项各value是pageSize的倍数
        fit: true, //datagrid自适应宽度
        fitColumn: false, //列自适应宽度
        striped: true, //行背景交换
        nowap: true, //列内容多时自动折至第二行
        border: false,
        queryParams: {          
        	purchasePlanId:purchaseId==""?null:parseInt(purchaseId)          
            },   
        idField: 'id', //主键
        columns:[[
			{field:'purchaseInfoId',title:'ID',width:80,checkbox:true ,},
			{field:'goodsId',title:'ID',width:80,hidden:true,editor:'textReadonly'},
			{field:'goodsName',title:'商品名称',width:80,
				editor:{
					type:'myCombogrid',
					options:{
						panelWidth:650,
						required:true,
					    idField:'name',
					    textField:'name',
					    url:'goods_search',
					    queryParams:{
					    	'searchStatus':1,
							'actionUsername':'${user.username }'							    	
					    },
					    onSelect: function (index,r) {
					    	var editors = $('#purchasePlanForm').datagrid('getEditors',editRow);
					    	var goodsId = editors[0];
					    	var goodsName = editors[1];
					    	var goodsType = editors[2];
					    	var goodsUnit = editors[3];
					    	var goodsInfo = editors[4];
					    	id = r.id;
					    	goodsId.target.val(r.id);
					    	goodsName.target.val(r.name);
					    	goodsType.target.val(r.type);
					    	goodsUnit.target.val(r.unit);
					    	goodsInfo.target.val(r.information);
					    },
					    onChange:function(newValue, oldValue){
					    	$($('#purchasePlanForm').datagrid('getEditors',editRow)[5].target).numberbox().next('span').find('input').focus();
					    },
					    columns:[[
					    {field:'id',title:'ID',width:60},
					    {field:'name',title:'名称',width:60},
					    {field:'type',title:'规格型号',width:100},
					    {field:'unit',title:'单位',width:120},
					    {field:'information',title:'备注',width:150}
					    ]]
				
					}}
			},
			{field:'goodsType',title:'规格型号',width:80,editor:'textReadonly'},
			{field:'goodsUnit',title:'单位',width:80,editor:'textReadonly'},
			{field:'goodsInformation',title:'备注',width:80,editor:'textReadonly'},
			{field:'purchaseInfoNumber',title:'数量',width:80,
				editor:{
					type:'numberbox',
					options:{required:true,min:1}
				}
			},
			{field:'purchaseInfoUnitPrice',title:'单价',width:80,
				editor:{
					type:'numberbox',
					options:{precision:2,required:true,min:0}
				}
			},
			{field:'supplierId',title:'供应商id',width:80,hidden:true,editor:'textReadonly'},
			{field:'supplierName',title:'供应商名称',width:180,
				editor:{
					type:'myCombogrid_supplier',
					options:{
						panelWidth:800,
						required:true,
					    idField:'name',
					    textField:'name',
					    url:'supplier_search',
					   // toolbar:'#supplierTool',
					    queryParams:{
					    	'searchStatus':1,
							'actionUsername':'${user.username }'							    	
					    },
					    onSelect: function (index,r) {
					    	var e = $('#purchasePlanForm').datagrid('getEditors',editRow);
					    	var supplierId = e[7];
					    	var supplierName = e[8];
					    	var supplierMessenger = e[9];
					    	var supplierTel = e[10];
					    	var supplierInformation = e[11];
					    	
					    	supplierId.target.val(r.id);
					    	supplierName.target.val(r.name);
					    	supplierMessenger.target.val(r.messenger);
					    	supplierTel.target.val(r.tel);
					    	supplierInformation.target.val(r.imformation);
					    	
					    },
					    columns:[[
					    {field:'id',title:'ID',width:60},
					    {field:'name',title:'名称',width:140},
					    {field:'messenger',title:'联系人',width:60},
					    {field:'tel',title:'联系电话',width:120},
					    {field:'information',title:'备注',width:150}
					    ]]
				
					}}
			},
			{field:'supplierMessenger',title:'联系人',width:80,editor:'textReadonly'},
			{field:'supplierTel',title:'联系方式',width:80,editor:'textReadonly'},
			{field:'supplierInformation',title:'备注',width:80,editor:'textReadonly'}
		]],
        
		toolbar:'#tool',
        onAfterEdit: function (rowIndex, rowData, changes) {
            //endEdit该方法触发此事件
            editRow = 'undefined';
        },
        onDblClickRow: function (rowIndex, rowData) {
        //双击开启编辑行
            if (editRow != 'undefined') {
                datagrid.datagrid("endEdit", editRow);
            }
            if (editRow == 'undefined') {
                datagrid.datagrid("beginEdit", rowIndex);
                editRow = rowIndex;
            }
        }
    });
    //名称
    $('#namebox').textbox('setValue',planName=='undefined'?"":planName);
});

function add() {//添加列表的操作按钮添加，修改，删除等
	if(status==0 || status=='new') { 
	    //添加时先判断是否有开启编辑的行，如果有则把开户编辑的那行结束编辑
	    if (editRow != 'undefined') {
	        datagrid.datagrid("endEdit", editRow);
	    }
	    //添加时如果没有正在编辑的行，则在datagrid的第一行插入一行
	    if (editRow == 'undefined') {
	        datagrid.datagrid("insertRow", {
	            index: 0, // index start with 0
	            row: {
	
	            }
	        });
	        //将新插入的那一行开户编辑状态
	        datagrid.datagrid("beginEdit", 0);
	        //给当前编辑的行赋值
	        editRow = 0;
	    }
	}else{
		$.messager.show({title:"提示",msg:"订单已生效、入库或作废，不能被更改！"});
	}

}
function deleteRows() {
	if(status==0 || status=='new') { 
	    //删除时先获取选择行
	    var rows = datagrid.datagrid("getSelections");
	    console.log(rows);
	    //选择要删除的行
	    if (rows.length > 0) {
	        $.messager.confirm("提示", "你确定要删除吗?", function (r) {
	            if (r) {
	                var ids = [];
	                for (var i = 0; i < rows.length; i++) {
	                    ids.push(rows[i].purchaseInfoId);
	                }
	                alert(ids.join(","));
	                //将选择到的行存入数组并用,分隔转换成字符串，
	                //本例只是前台操作没有与数据库进行交互所以此处只是弹出要传入后台的id
	              $.ajax({
	               	url:'purchaseInfo_deleteRows',
	               	type:'post',
	               	dataType:'json',
	               	traditional: true,
	               	data:{'deleteIds':ids, 'purchasePlanId':purchaseId==""?null:parseInt(purchaseId)},
	               	success:function(data){
	               		if(data.success){
	               			$.messager.show({title:"提示",msg:data.message});
	               		}
	               		 $('#purchasePlanForm').datagrid('reload');
	               		 datagrid.datagrid("unselectAll");
	               	}
	               }); 
	            } 
	        });
	    }
	    else {
	        $.messager.alert("提示", "请选择要删除的行", "error");
	    } 
	}else{
		$.messager.show({title:"提示",msg:"订单已生效或入库，不能被更改！"});
	}
}
function alter() {
	if(status==0  || status=='new') { 
	    //修改时要获取选择到的行
	    var rows = datagrid.datagrid("getSelections");
	    //如果只选择了一行则可以进行修改，否则不操作
	    if (rows.length == 1) {
	        //修改之前先关闭已经开启的编辑行，当调用endEdit该方法时会触发onAfterEdit事件
	        if (editRow != 'undefined') {
	            datagrid.datagrid("endEdit", editRow);
	        }
	        //当无编辑行时
	        if (editRow == 'undefined') {
	            //获取到当前选择行的下标
	            var index = datagrid.datagrid("getRowIndex", rows[0]);
	            //开启编辑
	            datagrid.datagrid("beginEdit", index);
	            //把当前开启编辑的行赋值给全局变量editRow
	            editRow = index;
	            //当开启了当前选择行的编辑状态之后，
	            //应该取消当前列表的所有选择行，要不然双击之后无法再选择其他行进行编辑
	            datagrid.datagrid("unselectAll");
	        }
	    }
	}else{
		$.messager.show({title:"提示",msg:"订单已生效或入库，不能被更改！"});
	}
}
function save() {
	if(status==0 || status=='new') { 
		if(status==0){if(!${permission.alterPurchasePlan }){$.messager.show({title:"警告",msg:"没有权限"});return false;}}
	    //保存时结束当前编辑的行，自动触发onAfterEdit事件如果要与后台交互可将数据通过Ajax提交后台
	    datagrid.datagrid("endEdit", editRow);
	   
	    if(editRow=='undefined') {
		    var rows = datagrid.datagrid('getRows');
		    var entities;
		    for(i = 0;i < rows.length;i++)
		    {
		       entities = entities  + JSON.stringify(rows[i]);  
		    }
		    if(entities!=null){
			    $.ajax({
			   	 url:'purchaseInfo_saveOrUpdate',
			   	 type:'post',
			   	 dataType:'json',
			   	 data:{
			   		 'entities':entities,
			   		 'planName':$('#namebox').textbox('getValue'),
			   		 'employeeId':'${user.employee.id}',
			   		 'purchasePlanId':purchaseId==""?null:parseInt(purchaseId)
			   	 },
			   	 success:function(data){
			   		 if(data.success){
			   			// 如果打开了采购计划标签，则刷新它 
			   			if(parent.$('#tt').tabs('exists',"采购计划管理")){
			   				var currentTab = parent.$('#tt').tabs('getTab',"采购计划管理");
			   				RefreshTab(currentTab);
			   			}
			   			var i=parent.$('#tt').tabs('getTabIndex',thisTabs);
			   			parent.$('#tt').tabs('close',i);
			   			 $.messager.show({title:"提示",msg:data.message});
			   		 }
			   		 $('#purchasePlanForm').datagrid('reload');
			   		 datagrid.datagrid("unselectAll");
			   	 }
			    });
		    }else{
		    	$.messager.alert("警告","请添加商品");
		    }
	    }else{
			   $.messager.alert("提示","请先结束当前编辑行");
		   }
	}else{
		$.messager.show({title:"提示",msg:"订单已生效或入库，不能被更改！"});
	}
}
function cancel() {
    //取消当前编辑行把当前编辑行罢'undefined'回滚改变的数据,取消选择的行
    editRow = 'undefined';
    datagrid.datagrid("rejectChanges");
    datagrid.datagrid("unselectAll");
}
</script>
</head>
<body>
	<table>
		<tr>
			<td><label>计划名称：</label></label></td>
			<td><input id="namebox" class="easyui-textbox"><td>
		</tr>
	</table>
	<div id="tool">
		<a class="easyui-linkbutton" iconCls="icon-add" data-options="plain:true" onclick="add()">添加</a>
		<a class="easyui-linkbutton" iconCls="icon-remove" data-options="plain:true" onclick="deleteRows()" >删除</a>
		<a class="easyui-linkbutton" iconCls="icon-edit" data-options="plain:true" onclick="alter()" >修改</a>
		<a class="easyui-linkbutton" iconCls="icon-save" data-options="plain:true" onclick="save()" >保存</a>
		<a class="easyui-linkbutton" iconCls="icon-redo" data-options="plain:true" onclick="cancel()" >取消编辑</a>
	</div>
	<table id="purchasePlanForm"></table>
</body>
</html>