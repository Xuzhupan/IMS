package wudeng.util;

import wudeng.entity.Permission;
import wudeng.entity.Role;

public class PermissionFactory {

	public static void init(Role role,Permission p){
		/** 用户权限初始化   */
		initUser(role.getUserPermission(),p);
		/** 员工权限初始化   */
		initEmployee(role.getEmployeePermission(),p);
		/** 角色权限初始化   */
		initRole(role.getRolePermission(),p);
		/** 部门权限初始化   */
		initDepartment(role.getDepartmentPermission(),p);
		/** 物品权限初始化   */
		initGoods(role.getGoodsPermission(),p);
		/** 客户权限初始化   */
		initCustomer(role.getCustomerPermission(),p);
		/** 供应商权限初始化   */
		initSupplier(role.getSupplierPermission(),p);
		/** 采购计划权限初始化   */
		initPurchasePlan(role.getPurchasePlanPermission(),p);
		/** 销售计划权限初始化   */
		initSellPlan(role.getSellPlanPermission(),p);
		/** 库存权限初始化   */
		initRepertory(role.getRepertoryPermission(),p);
		/** 库存容量权限初始化   */
		initRepertoryLimit(role.getRepertoryLimitPermission(),p);
		/** 入库记录权限初始化   */
		initInstockNote(role.getInstockNotePermission(),p);
		/** 出库记录权限初始化   */
		initOutstockNote(role.getOutstockNotePermission(),p);
		/** 菜单权限初始化   */
		initMenu(role.getMenuPermission(),p);
	}
	/**
	 * 用户权限初始化
	 * @param c
	 * @param p
	 */
	private static void initUser(int c,Permission p){
		p.setViewUser(get(c, 0)==0?false:true); //0号位 ，viewUser；
		p.setAddUser(get(c,1)==0?false:true);	//1号位，addUser
		p.setAlterPassword(get(c,2)==0?false:true);
		p.setAuthorize(get(c,3)==0?false:true);
		p.setLinkEmployee(get(c,4)==0?false:true); 
		p.setSetStatus4User(get(c,5)==0?false:true);
		p.setDeleteUser(get(c,6)==0?false:true);
		if(p.isAddUser()||p.isAlterPassword()||p.isAuthorize()||p.isLinkEmployee()||p.isSetStatus4User()||p.isDeleteUser()){ //如果可以 添加、修改、改变状态、删除 用户 ，就可以查看用户
			p.setViewUser(true);
		}
	}
	/**
	 * 员工权限初始化
	 */
	private static void initEmployee(int c,Permission p){
		p.setViewEmployee(get(c,0)==0?false:true); //0号位 ，viewEmployee；
		p.setAddEmployee(get(c,1)==0?false:true);	//1号位，addEmployee
		p.setAlterEmployee(get(c,2)==0?false:true); 
		p.setSetStatus4Employee(get(c,3)==0?false:true);
		p.setDeleteEmployee(get(c,4)==0?false:true);
		if(p.isAddEmployee()||p.isAlterEmployee()||p.isSetStatus4Employee()||p.isDeleteEmployee()){ 
			p.setViewEmployee(true);
		}
	}
	/**
	 * 角色权限初始化
	 */
	private static void initRole(int c,Permission p){
		p.setViewRole(get(c,0)==0?false:true); //0号位 ，viewRole；
		p.setAddRole(get(c,1)==0?false:true);	//1号位，addRole
		p.setAlterRole(get(c,2)==0?false:true); 
		p.setSetStatus4Role(get(c,3)==0?false:true);
		p.setDeleteRole(get(c,4)==0?false:true);
		if(p.isAddRole()||p.isAlterRole()||p.isSetStatus4Role()||p.isDeleteRole()){ 
			p.setViewRole(true);
		}
	}
	/**
	 * 部门权限初始化
	 */
	private static void initDepartment(int c,Permission p){
		p.setViewDepartment(get(c,0)==0?false:true); //0号位 ，viewDepartment；
		p.setAddDepartment(get(c,1)==0?false:true);	//1号位，addDepartment
		p.setAlterDepartment(get(c,2)==0?false:true); 
		p.setSetStatus2Delete4Department(get(c,3)==0?false:true);
		p.setDeleteDepartment(get(c,4)==0?false:true);
		if(p.isAddDepartment()||p.isAlterDepartment()||p.isSetStatus2Delete4Department()||p.isDeleteDepartment()){ 
			p.setViewDepartment(true);
		}
	}
	/**
	 * 物品权限初始化
	 */
	private static void initGoods(int c,Permission p){
		p.setViewGoods(get(c,0)==0?false:true); //0号位 ，viewGoods；
		p.setAddGoods(get(c,1)==0?false:true);	//1号位，addGoods
		p.setAlterGoods(get(c,2)==0?false:true); 
		p.setSetStatus2Delete4Goods(get(c,3)==0?false:true);
		p.setDeleteGoods(get(c,4)==0?false:true);
		if(p.isAddGoods()||p.isAlterGoods()||p.isSetStatus2Delete4Goods()||p.isDeleteGoods()){ 
			p.setViewGoods(true);
		}
	}
	/**
	 * 客户权限初始化
	 */
	private static void initCustomer(int c,Permission p){
		p.setViewCustomer(get(c,0)==0?false:true); //0号位 ，viewCustomer；
		p.setAddCustomer(get(c,1)==0?false:true);	//1号位，addCustomer
		p.setAlterCustomer(get(c,2)==0?false:true); 
		p.setSetStatus2Delete4Customer(get(c,3)==0?false:true);
		p.setDeleteCustomer(get(c,4)==0?false:true);
		if(p.isAddCustomer()||p.isAlterCustomer()||p.isSetStatus2Delete4Customer()||p.isDeleteCustomer()){ 
			p.setViewCustomer(true);
		}
	}
	/**
	 * 供应商权限初始化
	 */
	private static void initSupplier(int c,Permission p){
		p.setViewSupplier(get(c,0)==0?false:true); //0号位 ，viewSupplier；
		p.setAddSupplier(get(c,1)==0?false:true);	//1号位，addSupplier
		p.setAlterSupplier(get(c,2)==0?false:true); 
		p.setSetStatus2Delete4Supplier(get(c,3)==0?false:true);
		p.setDeleteSupplier(get(c,4)==0?false:true);
		if(p.isAddSupplier()||p.isAlterSupplier()||p.isSetStatus2Delete4Supplier()||p.isDeleteSupplier()){ 
			p.setViewSupplier(true);
		}
	}
	/**
	 * 采购计划权限初始化
	 */
	private static void initPurchasePlan(int c,Permission p){
		p.setViewPurchasePlan(get(c,0)==0?false:true); //0号位 ，viewPurchasePlan；
		p.setAddPurchasePlan(get(c,1)==0?false:true);	//1号位，addPurchasePlan
		p.setAlterPurchasePlan(get(c,2)==0?false:true); 
		p.setSetStatus2Delete4PurchasePlan(get(c,3)==0?false:true);
		p.setDeletePurchasePlan(get(c,4)==0?false:true);
		if(p.isAddPurchasePlan()||p.isAlterPurchasePlan()||p.isSetStatus2Delete4PurchasePlan()||p.isDeletePurchasePlan()){ 
			p.setViewPurchasePlan(true);
		}
	}
	/**
	 * 销售计划权限初始化
	 */
	private static void initSellPlan(int c,Permission p){
		p.setViewSellPlan(get(c,0)==0?false:true); //0号位 ，viewSellPlan；
		p.setAddSellPlan(get(c,1)==0?false:true);	//1号位，addSellPlan
		p.setAlterSellPlan(get(c,2)==0?false:true); 
		p.setSetStatus2Delete4SellPlan(get(c,3)==0?false:true);
		p.setDeleteSellPlan(get(c,4)==0?false:true);
		if(p.isAddSellPlan()||p.isAlterSellPlan()||p.isSetStatus2Delete4SellPlan()||p.isDeleteSellPlan()){ 
			p.setViewSellPlan(true);
		}
	}
	/**
	 * 库存权限初始化
	 */
	private static void initRepertory(int c,Permission p){
		p.setViewRepertory(get(c,0)==0?false:true);
	}
	/**
	 * 库存容量权限初始化
	 */
	private static void initRepertoryLimit(int c,Permission p){
		p.setSetLimit(get(c,0)==0?false:true);
	}
	/**
	 * 入库权限初始化
	 */
	private static void initInstockNote(int c,Permission p){
		p.setViewInstockNote(get(c,0)==0?false:true);
		p.setInstock(get(c,1)==0?false:true);
		p.setRevokeInstock(get(c,2)==0?false:true);
		if(p.isInstock()||p.isRevokeInstock()){
			p.setViewInstockNote(true);
		}
	}
	/**
	 * 出库权限初始化
	 */
	private static void initOutstockNote(int c,Permission p){
		p.setViewOutstockNote(get(c,0)==0?false:true);
		p.setOutstock(get(c,1)==0?false:true);
		p.setRevokeOutStock(get(c,2)==0?false:true);
		if(p.isOutstock()||p.isRevokeOutStock()){
			p.setViewOutstockNote(true);
		}
	}
	/**
	 * 菜单权限初始化
	 */
	private static void initMenu(int c,Permission p){
		p.setViewMenu(get(c,0)==0?false:true);
		p.setSetStatus4Menu(get(c,1)==0?false:true);
		if(p.isSetStatus4Menu()){
			p.setViewMenu(true);
		}
	}
	
