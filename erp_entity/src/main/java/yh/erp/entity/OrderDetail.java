package yh.erp.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
*	订单明细
*/
public class OrderDetail{
	public final static String STATE_NOT_IN ="0";
	public final static String STATE_IN ="1";
	
	public final static String STATE_NOT_OUT ="0";
	public final static String STATE_OUT ="1";
	
	
	private Integer uuid;//编号
	private Integer goodsUuid;//商品编号
	private String goodsName;//商品名称
	private Double price;//价格
	private Integer num;//数量
	private Double money;//金额
	private Date endTime;//结束日期
	private Integer ender;//库管员
	private Integer storeUuid;//仓库编号
	private String state;//采购：0=未入库，1=已入库  销售：0=未出库，1=已出库
	@JSONField(serialize=false)
	private Orders orders;//订单
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
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
	public Integer getEnder() {
		return ender;
	}
	public void setEnder(Integer ender) {
		this.ender = ender;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getStoreUuid() {
		return storeUuid;
	}
	public void setStoreUuid(Integer storeUuid) {
		this.storeUuid = storeUuid;
	}
	public Orders getOrders() {
		return orders;
	}
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	
}