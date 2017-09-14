package wudeng.entity;

public class Permission {
	/**
	 * 【销售订单】权限
	 */
	private boolean viewSellPlan; //查看销售计划表
	private boolean addSellPlan;	//添加销售计划
	private boolean alterSellPlan; //修改销售计划内容
	private boolean setStatus2Delete4SellPlan; //将销售计划表状态设置为作废(0to3)软删除、发布(0to1)、撤销(1to0)
	private boolean deleteSellPlan; //从数据库中删除销售计划，硬删除。
	/**
	 * 【采购订单】权限
	 */
	private boolean viewPurchasePlan; //查看采购计划表
	private boolean addPurchasePlan;	//添加采购计划
	private boolean alterPurchasePlan; //修改采购计划内容
	private boolean setStatus2Delete4PurchasePlan; //将采购计划表状态设置为作废(0to3)软删除、发布(0to1)、撤销(1to0)
	private boolean deletePurchasePlan; //从数据库中删除采购计划，硬删除。
	/**
	 * 【库存容量】权限
	 */
	private boolean setLimit; //设置物品库存最大、最小值， 临界向 计划人员（销售、采购） 发出提醒
	/**
	 * 【物品管理】
	 */
	private boolean viewGoods;
	private boolean addGoods;
	private boolean alterGoods;
	private boolean setStatus2Delete4Goods;
	private boolean deleteGoods;
	/**
	 * 【供应商管理】
	 */
	private boolean viewSupplier;
	private boolean addSupplier;
	private boolean alterSupplier;
	private boolean setStatus2Delete4Supplier;
	private boolean deleteSupplier;
	/**
	 * 【客户管理】
	 */
	private boolean viewCustomer;
	private boolean addCustomer;
	private boolean alterCustomer;
	private boolean setStatus2Delete4Customer;
	private boolean deleteCustomer;
	/**
	 * 【部门管理】
	 */
	private boolean viewDepartment;
	private boolean addDepartment;
	private boolean alterDepartment;
	private boolean setStatus2Delete4Department;
	private boolean deleteDepartment;
	/**
	 * 【菜单管理】
	 */
	private boolean viewMenu; //findAllMenu()
	private boolean setStatus4Menu;
	/**
	 * 【角色管理】
	 */
	private boolean viewRole;
	private boolean addRole;
	private boolean alterRole;
	private boolean setStatus4Role;
	private boolean deleteRole;
	/**
	 * 【员工管理】
	 */
	private boolean viewEmployee; //(姓名、性别、生日、入职时间、联系方式 、dept_id、role_id、状态 )
	private boolean addEmployee;
	private boolean alterEmployee;
	private boolean setStatus4Employee;
	private boolean deleteEmployee;
	/**
	 * 【用户管理】
	 */
	private boolean viewUser;
	private boolean addUser;
	private boolean alterPassword;
	private boolean authorize; //授权
	private boolean linkEmployee;
	private boolean setStatus4User;
	private boolean deleteUser;
	/**
	 * 【库存管理】
	 */
	private boolean viewRepertory; //查看物品库存
	
