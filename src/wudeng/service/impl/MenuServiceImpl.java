package wudeng.service.impl;


import org.springframework.stereotype.Service;

import wudeng.entity.Menu;
import wudeng.service.MenuService;
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService{

	public Menu findById(int id) {
		return baseDao.find("from Menu where id= "+id).get(0);
	}

}
