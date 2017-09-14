package wudeng.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;

import wudeng.entity.Permission;
import wudeng.entity.Role;
import wudeng.service.ObjectService;
import wudeng.service.PermissionService;
import wudeng.util.PermissionFactory;
	
@Controller
public class PermissionAction extends BaseAction implements ModelDriven<Permission>{
	private Integer roleId;
	private String roleName;
	
	private Permission permission = new Permission();
	@Autowired
	private ObjectService service;
	/*public void getPermission(){
		Permission p = service.getPermission(roleId);
		this.getRequest().getSession().setAttribute("permission", p);
	}*/
	
	public void save(){
		Role role;
		if(roleId!=null){
			role = (Role) service.get(Role.class, roleId);
		}else{
			role = new Role();
			role.setStatus(1);
		}
		role.setName(roleName);
		//权限过滤
		this.permissionFilter(role, permission);
		//权限保存
		PermissionFactory.release(role, permission);
		service.saveOrUpdate(role);
		JSONObject j = new JSONObject();
		j.put("success", true);
		this.write(j.toJSONString());
		
	}
	public Permission getModel() {
		return this.permission;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	//当前角色没有的权限不能被设置，过滤掉
	private void permissionFilter(Role role,Permission permission) {
		//获得角色权限
		Permission rolePermission = new Permission();
		PermissionFactory.init(role, rolePermission);
	
		/**
		 * 【销售订单】权限
		 */
		if(!rolePermission.isViewSellPlan() ) {
			permission.setViewSellPlan(false);
		}
		if(!rolePermission.isAddSellPlan() ) {
			permission.setAddSellPlan(false);
		}
		if(!rolePermission.isAlterSellPlan() ) {
			permission.setAlterSellPlan(false);
		}
		if(!rolePermission.isSetStatus2Delete4SellPlan() ) {
			permission.setSetStatus2Delete4SellPlan(false);
		}
		if(!rolePermission.isDeleteSellPlan() ) {
			permission.setDeleteSellPlan(false);
		}
		/**
		 * 【采购订单】权限
		 */
		if(!rolePermission.isViewPurchasePlan() ) {
			permission.setViewPurchasePlan(false);
		}
		if(!rolePermission.isAddPurchasePlan() ) {
			permission.setAddPurchasePlan(false);
		}
		if(!rolePermission.isAlterPurchasePlan() ) {
			permission.setAlterPurchasePlan(false);
		}
		if(!rolePermission.isSetStatus2Delete4PurchasePlan() ) {
			permission.setSetStatus2Delete4PurchasePlan(false);
		}
		if(!rolePermission.isDeletePurchasePlan() ) {
			permission.setDeletePurchasePlan(false);
		}
		/**
		 * 【库存容量】权限
		 */
		if(!rolePermission.isSetLimit() ) {
			permission.setSetLimit(false);
		}
		/**
		 * 【物品管理】
		 */
		if(!rolePermission.isViewGoods() ) {
			permission.setViewGoods(false);
		}
		if(!rolePermission.isAddGoods() ) {
			permission.setAddGoods(false);
		}
		if(!rolePermission.isAlterGoods() ) {
			permission.setAlterGoods(false);
		}
		if(!rolePermission.isSetStatus2Delete4Goods() ) {
			permission.setSetStatus2Delete4Goods(false);
		}
		if(!rolePermission.isDeleteGoods() ) {
			permission.setDeleteGoods(false);
		}
		/**
		 * 【供应商管理】
		 */
		if(!rolePermission.isViewSupplier() ) {
			permission.setViewSupplier(false);
		}
		if(!rolePermission.isAddSupplier() ) {
			permission.setAddSupplier(false);
		}
		if(!rolePermission.isAlterSupplier() ) {
			permission.setAlterSupplier(false);
		}
		if(!rolePermission.isSetStatus2Delete4Supplier() ) {
			permission.setSetStatus2Delete4Supplier(false);
		}
		if(!rolePermission.isDeleteSupplier() ) {
			permission.setDeleteSupplier(false);
		}
		/**
		 * 【客户管理】
		 */
		if(!rolePermission.isViewCustomer() ) {
			permission.setViewCustomer(false);
		}
		if(!rolePermission.isAddCustomer() ) {
			permission.setAddCustomer(false);
		}
		if(!rolePermission.isAlterCustomer() ) {
			permission.setAlterCustomer(false);
		}
		if(!rolePermission.isSetStatus2Delete4Customer() ) {
			permission.setSetStatus2Delete4Customer(false);
		}
		if(!rolePermission.isDeleteCustomer() ) {
			permission.setDeleteCustomer(false);
		}
		/**
		 * 【部门管理】
		 */
		if(!rolePermission.isViewDepartment() ) {
			permission.setViewDepartment(false);
		}
		if(!rolePermission.isAddDepartment() ) {
			permission.setAddDepartment(false);
		}
		if(!rolePermission.isAlterDepartment() ) {
			permission.setAlterDepartment(false);
		}
		if(!rolePermission.isSetStatus2Delete4Department() ) {
			permission.setSetStatus2Delete4Department(false);
		}
		if(!rolePermission.isDeleteDepartment() ) {
			permission.setDeleteDepartment(false);
		}
		/**
		 * 【菜单管理】
		 */
		if(!rolePermission.isViewMenu() ) {
			permission.setViewMenu(false);
		}
		if(!rolePermission.isSetStatus4Menu() ) {
			permission.setSetStatus4Menu(false);
		}
		/**
		 * 【角色管理】
		 */
		if(!rolePermission.isViewRole() ) {
			permission.setViewRole(false);
		}
		if(!rolePermission.isAddRole() ) {
			permission.setAddRole(false);
		}
		if(!rolePermission.isAlterRole() ) {
			permission.setAlterRole(false);
		}
		if(!rolePermission.isSetStatus4Role() ) {
			permission.setSetStatus4Role(false);
		}
		if(!rolePermission.isDeleteRole() ) {
			permission.setDeleteRole(false);
		}
		/**
		 * 【员工管理】
		 */
		if(!rolePermission.isViewEmployee() ) {
			permission.setViewEmployee(false);
		}
		if(!rolePermission.isAddEmployee() ) {
			permission.setAddEmployee(false);
		}
		if(!rolePermission.isAlterEmployee() ) {
			permission.setAlterEmployee(false);
		}
		if(!rolePermission.isSetStatus4Employee() ) {
			permission.setSetStatus4Employee(false);
		}
		if(!rolePermission.isDeleteEmployee() ) {
			permission.setDeleteEmployee(false);
		}
		/**
		 * 【用户管理】
		 */
		if(!rolePermission.isViewUser() ) {
			permission.setViewUser(false);
		}
		if(!rolePermission.isAddUser() ) {
			permission.setAddUser(false);
		}
		if(!rolePermission.isAlterPassword() ) {
			permission.setAlterPassword(false);
		}
		if(!rolePermission.isAuthorize() ) {
			permission.setAuthorize(false);
		}
		if(!rolePermission.isLinkEmployee() ) {
			permission.setLinkEmployee(false);
		}
		if(!rolePermission.isSetStatus4User() ) {
			permission.setSetStatus4User(false);
		}
		if(!rolePermission.isDeleteUser() ) {
			permission.setDeleteUser(false);
		}
		/**
		 * 【库存管理】
		 */
		if(!rolePermission.isViewRepertory() ) {
			permission.setViewRepertory(false);
		}
		
		/**
		 * 【出库、出库记录】
		 */
		if(!rolePermission.isViewOutstockNote() ) {
			permission.setViewOutstockNote(false);
		}
		if(!rolePermission.isOutstock() ) {
			permission.setOutstock(false);
		}
		if(!rolePermission.isRevokeOutStock() ) {
			permission.setRevokeOutStock(false);
		}
		/**
		 * 【入库、入库记录】
		 */
		if(!rolePermission.isViewInstockNote() ) {
			permission.setViewInstockNote(false);
		}
		if(!rolePermission.isInstock() ) {
			permission.setInstock(false);
		}
		if(!rolePermission.isRevokeInstock() ) {
			permission.setRevokeInstock(false);
		}
	}
	
}
