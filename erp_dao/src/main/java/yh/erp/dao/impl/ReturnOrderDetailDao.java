package yh.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;

import yh.erp.dao.IReturnOrderDetailDao;
import yh.erp.entity.ReturnOrderDetail;

public class ReturnOrderDetailDao extends BaseDao<ReturnOrderDetail> implements IReturnOrderDetailDao {

	@Override
	public DetachedCriteria getDC(ReturnOrderDetail t1, ReturnOrderDetail t2, Object param) {
		DetachedCriteria dc= DetachedCriteria.forClass(getClazz());
		return dc;
	}
}
