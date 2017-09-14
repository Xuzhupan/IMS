package wudeng.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;

import Global.StatusGlobal;
import Global.SysGlobal;
import myJson.ComboboxJson;
import myJson.RoleJson;
import wudeng.entity.Employee;
import wudeng.entity.Permission;
import wudeng.entity.Role;
import wudeng.entity.User;
import wudeng.service.RoleService;
import wudeng.util.PermissionFactory;

@Controller
public class RoleAction extends BaseAction implements ModelDriven<Role>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Role role = new Role();
	private Integer actionRoleId;
	private Integer rows;
	private Integer page;
	@Autowired
	private RoleService service;
	public void paginationFindAll(){
		//如果登录，actionUsername有值   执行下面方法
		if(actionRoleId!=null && !actionRoleId.equals("")){
			Role roleAction = service.get(Role.class, actionRoleId);
			List<Role> list = service.findAll(role);
			List<Role> removeList = this.removeSysUser(roleAction,list);
			JSONObject j = new JSONObject();
			List<RoleJson> jsons = new ArrayList<RoleJson>();
			RoleJson json;
			Permission p ;
			if(list!=null && list.size()>0){
				List<Role> li = service.paginationFindAndRemove(role, page, rows, removeList);
				for(Role r:li){
					json = new RoleJson();
					p = new Permission();
					json.setId(r.getId());
					json.setName(r.getName());
					json.setStatus(r.getStatus());
					PermissionFactory.init(r, p);
					json.setPermission(p);
					jsons.add(json);
				}
				j.put("total", list.size()-removeList.size());
				j.put("rows", jsons);
				this.write(j.toJSONString());
			}
		}else{
			this.write("");
		}
	}
	public void paginationFindAction(){
		//如果登录，actionUsername有值   执行下面方法
		if(actionRoleId!=null && !actionRoleId.equals("")){
			Role roleAction = service.get(Role.class, actionRoleId);
			List<Role> list = service.findStatusOf(role, StatusGlobal.ROLE_USABLE);
			List<Role> removeList = this.removeSysUser(roleAction,list);
			JSONObject j = new JSONObject();
			List<RoleJson> jsons = new ArrayList<RoleJson>();
			RoleJson json;
			Permission p ;
			if(list!=null && list.size()>0){
				List<Role> li = service.paginationFindStatusOfAndRemove(role, page, rows, StatusGlobal.ROLE_USABLE, removeList);
				for(Role r:li){
					json = new RoleJson();
					p = new Permission();
					json.setId(r.getId());
					json.setName(r.getName());
					json.setStatus(r.getStatus());
					PermissionFactory.init(r, p);
					json.setPermission(p);
					jsons.add(json);
				}
				j.put("total", list.size()-removeList.size());
				j.put("rows", jsons);
				this.write(j.toJSONString());
			}
		}else{
			this.write("");
		}
	}
	public void combobox(){
		//如果登录，actionUsername有值   执行下面方法
		if(actionRoleId!=null && !actionRoleId.equals("")){
			Role roleAction = service.get(Role.class, actionRoleId);
			List<Role> list = service.findAll(role);
			List<Role> removeList = this.removeSysUser(roleAction,list);
			if(list!=null && list.size()>0){
				List<Role> li = service.findAllAndRemove(role, removeList);
				List<ComboboxJson> jsons = new ArrayList<ComboboxJson>();
				ComboboxJson json;
				for(Role r:li){
					json = new ComboboxJson();
					json.setId(r.getId());
					json.setText(r.getName());
					jsons.add(json);
				}
				this.write(JSON.toJSONString(jsons));
			}
		}else{
			this.write("");
		}
	}
	public void alterStatus(){
		if(role.getId()!=null){
			role = service.get(Role.class, role.getId());
			role.setStatus(role.getStatus()==0?1:0);
			service.saveOrUpdate(role);
			JSONObject j = new JSONObject();
			j.put("success", true);
			this.write(j.toJSONString());
		}
	}
	public void delete(){
		if(role.getId()!=null){
			role = service.get(Role.class, role.getId());
			service.delete(role);
			JSONObject j = new JSONObject();
			j.put("success", true);
			this.write(j.toJSONString());
		}
	}
	
	//从角色管理中移除自己以及更高权限的人。 将不需要查询的人加入list；
			private List<Role> removeSysUser(Role r,List<Role> li){
				List<Role> list = new ArrayList<Role>();
				//如果登录用户是超级管理员，从角色管理中移除超级管理员，使之不能被设置。
				if(("超级管理员").equals(r.getName())){
					list.add(r);
				}
				//如果登录用户是系统管理员，从角色管理中移除超级管理员和系统管理员。
				else if(("系统管理员").equals(r.getName())){
					for(Role role:li){
						if(("超级管理员").equals(role.getName())){
							list.add(role);
						}
						if(("系统管理员").equals(role.getName())){
							list.add(r);
						}
					}
				}
				//如果登录用户是其它，从角色管理中移除超级管理员、系统管理员和它自己。
				else {
					list.add(r);
					for(Role role:li){
						if(("超级管理员").equals(role.getName())){
							list.add(r);
						}
						if(("系统管理员").equals(role.getName())){
							list.add(role);
						}
					}
				}
				
				return list;
			}
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public Role getModel() {
		return this.role;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getActionRoleId() {
		return actionRoleId;
	}
	public void setActionRoleId(Integer actionRoleId) {
		this.actionRoleId = actionRoleId;
	}
	
}
