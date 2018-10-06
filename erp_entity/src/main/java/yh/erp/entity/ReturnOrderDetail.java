package yh.erp.entity;

import java.util.Date;

/**
*	退货订单明细
*/
public class ReturnOrderDetail{
	private Integer uuid;//编号
	private Integer goodsUuid;//商品编号
	private String goodsName;//商品名称
	private Double price;//价格
	private Integer num;//数量
	private Double money;//金额
	private Date endTime;//结束日期
	private Integer ender;//库管员
	private Integer storeUuid;//仓库编号
	private String state;//状态
	private Integer ordersUuid;//退货订单编号
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	public Integer getGoodsUuid() {
		return goodsUuid;
	}
	public void setGoodsUuid(Integer goodsUuid) {
		this.goodsUuid = goodsUuid;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getEnder() {
		return ender;
	}
	public void setEnder(Integer ender) {
		this.ender = ender;
	}
	public Integer getStoreUuid() {
		return storeUuid;
	}
	public void setStoreUuid(Integer storeUuid) {
		this.storeUuid = storeUuid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getOrdersUuid() {
		return ordersUuid;
	}
	public void setOrdersUuid(Integer ordersUuid) {
		this.ordersUuid = ordersUuid;
	}
	
}