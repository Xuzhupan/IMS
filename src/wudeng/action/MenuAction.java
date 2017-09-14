package wudeng.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;

import wudeng.entity.Menu;
import wudeng.service.MenuService;
import wudeng.util.CreateJsonTreeByList;

@Controller
public class MenuAction extends BaseAction implements ModelDriven<Menu>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Menu menu = new Menu();
	@Autowired
	private MenuService service;

	/**
	 * 
	 */
	public void findMenus(){
		List<Menu> list = service.findAll(menu);
		if(list!=null && list.size()>0){
			this.write(CreateJsonTreeByList.createTreeJson(list));
		}
	}
	public void changeStatus(){
		menu = service.findById(menu.getId());
		menu.setStatus(menu.getStatus()==0?1:0);
		service.saveOrUpdate(menu);
		JSONObject j = new JSONObject();
		j.put("message", "修改成功,即将返回登录页面");
		j.put("success", true);
		this.write(JSON.toJSONString(j));
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	public Menu getModel() {
		return this.menu;
	}
}
