package wudeng.service;

import wudeng.entity.Menu;
import wudeng.entity.Role;

public interface IndexService extends BaseService {
	public Role findRoleById(int id);
	public Menu findMenuById(int id);
}
