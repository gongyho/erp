package yh.erp.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import yh.erp.entity.StoreAlert;
import yh.erp.entity.StoreDetail;

public class StoreDetailDao extends BaseDao<StoreDetail> implements yh.erp.dao.IStoreDetailDao {

	@Override
	public DetachedCriteria getDC(StoreDetail t1, StoreDetail t2, Object param) {
		DetachedCriteria dc= DetachedCriteria.forClass(getClazz());
		if(null!=t1) {
			if(null!=t1.getGoodsUuid()) {
				dc.add(Restrictions.eq("goodsUuid", t1.getGoodsUuid()));
			}
			if(null!=t1.getStoreUuid()) {
				dc.add(Restrictions.eq("storeUuid", t1.getStoreUuid()));
			}
		}
		return dc;
	}

	@Override
	public List<StoreAlert> getStoreAlertList() {
		return (List<StoreAlert>) getHibernateTemplate().find("from StoreAlert where storeNum<outNum");
	}
}
