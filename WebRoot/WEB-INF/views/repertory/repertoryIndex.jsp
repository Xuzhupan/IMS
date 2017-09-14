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
		$('#repertory').datagrid({
			iconCls:'icon-edit',
			fit:true,
			singleSelect:true,
			idField:'goodsId',
			url:'repertory_findAll',
			toolbar:'#tool',
			loadFilter:function(data){
				var s = '{"total":'+data.total;
				s = s+ ',"rows":[';
				$.each(data.rows,function(index,value){
					s=s +'{\"goodsId\":'+value.goodsId+',\"number\":'+value.number+
						',\"goodsName\":\"'+value.goods.name+'\"'+
						',\"goodsType\":\"'+value.goods.type+'\"'+
						',\"goodsUnit\":\"'+value.goods.unit+'\"'+
						',\"goodsInformation\":\"'+value.goods.information+'\"},';
				});
				s=s.substring(0,s.length-1);
				s= s +']';
				s=s +'}';
				return $.parseJSON(s);
			},
			columns:[[
				{field:'goodsId',title:'ID',width:80,},
				{field:'goodsName',title:'名称',width:80,},
				{field:'goodsType',title:'规格型号',width:100,},
				{field:'goodsUnit',title:'单位',width:50,},
				{field:'goodsInformation',title:'备注信息',width:180,},
				{field:'number',title:'库存值',width:80,align:'right',}
			]]
		});
		
		$('#search').click(function(){send();});
	});
	 
	function send(){
		$.getJSON(
				'repertory_search',
				$('#searchForm6').serialize(),
				function(data){
					if(data){
						$('#repertory').datagrid('loadData',data);
					}else{
						$('#repertory').datagrid('loadData',{'total':0,'row':[]});
					}
				}
			);
	}

</script>
</head>
<body>
	<table id="repertory"></table>
	<div id="tool">
		<form id="searchForm6">
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