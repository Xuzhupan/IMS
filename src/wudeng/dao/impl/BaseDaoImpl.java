package wudeng.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wudeng.dao.BaseDao;
@Repository
public class BaseDaoImpl<T> implements BaseDao<T> {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}

	public Serializable save(T o) {
		return null;
	}

	public void delete(T o) {
		if(o!=null){
			this.getCurrentSession().delete(o);
		}
	}

	public List<T> find(String hql) {
		return getCurrentSession().createQuery(hql).list();
	}
	/* 分页查询 */
	public List<T> paginationFind(String hql , int page,int rows){
		int currentpage = (page == 0) ? 1: page;//第几页  
        int pagesize = rows==0 ? 10: rows;//每页多少行
		return getCurrentSession().createQuery(hql).setFirstResult((currentpage-1)*pagesize).setMaxResults(pagesize).list();
	}
	
	public List<T> find(String hql, Map<String, Object> param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if(param !=null && !param.isEmpty()){
			for(String key:param.keySet()){
				q.setParameter(key, param.get(key));
			}
		}
		return q.list();
	}

	public T get(Class<T> c, Serializable id) {
		return (T)this.getCurrentSession().get(c, id);
	}

	public T get(String hql) {
		List<T> l = this.find(hql);
		if(l!=null&&l.size()>0){
			return l.get(0);
		}
		return null;
	}

	public T get(String hql, Map<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveOrUpdate(T o) {
		if(o != null){
			this.getCurrentSession().saveOrUpdate(o);
		}
		
	}
	
	public void merge(T o){
		if(o!= null){
			this.getCurrentSession().merge(o);
		}
	}

}
