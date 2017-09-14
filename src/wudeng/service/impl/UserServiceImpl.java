package wudeng.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import wudeng.entity.User;
import wudeng.service.UserService;
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
	//重写
	/* 分页查询基础上 移除指定对象 */
	public List<User> paginationFindAndRemove(User t , int page,int rows,List<User> list){
		String condition ;
		if(list.size()==0){
			condition = "";
		}else if(list.size()==1){
			condition = " where username != \'"+list.get(0).getUsername()+"\'";
		}else{
			StringBuffer s = new StringBuffer() ;
			s.append("(");
			for(User u:list){
				s.append("\'").append(u.getUsername()).append("\'").append(",");
			}
			s.deleteCharAt(s.length()-1);
			s.append(")");
			condition = " where username not in "+s.toString();
		}
		return baseDao.paginationFind("from "+t.getClass().getSimpleName()+condition,page,rows);
		
	}
	
	public List<User> search(User t, Map<String, Object> m) {
		List<User> list = null;
		StringBuffer condition = new StringBuffer();
		condition.append("from User ");
		if(m!=null && m.size()>0){
			condition.append("where ");
			if(m.get("username")!=null){
				condition.append(" username like "+m.get("username") ).append(" and");
			}
			if(m.get("role")!=null){
				condition.append(" role.id = "+m.get("role")).append(" and");
			}
			if(m.get("status")!=null){
				condition.append(" status = "+m.get("status")).append(" and");
			}
			condition.delete(condition.length()-3, condition.length());
		}
		list = baseDao.find(condition.toString());
		return list;
	}
}
