package yh.erp.biz.impl;

import yh.erp.biz.IReturnOrderDetailBiz;
import yh.erp.dao.IReturnOrderDetailDao;
import yh.erp.entity.ReturnOrderDetail;

public class ReturnOrderDetailBiz extends BaseBiz<ReturnOrderDetail> implements IReturnOrderDetailBiz {
	 private IReturnOrderDetailDao returnOrderDetailDao;

	public void setReturnOrderDetailDao(IReturnOrderDetailDao returnOrderDetailDao) {
		this.returnOrderDetailDao = returnOrderDetailDao;
		super.setBaseDao(returnOrderDetailDao);
	}
	 

}
