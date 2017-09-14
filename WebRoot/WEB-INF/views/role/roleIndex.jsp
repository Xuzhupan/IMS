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
	#permission{
		padding: 20;	
	}
	#permissionForm{
		margin-top: 20;
	}
	.permission-row{
		background: menu;
		height:30px;
		
	}
	.permission-label{
		background: orange;
		font-size: large;
		font-style: inherit;
		font-family: "楷体";
		border-radius: 5px 5px;
		text-align: center;
	}
	.permission-row td{
		width: 150px;
	}
	:checked{
		color: green;
	}
	
</style>
<script type="text/javascript">
	$(function(){
		$('#role').datagrid({
			fit:false,//自适应窗口
			url:'role_paginationFindAll?actionRoleId=${user.role.id}',
			nowrap:true,//提高加载性能
			striped:true,//斑马线效果（隔行显示不同颜色）
			pagination:true,//翻页
			rownumbers:true,//显示行号
			singleSelect:true,//单行选中
			toolbar:'#tool',
			onClickRow:function(rowIndex, rowData){
				if(rowData.id !=null){
					$('#roleId').textbox('setValue',rowData.id);
					$('#roleName').textbox('setValue',rowData.name);
					var permission = rowData.permission;
					init(permission);
				}else{
					$(':checked').prop('checked',false);
				}
			}, 
			columns:[[
				{field:'id',checkbox:true},
				{field:'name',title:'角色名称',width:'70%',editor:{
					type:'text',
					options:{required:true,min:1}
				}},
				{field:'status',title:'状态',width:'25%',formatter:function(value){if(value==1){return '<img src="css/themes/icons/ok.png">'}else{return '<img src="css/themes/icons/no.png">'}}}
			]]
		});
	});
	function init(permission){
		$(':checked').prop('checked',false);
		//用户权限
		if(permission.viewUser){
			$('#viewUser').prop('checked',true);
		}
		if(permission.addUser){
			$('#addUser').prop('checked',true);
		}
		if(permission.alterPassword){
			$('#alterPassword').prop('checked',true);
		}
		if(permission.authorize){
			$('#authorize').prop('checked',true);
		}
		if(permission.linkEmployee){
			$('#linkEmployee').prop('checked',true);
		}
		if(permission.setStatus4User){
			$('#setStatus4User').prop('checked',true);
		}
		if(permission.deleteUser){
			$('#deleteUser').prop('checked',true);
		}
		//员工权限
		if(permission.viewEmployee){
			$('#viewEmployee').prop('checked',true);
		}
		if(permission.addEmployee){
			$('#addEmployee').prop('checked',true);
		}
		if(permission.alterEmployee){
			$('#alterEmployee').prop('checked',true);
		}
		if(permission.setStatus4Employee){
			$('#setStatus4Employee').prop('checked',true);
		}
		if(permission.deleteEmployee){
			$('#deleteEmployee').prop('checked',true);
		}
		//部门权限
		if(permission.viewDepartment){
			$('#viewDepartment').prop('checked',true);
		}
		if(permission.addDepartment){
			$('#addDepartment').prop('checked',true);
		}
		if(permission.alterDepartment){
			$('#alterDepartment').prop('checked',true);
		}
		if(permission.setStatus2Delete4Department){
			$('#setStatus2Delete4Department').prop('checked',true);
		}
		if(permission.deleteDepartment){
			$('#deleteDepartment').prop('checked',true);
		}
		//菜单权限
		if(permission.viewMenu){
			$('#viewMenu').prop('checked',true);
		}
		if(permission.setStatus4Menu){
			$('#setStatus4Menu').prop('checked',true);
		}
		//角色权限
		if(permission.viewRole){
			$('#viewRole').prop('checked',true);
		}
		if(permission.addRole){
			$('#addRole').prop('checked',true);
		}
		if(permission.alterRole){
			$('#alterRole').prop('checked',true);
		}
		if(permission.setStatus4Role){
			$('#setStatus4Role').prop('checked',true);
		}
		if(permission.deleteRole){
			$('#deleteRole').prop('checked',true);
		}
		//物品权限
		if(permission.viewGoods){
			$('#viewGoods').prop('checked',true);
		}
		if(permission.addGoods){
			$('#addGoods').prop('checked',true);
		}
		if(permission.alterGoods){
			$('#alterGoods').prop('checked',true);
		}
		if(permission.setStatus2Delete4Goods){
			$('#setStatus2Delete4Goods').prop('checked',true);
		}
		if(permission.deleteGoods){
			$('#deleteGoods').prop('checked',true);
		}
		//客户权限
		if(permission.viewCustomer){
			$('#viewCustomer').prop('checked',true);
		}
		if(permission.addCustomer){
			$('#addCustomer').prop('checked',true);
		}
		if(permission.alterCustomer){
			$('#alterCustomer').prop('checked',true);
		}
		if(permission.setStatus2Delete4Customer){
			$('#setStatus2Delete4Customer').prop('checked',true);
		}
		if(permission.deleteCustomer){
			$('#deleteCustomer').prop('checked',true);
		}
		//供应商权限
		if(permission.viewSupplier){
			$('#viewSupplier').prop('checked',true);
		}
		if(permission.addSupplier){
			$('#addSupplier').prop('checked',true);
		}
		if(permission.alterSupplier){
			$('#alterSupplier').prop('checked',true);
		}
		if(permission.setStatus2Delete4Supplier){
			$('#setStatus2Delete4Supplier').prop('checked',true);
		}
		if(permission.deleteSupplier){
			$('#deleteSupplier').prop('checked',true);
		}
		//库存权限
		if(permission.viewRepertory){
			$('#viewRepertory').prop('checked',true);
		}
		if(permission.instock){
			$('#instock').prop('checked',true);
		}
		if(permission.revokeInstock){
			$('#revokeInstock').prop('checked',true);
		}
		if(permission.outstock){
			$('#outstock').prop('checked',true);
		}
		if(permission.revokeOutStock){
			$('#revokeOutStock').prop('checked',true);
		}
		//库存容量权限
		if(permission.setLimit){
			$('#setLimit').prop('checked',true);
		}
		//采购权限
		if(permission.viewPurchasePlan){
			$('#viewPurchasePlan').prop('checked',true);
		}
		if(permission.addPurchasePlan){
			$('#addPurchasePlan').prop('checked',true);
		}
		if(permission.alterPurchasePlan){
			$('#alterPurchasePlan').prop('checked',true);
		}
		if(permission.setStatus2Delete4PurchasePlan){
			$('#setStatus2Delete4PurchasePlan').prop('checked',true);
		}
		if(permission.deletePurchasePlan){
			$('#deletePurchasePlan').prop('checked',true);
		}
		//销售权限
		if(permission.viewSellPlan){
			$('#viewSellPlan').prop('checked',true);
		}
		if(permission.addSellPlan){
			$('#addSellPlan').prop('checked',true);
		}
		if(permission.alterSellPlan){
			$('#alterSellPlan').prop('checked',true);
		}
		if(permission.setStatus2Delete4SellPlan){
			$('#setStatus2Delete4SellPlan').prop('checked',true);
		}
		if(permission.deleteSellPlan){
			$('#deleteSellPlan').prop('checked',true);
		}
	}
	function add(){
		$('#role').datagrid('clearSelections');
		$('#roleId').textbox('setValue',null);
		$('#roleName').textbox('setValue',null);
		$(':checked').prop('checked',false);
		
	}
	function alterStatus(){
		$.ajax({
			url:'role_alterStatus',
			type:'post',
			dataType:'json',
			data:{'id':$('#roleId').textbox('getValue')},
			success:function(data){
				if(data.success){
					$.messager.show({title:"提示",msg:"成功"});
					$("#role").datagrid('reload');
				}
			}
		})
	}
	function deleteRole(){
		$.ajax({
			url:'role_delete',
			type:'post',
			dataType:'json',
			data:{'id':$('#roleId').textbox('getValue')},
			success:function(data){
				if(data.success){
					$.messager.show({title:"提示",msg:"成功"});
					$("#role").datagrid('reload');
				}
			}
		})
	}
	function save(){
		$('#roleName').form('submit',{
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success:function(data){
				$.ajax({
					url:'permission_save',
					type:'post',
					dataType:'json',
					data:{
						'roleId':$('#roleId').val(),
						'roleName':$('#roleName').val(),
						
						'viewUser':$('#viewUser').is(':checked'),
						'addUser':$('#addUser').is(':checked'),
						'alterPassword':$('#alterPassword').is(':checked'),
						'authorize':$('#authorize').is(':checked'),
						'linkEmployee':$('#linkEmployee').is(':checked'),
						'setStatus4User':$('#setStatus4User').is(':checked'),
						'deleteUser':$('#deleteUser').is(':checked'),
						
						'viewEmployee':$('#viewEmployee').is(':checked'),
						'addEmployee':$('#addEmployee').is(':checked'),
						'alterEmployee':$('#alterEmployee').is(':checked'),
						'setStatus4Employee':$('#setStatus4Employee').is(':checked'),
						'deleteEmployee':$('#deleteEmployee').is(':checked'),
						
						'viewDepartment':$('#viewDepartment').is(':checked'),
						'addDepartment':$('#addDepartment').is(':checked'),
						'alterDepartment':$('#alterDepartment').is(':checked'),
						'setStatus2Delete4Department':$('#setStatus2Delete4Department').is(':checked'),
						'deleteDepartment':$('#deleteDepartment').is(':checked'),
						
						'viewMenu':$('#viewMenu').is(':checked'),
						'setStatus4Menu':$('#setStatus4Menu').is(':checked'),
						
						'viewRole':$('#viewRole').is(':checked'),
						'addRole':$('#addRole').is(':checked'),
						'alterRole':$('#alterRole').is(':checked'),
						'setStatus4Role':$('#setStatus4Role').is(':checked'),
						'deleteRole':$('#deleteRole').is(':checked'),
						
						'viewGoods':$('#viewGoods').is(':checked'),
						'addGoods':$('#addGoods').is(':checked'),
						'alterGoods':$('#alterGoods').is(':checked'),
						'setStatus2Delete4Goods':$('#setStatus2Delete4Goods').is(':checked'),
						'deleteGoods':$('#deleteGoods').is(':checked'),
						
						'viewCustomer':$('#viewCustomer').is(':checked'),
						'addCustomer':$('#addCustomer').is(':checked'),
						'alterCustomer':$('#alterCustomer').is(':checked'),
						'setStatus2Delete4Customer':$('#setStatus2Delete4Customer').is(':checked'),
						'deleteCustomer':$('#deleteCustomer').is(':checked'),
						
						'viewSupplier':$('#viewSupplier').is(':checked'),
						'addSupplier':$('#addSupplier').is(':checked'),
						'alterSupplier':$('#alterSupplier').is(':checked'),
						'setStatus2Delete4Supplier':$('#setStatus2Delete4Supplier').is(':checked'),
						'deleteSupplier':$('#deleteSupplier').is(':checked'),
						
						'viewRepertory':$('#viewRepertory').is(':checked'),
						'instock':$('#instock').is(':checked'),
						'revokeInstock':$('#revokeInstock').is(':checked'),
						'outstock':$('#outstock').is(':checked'),
						'revokeOutStock':$('#revokeOutStock').is(':checked'),
						
						'setLimit':$('#setLimit').is(':checked'),
						
						'viewPurchasePlan':$('#viewPurchasePlan').is(':checked'),
						'addPurchasePlan':$('#addPurchasePlan').is(':checked'),
						'alterPurchasePlan':$('#alterPurchasePlan').is(':checked'),
						'setStatus2Delete4PurchasePlan':$('#setStatus2Delete4PurchasePlan').is(':checked'),
						'deletePurchasePlan':$('#deletePurchasePlan').is(':checked'),
						
						'viewSellPlan':$('#viewSellPlan').is(':checked'),
						'addSellPlan':$('#addSellPlan').is(':checked'),
						'alterSellPlan':$('#alterSellPlan').is(':checked'),
						'setStatus2Delete4SellPlan':$('#setStatus2Delete4SellPlan').is(':checked'),
						'deleteSellPlan':$('#deleteSellPlan').is(':checked')
						
					},
					success:function(data){
						if(data.success){
							$.messager.show({title:"提示",msg:"保存成功"});
							$('#role').datagrid('load');
						}
					}
				});
			}
		});
	}
