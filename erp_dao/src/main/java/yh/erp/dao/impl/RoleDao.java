package yh.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;

import yh.erp.dao.IRoleDao;
import yh.erp.entity.Role;

public class RoleDao extends BaseDao<Role> implements IRoleDao {

	@Override
	public DetachedCriteria getDC(Role t1, Role t2, Object param) {
		DetachedCriteria dc= DetachedCriteria.forClass(getClazz());
		return dc;
	}
}
