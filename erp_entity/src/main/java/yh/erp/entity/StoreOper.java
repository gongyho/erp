package yh.erp.entity;

import java.util.Date;

/**
*	仓库操作记录
*/
public class StoreOper{
	public final static String TYPE_IN="1";
	public final static String TYPE_OUT="2";
	
	private Integer uuid;//编号
	private Integer empUuid;//操作员工编号
	private Date operTime;//操作日期
	private Integer storeUuid;//仓库编号
	private Integer goodsUuid;//商品编号
	private Integer num;//数量
	private String type;//1：入库 2：出库
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	public Integer getEmpUuid() {
		return empUuid;
	}
	public void setEmpUuid(Integer empUuid) {
		this.empUuid = empUuid;
	}
	public Date getOperTime() {
		return operTime;
	}
	public void setOperTime(Date operTime) {
		this.operTime = operTime;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}