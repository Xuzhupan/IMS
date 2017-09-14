package wudeng.service.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wudeng.dao.BaseDao;
import wudeng.entity.User;
import wudeng.service.BaseService;
@Service
public class BaseServiceImpl<T> implements BaseService<T> {
	@Autowired
	protected BaseDao<T> baseDao;

	public List<T> find(String hql){
		return baseDao.find(hql);
	}
	
	public  List<T> findAll(T t) {
		return baseDao.find("from "+t.getClass().getSimpleName());
	}
	public Long count(T t){
		return (Long) baseDao.getCurrentSession().createQuery("select count(*) from "+t.getClass().getSimpleName()).uniqueResult();
	}
	public List<T> findStatusOf(T t,int status){
		return baseDao.find("from "+t.getClass().getSimpleName()+" where status = "+status);
	}
	
	/* 分页查询 */
	public List<T> paginationFind(T t , int page,int rows){
		return baseDao.paginationFind("from "+t.getClass().getSimpleName(),page,rows);
	}
	
	public List<T> paginationFind(T t ,String condition, int page,int rows){
		return baseDao.paginationFind("from "+t.getClass().getSimpleName()+" "+condition+" ",page,rows);
	}
	
	public List<T> paginationFindStatusOf(T t , int page,int rows ,int status){
		return baseDao.paginationFind("from "+t.getClass().getSimpleName()+" where status= "+status,page,rows);
	}
	
	public T findById(T t) {
		Integer i = null;
		try {
			i = (Integer) t.getClass().getDeclaredMethod("getId",null).invoke(t, null);
			System.out.println("----"+i);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<T> list = baseDao.find("from "+t.getClass().getSimpleName()+" where id= "+i);
		if(list.size()>0){
			return list.get(0);
		}
		return null; 
	}
	
	public T findById(T t ,int id){
		List<T> list = baseDao.find("from "+t.getClass().getSimpleName()+" where id= "+id);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	public T saveOrUpdate(T t) {
		baseDao.saveOrUpdate(t);
		return t;
	}
	
	public void merge(T o){
		baseDao.merge(o);
	}
	
	public void delete(T t) {
		baseDao.delete(t);
	}
	

	public T get(Class<T> c, Serializable id) {
		return baseDao.get(c, id);
	}

	public T get(String hql) {
		return baseDao.get(hql);
	}

	public List<T> search(T t, Map<String, Object> m) {
		List<T> list = null;
		StringBuffer condition = new StringBuffer();
		condition.append("from "+t.getClass().getSimpleName()+" ");
		if(m!=null && m.size()>0){
			condition.append(" where ");
			for(Map.Entry<String, Object> e : m.entrySet()){
				condition.append(" "+e.getKey()).append(e.getValue()).append(" and");
			}
			condition.delete(condition.length()-3, condition.length());
		}
		list = baseDao.find(condition.toString());
		return list;
	}
	

}
