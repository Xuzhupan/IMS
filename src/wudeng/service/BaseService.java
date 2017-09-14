package wudeng.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseService<T> {

	public List<T> find(String hql);
	
	public  List<T> findAll(T t);
	//统计总记录条数
	public Long count(T t);
	public List<T> findStatusOf(T t,int status);
	/* 分页查询 */
	public List<T> paginationFind(T t ,int page,int rows);
	public List<T> paginationFind(T t, String condition, int page, int rows);
	public List<T> paginationFindStatusOf(T t , int page,int rows ,int status);
	
	public T findById(T t);
	
	public T findById(T t , int id);
	
	public T get(Class<T> c, Serializable id);
	
	public T get(String hql);
	
	public T saveOrUpdate(T t);
	
	public void merge(T o);
	
	public void delete(T t);
	
	public List<T> search(T t,Map<String, Object> m);
}