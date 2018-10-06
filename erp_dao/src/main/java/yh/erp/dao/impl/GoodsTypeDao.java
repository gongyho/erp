package yh.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;

import yh.erp.dao.IGoodsTypeDao;
import yh.erp.entity.GoodsType;

public class GoodsTypeDao extends BaseDao<GoodsType> implements IGoodsTypeDao {

	@Override
	public DetachedCriteria getDC(GoodsType t1, GoodsType t2, Object param) {
		DetachedCriteria dc=DetachedCriteria.forClass(getClazz());
		return dc;
	}

	
}
