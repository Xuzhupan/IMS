package wudeng.entity;


public class Role {
	private Integer id;
	private String name;
	private Integer userPermission; //用户     权限
	private Integer employeePermission;// 员工    权限
	private Integer rolePermission; //角色权限
	private Integer departmentPermission; //部门 权限
	private Integer goodsPermission; //物品    权限
	private Integer customerPermission;//客户  权限
	private Integer supplierPermission; //供应商  权限
	private Integer purchasePlanPermission;// 采购计划   权限
	private Integer sellPlanPermission;//销售计划    权限
	private Integer repertoryPermission;//库存       权限
	private Integer repertoryLimitPermission; // 库存容量        权限
	private Integer instockNotePermission; //入库     权限
	private Integer outstockNotePermission; //出库     权限
	private Integer menuPermission;//菜单    权限
	private Integer status;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getUserPermission() {
		return userPermission;
	}
	public void setUserPermission(Integer userPermission) {
		this.userPermission = userPermission;
	}
	public Integer getEmployeePermission() {
		return employeePermission;
	}
	public void setEmployeePermission(Integer employeePermission) {
		this.employeePermission = employeePermission;
	}
	public Integer getDepartmentPermission() {
		return departmentPermission;
	}
	public void setDepartmentPermission(Integer departmentPermission) {
		this.departmentPermission = departmentPermission;
	}
	public Integer getGoodsPermission() {
		return goodsPermission;
	}
	public void setGoodsPermission(Integer goodsPermission) {
		this.goodsPermission = goodsPermission;
	}
	public Integer getCustomerPermission() {
		return customerPermission;
	}
	public void setCustomerPermission(Integer customerPermission) {
		this.customerPermission = customerPermission;
	}
	public Integer getSupplierPermission() {
		return supplierPermission;
	}
	public void setSupplierPermission(Integer supplierPermission) {
		this.supplierPermission = supplierPermission;
	}
	public Integer getPurchasePlanPermission() {
		return purchasePlanPermission;
	}
	public void setPurchasePlanPermission(Integer purchasePlanPermission) {
		this.purchasePlanPermission = purchasePlanPermission;
	}
	public Integer getSellPlanPermission() {
		return sellPlanPermission;
	}
	public void setSellPlanPermission(Integer sellPlanPermission) {
		this.sellPlanPermission = sellPlanPermission;
	}
	public Integer getRepertoryPermission() {
		return repertoryPermission;
	}
	public void setRepertoryPermission(Integer repertoryPermission) {
		this.repertoryPermission = repertoryPermission;
	}
	public Integer getRepertoryLimitPermission() {
		return repertoryLimitPermission;
	}
	public void setRepertoryLimitPermission(Integer repertoryLimitPermission) {
		this.repertoryLimitPermission = repertoryLimitPermission;
	}
	public Integer getInstockNotePermission() {
		return instockNotePermission;
	}
	public void setInstockNotePermission(Integer instockNotePermission) {
		this.instockNotePermission = instockNotePermission;
	}
	public Integer getOutstockNotePermission() {
		return outstockNotePermission;
	}
	public void setOutstockNotePermission(Integer outstockNotePermission) {
		this.outstockNotePermission = outstockNotePermission;
	}
	public Integer getMenuPermission() {
		return menuPermission;
	}
	public void setMenuPermission(Integer menuPermission) {
		this.menuPermission = menuPermission;
	}
	public Integer getRolePermission() {
		return rolePermission;
	}
	public void setRolePermission(Integer rolePermission) {
		this.rolePermission = rolePermission;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
