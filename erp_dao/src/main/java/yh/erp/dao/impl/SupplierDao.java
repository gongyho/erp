package yh.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import yh.erp.dao.ISupplierDao;
import yh.erp.entity.Supplier;

public class SupplierDao extends BaseDao<Supplier> implements ISupplierDao {

	@Override
	public DetachedCriteria getDC(Supplier t1, Supplier t2, Object param) {
		DetachedCriteria dc= DetachedCriteria.forClass(getClazz());
		if(null!=t1) {
			if(null!=t1.getType()&&t1.getType().trim().length()>0) {
				dc.add(Restrictions.eq("type", t1.getType()));
			}
			if(null!=t1.getName()&&t1.getName().trim().length()>0) {
				dc.add(Restrictions.ilike("name", t1.getName(), MatchMode.ANYWHERE));
			}
		}
		if(null!=t2) {
			if(null!=t2.getName()&&t2.getName().trim().length()>0) {
				dc.add(Restrictions.ilike("name", t2.getName(), MatchMode.ANYWHERE));
			}
		}
		return dc;
	}

}
