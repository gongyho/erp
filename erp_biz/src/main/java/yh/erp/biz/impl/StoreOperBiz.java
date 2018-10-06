package yh.erp.biz.impl;

import yh.erp.biz.IStoreOperBiz;
import yh.erp.dao.IStoreOperDao;
import yh.erp.entity.StoreOper;

public class StoreOperBiz extends BaseBiz<StoreOper> implements IStoreOperBiz {
	private IStoreOperDao storeOperDao;

	public void setStoreOperDao(IStoreOperDao storeOperDao) {
		this.storeOperDao = storeOperDao;
		super.setBaseDao(storeOperDao);
	}
}
