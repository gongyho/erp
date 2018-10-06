package yh.erp.web;

import yh.erp.biz.IGoodsTypeBiz;
import yh.erp.entity.GoodsType;

public class GoodsTypeAction extends BaseAction<GoodsType> {
	private IGoodsTypeBiz goodsTypeBiz;

	public void setGoodsTypeBiz(IGoodsTypeBiz goodsTypeBiz) {
		this.goodsTypeBiz = goodsTypeBiz;
		super.setBaseBiz(goodsTypeBiz);
	}
}
