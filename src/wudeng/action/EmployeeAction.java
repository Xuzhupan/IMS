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
import myJson.EmployeeJson;
import myJson.UserJson;
import wudeng.entity.Department;
import wudeng.entity.Employee;
import wudeng.entity.Role;
import wudeng.entity.User;
import wudeng.service.EmployeeService;
import wudeng.service.ObjectService;

@Controller
public class EmployeeAction extends BaseAction implements ModelDriven<Employee>{

	
	private static final long serialVersionUID = 1L;
	private Employee employee = new Employee();
	private Integer actionEmployeeId;
	private String searchEmployeeName;
	private String searchDept;
	private String searchStatus;
	private Integer deptId;
	private Integer roleId;
	private int rows;
	private int page;
	@Autowired
	private EmployeeService service;
	@Autowired
	private ObjectService	oService;
	public void paginationFindAll(){
		//如果登录，actionUsername有值   执行下面方法
			List<Employee> list = service.findAll(employee);
			JSONObject j = new JSONObject();
			List<EmployeeJson> jsons = new ArrayList<EmployeeJson>();
			EmployeeJson json;
			if(list!=null && list.size()>0){
				List<Employee> li = service.paginationFind(employee, page, rows);
				for(Employee e:li){
					json = new EmployeeJson();
					json.setEmployeeId(e.getId());
					json.setEmployeeName(e.getName());
					json.setSex(e.getSex());
					json.setBirthday(e.getBirthday());
					json.setEntryday(e.getEntryday());
					json.setTel(e.getTel());
					json.setDepartmentId(e.getDepartment().getId());
					json.setDepartmentName(e.getDepartment().getName());
					json.setStatus(e.getStatus());
					jsons.add(json);
				}
				j.put("total", list.size());
				j.put("rows", jsons);
				this.write(j.toJSONString());
			}
	}
	public void paginationFindAction(){
		//当前登录 
		
			
			List<Employee> list = service.findStatusOf(employee, StatusGlobal.EMPLOYEE_USABLE);
			
			JSONObject j = new JSONObject();
			List<EmployeeJson> jsons = new ArrayList<EmployeeJson>();
			EmployeeJson json;
			if(list!=null && list.size()>0){
				List<Employee> li = service.paginationFindStatusOf(employee, page, rows, StatusGlobal.EMPLOYEE_USABLE);
				for(Employee e:li){
					json = new EmployeeJson();
					json.setEmployeeId(e.getId());
					json.setEmployeeName(e.getName());
					json.setSex(e.getSex());
					json.setBirthday(e.getBirthday());
					json.setEntryday(e.getEntryday());
					json.setTel(e.getTel());
					json.setDepartmentId(e.getDepartment().getId());
					json.setDepartmentName(e.getDepartment().getName());
					json.setStatus(e.getStatus());
					jsons.add(json);
				}
				j.put("total", list.size());
				j.put("rows", jsons);
				this.write(j.toJSONString());
			}
	}
	
	public String addAndEidt(){
		//判断是编辑还是新增
		if(employee.getId()!=null&&!"".equals(employee.getId())){
			Employee e = service.findById(employee);
			this.getRequest().setAttribute("employee", e);
		}
		return SUCCESS;
	}
	public void saveOrUpdate(){
		Department department = (Department) oService.get(Department.class, deptId);
		employee.setDepartment(department);
		employee.setStatus(1);
		
		service.saveOrUpdate(this.employee);
		JSONObject j = new JSONObject();
		if(employee!=null&&!employee.getId().equals("")){
			j.put("message", "保存成功");
			j.put("success", true);
		}else{
			j.put("message", "保存失败");
			j.put("success", false);
		}
		this.write(JSON.toJSONString(j));
	}
	public void alterStatus(){
		employee = service.findById(employee);
		employee.setStatus(employee.getStatus()==StatusGlobal.EMPLOYEE_USABLE?StatusGlobal.EMPLOYEE_UNUSABLE:StatusGlobal.EMPLOYEE_USABLE);
		service.saveOrUpdate(employee);
	}
	public void delete(){
		employee = service.findById(employee);
		service.delete(employee);
		JSONObject j = new JSONObject();
		j.put("success", true);
		this.write(j.toJSONString());
	}
	public void search(){
		if(actionEmployeeId!=null && !actionEmployeeId.equals("")){
			Map<String,Object> m = new HashMap<String, Object>();
			if(searchEmployeeName!=null && !searchEmployeeName.equals("") ){
				m.put("name like ", "\'%"+searchEmployeeName+"%\'");
			}
			if(searchDept!=null && !searchDept.equals("") ){
				m.put("department.id = ", searchDept);
			}
			if(searchStatus!=null && !searchStatus.equals("") ){
				m.put("status = ", searchStatus);
			}
			List<Employee> list = service.search(employee, m);
			List<EmployeeJson> jsons = new ArrayList<EmployeeJson>();
			EmployeeJson json;
			JSONObject j = new JSONObject();
			if(list!=null && list.size()>0){
				for(Employee e:list){
					json = new EmployeeJson();
					json.setEmployeeId(e.getId());
					json.setEmployeeName(e.getName());
					json.setSex(e.getSex());
					json.setBirthday(e.getBirthday());
					json.setEntryday(e.getEntryday());
					json.setTel(e.getTel());
					json.setDepartmentId(e.getDepartment().getId());
					json.setDepartmentName(e.getDepartment().getName());
					json.setStatus(e.getStatus());
					jsons.add(json);
				}
				j.put("total", list.size());
				j.put("rows", jsons);
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
		return SUCCESS;
	}

	public Employee getModel() {
		return this.employee;
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
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getActionEmployeeId() {
		return actionEmployeeId;
	}
	public void setActionEmployeeId(Integer actionEmployeeId) {
		this.actionEmployeeId = actionEmployeeId;
	}
	public String getSearchDept() {
		return searchDept;
	}
	public void setSearchDept(String searchDept) {
		this.searchDept = searchDept;
	}
	public String getSearchStatus() {
		return searchStatus;
	}
	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}
	public String getSearchEmployeeName() {
		return searchEmployeeName;
	}
	public void setSearchEmployeeName(String searchEmployeeName) {
		this.searchEmployeeName = searchEmployeeName;
	}
}
