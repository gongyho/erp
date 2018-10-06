package yh.erp.biz.impl;

import yh.erp.biz.IInventoryBiz;
import yh.erp.dao.IInventoryDao;
import yh.erp.entity.Inventory;

public class InventoryBiz extends BaseBiz<Inventory> implements IInventoryBiz {
	private IInventoryDao inventoryDao;

	public void setInventoryDao(IInventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
		super.setBaseDao(inventoryDao);
	}
}
