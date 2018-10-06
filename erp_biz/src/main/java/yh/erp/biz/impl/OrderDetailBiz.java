package yh.erp.biz.impl;

import java.util.Date;
import java.util.List;

import com.redsum.bos.ws.impl.IWaybillWs;
import yh.erp.biz.IOrderDetailBiz;
import yh.erp.dao.IOrderDetailDao;
import yh.erp.dao.IStoreDetailDao;
import yh.erp.dao.IStoreOperDao;
import yh.erp.dao.ISupplierDao;
import yh.erp.entity.*;
import yh.erp.exception.ErpException;

public class OrderDetailBiz extends BaseBiz<OrderDetail> implements IOrderDetailBiz {
	private IOrderDetailDao orderDetailDao;
	private IStoreDetailDao storeDetailDao;
	private IStoreOperDao storeOperDao;
	private IWaybillWs waybillWs;
	private ISupplierDao supplierDao;

	public void setWaybillWs(IWaybillWs waybillWs) {
		this.waybillWs = waybillWs;
	}
	public void setSupplierDao(ISupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}
	public void setStoreDetailDao(IStoreDetailDao storeDetailDao) {
		this.storeDetailDao = storeDetailDao;
	}
	public void setStoreOperDao(IStoreOperDao storeOperDao) {
		this.storeOperDao = storeOperDao;
	}
	public void setOrderDetailDao(IOrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
		super.setBaseDao(orderDetailDao);
	}
	/**
	 * 	入库
	 */
	@Override
	public void doEnd(int uuid, int storeUuid, int empUuid) {
		OrderDetail detail=orderDetailDao.get(uuid);
		//判断是否已经过入库
		if(!OrderDetail.STATE_NOT_IN.equals(detail.getState())) {
			throw new ErpException("该商品已入库");
		}
		
		//1.入库
		detail.setState(OrderDetail.STATE_IN);
		detail.setEnder(empUuid);
		detail.setEndTime(new Date());
		detail.setStoreUuid(storeUuid);
		
		//2.库存明细添加
		StoreDetail storeDetail=new StoreDetail();
		storeDetail.setGoodsUuid(detail.getGoodsUuid());
		storeDetail.setStoreUuid(storeUuid);
		List<StoreDetail> storeDetailList=storeDetailDao.getList(storeDetail, null, null);
		if(storeDetailList.size()>0) {
			storeDetailList.get(0).setNum(storeDetailList.get(0).getNum()+detail.getNum());
		}else {
			storeDetail.setNum(detail.getNum());
			storeDetailDao.save(storeDetail);
		}
		
		//3.添加库存变更明细
		StoreOper storeOper=new StoreOper();
		storeOper.setEmpUuid(empUuid);
		storeOper.setNum(detail.getNum());
		storeOper.setGoodsUuid(detail.getGoodsUuid());
		storeOper.setStoreUuid(storeUuid);
		storeOper.setType(StoreOper.TYPE_IN);
		storeOper.setOperTime(new Date());
		
		storeOperDao.save(storeOper);
		
		//3.判断订单的明细是否全部入库
		Orders orders=detail.getOrders();
		OrderDetail queryDetail= new OrderDetail();
		queryDetail.setState(OrderDetail.STATE_NOT_IN);
		queryDetail.setOrders(orders);
		long count=orderDetailDao.getCount(queryDetail, null, null);
		if(count==0) {
			orders.setEnder(empUuid);
			orders.setEndTime(new Date());
			orders.setState(Orders.STATE_END);
		}
	}
	/**
	 * 	出库
	 */
	@Override
	public void doOutStore(int uuid, int storeUuid, int empUuid) {
		OrderDetail detail=orderDetailDao.get(uuid);
		//判断是否已经过入库
		if(!OrderDetail.STATE_NOT_OUT.equals(detail.getState())) {
			throw new ErpException("该商品已出库");
		}
		
		//1.入库
		detail.setState(OrderDetail.STATE_OUT);
		detail.setEnder(empUuid);
		detail.setEndTime(new Date());
		detail.setStoreUuid(storeUuid);
		
		//2.库存明细添加
		StoreDetail storeDetail=new StoreDetail();
		storeDetail.setGoodsUuid(detail.getGoodsUuid());
		storeDetail.setStoreUuid(storeUuid);
		List<StoreDetail> storeDetailList=storeDetailDao.getList(storeDetail, null, null);
		if(storeDetailList.size()>0) {
			storeDetailList.get(0).setNum(storeDetailList.get(0).getNum()-detail.getNum());
			if(storeDetailList.get(0).getNum()<0) {
				throw new ErpException("库存不足");
			}
		}else {
			throw new ErpException("库存不足");
		}
		
		//3.添加库存变更明细
		StoreOper storeOper=new StoreOper();
		storeOper.setEmpUuid(empUuid);
		storeOper.setNum(detail.getNum());
		storeOper.setGoodsUuid(detail.getGoodsUuid());
		storeOper.setStoreUuid(storeUuid);
		storeOper.setType(StoreOper.TYPE_OUT);
		storeOper.setOperTime(new Date());
		
		storeOperDao.save(storeOper);
		
		//3.判断订单的明细是否全部入库
		Orders orders=detail.getOrders();
		OrderDetail queryDetail= new OrderDetail();
		queryDetail.setState(OrderDetail.STATE_NOT_OUT);
		queryDetail.setOrders(orders);
		long count=orderDetailDao.getCount(queryDetail, null, null);
		if(count==0) {
			orders.setEnder(empUuid);
			orders.setEndTime(new Date());
			orders.setState(Orders.STATE_OUT);
			//获取客户信息
			Supplier supplier =supplierDao.get(orders.getSupplierUuid());
			Long waybill=waybillWs.addWaybill(1l,supplier.getAddress(),supplier.getContact(),supplier.getTele(),"--");
			orders.setWayBills(waybill.intValue());
		}
	}
}
