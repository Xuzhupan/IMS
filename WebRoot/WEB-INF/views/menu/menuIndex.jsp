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
		$('#menuBox').treegrid({
			fit:true,
			url:'menu_findMenus',
			rownumbers:true,
			idField:'id',
			treeField:'name',
			columns:[[
				    {field:'name',title:'名称',width:180},
				    {field:'status',title:'改变状态',width:100,formatter:function(value,row){
				    	if(${permission.setStatus4Menu}){
					    	//菜单管理不能被停用，
						    	if(row.id==5){
						    		return null;
						    	}
						    	if(value==1){
						    		var d = '<img src="css/themes/icons/cancel.png" style="margin:-4 5;height:13px;width:13px;"/><a href="#" onclick="changeStatus(this)">停用</a>';
						    	}else{
						    		var d = '<img src="css/themes/icons/ok.png" style="margin:-4 5;height:13px;width:13px;"/><a href="#" onclick="changeStatus(this)">启用</a>';
						    	}
								return d;
				    		}
							return null;
				    	}
				    }
				    ]]
		});
		
	});
	function get_Row(select,index){
        	 var row = $('#'+select).treegrid('find',index);
        	 return row;
    }
	function getRowIndex(target){
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('node-id'));
	}
	function changeStatus(target){
		var Msg;
	    console.log(get_Row('menuBox',getRowIndex(target)));
		if(get_Row('menuBox',getRowIndex(target)).status==1){
			Msg="停用";
		}else{
			Msg="启用";
		}
		$.messager.confirm('提示','确认'+Msg+'吗?',function(r){
			if (r){
				$.ajax({
					url:'menu_changeStatus',
					type:'post',
					dataType:'json',
					data:{'id':get_Row('menuBox',getRowIndex(target)).id},
					success:function(data){
						window.setTimeout('window.parent.location.href = "loginOut.action"',2000);
					/* 	window.parent.location.href = "loginOut.action"; */
						if(data.success){
							$.messager.show({title:"提示",msg:data.message});
						}
					}
				});
			}
		});
	}
</script>
</head>
<body>
	<table id="menuBox">
	</table>
</body>
</html>