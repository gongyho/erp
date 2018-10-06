package yh.erp.entity;

/**
*	商品
*/
public class Goods{
	private Integer uuid;//编号
	private String name;//名称
	private String origin;//产地
	private String producer;//厂家
	private String unit;//计量单位
	private Double inPrice;//进货价格
	private Double outPrice;//销售价格
	private Integer goodsTypeUuid;//商品类型
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Double getInPrice() {
		return inPrice;
	}
	public void setInPrice(Double inPrice) {
		this.inPrice = inPrice;
	}
	public Double getOutPrice() {
		return outPrice;
	}
	public void setOutPrice(Double outPrice) {
		this.outPrice = outPrice;
	}
	public Integer getGoodsTypeUuid() {
		return goodsTypeUuid;
	}
	public void setGoodsTypeUuid(Integer goodsTypeUuid) {
		this.goodsTypeUuid = goodsTypeUuid;
	}
	
}