package wudeng.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;

import Global.StatusGlobal;
import Global.SysGlobal;
import myJson.UserJson;
import wudeng.entity.Employee;
import wudeng.entity.Permission;
import wudeng.entity.Role;
import wudeng.entity.User;
import wudeng.service.ObjectService;
import wudeng.service.UserService;
import wudeng.util.PermissionFactory;
@Controller
public class UserAction extends BaseAction implements ModelDriven<User>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user = new User();
	public User getModel() {
		return this.user;
	}
	private Integer page;
	private Integer rows;
	private Integer employeeId;
	private Integer roleId;
	private String actionUsername;//当前登入用户
	private String searchUsername;
	private String searchRole;
	private String searchStatus;
	@Autowired
	private UserService service;
	@Autowired
	private ObjectService oService;
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public void findAll(){
		List<User> list = service.findAll(user);
		if(user!=null){
			this.write(JSON.toJSONString(list));
		}
	}
	public void paginationFindAll(){
		//如果登录，actionUsername有值   执行下面方法
		if(actionUsername!=null && !actionUsername.equals("")){
			User userAction = service.get(User.class, actionUsername);
			List<User> list = service.findAll(user);
			List<User> removeList = this.removeSysUser(userAction,list);
			JSONObject j = new JSONObject();
			if(list!=null && list.size()>0){
				List<UserJson> userJsons = new ArrayList<UserJson>();
				List<User> li = service.paginationFindAndRemove(user, page, rows, removeList);
				UserJson json;
				for(User u : li){
					json = new UserJson();
					json.setUsername(u.getUsername());
					json.setPassword(u.getPassword());
					json.setEmployeeId(u.getEmployee().getId());
					json.setEmployeeName(u.getEmployee().getName());
					json.setRoleId(u.getRole().getId());
					json.setRoleName(u.getRole().getName());
					json.setStatus(u.getStatus());
					userJsons.add(json);
				}
				j.put("total", list.size()-removeList.size());
				j.put("rows", userJsons);
				this.write(j.toJSONString());
			}
		}else{
			this.write("");
		}
	}
	public void save(){
		if(user.getUsername()!=null&&user.getPassword()!=null){
			Employee employee = (Employee) oService.get(Employee.class, employeeId);
			user.setEmployee(employee);
			Role role = (Role) oService.get(Role.class,roleId);
			user.setRole(role);
			user.setStatus(1);
			service.saveOrUpdate(user);
		}
		JSONObject j = new JSONObject();
		j.put("success", true);
		this.write(j.toJSONString());
	}
	public void alterPassword(){
		if(user.getUsername()!=null){
			User u = service.get(User.class, user.getUsername());
			u.setPassword(user.getPassword());
			service.saveOrUpdate(u);
		}
		JSONObject j = new JSONObject();
		j.put("success", true);
		this.write(j.toJSONString());
	}
	public String returnEmployee(){//返回默认绑定员工号
		if(user.getUsername()!=null){
			User u = service.get(User.class, user.getUsername());
			this.getRequest().setAttribute("defaultEmployeeId", u.getEmployee().getId());
		}
		return SUCCESS;
	}
	public void linkEmployee(){
		user = service.get(User.class, user.getUsername());
		Employee employee = (Employee) oService.get(Employee.class, employeeId);
		user.setEmployee(employee);
		service.saveOrUpdate(user);
		JSONObject j = new JSONObject();
		j.put("success", true);
		this.write(j.toJSONString());
	}
	public String returnRoleId(){//返回本来的角色
		if(user.getUsername()!=null){
			User u = service.get(User.class, user.getUsername());
			this.getRequest().setAttribute("defaultRoleId", u.getRole().getId());
		}
		return SUCCESS;
	}
	public void authorize(){
		user = service.get(User.class, user.getUsername());
		Role role = (Role) oService.get(Role.class,roleId);
		user.setRole(role);
		service.saveOrUpdate(user);
		JSONObject j = new JSONObject();
		j.put("success", true);
		this.write(j.toJSONString());
	}
	public void alterStatus(){
		user = user = service.get(User.class, user.getUsername());
		user.setStatus(user.getStatus()==StatusGlobal.USER_USABLE?StatusGlobal.USER_UNUSABLE:StatusGlobal.USER_USABLE);
		service.saveOrUpdate(user);
		service.saveOrUpdate(user);
		JSONObject j = new JSONObject();
		j.put("success", true);
		this.write(j.toJSONString());
	}
	public void deleteUser(){
		user = user = service.get(User.class, user.getUsername());
		service.delete(user);
		JSONObject j = new JSONObject();
		j.put("success", true);
		this.write(j.toJSONString());
	}
	public void search(){
		if(actionUsername!=null && !actionUsername.equals("")){
			Map<String,Object> m = new HashMap<String, Object>();
			if(searchUsername!=null && !searchUsername.equals("") ){
				m.put("username", "\'%"+searchUsername+"%\'");
			}
			if(searchRole!=null && !searchRole.equals("") ){
				m.put("role", searchRole);
			}
			if(searchStatus!=null && !searchStatus.equals("") ){
				m.put("status", searchStatus);
			}
			List<User> list = service.search(user, m);
			User userAction = service.get(User.class, actionUsername);
			List<User> removeList = this.removeSysUser(userAction,list);
			list.removeAll(removeList);
			JSONObject j = new JSONObject();
			if(list!=null && list.size()>0){
				List<UserJson> userJsons = new ArrayList<UserJson>();
				UserJson json;
				for(User u : list){
					json = new UserJson();
					json.setUsername(u.getUsername());
					json.setPassword(u.getPassword());
					json.setEmployeeId(u.getEmployee().getId());
					json.setEmployeeName(u.getEmployee().getName());
					json.setRoleId(u.getRole().getId());
					json.setRoleName(u.getRole().getName());
					json.setStatus(u.getStatus());
					userJsons.add(json);
				}
				j.put("total", list.size());
				j.put("rows", userJsons);
				this.write(j.toJSONString());
			}else{
				j.put("total", 0);
				j.put("rows", "");
				this.write(j.toJSONString());
			}
		}else{
			this.write("");
		}
	}
	
	//从用户管理中移除自己以及更高权限的人。 将不需要查询的人加入list；
	private List<User> removeSysUser(User u,List<User> li){
		List<User> list = new ArrayList<User>();
		//如果登录用户是超级管理员，从用户管理中移除超级管理员，使之不能被设置。
		if(("超级管理员").equals(u.getRole().getName())){
			list.add(u);
		}
		//如果登录用户是系统管理员，从用户管理中移除超级管理员和系统管理员。
		else if(("系统管理员").equals(u.getRole().getName())){
			for(User user:li){
				if(("超级管理员").equals(user.getRole().getName())){
					list.add(user);
				}
				if(("系统管理员").equals(user.getRole().getName())){
					list.add(user);
				}
			}
		}
		//如果登录用户是其它，从用户管理中移除超级管理员、系统管理员和它自己。
		else {
			list.add(u);
			for(User user:li){
				if(("超级管理员").equals(user.getRole().getName())){
					list.add(user);
				}
				if(("系统管理员").equals(user.getRole().getName())){
					list.add(user);
				}
			}
		}
		
		return list;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getActionUsername() {
		return actionUsername;
	}

	public void setActionUsername(String actionUsername) {
		this.actionUsername = actionUsername;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getSearchUsername() {
		return searchUsername;
	}

	public void setSearchUsername(String searchUsername) {
		this.searchUsername = searchUsername;
	}

	public String getSearchRole() {
		return searchRole;
	}

	public void setSearchRole(String searchRole) {
		this.searchRole = searchRole;
	}

	public String getSearchStatus() {
		return searchStatus;
	}

	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}
	

}
