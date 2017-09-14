package wudeng.service;

import java.util.List;

import wudeng.entity.Employee;

public interface EmployeeService extends BaseService<Employee>{
	//重写
	public List<Employee> paginationFindAndRemove(Employee t , int page,int rows,List<Employee> list);
	//重写
	public List<Employee> paginationFindStatusOfAndRemove(Employee t , int page,int rows,int status,List<Employee> list);
}
