package yh.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;

import yh.erp.dao.IMenuDao;
import yh.erp.entity.Menu;

public class MenuDao extends BaseDao<Menu> implements IMenuDao {

	@Override
	public DetachedCriteria getDC(Menu t1, Menu t2, Object param) {
		DetachedCriteria dc= DetachedCriteria.forClass(getClazz());
		return dc;
	}



}
