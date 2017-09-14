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
import wudeng.entity.Goods;
import wudeng.entity.PurchasePlan;
import wudeng.service.PurchasePlanService;

@Controller
public class PurchasePlanAction extends BaseAction implements ModelDriven<PurchasePlan>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PurchasePlan purchasePlan = new PurchasePlan();
	private int page;
	private int rows;
	private String action;//改变状态的操作 标识"0to1" "1to2" ...
	private String actionUsername;
	private String searchPurchaseId;
	private String searchPurchaseName;
	private String searchPurchaseEmployee;
	private String searchStatus;
	@Autowired
	private PurchasePlanService service;
	
	public void findAll(){
		List<PurchasePlan> list = service.findAll(purchasePlan);
		JSONObject j = new JSONObject();
		if(list.size()>0){
			j.put("total", list.size());
			j.put("rows", list);
			this.write(j.toJSONString());
		}
	}
	public void paginationFindAll(){
		JSONObject j = new JSONObject();
		List<PurchasePlan> list = service.findAll(purchasePlan);
		List<PurchasePlanJson> jsons = new ArrayList<PurchasePlanJson>();
		PurchasePlanJson json ;
		if(list!=null && list.size()>0){
			List<PurchasePlan> li = service.paginationFind(purchasePlan, page, rows);
			for(PurchasePlan p : li){
				json = new PurchasePlanJson();
				json.setPurchaseId(p.getId());
				json.setPlanName(p.getPlanName());
				json.setEmployeeId(p.getEmployee().getId());
				json.setEmployeeName(p.getEmployee().getName());
				json.setCreateDate(p.getCreateDate());
				json.setStatus(p.getStatus());
				jsons.add(json);
			}
			j.put("total", list.size());
			j.put("rows", jsons);
			this.write(j.toJSONString());
		}
	}
	public void findStatus(){
		JSONObject j = new JSONObject();
			List<PurchasePlan> list =service.findStatusOf(1); //1生效
			List<PurchasePlanJson> li = new ArrayList<PurchasePlanJson>();
			PurchasePlanJson json ;
			if(list!=null){
				for(PurchasePlan p : list){
					json= new PurchasePlanJson();
					json.setPurchaseId(p.getId());
					json.setPlanName(p.getPlanName());
					json.setEmployeeId(p.getEmployee().getId());
					json.setEmployeeName(p.getEmployee().getName());
					json.setCreateDate(p.getCreateDate());
					json.setStatus(p.getStatus());
					li.add(json);
				}
				if(li!=null && li.size()>0){
					j.put("total", list.size());
					j.put("rows", li);
					this.write(j.toJSONString());
				}
			}else{
				
			}
		
	}
	public void saveOrUpdate(){
		
	}
	public void alterStatus(){
		JSONObject j = new JSONObject();
		purchasePlan = service.findById(purchasePlan);
		if(action.equals("0to1")){
			if(purchasePlan.getStatus()==0){
				purchasePlan.setStatus(1);
				service.saveOrUpdate(purchasePlan);
				j.put("success", true);
			}else{
				j.put("success", false);
			}
		}
		if(action.equals("1to0")){
			if(purchasePlan.getStatus()==1){
				purchasePlan.setStatus(0);
				service.saveOrUpdate(purchasePlan);
				j.put("success", true);
			}else{
				j.put("success", false);
			}
		}
		if(action.equals("0to3")){
			if(purchasePlan.getStatus()==0){
				purchasePlan.setStatus(3);
				service.saveOrUpdate(purchasePlan);
				j.put("success", true);
			}else{
				j.put("success", false);
			}
		}
		if(action.equals("3to0")){
			if(purchasePlan.getStatus()==3){
				purchasePlan.setStatus(0);
				service.saveOrUpdate(purchasePlan);
				j.put("success", true);
			}else{
				j.put("success", false);
			}
		}
		
		this.write(j.toJSONString());
	}
	public void delete(){
		JSONObject j = new JSONObject();
		purchasePlan = service.findById(purchasePlan);
		if(purchasePlan.getStatus()==3){
			service.delete(purchasePlan);
			j.put("success", true);
		}else{
			j.put("success", false);
		}
	}
	public void search(){
		if(actionUsername!=null && !actionUsername.equals("")){
			Map<String,Object> m = new HashMap<String, Object>();
			if(searchPurchaseId!=null && !searchPurchaseId.equals("") ){
				m.put("id = ", searchPurchaseId);
			}
			if(searchPurchaseName!=null && !searchPurchaseName.equals("") ){
				m.put("planName like ", "\'%"+searchPurchaseName+"%\'");
			}
			if(searchPurchaseEmployee!=null && !searchPurchaseEmployee.equals("") ){
				m.put("employee.name like ", "\'%"+searchPurchaseEmployee+"%\'");
			}
			if(searchStatus!=null && !searchStatus.equals("") ){
				m.put("status = ", searchStatus);
			}
			List<PurchasePlan> list = service.search(purchasePlan, m);
			JSONObject j = new JSONObject();
			List<PurchasePlanJson> jsons = new ArrayList<PurchasePlanJson>();
			PurchasePlanJson json ;
			if(list!=null && list.size()>0){
				for(PurchasePlan p : list){
					json = new PurchasePlanJson();
					json.setPurchaseId(p.getId());
					json.setPlanName(p.getPlanName());
					json.setEmployeeId(p.getEmployee().getId());
					json.setEmployeeName(p.getEmployee().getName());
					json.setCreateDate(p.getCreateDate());
					json.setStatus(p.getStatus());
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

	public PurchasePlan getModel() {
		return this.purchasePlan;
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
	public String getSearchPurchaseId() {
		return searchPurchaseId;
	}
	public void setSearchPurchaseId(String searchPurchaseId) {
		this.searchPurchaseId = searchPurchaseId;
	}
	public String getSearchPurchaseName() {
		return searchPurchaseName;
	}
	public void setSearchPurchaseName(String searchPurchaseName) {
		this.searchPurchaseName = searchPurchaseName;
	}
	public String getSearchPurchaseEmployee() {
		return searchPurchaseEmployee;
	}
	public void setSearchPurchaseEmployee(String searchPurchaseEmployee) {
		this.searchPurchaseEmployee = searchPurchaseEmployee;
	}
	public String getSearchStatus() {
		return searchStatus;
	}
	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}
	
}
