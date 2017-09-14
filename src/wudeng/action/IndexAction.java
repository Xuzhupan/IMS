package wudeng.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;

import wudeng.entity.Permission;
import wudeng.entity.Role;
import wudeng.service.IndexService;
import wudeng.util.MenuFactory;
import wudeng.util.PermissionFactory;

@Controller
public class IndexAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	private int roleId;
	
	@Autowired
	private IndexService service;
	
	public void initMenu(){
		Role r = service.findRoleById(roleId);
		Permission p = new Permission();
		PermissionFactory.init(r, p);
		System.out.println(p);
		String menu = MenuFactory.CreateMenuByPermission(p, service);
		System.out.println(JSON.toJSONString(r));
		System.out.println(menu);
		this.write(menu);
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}
