package yh.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;

import yh.erp.dao.IReturnOrdersDao;
import yh.erp.entity.ReturnOrders;

public class ReturnOrdersDao extends BaseDao<ReturnOrders> implements IReturnOrdersDao {

	@Override
	public DetachedCriteria getDC(ReturnOrders t1, ReturnOrders t2, Object param) {
		DetachedCriteria dc= DetachedCriteria.forClass(getClazz());
		return dc;
	}

}
