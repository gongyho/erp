package yh.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import yh.erp.dao.IOrderDetailDao;
import yh.erp.entity.OrderDetail;

public class OrderDetailDao extends BaseDao<OrderDetail> implements IOrderDetailDao {

	@Override
	public DetachedCriteria getDC(OrderDetail t1, OrderDetail t2, Object param) {
		DetachedCriteria dc= DetachedCriteria.forClass(getClazz());
		if(null!=t1) {
			if(null!=t1.getState()&&t1.getState().trim().length()>0) {
				dc.add(Restrictions.eq("state", t1.getState()));
			}
			if(null!=t1.getOrders()) {
				dc.add(Restrictions.eq("orders", t1.getOrders()));
			}
		}
		return dc;
	}
}
