package yh.erp.entity;

/**
*	仓库库存
*/
public class StoreDetail{
	private Integer uuid;//编号
	private Integer storeUuid;//仓库编号
	private Integer goodsUuid;//商品编号
	private Integer num;//数量
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	public Integer getStoreUuid() {
		return storeUuid;
	}
	public void setStoreUuid(Integer storeUuid) {
		this.storeUuid = storeUuid;
	}
	public Integer getGoodsUuid() {
		return goodsUuid;
	}
	public void setGoodsUuid(Integer goodsUuid) {
		this.goodsUuid = goodsUuid;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
}