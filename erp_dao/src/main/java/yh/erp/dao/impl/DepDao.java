package yh.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import yh.erp.dao.IDepDao;
import yh.erp.entity.Dep;

public class DepDao extends BaseDao<Dep>  implements IDepDao{
	
	/**
	 * 	重写父类 添加限制条件方法
	 */
	@Override
	public DetachedCriteria getDC(Dep t1,Dep t2,Object param) {
		DetachedCriteria dc=DetachedCriteria.forClass(getClazz());
		if(null!=t1) {
			if(null!=t1.getName()&&t1.getName().trim().length()>0) {
				dc.add(Restrictions.ilike("name", t1.getName(), MatchMode.ANYWHERE));
			}
			if(null!=t1.getTele()&&t1.getTele().trim().length()>0) {
				dc.add(Restrictions.ilike("tele", t1.getTele(), MatchMode.ANYWHERE));
			}
		}
		return dc;
	}
}
