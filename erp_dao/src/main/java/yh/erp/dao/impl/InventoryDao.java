package yh.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;

import yh.erp.dao.IInventoryDao;
import yh.erp.entity.Inventory;

public class InventoryDao extends BaseDao<Inventory> implements IInventoryDao {

	@Override
	public DetachedCriteria getDC(Inventory t1, Inventory t2, Object param) {
		DetachedCriteria dc= DetachedCriteria.forClass(getClazz());
		return dc;
	}
	
}
