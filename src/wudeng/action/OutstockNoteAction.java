package wudeng.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;

import myJson.OutstockNoteJson;
import wudeng.entity.Employee;
import wudeng.entity.OutstockNote;
import wudeng.entity.PurchaseInfo;
import wudeng.entity.PurchasePlan;
import wudeng.entity.SellInfo;
import wudeng.entity.SellPlan;
import wudeng.service.ObjectService;

@Controller
public class OutstockNoteAction extends BaseAction implements ModelDriven<OutstockNote>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OutstockNote outstockNote = new OutstockNote();
	private Integer sellPlanId;
	private Integer employeeId;
	private int rows;
	private int page;

	@Autowired
	private ObjectService service;
	public void paginationFindAll(){
		List<OutstockNote> list = service.findAll(outstockNote);
		JSONObject j = new JSONObject();
		if(list!=null && list.size()>0){
			List<OutstockNoteJson> noteJsons = new ArrayList<OutstockNoteJson>();
			List<OutstockNote> li = service.paginationFind(outstockNote, page, rows);
			OutstockNoteJson noteJson ;
			for(OutstockNote n : li){
				noteJson = new OutstockNoteJson();
				noteJson.setOutstockNoteId(n.getId());
				noteJson.setSellPlanId(n.getSellPlan().getId());
				noteJson.setSellPlanCustomerId(n.getSellPlan().getCustomer().getId());
				noteJson.setPlan_employeeId(n.getSellPlan().getEmployee().getId());
				noteJson.setPlan_employeeName(n.getSellPlan().getEmployee().getName());
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
	public void outstock(){
		JSONObject j = new JSONObject();
		SellPlan plan = (SellPlan) service.get(SellPlan.class, sellPlanId);
		if(plan.getStatus()==1){
			plan.setStatus(2); //2 入库成功
			service.saveOrUpdate(plan);
			Employee employee = (Employee) service.get(Employee.class, employeeId);
			outstockNote.setSellPlan(plan);
			outstockNote.setEmployee(employee);
			outstockNote.setCreateDate(new Date(System.currentTimeMillis())); 
			outstockNote.setStatus(Integer.valueOf(1));//1 入库操作
			Set<SellInfo> set =  plan.getSellInfos();
			Integer number;
			for(SellInfo si : set){
				number = si.getGoods().getRepertory().getNumber();//获得原库存
				number = number - si.getNumber(); //原库存减去出库的数量
				si.getGoods().getRepertory().setNumber(number);
				service.saveOrUpdate(si);
			}
			service.saveOrUpdate(outstockNote);
			j.put("success", true);
			this.write(j.toJSONString());
		}else{
			j.put("success", false);
			this.write(j.toJSONString());
		}
	}
	public void rollback(){
		SellPlan plan = (SellPlan) service.get(SellPlan.class, sellPlanId); //拿到计划Id
		plan.setStatus(1); //修改状态为 1 有效
		Employee employee = (Employee) service.get(Employee.class, employeeId); //操作人
		outstockNote.setSellPlan(plan);//新记录 撤销出库记录
		outstockNote.setEmployee(employee);
		outstockNote.setCreateDate(new Date());
		outstockNote.setStatus(Integer.valueOf(0)); //设置记录状态 撤销操作
		Set<SellInfo> set =  plan.getSellInfos();
		Integer number;
		for(SellInfo si : set){
			number = si.getGoods().getRepertory().getNumber();//获得原库存
			number = number + si.getNumber(); //原库存加回的数量
			si.getGoods().getRepertory().setNumber(number);
			service.saveOrUpdate(si);
		}
		service.saveOrUpdate(outstockNote);
		JSONObject j = new JSONObject();
		j.put("success", true);
		this.write(j.toJSONString());
	}
	
	public boolean canRollback(OutstockNote n){ //判断该记录是否可以撤销
		//如果采购计划处于生效状态，可以入库，那么处于0（撤销）状态下的记录可以被回滚 --即将计划入库.
//		if(n.getSellPlan().getStatus()==1){
//			if(n.getStatus()==0){
//				return true;
//			}else{
//				return false;
//			}
//		}
		//如果采购计划处于2 已入库状态 ，那么处于1 (入库）状态的记录可以被回滚  --即将计划撤回生效状态
		if(n.getSellPlan().getStatus()==2){
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
	public OutstockNote getModel() {
		return this.outstockNote;
	}

	public Integer getSellPlanId() {
		return sellPlanId;
	}

	public void setSellPlanId(Integer sellPlanId) {
		this.sellPlanId = sellPlanId;
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
