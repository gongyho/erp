package yh.erp.biz;

import yh.erp.entity.OrderDetail;

public interface IOrderDetailBiz extends IBaseBiz<OrderDetail> {
	void doEnd(int uuid,int storeUuid,int empUuid);
	void doOutStore(int uuid,int storeUuid,int empUuid);
}
