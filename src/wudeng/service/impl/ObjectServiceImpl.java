package wudeng.service.impl;

import org.springframework.stereotype.Service;

import wudeng.entity.Goods;
import wudeng.entity.Menu;
import wudeng.entity.PurchaseInfo;
import wudeng.entity.PurchasePlan;
import wudeng.entity.Role;
import wudeng.entity.User;
import wudeng.service.ObjectService;
@Service
public class ObjectServiceImpl extends BaseServiceImpl implements ObjectService{

	public Role findRole() {
		return (Role) this.baseDao.getCurrentSession().get(Role.class, 1);
	}

	public Menu findById(int id) {
		return (Menu) baseDao.getCurrentSession().get(Menu.class, id);
	}

	public User findUserByName(String username) {
		return (User) baseDao.getCurrentSession().createQuery("from User where username='"+username+"'").list().get(0);
	}

	public Goods findGoodstById(int id) {
		return  (Goods) baseDao.getCurrentSession().get(Goods.class, id);
	}

	public PurchasePlan findPurchasePlanById(int id) {
		return   (PurchasePlan) baseDao.getCurrentSession().get(PurchasePlan.class, id);
	}

	public PurchaseInfo findPurchaseInfoById(int id) {
		return   (PurchaseInfo) baseDao.getCurrentSession().get(PurchaseInfo.class, id);
	}
	
}
