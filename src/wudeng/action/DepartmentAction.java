package wudeng.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;

import myJson.ComboboxJson;
import myJson.EmployeeJson;
import sun.print.resources.serviceui;
import wudeng.entity.Department;
import wudeng.entity.Employee;
import wudeng.entity.Role;
import wudeng.service.DepartmentService;
@Controller
public class DepartmentAction extends BaseAction implements ModelDriven<Department>{

	private static final long serialVersionUID = 1L;
	private Department department = new Department();
	private String actionUsername;
	private String searchDeptName;
	private String searchStatus;
	private int rows;
	private int page;
	
	@Autowired
	private DepartmentService service;

	public void findAll(){
		List<Department> list = service.findAll(department);
		JSONObject j = new JSONObject();
		if(list!=null && list.size()>0){
			j.put("total", list.size());
			j.put("rows", list);
			this.write(j.toJSONString());
		}
	}
	public void paginationFindAll(){
		List<Department> list = service.findAll(department);
		JSONObject j = new JSONObject();
		if(list!=null && list.size()>0){
			List<Department> li = service.paginationFind(department, page, rows);
			j.put("total", list.size());
			j.put("rows", li);
			this.write(j.toJSONString());
		}
	}
	public void paginationFindAction(){
		List<Department> list = service.findStatusOf(department, 1);
		JSONObject j = new JSONObject();
		if(list!=null && list.size()>0){
			List<Department> li = service.paginationFindStatusOf(department, page, rows, 1);
			j.put("total", list.size());
			j.put("rows", li);
			this.write(j.toJSONString());
		}
	}
	public void combobox(){
		//如果登录，actionUsername有值   执行下面方法
		if(actionUsername!=null && !actionUsername.equals("")){
			List<Department> list = service.findAll(department);
			if(list!=null && list.size()>0){
				List<ComboboxJson> jsons = new ArrayList<ComboboxJson>();
				ComboboxJson json;
				for(Department d:list){
					json = new ComboboxJson();
					json.setId(d.getId());
					json.setText(d.getName());
					jsons.add(json);
				}
				this.write(JSON.toJSONString(jsons));
			}
		}else{
			this.write("");
		}
	}
	public String addAndEidt(){
		//判断是编辑还是新增
		if(department.getId()!=null&&!"".equals(department.getId())){
			Department d = service.findById(department);
			this.getRequest().setAttribute("department", d);
		}
		return SUCCESS;
	}
	public void saveOrUpdate(){
		Department d = service.findById(this.department);
		service.saveOrUpdate(this.department);
		JSONObject j = new JSONObject();
		if(department!=null&&!department.getId().equals("")){
			j.put("message", "保存成功");
			j.put("success", true);
		}else{
			j.put("message", "保存失败");
			j.put("success", false);
		}
		this.write(JSON.toJSONString(j));
	}
	public void alterStatus(){
		this.saveOrUpdate();
	}
	public void delete(){
		JSONObject j = new JSONObject();
		try {
			service.delete(department);
			j.put("message", "删除成功");
			j.put("success", true);
		} catch (Exception e) {
			j.put("message", "删除失败");
			j.put("success", false);
			e.printStackTrace();
		}finally{
			this.write(JSON.toJSONString(j));
		}
	}
	public void search(){
		if(actionUsername!=null && !actionUsername.equals("")){
			Map<String,Object> m = new HashMap<String, Object>();
			if(searchDeptName!=null && !searchDeptName.equals("") ){
				m.put("name like ", "\'%"+searchDeptName+"%\'");
			}
			if(searchStatus!=null && !searchStatus.equals("") ){
				m.put("status = ", searchStatus);
			}
			List<Department> list = service.search(department, m);
			JSONObject j = new JSONObject();
			if(list!=null && list.size()>0){
				j.put("total", list.size());
				j.put("rows", list);
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
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	public Department getModel() {
		return this.department;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	public String getActionUsername() {
		return actionUsername;
	}
	public void setActionUsername(String actionUsername) {
		this.actionUsername = actionUsername;
	}
	public String getSearchDeptName() {
		return searchDeptName;
	}
	public void setSearchDeptName(String searchDeptName) {
		this.searchDeptName = searchDeptName;
	}
	public String getSearchStatus() {
		return searchStatus;
	}
	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}
	
}
