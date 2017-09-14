package wudeng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wudeng.dao.BaseDao;
import wudeng.entity.Menu;
import wudeng.entity.Role;
import wudeng.service.IndexService;
@Service
public class IndexServiceImpl extends BaseServiceImpl implements IndexService{
	@Autowired
	private BaseDao baseDao;

	public Role findRoleById(int id) {
		List<Role> list = baseDao.getCurrentSession().createQuery("from Role where id = "+id).list();
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	public Menu findMenuById(int id) {
		return (Menu) baseDao.getCurrentSession().get(Menu.class,id);
	}

}
