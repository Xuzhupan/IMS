package test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.xml.internal.stream.Entity;

import wudeng.entity.Goods;
import wudeng.entity.PurchaseInfo;
import wudeng.entity.PurchasePlan;
import wudeng.entity.Supplier;
import wudeng.service.ObjectService;
@Controller
public class PermissionTest extends ActionSupport{
	@Autowired
	private ObjectService service;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public void initPermission(){
		/*Role r = service.findRole();
		System.out.println(r.getName());
		Permission p = new Permission();
		PermissionFactory.init(r, p);
		System.out.println(p.toString());
		Menu u = service.findById(1);*/
	/*	User u = service.findUserByName("q");
		System.out.println(u.getEmployee().getBirthday());
		System.out.println(u.getEmployee().getDepartment().getName());
		System.out.println(u.getEmployee().getRole().getName());
		System.out.println(JSON.toJSONString(u));*/
		
		/*Goods g = service.findGoodstById(1);
		System.out.println(g.getRepertory().getNumber());
		System.out.println(g.getRepertoryLimit().getMaxNumber());*/
		
		PurchasePlan pp = service.findPurchasePlanById(2);
		Set<PurchaseInfo> set = pp.getPurchaseInfos();
		Goods g = service.findGoodstById(4);
		PurchaseInfo pi = new PurchaseInfo();
		Supplier sp = (Supplier) service.get(Supplier.class, 1);
		pi.setGoods(g);
		pi.setSupplier(sp);
		pi.setNumber(88);
		pi.setUnitPrice(33D);
		pi.setPurchasePlan(pp);
		set.add(pi);
		pp.setPurchaseInfos(set);
		
		service.merge(pp);
//				
//		System.out.println(pp.getEmployee().getName());
//		PurchaseInfo pi = service.findPurchaseInfoById(4);
//		System.out.println(pi.getPurchasePlan().getEmployee().getName());
//		System.out.println(pi.getSupplier().getName());
		
	}
	
}
