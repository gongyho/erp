package yh.erp.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import yh.erp.dao.IBaseDao;

public abstract class BaseDao<T> extends HibernateDaoSupport implements IBaseDao<T> {
	private Class<T> clazz;
	public Class<T> getClazz() {
		return clazz;
	}

	public BaseDao() {
		this.clazz=(Class<T>)(((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}
	
	/**
	 *	 获取列表
	 */
	@Override
	public List<T> getList() {
		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		return (List<T>) super.getHibernateTemplate().findByCriteria(criteria);
	}

	/**
	 * 	获取列表 带限制条件
	 */
	@Override
	public List<T> getList(T t1, T t2, Object param, int firstResult, int maxResults) {
		DetachedCriteria dc = getDC(t1, t2, param);
		return (List<T>) getHibernateTemplate().findByCriteria(dc, firstResult, maxResults);
	}
	/**
	 * 	获取列表 不带分页
	 */
	public List<T> getList(T t1,T t2,Object param){
		DetachedCriteria dc = getDC(t1, t2, param);
		return (List<T>) getHibernateTemplate().findByCriteria(dc);
	}
	/**
	 *	 加载条目数
	 */
	@Override
	public long getCount(T t1, T t2, Object param) {
		DetachedCriteria dc = getDC(t1, t2, param);
		dc.setProjection(Projections.rowCount());
		return (Long) getHibernateTemplate().findByCriteria(dc).get(0);
	}

	/**
	 * 	保存或更新
	 */
	@Override
	public void saveOrUpdate(T t) {
		getHibernateTemplate().clear();//清除内存存的实体
		getHibernateTemplate().saveOrUpdate(t);
	}
	
	/**
	 * 保存
	 */
	@Override
	public void save(T t) {
		getHibernateTemplate().save(t);
	};
	
	/**
	 * 	更新
	 */
	@Override
	public void update(T t) {
		getHibernateTemplate().update(t);
	}
	/**
	 * 	删除
	 */
	@Override
	public void delete(int uuid) {
		T t = getHibernateTemplate().get(clazz, uuid);
		getHibernateTemplate().delete(t);
	}
	/**
	 * 	加载实体
	 */
	@Override
	public T get(int uuid) {
		return getHibernateTemplate().get(clazz, uuid);
	}
	/**
	 * 	加载实体
	 */
	@Override
	public T get(String uuid) {
		return getHibernateTemplate().get(clazz, uuid);
	}

	/**
	 *	 子类需要覆盖 根据对象数据添加限制条件，返回离线QBC
	 * 
	 * @param t1
	 * @param t2
	 * @param param
	 * @return
	 */
	public abstract DetachedCriteria getDC(T t1, T t2, Object param);
	
}
