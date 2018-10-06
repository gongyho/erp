package yh.erp.biz.impl;

import yh.erp.biz.IReturnOrdersBiz;
import yh.erp.dao.IReturnOrdersDao;
import yh.erp.entity.ReturnOrders;

public class ReturnOrdersBiz extends BaseBiz<ReturnOrders> implements IReturnOrdersBiz {
	private IReturnOrdersDao returnOrdersDao;

	public void setReturnOrdersDao(IReturnOrdersDao returnOrdersDao) {
		this.returnOrdersDao = returnOrdersDao;
		super.setBaseDao(returnOrdersDao);
	}
	

}
