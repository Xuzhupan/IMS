package wudeng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wudeng.dao.BaseDao;
import wudeng.entity.Employee;
import wudeng.entity.User;
import wudeng.service.LoginService;
@Service
public class LoginServiceImpl extends BaseServiceImpl implements LoginService{
	@Autowired
	private BaseDao baseDao;
	
	public User getLoginUser(User u) {
		List<User> list = baseDao.getCurrentSession().createQuery("from User where username='"+u.getUsername()+"' and password='"+u.getPassword()+"' and status=1 ").list();
		if(list.size()!=0){
			return list.get(0); 
		}
		return null;
	}

}
