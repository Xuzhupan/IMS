package wudeng.util;

import wudeng.entity.Menu;
import wudeng.entity.Permission;
import wudeng.service.BaseService;
import wudeng.service.IndexService;
public class MenuFactory {
	public static final int SYS_MENU_ID = 1 ; //系统管理id （父菜单）
	public static final int USER_MENU_ID = 2 ;//用户管理id （菜单）
	public static final int EMPLOYEE_MENU_ID = 3 ;
	public static final int DEPARTMENT_MENU_ID = 4 ;
	public static final int MENU_MENU_ID = 5 ;
	public static final int ROLE_MENU_ID = 6 ;
	
	public static final int OPERATION_MENU_ID = 7; //功能菜单(父菜单)
	public static final int GOODS_MENU_ID = 8 ;
	public static final int CUSTOMER_MENU_ID= 9;
	public static final int SUPPLIER_MENU_ID =10;
	public static final int REPERTORY_MENU_ID =11;
	public static final int REPERTORY_LIMIT_MENU_ID = 12;
	public static final int PURCHASE_PLAN_MENU_ID =13;
	public static final int SELL_PLAN_MENU_ID =14 ;
	public static final int OUTSTOCK_MENU_ID =15;
	public static final int INSTOCK_MENU_ID =16 ;
	
	public static String CreateMenuByPermission(Permission p,BaseService<Menu> service){
		StringBuffer s = new StringBuffer();
		//Begin
		s.append("[");
		if(getStatus(service, SYS_MENU_ID)&&( p.isViewUser()||p.isViewEmployee()||p.isViewDepartment()||p.isViewMenu()||p.isViewRole() )){
			//Start
			s.append("{")
				.append("\"name\":").append("\"").append(service.findById(new Menu(),SYS_MENU_ID).getName()).append("\"").append(",")
				.append("\"children\":[");
				if(p.isViewUser()&& getStatus(service, USER_MENU_ID)){
					s.append(MenuToJson(service.findById(new Menu(),USER_MENU_ID))).append(",");
				}
				if(p.isViewEmployee()&& getStatus(service, EMPLOYEE_MENU_ID)){
					s.append(MenuToJson(service.findById(new Menu(),EMPLOYEE_MENU_ID))).append(",");
				}
				if(p.isViewDepartment()&& getStatus(service, DEPARTMENT_MENU_ID)){
					s.append(MenuToJson(service.findById(new Menu(),DEPARTMENT_MENU_ID))).append(",");
				}
				if(p.isViewMenu()&& getStatus(service, MENU_MENU_ID)){
					s.append(MenuToJson(service.findById(new Menu(),MENU_MENU_ID))).append(",");
				}
				if(p.isViewRole()&& getStatus(service, ROLE_MENU_ID)){
					s.append(MenuToJson(service.findById(new Menu(),ROLE_MENU_ID))).append(",");
				}
				s.deleteCharAt(s.length()-1);//去最后的逗号
				//End start
				s.append("]")
			.append("}").append(",");
		}
		if(!getStatus(service, SYS_MENU_ID)){if(p.isViewMenu()){ //菜单管理不能设置为不可用
			s.append("{")
			.append("\"name\":").append("\"").append(service.findById(new Menu(),SYS_MENU_ID).getName()).append("\"").append(",")
			.append("\"children\":[");
			if(p.isViewMenu()){
				s.append(MenuToJson(service.findById(new Menu(),MENU_MENU_ID))).append(",");
			}
			s.deleteCharAt(s.length()-1);//去最后的逗号
			//End start
			s.append("]")
		.append("}").append(",");
		}
		}
		if(getStatus(service, OPERATION_MENU_ID)&&(p.isViewGoods()||p.isViewCustomer()||p.isViewSupplier()||p.isViewRepertory()||
				p.isSetLimit()||p.isViewPurchasePlan()|p.isViewSellPlan()||p.isViewOutstockNote()||p.isViewInstockNote())){
			//start
			s.append("{")
				.append("\"name\":").append("\"").append(service.findById(new Menu(),OPERATION_MENU_ID).getName()).append("\"").append(",")
				.append("\"children\":[");
				if(p.isViewGoods()&& getStatus(service, GOODS_MENU_ID)){
					s.append(MenuToJson(service.findById(new Menu(),GOODS_MENU_ID))).append(",");
				}
				if(p.isViewCustomer()&& getStatus(service, CUSTOMER_MENU_ID)){
					s.append(MenuToJson(service.findById(new Menu(),CUSTOMER_MENU_ID))).append(",");
				}
				if(p.isViewSupplier()&& getStatus(service, SUPPLIER_MENU_ID)){
					s.append(MenuToJson(service.findById(new Menu(),SUPPLIER_MENU_ID))).append(",");
				}
				if(p.isViewRepertory()&& getStatus(service, REPERTORY_MENU_ID)){
					s.append(MenuToJson(service.findById(new Menu(),REPERTORY_MENU_ID))).append(",");
				}
				if(p.isSetLimit()&& getStatus(service, REPERTORY_LIMIT_MENU_ID)){
					s.append(MenuToJson(service.findById(new Menu(),REPERTORY_LIMIT_MENU_ID))).append(",");
				}
				if(p.isViewPurchasePlan()&& getStatus(service, PURCHASE_PLAN_MENU_ID)){
					s.append(MenuToJson(service.findById(new Menu(),PURCHASE_PLAN_MENU_ID))).append(",");
				}
				if(p.isViewSellPlan()&& getStatus(service, SELL_PLAN_MENU_ID)){
					s.append(MenuToJson(service.findById(new Menu(),SELL_PLAN_MENU_ID))).append(",");
				}
				if(p.isViewOutstockNote()&& getStatus(service, OUTSTOCK_MENU_ID)){
					s.append(MenuToJson(service.findById(new Menu(),OUTSTOCK_MENU_ID))).append(",");
				}
				if(p.isViewInstockNote()&& getStatus(service, INSTOCK_MENU_ID)){
					s.append(MenuToJson(service.findById(new Menu(),INSTOCK_MENU_ID))).append(",");
				}
				s.deleteCharAt(s.length()-1);//去最后的逗号
				s.append("]");
		   
			//end start
			s.append("}")
				.append(",");
		}
		
		
		s.deleteCharAt(s.length()-1);//去最后的逗号
		//End
		s.append("]");
		return s.toString();
		
	}
	
	private static String MenuToJson(Menu m){
		StringBuffer s = new StringBuffer();
		s.append("{")
			.append("\"id\":").append("\"").append(m.getId()).append("\"").append(",")
			.append("\"name\":").append("\"").append(m.getName()).append("\"").append(",")
			.append("\"url\":").append("\"").append(m.getUrl()).append("\"").append(",")
			.append("\"icon\":").append("\"").append(m.getIcon()).append("\"").append(",")
			.append("\"parentId\":").append("\"").append(m.getParentId()).append("\"").append(",")
			.append("\"level\":").append("\"").append(m.getLevel()).append("\"").append(",")
			.append("\"status\":").append("\"").append(m.getStatus()).append("\"")
			
		.append("}");
		
		return s.toString();
	}
	
	private static boolean getStatus(BaseService<Menu> service,int menuId){
		Menu menu = service.get(Menu.class, menuId);
		if(menu.getStatus()==1){
			return true;
		}
		return false;
	}
}
