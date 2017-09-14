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
import wudeng.entity.Supplier;
import wudeng.service.SupplierService;
@Controller
public class SupplierAction extends BaseAction implements ModelDriven<Supplier>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int rows;
	private int page;
	private String actionUsername;
	private String searchSupplierName;
	private String searchSupplierTax;
	private String searchBankName;
	private String searchStatus;
	
	private Supplier supplier = new Supplier();
	@Autowired
	private SupplierService service;
	public void findAll(){
		List<Supplier> list = service.findAll(supplier);
		JSONObject j = new JSONObject();
		if(list!=null && list.size()>0){
			j.put("total", list.size());
			j.put("rows", list);
			this.write(j.toJSONString());
		}
	}
	public void paginationFindAll(){
		List<Supplier> list = service.findAll(supplier);
		JSONObject j = new JSONObject();
		if(list!=null && list.size()>0){
			List<Supplier> li = service.paginationFind(supplier, page, rows);
			j.put("total", list.size());
			j.put("rows", li);
			this.write(j.toJSONString());
		}
	}
	public String addAndEidt(){
		//判断是编辑还是新增
		if(supplier.getId()!=null&&!"".equals(supplier.getId())){
			Supplier s = service.findById(supplier);
			this.getRequest().setAttribute("supplier", s);
		}
		return SUCCESS;
	}
	public void saveOrUpdate(){
		Supplier s = service.findById(this.supplier);
		if(s==null){
			this.supplier.setCreateDate(new Date());
		}else{
			this.supplier.setCreateDate(s.getCreateDate());
		}
		service.saveOrUpdate(this.supplier);
		JSONObject j = new JSONObject();
		if(supplier!=null&&!supplier.getId().equals("")){
			j.put("message", "保存成功");
			j.put("success", true);
		}else{
			j.put("message", "保存失败");
			j.put("success", false);
		}
		this.write(JSON.toJSONString(j));
	}
	public void alterStatus(){
		Supplier s = service.findById(this.supplier);
		if(s==null){
			this.supplier.setCreateDate(new Date());
		}else{
			this.supplier.setCreateDate(s.getCreateDate());
		}
		service.saveOrUpdate(supplier);
		JSONObject j = new JSONObject();
		if(supplier!=null&&!supplier.getId().equals("")){
			j.put("success", true);
		}else{
			j.put("success", false);
		}
		this.write(JSON.toJSONString(j));
	}
	public void delete(){
		JSONObject j = new JSONObject();
		try {
			service.delete(supplier);
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
			if(searchSupplierName!=null && !searchSupplierName.equals("") ){
				m.put("name like ", "\'%"+searchSupplierName+"%\'");
			}
			if(searchSupplierTax!=null && !searchSupplierTax.equals("") ){
				m.put("taxpayerIdentity like ", "\'%"+searchSupplierTax+"%\'");
			}
			if(searchBankName!=null && !searchBankName.equals("") ){
				m.put("bankName like ", "\'%"+searchBankName+"%\'");
			}
			if(searchStatus!=null && !searchStatus.equals("") ){
				m.put("status = ", searchStatus);
			}
			List<Supplier> list = service.search(supplier, m);
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
	public Supplier getModel() {
		return supplier;
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
	public String getSearchSupplierName() {
		return searchSupplierName;
	}
	public void setSearchSupplierName(String searchSupplierName) {
		this.searchSupplierName = searchSupplierName;
	}
	public String getSearchSupplierTax() {
		return searchSupplierTax;
	}
	public void setSearchSupplierTax(String searchSupplierTax) {
		this.searchSupplierTax = searchSupplierTax;
	}
	public String getSearchBankName() {
		return searchBankName;
	}
	public void setSearchBankName(String searchBankName) {
		this.searchBankName = searchBankName;
	}
	public String getSearchStatus() {
		return searchStatus;
	}
	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}
	
}
