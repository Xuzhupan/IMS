package wudeng.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import wudeng.entity.Employee;
import wudeng.entity.Role;
import wudeng.service.RoleService;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService{
	/*public  List<Role> findAll(Role t){
		return baseDao.find("from Role where id!= 1");
	}
	public List<Role> paginationFind(Role t , int page,int rows){
		return baseDao.paginationFind("from Role where id!=1",page,rows);
	}*/
	public List<Role> findAllAndRemove(Role t,List<Role> list){
		String condition ;
		if(list.size()==0){
			condition = "";
		}else if(list.size()==1){
			condition = " where id != "+list.get(0).getId();
		}else{
			StringBuffer s = new StringBuffer() ;
			s.append("(");
			for(Role r:list){
				s.append(r.getId()).append(",");
			}
			s.deleteCharAt(s.length()-1);
			s.append(")");
			condition = " where id not in "+s.toString();
		}
		return baseDao.find("from "+t.getClass().getSimpleName()+condition);
	}
	public List<Role> paginationFindAndRemove(Role t, int page, int rows, List<Role> list) {
		String condition ;
		if(list.size()==0){
			condition = "";
		}else if(list.size()==1){
			condition = " where id != "+list.get(0).getId();
		}else{
			StringBuffer s = new StringBuffer() ;
			s.append("(");
			for(Role r:list){
				s.append(r.getId()).append(",");
			}
			s.deleteCharAt(s.length()-1);
			s.append(")");
			condition = " where id not in "+s.toString();
		}
		return baseDao.paginationFind("from "+t.getClass().getSimpleName()+condition,page,rows);
	}
	public List<Role> paginationFindStatusOfAndRemove(Role t, int page, int rows, int status,
			List<Role> list) {
		String condition ;
		if(list.size()==0){
			condition = "";
		}else if(list.size()==1){
			condition = " where id != "+list.get(0).getId();
		}else{
			StringBuffer s = new StringBuffer() ;
			s.append("(");
			for(Role r:list){
				s.append(r.getId()).append(",");
			}
			s.deleteCharAt(s.length()-1);
			s.append(")");
			condition = " where status= "+status+" and id not in "+s.toString();
		}
		return baseDao.paginationFind("from "+t.getClass().getSimpleName()+condition,page,rows);
	}
}
