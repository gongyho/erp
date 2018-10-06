package yh.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;

import yh.erp.dao.IStoreOperDao;
import yh.erp.entity.StoreOper;

public class StoreOperDao extends BaseDao<StoreOper> implements IStoreOperDao {

	@Override
	public DetachedCriteria getDC(StoreOper t1, StoreOper t2, Object param) {
		DetachedCriteria dc= DetachedCriteria.forClass(getClazz());
		return dc;
	}
}