	public static int get(int num, int index)
    {
        return (num & (0x1 << index)) >> index;
    }
	public static void main(String[] args) {
		System.out.print(PermissionFactory.get(7, 0));
		System.out.print(PermissionFactory.get(7, 1));
		System.out.print(PermissionFactory.get(7, 2));
		System.out.print(PermissionFactory.get(7, 3));
	}
	/** 
	 *  将权限boolean值转化为十进制存储到Role中  init的逆过程
	 */
	public static void release(Role role,Permission p){
		/** 用户权限固定   */
		releaseUser(role,p);
		/** 员工权限固定   */
		releaseEmployee(role,p);
		/** 角色权限固定   */
		releaseRole(role,p);
		/** 部门权限固定   */
		releaseDepartment(role,p);
		/** 物品权限固定   */
		releaseGoods(role,p);
		/** 客户权限固定   */
		releaseCustomer(role,p);
		/** 供应商权限固定   */
		releaseSupplier(role,p);
		/** 采购计划权限固定  */
		releasePurchasePlan(role,p);
		/** 销售计划权限固定   */
		releaseSellPlan(role,p);
		/** 库存权限固定   */
		releaseRepertory(role,p);
		/** 库存容量权限固定   */
		releaseRepertoryLimit(role,p);
		/** 入库记录权限固定   */
		releaseInstockNote(role,p);
		/** 出库记录权限固定   */
		releaseOutstockNote(role,p);
		/** 菜单权限固定   */
		releaseMenu(role,p);
	}
	private static void releaseUser(Role role, Permission p) {
		String s ="";
		if(p.isDeleteUser()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isSetStatus4User()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isLinkEmployee()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isAuthorize()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isAlterPassword()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isAddUser()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isViewUser()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		role.setUserPermission(Integer.valueOf(s, 2));
	}
	private static void releaseEmployee(Role role, Permission p) {
		String s ="";
		if(p.isDeleteEmployee()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isSetStatus4Employee()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isAlterEmployee()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isAddEmployee()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isViewEmployee()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		role.setEmployeePermission(Integer.valueOf(s, 2));
		
	}
	private static void releaseRole(Role role, Permission p) {
		String s ="";
		if(p.isDeleteRole()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isSetStatus4Role()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isAlterRole()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isAddRole()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isViewRole()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		role.setRolePermission(Integer.valueOf(s, 2));
		
	}
	private static void releaseDepartment(Role role, Permission p) {
		String s ="";
		if(p.isDeleteDepartment()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isSetStatus2Delete4Department()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isAlterDepartment()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isAddDepartment()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isViewDepartment()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		role.setDepartmentPermission(Integer.valueOf(s, 2));
		
	}
	private static void releaseGoods(Role role, Permission p) {
		String s ="";
		if(p.isDeleteGoods()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isSetStatus2Delete4Goods()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isAlterGoods()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isAddGoods()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isViewGoods()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		role.setGoodsPermission(Integer.valueOf(s, 2));
		
	}
	private static void releaseCustomer(Role role, Permission p) {
		String s ="";
		if(p.isDeleteCustomer()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isSetStatus2Delete4Customer()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isAlterCustomer()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isAddCustomer()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isViewCustomer()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		role.setCustomerPermission(Integer.valueOf(s, 2));
		
	}
	private static void releaseSupplier(Role role, Permission p) {
		String s ="";
		if(p.isDeleteSupplier()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isSetStatus2Delete4Supplier()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isAlterSupplier()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isAddSupplier()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isViewSupplier()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		role.setSupplierPermission(Integer.valueOf(s, 2));
		
	}
	private static void releasePurchasePlan(Role role, Permission p) {
		String s ="";
		if(p.isDeletePurchasePlan()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isSetStatus2Delete4PurchasePlan()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isAlterPurchasePlan()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isAddPurchasePlan()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isViewPurchasePlan()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		role.setPurchasePlanPermission(Integer.valueOf(s, 2));
		
	}
	private static void releaseSellPlan(Role role, Permission p) {
		String s ="";
		if(p.isDeleteSellPlan()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isSetStatus2Delete4SellPlan()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isAlterSellPlan()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isAddSellPlan()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isViewSellPlan()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		role.setSellPlanPermission(Integer.valueOf(s, 2));
		
	}
	private static void releaseRepertory(Role role, Permission p) {
		String s ="";
		if(p.isViewRepertory()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		role.setRepertoryPermission(Integer.valueOf(s, 2));
		
	}
	private static void releaseRepertoryLimit(Role role, Permission p) {
		String s ="";
		if(p.isSetLimit()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		role.setRepertoryLimitPermission(Integer.valueOf(s, 2));
		
	}
	private static void releaseInstockNote(Role role, Permission p) {
		String s ="";
		if(p.isRevokeInstock()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isInstock()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isViewInstockNote()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		role.setInstockNotePermission(Integer.valueOf(s, 2));
		
	}
	private static void releaseOutstockNote(Role role, Permission p) {
		String s ="";
		if(p.isRevokeOutStock()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isOutstock()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isViewOutstockNote()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		role.setOutstockNotePermission(Integer.valueOf(s, 2));
		
	}
	private static void releaseMenu(Role role, Permission p) {
		String s ="";
		if(p.isSetStatus4Menu()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		if(p.isViewMenu()){
			s=s+"1";
		}else{
			s=s+"0";
		}
		role.setMenuPermission(Integer.valueOf(s, 2));
		
	}
	
}
