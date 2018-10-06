package yh.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import yh.erp.dao.IGoodsDao;
import yh.erp.entity.Goods;

public class GoodsDao extends BaseDao<Goods> implements IGoodsDao {

	@Override
	public DetachedCriteria getDC(Goods t1, Goods t2, Object param) {
		DetachedCriteria dc=DetachedCriteria.forClass(getClazz());
		if(null!=t1) {
			if(null!=t1.getName()&&t1.getName().trim().length()>0) {
				dc.add(Restrictions.ilike("name", t1.getName(), MatchMode.ANYWHERE));
			}
		}
		return dc;
	}
	
}
