package yh.erp.web;

import yh.erp.biz.IStoreBiz;
import yh.erp.entity.Store;

public class StoreAction extends BaseAction<Store> {
	private IStoreBiz storeBiz;

	public void setStoreBiz(IStoreBiz storeBiz) {
		this.storeBiz = storeBiz;
		super.setBaseBiz(storeBiz);
	}
	/**
	 * 加载登陆者的仓库
	 */
	public void myList() {
		if(null==getT1()) {
			setT1(new Store());
		}
		getT1().setEmpUuid(getLoginUser().getUuid());
		super.list();
	}
}
