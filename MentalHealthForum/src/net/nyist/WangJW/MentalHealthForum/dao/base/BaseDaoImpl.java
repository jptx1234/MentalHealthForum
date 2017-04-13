package net.nyist.WangJW.MentalHealthForum.dao.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import net.nyist.WangJW.MentalHealthForum.domain.PageBean;


public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

	private Class<T> domainCls;
	
	@Resource
	public void setSf(SessionFactory sf){
		super.setSessionFactory(sf);
	}
	
	@SuppressWarnings("unchecked")
	public BaseDaoImpl(){
		ParameterizedType superClass = (ParameterizedType) this.getClass().getGenericSuperclass();
		try {
			domainCls = (Class<T>) superClass.getActualTypeArguments()[0];
		} catch (IndexOutOfBoundsException | NullPointerException e) {
			throw new RuntimeException("实例化 BaseDaoImpl 时泛型不正确",e);
		}
	}
	
	@Override
	public void save(T t) {
		getHibernateTemplate().save(t);
	}

	@Override
	public void delete(T t) {
		getHibernateTemplate().delete(t);
	}

	@Override
	public void update(T t) {
		getHibernateTemplate().update(t);
	}
	
	@Override
	public void saveOrUpdate(T t) {
		getHibernateTemplate().saveOrUpdate(t);
	}

	@Override
	public T findById(Serializable id) {
		return getHibernateTemplate().get(domainCls, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listAll() {
		return getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(domainCls));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByCriteria(DetachedCriteria dc) {
		return getHibernateTemplate().findByCriteria(dc);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNamedQuery(String queryName, Object... args) {
		return getHibernateTemplate().findByNamedQuery(queryName, args);
	}

	@Override
	public void execNamedQuery(String queryName, Object... args) {
		Session session = getSession();
		Query query = session.getNamedQuery(queryName);
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		query.executeUpdate();
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		DetachedCriteria dc = pageBean.getDetachedCriteria();
		dc.setProjection(Projections.rowCount());
		List<Long> list = this.getHibernateTemplate().findByCriteria(dc);
		pageBean.setTotal(list.get(0).intValue());
		
		dc.setProjection(null);
		dc.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		int firstResult = (pageBean.getCurrentPage() - 1) * pageBean.getPageSize();
		List<T> resultList = this.getHibernateTemplate().findByCriteria(dc, firstResult, pageBean.getPageSize());
		pageBean.setRows(resultList);
		
	}


}