	/**
	 * 【出库、出库记录】
	 */
	private boolean viewOutstockNote;
	private boolean outstock; //出库   将销售订单设置为已出库(1to2) 修改库存数量（alterRepertory）
	private boolean revokeOutStock;//撤销出库操作  将销售订单设置为生效(2to1) 修改库存数量（alterRepertory）
	/**
	 * 【入库、入库记录】
	 */
	private boolean viewInstockNote;
	private boolean instock;//入库  将采购订单设置为已入库(1to2) 修改库存数量（alterRepertory）
	private boolean revokeInstock;//撤销出库操作  将采购订单设置为生效(2to1) 修改库存数量（alterRepertory）
	public boolean isViewSellPlan() {
		return viewSellPlan;
	}
	public void setViewSellPlan(boolean viewSellPlan) {
		this.viewSellPlan = viewSellPlan;
	}
	public boolean isAddSellPlan() {
		return addSellPlan;
	}
	public void setAddSellPlan(boolean addSellPlan) {
		this.addSellPlan = addSellPlan;
	}
	public boolean isAlterSellPlan() {
		return alterSellPlan;
	}
	public void setAlterSellPlan(boolean alterSellPlan) {
		this.alterSellPlan = alterSellPlan;
	}
	public boolean isSetStatus2Delete4SellPlan() {
		return setStatus2Delete4SellPlan;
	}
	public void setSetStatus2Delete4SellPlan(boolean setStatus2Delete4SellPlan) {
		this.setStatus2Delete4SellPlan = setStatus2Delete4SellPlan;
	}
	public boolean isDeleteSellPlan() {
		return deleteSellPlan;
	}
	public void setDeleteSellPlan(boolean deleteSellPlan) {
		this.deleteSellPlan = deleteSellPlan;
	}
	public boolean isViewPurchasePlan() {
		return viewPurchasePlan;
	}
	public void setViewPurchasePlan(boolean viewPurchasePlan) {
		this.viewPurchasePlan = viewPurchasePlan;
	}
	public boolean isAddPurchasePlan() {
		return addPurchasePlan;
	}
	public void setAddPurchasePlan(boolean addPurchasePlan) {
		this.addPurchasePlan = addPurchasePlan;
	}
	public boolean isAlterPurchasePlan() {
		return alterPurchasePlan;
	}
	public void setAlterPurchasePlan(boolean alterPurchasePlan) {
		this.alterPurchasePlan = alterPurchasePlan;
	}
	public boolean isSetStatus2Delete4PurchasePlan() {
		return setStatus2Delete4PurchasePlan;
	}
	public void setSetStatus2Delete4PurchasePlan(boolean setStatus2Delete4PurchasePlan) {
		this.setStatus2Delete4PurchasePlan = setStatus2Delete4PurchasePlan;
	}
	public boolean isDeletePurchasePlan() {
		return deletePurchasePlan;
	}
	public void setDeletePurchasePlan(boolean deletePurchasePlan) {
		this.deletePurchasePlan = deletePurchasePlan;
	}
	public boolean isSetLimit() {
		return setLimit;
	}
	public void setSetLimit(boolean setLimit) {
		this.setLimit = setLimit;
	}
	public boolean isViewGoods() {
		return viewGoods;
	}
	public void setViewGoods(boolean viewGoods) {
		this.viewGoods = viewGoods;
	}
	public boolean isAddGoods() {
		return addGoods;
	}
	public void setAddGoods(boolean addGoods) {
		this.addGoods = addGoods;
	}
	public boolean isAlterGoods() {
		return alterGoods;
	}
	public void setAlterGoods(boolean alterGoods) {
		this.alterGoods = alterGoods;
	}
	public boolean isSetStatus2Delete4Goods() {
		return setStatus2Delete4Goods;
	}
	public void setSetStatus2Delete4Goods(boolean setStatus2Delete4Goods) {
		this.setStatus2Delete4Goods = setStatus2Delete4Goods;
	}
	public boolean isDeleteGoods() {
		return deleteGoods;
	}
	public void setDeleteGoods(boolean deleteGoods) {
		this.deleteGoods = deleteGoods;
	}
	public boolean isViewSupplier() {
		return viewSupplier;
	}
	public void setViewSupplier(boolean viewSupplier) {
		this.viewSupplier = viewSupplier;
	}
	public boolean isAddSupplier() {
		return addSupplier;
	}
	public void setAddSupplier(boolean addSupplier) {
		this.addSupplier = addSupplier;
	}
	public boolean isAlterSupplier() {
		return alterSupplier;
	}
	public void setAlterSupplier(boolean alterSupplier) {
		this.alterSupplier = alterSupplier;
	}
	public boolean isSetStatus2Delete4Supplier() {
		return setStatus2Delete4Supplier;
	}
	public void setSetStatus2Delete4Supplier(boolean setStatus2Delete4Supplier) {
		this.setStatus2Delete4Supplier = setStatus2Delete4Supplier;
	}
	public boolean isDeleteSupplier() {
		return deleteSupplier;
	}
	public void setDeleteSupplier(boolean deleteSupplier) {
		this.deleteSupplier = deleteSupplier;
	}
	public boolean isViewCustomer() {
		return viewCustomer;
	}
	public void setViewCustomer(boolean viewCustomer) {
		this.viewCustomer = viewCustomer;
	}
	public boolean isAddCustomer() {
		return addCustomer;
	}
	public void setAddCustomer(boolean addCustomer) {
		this.addCustomer = addCustomer;
	}
	public boolean isAlterCustomer() {
		return alterCustomer;
	}
	public void setAlterCustomer(boolean alterCustomer) {
		this.alterCustomer = alterCustomer;
	}
	public boolean isSetStatus2Delete4Customer() {
		return setStatus2Delete4Customer;
	}
	public void setSetStatus2Delete4Customer(boolean setStatus2Delete4Customer) {
		this.setStatus2Delete4Customer = setStatus2Delete4Customer;
	}
	public boolean isDeleteCustomer() {
		return deleteCustomer;
	}
	public void setDeleteCustomer(boolean deleteCustomer) {
		this.deleteCustomer = deleteCustomer;
	}
	public boolean isViewDepartment() {
		return viewDepartment;
	}
	public void setViewDepartment(boolean viewDepartment) {
		this.viewDepartment = viewDepartment;
	}
	public boolean isAddDepartment() {
		return addDepartment;
	}
	public void setAddDepartment(boolean addDepartment) {
		this.addDepartment = addDepartment;
	}
	public boolean isAlterDepartment() {
		return alterDepartment;
	}
	public void setAlterDepartment(boolean alterDepartment) {
		this.alterDepartment = alterDepartment;
	}
	public boolean isSetStatus2Delete4Department() {
		return setStatus2Delete4Department;
	}
	public void setSetStatus2Delete4Department(boolean setStatus2Delete4Department) {
		this.setStatus2Delete4Department = setStatus2Delete4Department;
	}
	public boolean isDeleteDepartment() {
		return deleteDepartment;
	}
	public void setDeleteDepartment(boolean deleteDepartment) {
		this.deleteDepartment = deleteDepartment;
	}
	public boolean isViewMenu() {
		return viewMenu;
	}
	public void setViewMenu(boolean viewMenu) {
		this.viewMenu = viewMenu;
	}
	public boolean isSetStatus4Menu() {
		return setStatus4Menu;
	}
	public void setSetStatus4Menu(boolean setStatus4Menu) {
		this.setStatus4Menu = setStatus4Menu;
	}
	public boolean isViewRole() {
		return viewRole;
	}
	public void setViewRole(boolean viewRole) {
		this.viewRole = viewRole;
	}
	public boolean isAddRole() {
		return addRole;
	}
	public void setAddRole(boolean addRole) {
		this.addRole = addRole;
	}
	public boolean isAlterRole() {
		return alterRole;
	}
	public void setAlterRole(boolean alterRole) {
		this.alterRole = alterRole;
	}
	public boolean isSetStatus4Role() {
		return setStatus4Role;
	}
	public void setSetStatus4Role(boolean setStatus4Role) {
		this.setStatus4Role = setStatus4Role;
	}
	public boolean isDeleteRole() {
		return deleteRole;
	}
	public void setDeleteRole(boolean deleteRole) {
		this.deleteRole = deleteRole;
	}
	public boolean isViewEmployee() {
		return viewEmployee;
	}
	public void setViewEmployee(boolean viewEmployee) {
		this.viewEmployee = viewEmployee;
	}
	public boolean isAddEmployee() {
		return addEmployee;
	}
	public void setAddEmployee(boolean addEmployee) {
		this.addEmployee = addEmployee;
	}
	public boolean isAlterEmployee() {
		return alterEmployee;
	}
	public void setAlterEmployee(boolean alterEmployee) {
		this.alterEmployee = alterEmployee;
	}
	public boolean isSetStatus4Employee() {
		return setStatus4Employee;
	}
	public void setSetStatus4Employee(boolean setStatus4Employee) {
		this.setStatus4Employee = setStatus4Employee;
	}
	public boolean isDeleteEmployee() {
		return deleteEmployee;
	}
	public void setDeleteEmployee(boolean deleteEmployee) {
		this.deleteEmployee = deleteEmployee;
	}
	public boolean isViewUser() {
		return viewUser;
	}
	public void setViewUser(boolean viewUser) {
		this.viewUser = viewUser;
	}
	public boolean isAddUser() {
		return addUser;
	}
	public void setAddUser(boolean addUser) {
		this.addUser = addUser;
	}
	public boolean isSetStatus4User() {
		return setStatus4User;
	}
	public void setSetStatus4User(boolean setStatus4User) {
		this.setStatus4User = setStatus4User;
	}
	public boolean isDeleteUser() {
		return deleteUser;
	}
	public void setDeleteUser(boolean deleteUser) {
		this.deleteUser = deleteUser;
	}
	public boolean isViewRepertory() {
		return viewRepertory;
	}
	public void setViewRepertory(boolean viewRepertory) {
		this.viewRepertory = viewRepertory;
	}
	public boolean isViewOutstockNote() {
		return viewOutstockNote;
	}
	public void setViewOutstockNote(boolean viewOutstockNote) {
		this.viewOutstockNote = viewOutstockNote;
	}
	public boolean isOutstock() {
		return outstock;
	}
	public void setOutstock(boolean outstock) {
		this.outstock = outstock;
	}
	public boolean isRevokeOutStock() {
		return revokeOutStock;
	}
	public void setRevokeOutStock(boolean revokeOutStock) {
		this.revokeOutStock = revokeOutStock;
	}
	public boolean isViewInstockNote() {
		return viewInstockNote;
	}
	public void setViewInstockNote(boolean viewInstockNote) {
		this.viewInstockNote = viewInstockNote;
	}
	public boolean isInstock() {
		return instock;
	}
	public void setInstock(boolean instock) {
		this.instock = instock;
	}
	public boolean isRevokeInstock() {
		return revokeInstock;
	}
	public void setRevokeInstock(boolean revokeInstock) {
		this.revokeInstock = revokeInstock;
	}
	public boolean isAlterPassword() {
		return alterPassword;
	}
	public void setAlterPassword(boolean alterPassword) {
		this.alterPassword = alterPassword;
	}
	public boolean isLinkEmployee() {
		return linkEmployee;
	}
	public void setLinkEmployee(boolean linkEmployee) {
		this.linkEmployee = linkEmployee;
	}
	public boolean isAuthorize() {
		return authorize;
	}
	public void setAuthorize(boolean authorize) {
		this.authorize = authorize;
	}
	
	
}
