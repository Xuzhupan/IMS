package wudeng.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import wudeng.entity.Employee;
import wudeng.entity.User;
import wudeng.service.EmployeeService;
@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements EmployeeService{
	//重写
		/* 分页查询基础上 移除指定对象 */
		public List<Employee> paginationFindAndRemove(Employee t , int page,int rows,List<Employee> list){
			String condition ;
			if(list.size()==0){
				condition = "";
			}else if(list.size()==1){
				condition = " where id != "+list.get(0).getId();
			}else{
				StringBuffer s = new StringBuffer() ;
				s.append("(");
				for(Employee e:list){
					s.append(e.getId()).append(",");
				}
				s.deleteCharAt(s.length()-1);
				s.append(")");
				condition = " where id not in "+s.toString();
			}
			return baseDao.paginationFind("from "+t.getClass().getSimpleName()+condition,page,rows);
			
		}
		/* 分页查询制定状态的基础上 移除指定对象 */
		public List<Employee> paginationFindStatusOfAndRemove(Employee t , int page,int rows,int status,List<Employee> list){
			String condition ;
			if(list.size()==0){
				condition = "";
			}else if(list.size()==1){
				condition = " where id != "+list.get(0).getId();
			}else{
				StringBuffer s = new StringBuffer() ;
				s.append("(");
				for(Employee e:list){
					s.append(e.getId()).append(",");
				}
				s.deleteCharAt(s.length()-1);
				s.append(")");
				condition = " where status= "+status+" and id not in "+s.toString();
			}
			return baseDao.paginationFind("from "+t.getClass().getSimpleName()+condition,page,rows);
			
		}
}
