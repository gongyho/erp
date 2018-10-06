package yh.erp.web;

import yh.erp.biz.IGoodsBiz;
import yh.erp.entity.Goods;

public class GoodsAction extends BaseAction<Goods> {
	private IGoodsBiz goodsBiz;

	public void setGoodsBiz(IGoodsBiz goodsBiz) {
		this.goodsBiz = goodsBiz;
		super.setBaseBiz(goodsBiz);
	}
	
}
