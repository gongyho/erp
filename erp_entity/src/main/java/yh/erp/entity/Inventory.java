package yh.erp.entity;

import java.util.Date;

/**
*	盘盈盘亏
*/
public class Inventory{
	private Integer uuid;//编号
	private Integer goodsUuid;//商品
	private Integer storeUuid;//仓库
	private Integer num;//数量
	private String type;//类型
	private Date createTime;//登记日期
	private Date checkTime;//审核日期
	private Integer creater;//登记人
	private Integer checker;//审核人
	private String state;//状态
	private String remark;//备注
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getGoodsUuid() {
		return goodsUuid;
	}
	public void setGoodsUuid(Integer goodsUuid) {
		this.goodsUuid = goodsUuid;
	}
	public Integer getStoreUuid() {
		return storeUuid;
	}
	public void setStoreUuid(Integer storeUuid) {
		this.storeUuid = storeUuid;
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
	
}