package wudeng.action;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;

import myJson.PurchaseInfoJson;
import myJson.SellInfoJson;
import wudeng.entity.Customer;
import wudeng.entity.Employee;
import wudeng.entity.Goods;
import wudeng.entity.PurchaseInfo;
import wudeng.entity.PurchasePlan;
import wudeng.entity.SellInfo;
import wudeng.entity.SellPlan;
import wudeng.entity.Supplier;
import wudeng.service.ObjectService;

@Controller
public class SellInfoAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer sellPlanId;
	private String planName;
	private SellPlan sellPlan;
	private Integer customerId;
	private Integer employeeId;
	private List<Integer> deleteIds;
	private String entities;
	
	@Autowired
	private ObjectService service;
	
	public void findPlanInfo(){
		if(sellPlanId!=null){
			sellPlan = (SellPlan) service.get(SellPlan.class, sellPlanId);
			Set<SellInfo> set = sellPlan.getSellInfos();
			Set<SellInfoJson> myset = new HashSet<SellInfoJson>();
			SellInfoJson infoJson = null;
			for(SellInfo s : set){
				infoJson = new SellInfoJson();
				infoJson.setSellInfoId(s.getId());
				infoJson.setSellInfoNumber(s.getNumber());
				infoJson.setSellInfoUnitPrice(s.getUnitPrice());
				infoJson.setGoodsId(s.getGoods().getId());
				infoJson.setGoodsName(s.getGoods().getName());
				infoJson.setGoodsType(s.getGoods().getType());
				infoJson.setGoodsUnit(s.getGoods().getUnit());
				infoJson.setGoodsInformation(s.getGoods().getInformation());
				infoJson.setGoodsStatus(s.getGoods().getStatus());
				myset.add(infoJson);
			}
			Properties j = new Properties();
			if(set!=null && set.size()>0){
				j.put("total", myset.size());
				j.put("rows", myset);
				System.out.println(JSON.toJSONString(j));
				this.write(JSON.toJSONString(j));
			}
		}
	}
	public void saveOrUpdate(){
		entities = entities.replace("undefined", "");
		entities = entities.replace("}{", "},{");
		entities = "[" + entities.toString() + "]";
		Customer customer = (Customer) service.get(Customer.class, customerId);
		if(sellPlanId!=null){
			sellPlan = (SellPlan) service.get(SellPlan.class, sellPlanId);
			sellPlan.setCustomer(customer);
			sellPlan.setPlanName(planName);
		}else{
			sellPlan = new SellPlan();
			Employee employee = (Employee) service.get(Employee.class, employeeId);
			sellPlan.setEmployee(employee);
			sellPlan.setCustomer(customer);
			sellPlan.setPlanName(planName);
			sellPlan.setCreateDate(new Date());
			sellPlan.setStatus(0);
		}
		SellInfo info;
		Goods goods = null;
		Set<SellInfo> set = new HashSet<SellInfo>();
		List<SellInfoJson> infoJsons = JSON.parseArray(entities, SellInfoJson.class);
		for(SellInfoJson s:infoJsons){
			if(s.getSellInfoId()==null){
				info = new SellInfo();
				if(s.getGoodsId()!=null){
					goods = (Goods) service.get(Goods.class, s.getGoodsId());
				}
				info.setGoods(goods);
				info.setUnitPrice(s.getSellInfoUnitPrice());
				info.setNumber(s.getSellInfoNumber());
				info.setSellPlan(sellPlan);
				set.add(info);
			}else{
				info = (SellInfo) service.get(SellInfo.class, s.getSellInfoId());
				if(s.getGoodsId()!=null){
					goods = (Goods) service.get(Goods.class, s.getGoodsId());
				}
				info.setGoods(goods);
				info.setUnitPrice(s.getSellInfoUnitPrice());
				info.setNumber(s.getSellInfoNumber());
				info.setSellPlan(sellPlan);
				set.add(info);
			}
		}
		
		sellPlan.setSellInfos(set);
		service.merge(sellPlan);
		
		Properties j = new Properties();
		j.put("success", true);
		j.put("message", "保存成功");
		this.write(JSON.toJSONString(j));
	}
	public void deleteRows(){
		Properties j = new Properties();
		SellPlan plan = (SellPlan) service.get(SellPlan.class, sellPlanId);
		SellInfo info ;
		if(deleteIds.size()>0){
			for(Integer i : deleteIds){
				info = (SellInfo) service.get(SellInfo.class, i);
				service.delete(info);
			}
		}
		j.put("success", true);
		j.put("message", "删除成功");
		this.write(JSON.toJSONString(j));
	}
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	public Integer getSellPlanId() {
		return sellPlanId;
	}
	public void setSellPlanId(Integer sellPlanId) {
		this.sellPlanId = sellPlanId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public List<Integer> getDeleteIds() {
		return deleteIds;
	}
	public void setDeleteIds(List<Integer> deleteIds) {
		this.deleteIds = deleteIds;
	}
	public String getEntities() {
		return entities;
	}
	public void setEntities(String entities) {
		this.entities = entities;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	

}
