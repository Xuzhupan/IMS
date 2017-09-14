package wudeng.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wudeng.dao.BaseDao;
import wudeng.entity.Permission;
import wudeng.entity.Role;
import wudeng.service.PermissionService;
import wudeng.util.PermissionFactory;
@Service
public class PerssionServiceImpl implements PermissionService{
	@Autowired
	private BaseDao baseDao;
	public Permission getPermission(int roleId) {
		Role r = (Role) baseDao.get(Role.class, roleId);
		Permission p = new Permission();
		PermissionFactory.init(r, p);
		return p;
	}

}
