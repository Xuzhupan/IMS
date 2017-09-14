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
		$('#repertoryLimit').datagrid({
			iconCls:'icon-edit',
			fit:true,
			singleSelect:true,
			idField:'goodsId',
			toolbar:'#tool',
			url:'repertoryLimit_findAll',
			rowStyler:function(index,row){ 
				if(row.status==1){
					return 'background-color:#77DDFF;color:green;';
				}else{
					return 'background-color:#DDDDDD;color:red;';
				}
		    },
			columns:[[
				{field:'goodsId',title:'ID',width:80,},
				{field:'goodsName',title:'名称',width:80,},//textReadonly -拓展
				{field:'goodsType',title:'规格型号',width:100,},
				{field:'goodsUnit',title:'单位',width:50,},
				{field:'goodsInformation',title:'备注信息',width:180,},
				{field:'maxNumber',title:'最大库存值',width:80,align:'right',editor:'numberbox'},
				{field:'minNumber',title:'最小库存值',width:80,align:'right',editor:'numberbox'},
				{field:'action',title:'Action',width:125,align:'center',
					formatter:function(value,row,index){
						if (row.editing){
							var s = '<a href="#" onclick="saverow(this)">保存</a> ';
							var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
							return s+"  "+c;
						} else {
							if(row.status==1){
								var e = '<img src="css/themes/icons/pencil.png" style="margin:-4 5"/><a href="#" onclick="editrow(this)">编辑</a> ';
								var d = '<img src="css/themes/icons/cancel.png" style="margin:-4 5;height:13px;width:13px;"/><a href="#" onclick="changeStatus(this)">停用</a>';
								return e+d;
							}else{
								var d = '<img src="css/themes/icons/ok.png" style="margin:-4 5;height:13px;width:13px;"/><a href="#" onclick="changeStatus(this)">启用</a>';
								return d;
							}
						}
					}
				}
				
			]],
			onBeforeEdit:function(index,row){
				row.editing = true;
				updateActions(index);
			},
			onAfterEdit:function(index,row){
				row.editing = false;
				updateActions(index);
			},
			onCancelEdit:function(index,row){
				row.editing = false;
				updateActions(index);
			}
		
		});
		
		$('#search').click(function(){send();});
	});
	
	function updateActions(index){
		/* $('#tt').datagrid('updateRow',{
			index: index,
			row:{}
		}); */
		$('#repertoryLimit').datagrid('refreshRow', index);
	}
	function getRowIndex(target){
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	}
	function editrow(target){
		$('#repertoryLimit').datagrid('selectRow',getRowIndex(target));
		$('#repertoryLimit').datagrid('beginEdit', getRowIndex(target));
	}
	function changeStatus(target){
		var Msg;
		if(getRow('repertoryLimit',getRowIndex(target)).status==1){
			Msg="停用";
		}else{
			Msg="启用";
		}
		$.messager.confirm('提示','确认'+Msg+'吗?',function(r){
			if (r){
				$.ajax({
					url:'repertoryLimit_changeStatus',
					type:'post',
					dataType:'json',
					data:{'goodsId':getRow('repertoryLimit',getRowIndex(target)).goodsId},
					success:function(data){
						$('#repertoryLimit').datagrid('reload');
						if(data.success){
							$.messager.show({title:"提示",msg:data.message});
						}
					}
				});
			}
		});
	}
	function saverow(target){
		if(getRow('repertoryLimit',getRowIndex(target)).status==1){
			var i = getRowIndex(target);
			var editors = $('#repertoryLimit').datagrid('getEditors',i);
			
			var maxNumber = editors[0];
			var minNumber = editors[1];
	
			$.ajax({
				url:'repertoryLimit_saveOrUpdate',
				type:'post',
				dataType:'json',
				data:{
					goodsId:getRow('repertoryLimit',getRowIndex(target)).goodsId,
					maxNumber:maxNumber.target.val(),
					minNumber:minNumber.target.val()
				},
				success:function(data){
					$.messager.show({title:"提示",msg:data.message});
				}
			}); 
			$('#repertoryLimit').datagrid('endEdit', i);
		}else{
			$.messager.alert("提示","未启用");
		}
	}
	function cancelrow(target){
		$('#repertoryLimit').datagrid('cancelEdit', getRowIndex(target));
	}
	function insert(){
		var row = $('#repertoryLimit').datagrid('getSelected');
		if (row){
			var index = $('#repertoryLimit').datagrid('getRowIndex', row);
		} else {
			index = 0;
		}
		$('#repertoryLimit').datagrid('insertRow', {
			index: index,
			row:{
				
			}
		});
		$('#repertoryLimit').datagrid('selectRow',index);
		$('#repertoryLimit').datagrid('beginEdit',index);
	}
	
	function send(){
		$.getJSON(
				'repertoryLimit_search',
				$('#searchForm7').serialize(),
				function(data){
					if(data){
						$('#repertoryLimit').datagrid('loadData',data);
					}else{
						$('#repertoryLimit').datagrid('loadData',{'total':0,'row':[]});
					}
				}
			);
	}
</script>
</head>
<body>
	<table id="repertoryLimit"></table>
	<div id="tool">
		<form id="searchForm7">
		<table>
			<tr>
				<td><label>货物名称：</label></td>
				<td><input  class="easyui-textbox" name="searchGoodsName" data-options=" onChange:function(){send();}"/></td>
				<td><label>规格型号：</label></td>
				<td><input  class="easyui-textbox" name="searchGoodsType" data-options=" onChange:function(){send();}"/></td>
				<td><a class="easyui-linkbutton" id="search">搜索</a></td>
				<td><input type="hidden" name="actionUsername" value="${user.username }"></td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>