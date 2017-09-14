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
import wudeng.entity.Employee;
import wudeng.entity.Goods;
import wudeng.entity.PurchaseInfo;
import wudeng.entity.PurchasePlan;
import wudeng.entity.Supplier;
import wudeng.service.ObjectService;

@Controller
public class PurchaseInfoAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer purchasePlanId;
	private String planName;
	private Integer employeeId;
	private List<Integer> deleteIds;
	private PurchasePlan purchasePlan;
	private String entities;
	@Autowired
	private ObjectService service;
	public void findPlanInfo(){
		if(purchasePlanId!=null){
			purchasePlan = (PurchasePlan) service.get(PurchasePlan.class, purchasePlanId);
			Set<PurchaseInfo> set = purchasePlan.getPurchaseInfos();
			Set<PurchaseInfoJson> myset = new HashSet<PurchaseInfoJson>();
			PurchaseInfoJson infoJson = null;
			for(PurchaseInfo p : set){
				infoJson = new PurchaseInfoJson();
				infoJson.setPurchaseInfoId(p.getId());
				infoJson.setPurchaseInfoNumber(p.getNumber());
				infoJson.setPurchaseInfoUnitPrice(p.getUnitPrice());
				infoJson.setGoodsId(p.getGoods().getId());
				infoJson.setGoodsName(p.getGoods().getName());
				infoJson.setGoodsType(p.getGoods().getType());
				infoJson.setGoodsUnit(p.getGoods().getUnit());
				infoJson.setGoodsInformation(p.getGoods().getInformation());
				infoJson.setGoodsStatus(p.getGoods().getStatus());
				infoJson.setSupplierId(p.getSupplier().getId());
				infoJson.setSupplierName(p.getSupplier().getName());
				infoJson.setSupplierMessenger(p.getSupplier().getMessenger());
				infoJson.setSupplierTel(p.getSupplier().getTel());
				infoJson.setSupplierTaxpayerIdentity(p.getSupplier().getTaxpayerIdentity());
				infoJson.setSupplierBankName(p.getSupplier().getBankName());
				infoJson.setSupplierBankAccount(p.getSupplier().getBankAccount());
				infoJson.setSupplierRegisterTel(p.getSupplier().getRegisterTel());
				infoJson.setSupplierRegisterAddress(p.getSupplier().getRegisterAddress());
				infoJson.setSupplierInformation(p.getSupplier().getInformation());
				infoJson.setSupplierCreateDate(p.getSupplier().getCreateDate());
				infoJson.setSupplierStatus(p.getSupplier().getStatus());
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
		if(purchasePlanId!=null){
			purchasePlan = (PurchasePlan) service.get(PurchasePlan.class, purchasePlanId);
		}else{
			purchasePlan = new PurchasePlan();
			Employee employee = (Employee) service.get(Employee.class, employeeId);
			purchasePlan.setEmployee(employee);
			purchasePlan.setCreateDate(new Date());
			purchasePlan.setStatus(0);
		}
		purchasePlan.setPlanName(planName);
		PurchaseInfo info;
		Goods goods = null;
		Supplier supplier = null;
		Set<PurchaseInfo> set = new HashSet<PurchaseInfo>();
		List<PurchaseInfoJson> infoJsons = JSON.parseArray(entities, PurchaseInfoJson.class);
		for(PurchaseInfoJson j:infoJsons){
			if(j.getPurchaseInfoId()==null){
				info = new PurchaseInfo();
				if(j.getGoodsId()!=null){
					goods = (Goods) service.get(Goods.class, j.getGoodsId());
				}
				if(j.getSupplierId()!=null){
					supplier = (Supplier) service.get(Supplier.class, j.getSupplierId());
				}
				info.setGoods(goods);
				info.setSupplier(supplier);
				info.setUnitPrice(j.getPurchaseInfoUnitPrice());
				info.setNumber(j.getPurchaseInfoNumber());
				info.setPurchasePlan(purchasePlan);
				set.add(info);
			}else{
				info = (PurchaseInfo) service.get(PurchaseInfo.class, j.getPurchaseInfoId());
				if(j.getGoodsId()!=null){
					goods = (Goods) service.get(Goods.class, j.getGoodsId());
				}
				if(j.getSupplierId()!=null){
					supplier = (Supplier) service.get(Supplier.class, j.getSupplierId());
				}
				info.setGoods(goods);
				info.setSupplier(supplier);
				info.setUnitPrice(j.getPurchaseInfoUnitPrice());
				info.setNumber(j.getPurchaseInfoNumber());
				info.setPurchasePlan(purchasePlan);
				set.add(info);
			}
		}
		
		purchasePlan.setPurchaseInfos(set);
		service.merge(purchasePlan);
		
		Properties j = new Properties();
		j.put("success", true);
		j.put("message", "保存成功");
		this.write(JSON.toJSONString(j));
	}
	public void deleteRows(){
		Properties j = new Properties();
		PurchasePlan plan = (PurchasePlan) service.get(PurchasePlan.class, purchasePlanId);
		PurchaseInfo info ;
		if(deleteIds.size()>0){
			for(Integer i : deleteIds){
				info = (PurchaseInfo) service.get(PurchaseInfo.class, i);
				service.delete(info);
			}
		}
		j.put("success", true);
		j.put("message", "删除成功");
		this.write(JSON.toJSONString(j));
	}
	
	public Integer getPurchasePlanId() {
		return purchasePlanId;
	}
	public void setPurchasePlanId(Integer purchasePlanId) {
		this.purchasePlanId = purchasePlanId;
	}


	public String getEntities() {
		return entities;
	}


	public void setEntities(String entities) {
		this.entities = entities;
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

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}


}
