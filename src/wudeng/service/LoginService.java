package wudeng.service;

import wudeng.entity.Employee;
import wudeng.entity.User;

public interface LoginService extends BaseService {
	public User getLoginUser(User u);
}
