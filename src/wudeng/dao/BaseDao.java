package wudeng.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

/**
 * CRUD通用接口
 * @author HQJ
 * @param <T>
 */
public interface BaseDao<T> {
	public Session getCurrentSession();
	
	/**
	 * 
	 * 作者：HQJ
	 * 功能：保存
	 * 时间：下午4:29:59
	 *@return
	 */
	public Serializable save(T o);
	/**
	 * 
	 * 作者：HQJ
	 * 功能：删除一个对象
	 * 时间：下午4:31:37
	 *@param o
	 */
	public void delete(T o);
	
	/**
	 * 
	 * 作者：HQJ
	 * 功能：根据查询语句查出
	 * 时间：下午4:32:32
	 *@param hql
	 *@return
	 */
	public List<T> find(String hql);
	/* 分页查询 */ 
	public List<T> paginationFind(String hql , int page,int rows);
	/**
	 * 
	 * 作者：HQJ
	 * 功能：按照参数查询
	 * 时间：下午4:34:41
	 *@param hql
	 *@param param
	 *@return
	 */
	public List<T> find(String hql,Map<String, Object> param);
	
	/**
	 * 
	 * 作者：HQJ
	 * 功能：根据id查询
	 * 时间：下午4:35:53
	 *@param c
	 *@param id
	 *@return
	 */
	public T get(Class<T> c,Serializable id);
	
	/**
	 * 
	 * 作者：HQJ
	 * 功能：根据hql查询
	 * 时间：下午4:36:35
	 *@param hql
	 *@return
	 */
	public T get(String hql);
	/**
	 * 
	 * 作者：HQJ
	 * 功能：根据参数查询
	 * 时间：下午4:38:09
	 *@param hql
	 *@param param
	 *@return
	 */
	public T get(String hql,Map<String, Object> param);
	/**
	 * 
	 * 作者：HQJ
	 * 功能：保存或者更新对象
	 * 时间：下午4:37:54
	 *@param t
	 */
	public void saveOrUpdate(T t);
	
	public void merge(T o);

}
