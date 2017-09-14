package wudeng.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;

import myJson.PurchasePlanJson;
import myJson.SellPlanJson;
import wudeng.entity.PurchasePlan;
import wudeng.entity.SellPlan;
import wudeng.service.SellPlanService;

@Controller
public class SellPlanAction extends BaseAction implements ModelDriven<SellPlan>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SellPlan sellPlan = new SellPlan();
	private int page;
	private int rows;
	private String action;
	private String actionUsername;
	private String searchSellId;
	private String searchSellName;
	private String searchSellEmployee;
	private String searchStatus;
	
	@Autowired
	private SellPlanService service;
	
	public void paginationFindAll(){
		List<SellPlan> list = service.findAll(sellPlan);
		JSONObject j = new JSONObject();
		if(list!=null && list.size()>0){
			List<SellPlanJson> sellPlanJsons = new ArrayList<SellPlanJson>();
			List<SellPlan> li = service.paginationFind(sellPlan, page, rows);
			SellPlanJson sellPlanJson ;
			for(SellPlan s: li){
				sellPlanJson = new SellPlanJson();
				sellPlanJson.setSellPlanId(s.getId());
				sellPlanJson.setPlanName(s.getPlanName());
				sellPlanJson.setEmployeeId(s.getEmployee().getId());
				sellPlanJson.setEmployeeName(s.getEmployee().getName());
				sellPlanJson.setCustomerId(s.getCustomer().getId());
				sellPlanJson.setCustomerName(s.getCustomer().getName());
				sellPlanJson.setCreateDate(s.getCreateDate());
				sellPlanJson.setStatus(s.getStatus());
				sellPlanJsons.add(sellPlanJson);
			}
			j.put("total", list.size());
			j.put("rows", sellPlanJsons);
			this.write(j.toJSONString());
		}
	}
	public void findStatus(){
		JSONObject j = new JSONObject();
		List<SellPlan> list = service.findStatusOf(sellPlan, 1);
		List<SellPlanJson> li = new ArrayList<SellPlanJson>();
		SellPlanJson json;
		if(list!=null ){
			for(SellPlan s : list){
				json = new SellPlanJson();
				json.setSellPlanId(s.getId());
				json.setPlanName(s.getPlanName());
				json.setEmployeeId(s.getEmployee().getId());
				json.setEmployeeName(s.getEmployee().getName());
				json.setCustomerId(s.getCustomer().getId());
				json.setCustomerName(s.getCustomer().getName());
				json.setCreateDate(s.getCreateDate());
				json.setStatus(s.getStatus());
				li.add(json);
			}
			j.put("total", list.size());
			j.put("rows", li);
			this.write(j.toJSONString());
		}
	}
	public void alterStatus(){
		JSONObject j = new JSONObject();
		sellPlan = service.findById(sellPlan);
		if(action.equals("0to1")){
			if(sellPlan.getStatus()==0){
				sellPlan.setStatus(1);
				service.saveOrUpdate(sellPlan);
				j.put("success", true);
			}else{
				j.put("success", false);
			}
		}
		if(action.equals("1to0")){
			if(sellPlan.getStatus()==1){
				sellPlan.setStatus(0);
				service.saveOrUpdate(sellPlan);
				j.put("success", true);
			}else{
				j.put("success", false);
			}
		}
		if(action.equals("0to3")){
			if(sellPlan.getStatus()==0){
				sellPlan.setStatus(3);
				service.saveOrUpdate(sellPlan);
				j.put("success", true);
			}else{
				j.put("success", false);
			}
		}
		if(action.equals("3to0")){
			if(sellPlan.getStatus()==3){
				sellPlan.setStatus(0);
				service.saveOrUpdate(sellPlan);
				j.put("success", true);
			}else{
				j.put("success", false);
			}
		}
		
		this.write(j.toJSONString());
	}
	public void delete(){
		JSONObject j = new JSONObject();
		sellPlan = service.findById(sellPlan);
		if(sellPlan.getStatus()==3){
			service.delete(sellPlan);
			j.put("success", true);
		}else{
			j.put("success", false);
		}
	}
	public void search(){
		if(actionUsername!=null && !actionUsername.equals("")){
			Map<String,Object> m = new HashMap<String, Object>();
			if(searchSellId!=null && !searchSellId.equals("") ){
				m.put("id = ", searchSellId);
			}
			if(searchSellName!=null && !searchSellName.equals("") ){
				m.put("planName like ", "\'%"+searchSellName+"%\'");
			}
			if(searchSellEmployee!=null && !searchSellEmployee.equals("") ){
				m.put("employee.name like ", "\'%"+searchSellEmployee+"%\'");
			}
			if(searchStatus!=null && !searchStatus.equals("") ){
				m.put("status = ", searchStatus);
			}
			List<SellPlan> list = service.search(sellPlan, m);
			JSONObject j = new JSONObject();
			if(list!=null && list.size()>0){
				List<SellPlanJson> sellPlanJsons = new ArrayList<SellPlanJson>();
				SellPlanJson sellPlanJson ;
				for(SellPlan s: list){
					sellPlanJson = new SellPlanJson();
					sellPlanJson.setSellPlanId(s.getId());
					sellPlanJson.setPlanName(s.getPlanName());
					sellPlanJson.setEmployeeId(s.getEmployee().getId());
					sellPlanJson.setEmployeeName(s.getEmployee().getName());
					sellPlanJson.setCustomerId(s.getCustomer().getId());
					sellPlanJson.setCustomerName(s.getCustomer().getName());
					sellPlanJson.setCreateDate(s.getCreateDate());
					sellPlanJson.setStatus(s.getStatus());
					sellPlanJsons.add(sellPlanJson);
				}
				j.put("total", list.size());
				j.put("rows", sellPlanJsons);
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
	public SellPlan getModel() {
		return this.sellPlan;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	public String getActionUsername() {
		return actionUsername;
	}
	public void setActionUsername(String actionUsername) {
		this.actionUsername = actionUsername;
	}
	public String getSearchSellId() {
		return searchSellId;
	}
	public void setSearchSellId(String searchSellId) {
		this.searchSellId = searchSellId;
	}
	public String getSearchSellName() {
		return searchSellName;
	}
	public void setSearchSellName(String searchSellName) {
		this.searchSellName = searchSellName;
	}
	public String getSearchSellEmployee() {
		return searchSellEmployee;
	}
	public void setSearchSellEmployee(String searchSellEmployee) {
		this.searchSellEmployee = searchSellEmployee;
	}
	public String getSearchStatus() {
		return searchStatus;
	}
	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}

}
