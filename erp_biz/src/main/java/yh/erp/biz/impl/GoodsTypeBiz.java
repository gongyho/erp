package yh.erp.biz.impl;

import yh.erp.biz.IGoodsTypeBiz;
import yh.erp.dao.IGoodsTypeDao;
import yh.erp.entity.GoodsType;

public class GoodsTypeBiz extends BaseBiz<GoodsType> implements IGoodsTypeBiz {
	private IGoodsTypeDao goodsTypeDao;

	public void setGoodsTypeDao(IGoodsTypeDao goodsTypeDao) {
		this.goodsTypeDao = goodsTypeDao;
		super.setBaseDao(goodsTypeDao);
	}
	
}
