package wudeng.service;

import java.util.List;

import wudeng.entity.Permission;
import wudeng.entity.PurchasePlan;

public interface PurchasePlanService extends BaseService<PurchasePlan>{
	public List<PurchasePlan> findStatusOf(int status);
}
