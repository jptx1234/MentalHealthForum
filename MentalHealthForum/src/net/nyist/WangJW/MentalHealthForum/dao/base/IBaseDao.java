package net.nyist.WangJW.MentalHealthForum.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import net.nyist.WangJW.MentalHealthForum.domain.PageBean;


public interface IBaseDao<T> {

	void save(T t);
	
	void delete(T t);
	
	void update(T t);
	
	void saveOrUpdate(T t);
	
	T findById(Serializable id);
	
	List<T> listAll();
	
	List<T> findByCriteria(DetachedCriteria dc);
	
	List<T> findByNamedQuery(String queryName, Object... args);
	
	void execNamedQuery(String queryName, Object... args);
	
	void pageQuery(PageBean pageBean);
}
