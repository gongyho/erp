package yh.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import yh.erp.dao.IStoreDao;
import yh.erp.entity.Store;

public class StoreDao extends BaseDao<Store> implements IStoreDao {

	@Override
	public DetachedCriteria getDC(Store t1, Store t2, Object param) {
		DetachedCriteria dc= DetachedCriteria.forClass(getClazz());
		if(null!=t1) {
			if(null!=t1.getEmpUuid()) {
				dc.add(Restrictions.eq("empUuid", t1.getEmpUuid()));
			}
		}
		return dc;
	}
}
