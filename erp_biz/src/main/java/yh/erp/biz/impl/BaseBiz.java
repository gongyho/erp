package yh.erp.biz.impl;

import java.util.List;

import yh.erp.biz.IBaseBiz;
import yh.erp.dao.IBaseDao;

public class BaseBiz<T> implements IBaseBiz<T> {
	private IBaseDao<T> baseDao;
	
	public void setBaseDao(IBaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}
	@Override
	public List<T> getList() {
		return baseDao.getList();
	}
	@Override
	public List<T> getList(T t1,T t2,Object param,int firstResult,int maxResults) {
		return baseDao.getList(t1,t2,param,firstResult,maxResults);
	}
	@Override
	public long getCount(T t1,T t2,Object param) {
		return baseDao.getCount(t1,t2,param);
	}
	@Override
	public void saveOrUpdate(T t) {
		baseDao.saveOrUpdate(t);
	}
	@Override
	public void delete(int uuid) {
		baseDao.delete(uuid);
	}
	@Override
	public T get(int uuid) {
		return (T) baseDao.get(uuid);
	}
	@Override
	public T get(String uuid) {
		return (T) baseDao.get(uuid);
	}
	@Override
	public List<T> getList(T t1, T t2, Object param) {
		return baseDao.getList(t1, t2, param);
	}
}
