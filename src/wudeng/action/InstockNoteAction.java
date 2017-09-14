package wudeng.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;

import myJson.InstockNoteJson;
import wudeng.entity.Customer;
import wudeng.entity.Employee;
import wudeng.entity.InstockNote;
import wudeng.entity.PurchaseInfo;
import wudeng.entity.PurchasePlan;
import wudeng.service.ObjectService;

@Controller
public class InstockNoteAction extends BaseAction implements ModelDriven<InstockNote>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InstockNote instockNote =  new InstockNote();
	private Integer purchasePlanId;
	private Integer employeeId;
	private int rows;
	private int page;
	@Autowired
	private ObjectService service;
	public void paginationFindAll(){
		List<InstockNote> list = service.findAll(instockNote);
		JSONObject j = new JSONObject();
		if(list!=null && list.size()>0){
			List<InstockNoteJson> noteJsons = new ArrayList<InstockNoteJson>();
			List<InstockNote> li = service.paginationFind(instockNote,"order by id desc", page, rows);
			InstockNoteJson noteJson ;
			for(InstockNote n : li){
				noteJson = new InstockNoteJson();
				noteJson.setInstockNoteId(n.getId());
				noteJson.setPurchaseId(n.getPurchasePlan().getId());
				noteJson.setPlan_employeeId(n.getPurchasePlan().getEmployee().getId());
				noteJson.setPlan_employeeName(n.getPurchasePlan().getEmployee().getName());
				noteJson.setNote_employeeId(n.getEmployee().getId());
				noteJson.setNote_employeeName(n.getEmployee().getName());
				noteJson.setCreateDate(n.getCreateDate());
				noteJson.setStatus(n.getStatus());
				noteJson.setCanRollback(canRollback(n)); //设置可撤销标识
				noteJsons.add(noteJson);
			}
			j.put("total", list.size());
			j.put("rows", noteJsons);
			this.write(j.toJSONString());
		}
	}
	public void instock(){
		JSONObject j = new JSONObject();
		PurchasePlan plan = (PurchasePlan) service.get(PurchasePlan.class, purchasePlanId);
		if(plan.getStatus()==1){
			plan.setStatus(2); //2 入库成功
			service.saveOrUpdate(plan);
			Employee employee = (Employee) service.get(Employee.class, employeeId);
			instockNote.setPurchasePlan(plan);
			instockNote.setEmployee(employee);
			instockNote.setCreateDate(new Date(System.currentTimeMillis())); 
			instockNote.setStatus(Integer.valueOf(1));//1 入库操作
			Set<PurchaseInfo> set =  plan.getPurchaseInfos();
			Integer number;
			for(PurchaseInfo pi : set){
				number = pi.getGoods().getRepertory().getNumber();//获得原库存
				number = number + pi.getNumber(); //原库存加上新入库的数量
				pi.getGoods().getRepertory().setNumber(number);
				service.saveOrUpdate(pi);
			}
			service.saveOrUpdate(instockNote);
			j.put("success", true);
			this.write(j.toJSONString());
		}else{
			j.put("success", false);
			this.write(j.toJSONString());
		}
	}
	public void rollback(){
		PurchasePlan plan = (PurchasePlan) service.get(PurchasePlan.class, purchasePlanId); //拿到计划Id
		plan.setStatus(1); //修改状态为 1 有效
		Employee employee = (Employee) service.get(Employee.class, employeeId); //操作人
		instockNote.setPurchasePlan(plan); //新记录 撤销入库记录
		instockNote.setEmployee(employee);
		instockNote.setCreateDate(new Date());
		instockNote.setStatus(Integer.valueOf(0)); //设置记录状态 撤销操作
		Set<PurchaseInfo> set =  plan.getPurchaseInfos();
		Integer number;
		for(PurchaseInfo pi : set){
			number = pi.getGoods().getRepertory().getNumber();//获得原库存
			number = number - pi.getNumber(); //原库存减去入库的数量
			pi.getGoods().getRepertory().setNumber(number);
			service.saveOrUpdate(pi);
		}
		service.saveOrUpdate(instockNote);
		JSONObject j = new JSONObject();
		j.put("success", true);
		this.write(j.toJSONString());
	}
	public boolean canRollback(InstockNote n){ //判断该记录是否可以撤销
		//如果采购计划处于生效状态，可以入库，那么处于0（撤销）状态下的记录可以被回滚 --即将计划入库.
//		if(n.getPurchasePlan().getStatus()==1){
//			if(n.getStatus()==0){
//				return true;
//			}else{
//				return false;
//			}
//		}
		//如果采购计划处于2 已入库状态 ，那么处于1 (入库）状态的记录可以被回滚  --即将计划撤回生效状态
		if(n.getPurchasePlan().getStatus()==2){
			if(n.getStatus()==1){
				return true;
			}else{
				return false;
			}
		}
		
		return false;
	}
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	public InstockNote getModel() {
		return this.instockNote;
	}

	public Integer getPurchasePlanId() {
		return purchasePlanId;
	}

	public void setPurchasePlanId(Integer purchasePlanId) {
		this.purchasePlanId = purchasePlanId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
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
	

}
