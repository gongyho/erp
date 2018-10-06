package yh.erp.web;

import yh.erp.biz.IStoreOperBiz;
import yh.erp.entity.StoreOper;

public class StoreOperAction extends BaseAction<StoreOper> {
	private IStoreOperBiz storeOperBiz;

	public void setStoreOperBiz(IStoreOperBiz storeOperBiz) {
		this.storeOperBiz = storeOperBiz;
		super.setBaseBiz(storeOperBiz);
	}
	
} 
