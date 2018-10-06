package yh.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import yh.erp.dao.IOrdersDao;
import yh.erp.entity.Orders;

public class OrdersDao extends BaseDao<Orders> implements IOrdersDao {

	@Override
	public DetachedCriteria getDC(Orders t1, Orders t2, Object param) {
		DetachedCriteria dc= DetachedCriteria.forClass(getClazz());
		if(null!=t1) {
			if(null!=t1.getState()&&t1.getState().trim().length()>0) {
				dc.add(Restrictions.eq("state", t1.getState()));
			}
			if(null!=t1.getType()&&t1.getType().trim().length()>0) {
				dc.add(Restrictions.eq("type", t1.getType()));
			}
			if(null!=t1.getCreater()) {
				dc.add(Restrictions.eq("creater", t1.getCreater()));
			}
		}
		return dc;
	}
}
