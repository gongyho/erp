package yh.erp.web;

import yh.erp.biz.IOrderDetailBiz;
import yh.erp.entity.Emp;
import yh.erp.entity.OrderDetail;
import yh.erp.exception.ErpException;

public class OrderDetailAction extends BaseAction<OrderDetail>{
	private IOrderDetailBiz orderDetailBiz;

	public void setOrderDetailBiz(IOrderDetailBiz orderDetailBiz) {
		this.orderDetailBiz = orderDetailBiz;
		super.setBaseBiz(orderDetailBiz);
	}

	private Integer storeUuid;
	public Integer getStoreUuid() {
		return storeUuid;
	}
	public void setStoreUuid(Integer storeUuid) {
		this.storeUuid = storeUuid;
	}
	/**
	 * 	入库
	 */
	public void doEnd() {
		Emp loginUser=getLoginUser();
		if(null==loginUser) {
			ajaxReturn(false,"还没有登陆");
			return;
		}
		try {
			orderDetailBiz.doEnd(getUuid(),storeUuid, loginUser.getUuid());
			ajaxReturn(true, "");
		}catch(ErpException e) {
			ajaxReturn(false, e.getMessage());
		}catch(Exception e) {
			ajaxReturn(false, "入库失败");
			e.printStackTrace();
		}
	}
	/**
	 * 出库
	 */
	public void doOutStore() {
		Emp loginUser=getLoginUser();
		if(null==loginUser) {
			ajaxReturn(false,"还没有登陆");
			return;
		}
		try {
			orderDetailBiz.doOutStore(getUuid(),storeUuid, loginUser.getUuid());
			ajaxReturn(true, "");
		}catch(ErpException e) {
			ajaxReturn(false, e.getMessage());
		}catch(Exception e) {
			ajaxReturn(false, "入库失败");
			e.printStackTrace();
		}
	}
	
}
