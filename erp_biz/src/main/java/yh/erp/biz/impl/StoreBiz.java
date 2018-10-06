package yh.erp.biz.impl;

import yh.erp.biz.IStoreBiz;
import yh.erp.dao.IStoreDao;
import yh.erp.entity.Store;

public class StoreBiz extends BaseBiz<Store> implements IStoreBiz {
	private IStoreDao storeDao;

	public void setStoreDao(IStoreDao storeDao) {
		this.storeDao = storeDao;
		super.setBaseDao(storeDao);
	}
	
}
