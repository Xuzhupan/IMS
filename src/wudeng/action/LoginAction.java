package wudeng.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;

import Global.SysGlobal;
import wudeng.entity.Permission;
import wudeng.entity.User;
import wudeng.service.LoginService;
import wudeng.util.PermissionFactory;

@Controller
public class LoginAction extends BaseAction implements ModelDriven<User>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user = new User();
	public User getModel() {
		return this.user;
	}
	private String passwordNew;
	@Autowired
	private LoginService service;
	
	public void login(){
		User u = service.getLoginUser(user);
		if(u!=null && u.getRole().getStatus()==1){
			this.getRequest().getSession().setAttribute("user", u);
//			this.getRequest().getSession().setAttribute("employee", u.getEmployee());
			Permission p = new Permission();
			PermissionFactory.init(u.getRole(), p);
			this.getRequest().getSession().setAttribute("permission", p);
			this.write("{\"success\":true}");
		}else{
			this.write("{\"success\":false}");
		}
	}
	public void updatePwd(){
		User u = service.getLoginUser(user);
		if(u!=null){
			u.setPassword(passwordNew);
			service.saveOrUpdate(u);
			this.write("{\"success\":true}");
		}else{
			this.write("{\"success\":false}");
		}
	}
	public String loginOut(){
		this.getRequest().getSession().removeAttribute("user");
		return SUCCESS;
	}
	@Override
	public String execute() throws Exception {
		return "success";
	}
	public String getPasswordNew() {
		return passwordNew;
	}
	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
	}

}
