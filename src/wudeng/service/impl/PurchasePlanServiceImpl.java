package wudeng.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import wudeng.entity.PurchasePlan;
import wudeng.service.PurchasePlanService;

@Service
public class PurchasePlanServiceImpl extends BaseServiceImpl<PurchasePlan> implements PurchasePlanService{
	public List<PurchasePlan> findStatusOf(int status){
		List<PurchasePlan> list = baseDao.find("from PurchasePlan where status = "+status);
		if(list.size()>0){
			return list;
		}
		return null;
	}
}
