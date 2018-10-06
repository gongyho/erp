package yh.erp.dao;

import java.util.List;

public interface IBaseDao <T> {
	List<T> getList();
	List<T> getList(T t1,T t2,Object param,int firstResult,int maxResults);
	List<T> getList(T t1,T t2,Object param);
	long getCount(T t1,T t2,Object param);
	void saveOrUpdate(T t);
	void save(T t);
	void update(T t);
	void delete(int uuid);
	T get(int uuid);
	T get(String uuid);
}
