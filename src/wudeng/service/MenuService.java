package wudeng.service;

import wudeng.entity.Menu;

public interface MenuService extends BaseService<Menu>{
	public Menu findById(int id);
}
