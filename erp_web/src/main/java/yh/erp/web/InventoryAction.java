package yh.erp.web;

import yh.erp.biz.IInventoryBiz;
import yh.erp.entity.Inventory;

public class InventoryAction extends BaseAction<Inventory> {
	private IInventoryBiz InventoryBiz;

	public void setInventoryBiz(IInventoryBiz inventoryBiz) {
		InventoryBiz = inventoryBiz;
		super.setBaseBiz(inventoryBiz);
	}
	
}
