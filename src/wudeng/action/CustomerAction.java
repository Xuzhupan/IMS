package wudeng.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;

import wudeng.entity.Customer;
import wudeng.entity.Goods;
import wudeng.service.CustomerService;

@Controller
public class CustomerAction extends BaseAction implements ModelDriven<Customer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int rows;
	private int page;
	private String actionUsername;
	private String searchCustomerName;
	private String searchStatus;
	
	private Customer customer= new Customer();
	@Autowired
	private CustomerService service;
	public void findAll(){
		List<Customer> list = service.findAll(customer);
		JSONObject j = new JSONObject();
		if(list!=null && list.size()>0){
			j.put("total", list.size());
			j.put("rows", list);
			this.write(j.toJSONString());
		}
	}
	public void paginationFindAll(){
		List<Customer> list = service.findAll(customer);
		JSONObject j = new JSONObject();
		if(list!=null && list.size()>0){
			List<Customer> li = service.paginationFind(customer, page, rows);
			j.put("total", list.size());
			j.put("rows", li);
			this.write(j.toJSONString());
		}
	}
	public void paginationFindActive(){
		List<Customer> list = service.findStatusOf(customer, 1);//1可用
		JSONObject j = new JSONObject();
		if(list!=null && list.size()>0){
			List<Customer> li = service.paginationFindStatusOf(customer, page, rows, 1);
			j.put("total", list.size());
			j.put("rows", li);
			this.write(j.toJSONString());
		}
	}
	public String addAndEidt(){
		//判断是编辑还是新增
		if(customer.getId()!=null&&!"".equals(customer.getId())){
			Customer c = service.findById(customer);
			this.getRequest().setAttribute("customer", c);
		}
		return SUCCESS;
	}
	public void saveOrUpdate(){
		Customer c = service.findById(this.customer);
		if(c==null){
			this.customer.setCreateDate(new Date());
		}else{
			this.customer.setCreateDate(c.getCreateDate());
		}
		service.saveOrUpdate(this.customer);
		JSONObject j = new JSONObject();
		if(customer!=null&&!customer.getId().equals("")){
			j.put("message", "保存成功");
			j.put("success", true);
		}else{
			j.put("message", "保存失败");
			j.put("success", false);
		}
		this.write(JSON.toJSONString(j));
	}
	public void alterStatus(){
		Customer c = service.findById(this.customer);
		if(c==null){
			this.customer.setCreateDate(new Date());
		}else{
			this.customer.setCreateDate(c.getCreateDate());
		}
		service.saveOrUpdate(customer);
		JSONObject j = new JSONObject();
		if(customer!=null&&!customer.getId().equals("")){
			j.put("success", true);
		}else{
			j.put("success", false);
		}
		this.write(JSON.toJSONString(j));
	}
	public void delete(){
		JSONObject j = new JSONObject();
		try {
			service.delete(customer);
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
			if(searchCustomerName!=null && !searchCustomerName.equals("") ){
				m.put("name like ", "\'%"+searchCustomerName+"%\'");
			}
			if(searchStatus!=null && !searchStatus.equals("") ){
				m.put("status = ", searchStatus);
			}
			List<Customer> list = service.search(customer, m);
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
		return SUCCESS;
	}
	public Customer getModel() {
		return customer;
	}
	public String getActionUsername() {
		return actionUsername;
	}
	public void setActionUsername(String actionUsername) {
		this.actionUsername = actionUsername;
	}
	public String getSearchCustomerName() {
		return searchCustomerName;
	}
	public void setSearchCustomerName(String searchCustomerName) {
		this.searchCustomerName = searchCustomerName;
	}
	public String getSearchStatus() {
		return searchStatus;
	}
	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}
}