</script>
</head>
<body>
	<div id="cc" class="easyui-layout" style="width:100%;height:98.99%;">
    	<div id="role" data-options="region:'west',split:true" style="width:21%;"></div>
    	<div id="permission" data-options="region:'center',">
    		<form id="roleNameForm">
    			<table>
    				<tr>
    					<td><label>角色名称：</label></td>
    					<td><input id="roleName" class="easyui-textbox" data-options="required:true"></td>
    					<td><c:if test="${permission.alterRole||permission.addRole}">
							<a class="easyui-linkbutton" iconCls="icon-save" onclick="save()">保存</a>
							</c:if>
						</td>
    				</tr>
    			</table>
    		</form>
    		<form id="permissionForm">
    			<table>
    				<tr>
    					<td><input id="roleId" name="roleId" class="easyui-textbox" type="hidden"/>
    				</tr>
    				<tr class="permission-row">
    					<td class="permission-label"><label>用户权限</label></td>
    					<td><input type="checkbox" id="viewUser" name="viewUser" value="1" <c:if test="${!permission.viewUser }">disabled="disabled"</c:if> >查看 </td>
    					<td><input type="checkbox" id="addUser" name="addUser" value="1" <c:if test="${!permission.addUser }">disabled="disabled"</c:if> >添加 </td>
    					<td><input type="checkbox" id="alterPassword"  name="alterPassword" value="1" <c:if test="${!permission.alterPassword }">disabled="disabled"</c:if> >修改密码 </td>
    					<td><input type="checkbox" id="authorize"  name="authorize" value="1" <c:if test="${!permission.authorize }">disabled="disabled"</c:if> >用户授权 </td>
    					<td><input type="checkbox" id="linkEmployee"  name="linkEmployee" value="1" <c:if test="${!permission.linkEmployee }">disabled="disabled"</c:if> >关联员工 </td>
    					<td><input type="checkbox" id="setStatus4User" name="setStatus4User" value="1" <c:if test="${!permission.setStatus4User }">disabled="disabled"</c:if> >改变状态 </td>
    					<td><input type="checkbox" id="deleteUser" name="deleteUser" value="1" <c:if test="${!permission.deleteUser }">disabled="disabled"</c:if> >删除 </td>
    				</tr>
    				<tr class="permission-row">
    					<td class="permission-label"><label>员工权限</label></td>
    					<td><input type="checkbox" id="viewEmployee" name="viewEmployee" value="1" <c:if test="${!permission.viewEmployee }">disabled="disabled"</c:if> >查看 </td>
    					<td><input type="checkbox" id="addEmployee" name="addEmployee" value="1" <c:if test="${!permission.addEmployee }">disabled="disabled"</c:if> >添加 </td>
    					<td><input type="checkbox" id="alterEmployee" name="alterEmployee" value="1" <c:if test="${!permission.alterEmployee }">disabled="disabled"</c:if> >修改</td>
    					<td><input type="checkbox" id="setStatus4Employee" name="setStatus4Employee" value="1" <c:if test="${!permission.setStatus4Employee }">disabled="disabled"</c:if> >改变状态</td>
    					<td><input type="checkbox" id="deleteEmployee" name="deleteEmployee" value="1" <c:if test="${!permission.deleteEmployee }">disabled="disabled"</c:if> >删除 </td>
    				</tr>
    				<tr class="permission-row">
    					<td class="permission-label"><label>部门权限</label></td>
    					<td><input type="checkbox" id="viewDepartment" name="viewDepartment" value="1" <c:if test="${!permission.viewDepartment }">disabled="disabled"</c:if> >查看 </td>
    					<td><input type="checkbox" id="addDepartment" name="addDepartment" value="1" <c:if test="${!permission.addDepartment }">disabled="disabled"</c:if> >添加 </td>
    					<td><input type="checkbox" id="alterDepartment" name="alterDepartment" value="1" <c:if test="${!permission.alterDepartment }">disabled="disabled"</c:if> >修改</td>
    					<td><input type="checkbox" id="setStatus2Delete4Department" name="setStatus2Delete4Department" value="1" <c:if test="${!permission.setStatus2Delete4Department }">disabled="disabled"</c:if> >改变状态</td>
    					<td><input type="checkbox" id="deleteDepartment" name="deleteDepartment"  value="1" <c:if test="${!permission.deleteDepartment }">disabled="disabled"</c:if> >删除 </td>
    				</tr>
    				<tr class="permission-row">
    					<td class="permission-label"><label>菜单权限</label></td>
    					<td><input type="checkbox" id="viewMenu" name="viewMenu" value="1" <c:if test="${!permission.viewMenu }">disabled="disabled"</c:if> >查看 </td>
    					<td><input type="checkbox" id="setStatus4Menu" name="setStatus4Menu" value="1" <c:if test="${!permission.setStatus4Menu }">disabled="disabled"</c:if> >改变状态</td>
    				</tr>
    				<tr class="permission-row">
    					<td class="permission-label"><label>角色权限</label></td>
    					<td><input type="checkbox" id="viewRole" name="viewRole" value="1" <c:if test="${!permission.viewRole }">disabled="disabled"</c:if> >查看 </td>
    					<td><input type="checkbox" id="addRole" name="addRole" value="1" <c:if test="${!permission.addRole }">disabled="disabled"</c:if> >添加 </td>
    					<td><input type="checkbox" id="alterRole" name="alterRole" value="1" <c:if test="${!permission.alterRole }">disabled="disabled"</c:if> >修改</td>
    					<td><input type="checkbox" id="setStatus4Role" name="setStatus4Role" value="1" <c:if test="${!permission.setStatus4Role }">disabled="disabled"</c:if> >改变状态</td>
    					<td><input type="checkbox" id="deleteRole" name="deleteRole" value="1" <c:if test="${!permission.deleteRole }">disabled="disabled"</c:if> >删除 </td>
    				</tr>
    				<tr class="permission-row">
    					<td class="permission-label"><label>物品权限</label></td>
    					<td><input type="checkbox" id="viewGoods" name="viewGoods" value="1" <c:if test="${!permission.viewGoods }">disabled="disabled"</c:if> >查看 </td>
    					<td><input type="checkbox" id="addGoods" name="addGoods" value="1" <c:if test="${!permission.addGoods }">disabled="disabled"</c:if> >添加 </td>
    					<td><input type="checkbox" id="alterGoods" name="alterGoods" value="1" <c:if test="${!permission.alterGoods }">disabled="disabled"</c:if> >修改</td>
    					<td><input type="checkbox" id="setStatus2Delete4Goods" name="setStatus2Delete4Goods" value="1" <c:if test="${!permission.setStatus2Delete4Goods }">disabled="disabled"</c:if> >改变状态</td>
    					<td><input type="checkbox" id="deleteGoods" name="deleteGoods"  value="1" <c:if test="${!permission.deleteGoods }">disabled="disabled"</c:if> >删除 </td>
    				</tr>
    				<tr class="permission-row">
    					<td class="permission-label"><label>客户权限</label></td>
    					<td><input type="checkbox" id="viewCustomer" name="viewCustomer" value="1" <c:if test="${!permission.viewCustomer }">disabled="disabled"</c:if> >查看 </td>
    					<td><input type="checkbox" id="addCustomer" name="addCustomer" value="1" <c:if test="${!permission.addCustomer }">disabled="disabled"</c:if> >添加 </td>
    					<td><input type="checkbox" id="alterCustomer" name="alterCustomer" value="1" <c:if test="${!permission.alterCustomer }">disabled="disabled"</c:if> >修改</td>
    					<td><input type="checkbox" id="setStatus2Delete4Customer" name="setStatus2Delete4Customer"  value="1" <c:if test="${!permission.setStatus2Delete4Customer }">disabled="disabled"</c:if> >改变状态</td>
    					<td><input type="checkbox" id="deleteCustomer" name="deleteCustomer" value="1" <c:if test="${!permission.deleteCustomer }">disabled="disabled"</c:if> >删除 </td>
    				</tr>
    				<tr class="permission-row">
    					<td class="permission-label"><label>供应商权限</label></td>
    					<td><input type="checkbox" id="viewSupplier" name="viewSupplier" value="1" <c:if test="${!permission.viewSupplier }">disabled="disabled"</c:if> >查看 </td>
    					<td><input type="checkbox" id="addSupplier" name="addSupplier" value="1" <c:if test="${!permission.addSupplier }">disabled="disabled"</c:if> >添加 </td>
    					<td><input type="checkbox" id="alterSupplier" name="alterSupplier" value="1" <c:if test="${!permission.alterSupplier }">disabled="disabled"</c:if> >修改</td>
    					<td><input type="checkbox" id="setStatus2Delete4Supplier" name="setStatus2Delete4Supplier" value="1" <c:if test="${!permission.setStatus2Delete4Supplier }">disabled="disabled"</c:if> >改变状态</td>
    					<td><input type="checkbox" id="deleteSupplier" name="deleteSupplier" value="1" <c:if test="${!permission.deleteSupplier }">disabled="disabled"</c:if> >删除 </td>
    				</tr>
    				<tr class="permission-row">
    					<td class="permission-label"><label>库存权限</label></td>
    					<td><input type="checkbox" id="viewRepertory" name="viewRepertory" value="1" <c:if test="${!permission.viewRepertory }">disabled="disabled"</c:if> >查看 </td>
    					<td><input type="checkbox" id="instock" name="instock" value="1" <c:if test="${!permission.instock }">disabled="disabled"</c:if> >入库</td>
    					<td><input type="checkbox" id="revokeInstock" name="revokeInstock" value="1" <c:if test="${!permission.revokeInstock }">disabled="disabled"</c:if> >撤销入库</td>
    					<td><input type="checkbox" id="outstock" name="outstock" value="1" <c:if test="${!permission.outstock }">disabled="disabled"</c:if> >出库</td>
    					<td><input type="checkbox" id="revokeOutStock" name="revokeOutStock" value="1" <c:if test="${!permission.revokeOutStock }">disabled="disabled"</c:if> >撤销出库</td>
    				</tr>
    				<tr class="permission-row">
    					<td class="permission-label"><label>库存容量权限</label></td>
    					<td><input type="checkbox" id="setLimit" name="setLimit" value="1" <c:if test="${!permission.setLimit }">disabled="disabled"</c:if> >设置库存容量 </td>
    				</tr>
    				<tr class="permission-row">
    					<td class="permission-label"><label>采购权限</label></td>
    					<td><input type="checkbox" id="viewPurchasePlan" name="viewPurchasePlan" value="1" <c:if test="${!permission.viewPurchasePlan }">disabled="disabled"</c:if> >查看 </td>
    					<td><input type="checkbox" id="addPurchasePlan" name="addPurchasePlan" value="1" <c:if test="${!permission.addPurchasePlan }">disabled="disabled"</c:if> >添加 </td>
    					<td><input type="checkbox" id="alterPurchasePlan" name="alterPurchasePlan" value="1" <c:if test="${!permission.alterPurchasePlan }">disabled="disabled"</c:if> >修改</td>
    					<td><input type="checkbox" id="setStatus2Delete4PurchasePlan" name="setStatus2Delete4PurchasePlan" value="1" <c:if test="${!permission.setStatus2Delete4PurchasePlan }">disabled="disabled"</c:if> >改变状态</td>
    					<td><input type="checkbox" id="deletePurchasePlan" name="deletePurchasePlan" value="1" <c:if test="${!permission.deletePurchasePlan }">disabled="disabled"</c:if> >删除 </td>
    				</tr>
    				<tr class="permission-row">
    					<td class="permission-label"><label>销售权限</label></td>
    					<td><input type="checkbox" id="viewSellPlan" name="viewSellPlan" value="1" <c:if test="${!permission.viewSellPlan }">disabled="disabled"</c:if> >查看 </td>
    					<td><input type="checkbox" id="addSellPlan" name="addSellPlan" value="1" <c:if test="${!permission.addSellPlan }">disabled="disabled"</c:if> >添加 </td>
    					<td><input type="checkbox" id="alterSellPlan" name="alterSellPlan" value="1" <c:if test="${!permission.alterSellPlan }">disabled="disabled"</c:if> >修改</td>
    					<td><input type="checkbox" id="setStatus2Delete4SellPlan" name="setStatus2Delete4SellPlan" value="1" <c:if test="${!permission.setStatus2Delete4SellPlan }">disabled="disabled"</c:if> >改变状态</td>
    					<td><input type="checkbox" id="deleteSellPlan" name="deleteSellPlan" value="1" <c:if test="${!permission.deleteSellPlan }">disabled="disabled"</c:if> >删除 </td>
    				</tr>
    			</table>
    		</form>
    	</div>
	</div>
	<div id="tool">
		<c:if test="${permission.addRole }">
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="add()">新增角色</a>
		</c:if>
		<c:if test="${permission.setStatus4Role }">
			<a class="easyui-linkbutton" iconCls="icon-edit" onclick="alterStatus()">改变状态</a>
		</c:if>
		<c:if test="${permission.deleteRole }">
			<a class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteRole()">删除角色</a>
		</c:if>
	</div>
</body>
</html>