package wudeng.service;

import java.util.List;
import java.util.Map;

import wudeng.entity.User;

public interface UserService extends BaseService<User>{
	//重写
	public List<User> paginationFindAndRemove(User t , int page,int rows,List<User> list);
	//重写
	public List<User> search(User t,Map<String, Object> m);
}
