package yh.erp.biz.impl;

import yh.erp.biz.IGoodsBiz;
import yh.erp.dao.IGoodsDao;
import yh.erp.entity.Goods;

public class GoodsBiz extends BaseBiz<Goods> implements IGoodsBiz {
	private IGoodsDao goodsDao;

	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
		super.setBaseDao(goodsDao);
	}
	
	
}
