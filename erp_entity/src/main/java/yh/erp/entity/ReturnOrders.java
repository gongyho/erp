package yh.erp.entity;

import java.util.Date;

/**
*	退货订单
*/
public class ReturnOrders{
	private Integer uuid;//编号
	private Date createTime;//生成日期
	private Date checkTime;//检查日期
	private Date endTime;//结束日期
	private String type;//订单类型
	private Integer creater;//下单员
	private Integer checker;//审核员工编号
	private Integer ender;//库管员
	private Integer supplierUuid;//供应商及客户编号
	private Double totalMoney;//合计金额
	private String state;//订单状态
	private Integer wayBills;//运单号
	private Integer ordersUuid;//原订单编号
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getCreater() {
		return creater;
	}
	public void setCreater(Integer creater) {
		this.creater = creater;
	}
	public Integer getChecker() {
		return checker;
	}
	public void setChecker(Integer checker) {
		this.checker = checker;
	}
	public Integer getEnder() {
		return ender;
	}
	public void setEnder(Integer ender) {
		this.ender = ender;
	}
	public Integer getSupplierUuid() {
		return supplierUuid;
	}
	public void setSupplierUuid(Integer supplierUuid) {
		this.supplierUuid = supplierUuid;
	}
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getWayBills() {
		return wayBills;
	}
	public void setWayBills(Integer wayBills) {
		this.wayBills = wayBills;
	}
	public Integer getOrdersUuid() {
		return ordersUuid;
	}
	public void setOrdersUuid(Integer ordersUuid) {
		this.ordersUuid = ordersUuid;
	}
	
}