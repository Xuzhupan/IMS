package wudeng.service;

import java.util.List;

import wudeng.entity.Employee;
import wudeng.entity.Role;

public interface RoleService extends BaseService<Role>{
	//重写
	public List<Role> findAllAndRemove(Role t,List<Role> list);
	//重写
	public List<Role> paginationFindAndRemove(Role t , int page,int rows,List<Role> list);
	//重写
	public List<Role> paginationFindStatusOfAndRemove(Role t , int page,int rows,int status,List<Role> list);
}
