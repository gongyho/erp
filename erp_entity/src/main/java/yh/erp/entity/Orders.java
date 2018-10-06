package yh.erp.entity;

import java.util.Date;
import java.util.List;

/**
*	订单
*/
public class Orders{
	public final static String TYPE_IN ="1";
	public final static String TYPE_OUT ="2";
	
	public final static String STATE_CREATE ="0";
	public final static String STATE_CHECK ="1";
	public final static String STATE_START ="2";
	public final static String STATE_END ="3";
	
	public final static String STATE_NOT_OUT ="0";
	public final static String STATE_OUT ="1";
	
	
	private Integer uuid;//编号
	private Date createTime;//生成日期
	private Date checkTime;//审核日期
	private Date startTime;//确认日期
	private Date endTime;//入库或出库日期
	private String type;//1:采购 2:销售
	private String createrName;
	private Integer creater;//下单员
	private String checkerName;
	private Integer checker;//审核员
	private String starterName;
	private Integer starter;//采购员
	private String enderName;
	private Integer ender;//库管员
	private String supplierName;
	private Integer supplierUuid;//供应商或客户
	private Double totalMoney;//合计金额
	private String state;//采购: 0:未审核 1:已审核, 2:已确认, 3:已入库；销售：0:未出库 1:已出库
	private Integer wayBills;//运单号
	private List<OrderDetail> OrderDetails;
	
	public String getCreaterName() {
		return createrName;
	}
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	public String getCheckerName() {
		return checkerName;
	}
	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}
	public String getStarterName() {
		return starterName;
	}
	public void setStarterName(String starterName) {
		this.starterName = starterName;
	}
	public String getEnderName() {
		return enderName;
	}
	public void setEnderName(String enderName) {
		this.enderName = enderName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public List<OrderDetail> getOrderDetails() {
		return OrderDetails;
	}
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		OrderDetails = orderDetails;
	}
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
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
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
	public Integer getStarter() {
		return starter;
	}
	public void setStarter(Integer starter) {
		this.starter = starter;
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
}