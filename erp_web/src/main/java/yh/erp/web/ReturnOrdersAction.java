package yh.erp.web;

import yh.erp.biz.IReturnOrdersBiz;
import yh.erp.entity.ReturnOrders;

public class ReturnOrdersAction extends BaseAction<ReturnOrders> {
	private IReturnOrdersBiz returnOrdersBiz;

	public void setReturnOrdersBiz(IReturnOrdersBiz returnOrdersBiz) {
		this.returnOrdersBiz = returnOrdersBiz;
		super.setBaseBiz(returnOrdersBiz);
	}
	
}
