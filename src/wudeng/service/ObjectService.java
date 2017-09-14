package wudeng.service;

import wudeng.entity.Goods;
import wudeng.entity.Menu;
import wudeng.entity.PurchaseInfo;
import wudeng.entity.PurchasePlan;
import wudeng.entity.Role;
import wudeng.entity.User;

public interface ObjectService extends BaseService{
	public Role findRole();
	public Menu findById(int id);
	public User findUserByName(String s);
	public Goods findGoodstById(int id);
	public PurchasePlan findPurchasePlanById(int id);
	public PurchaseInfo findPurchaseInfoById(int id);
}
