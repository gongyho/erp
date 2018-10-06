package yh.erp.entity;

/**
*	商品分类
*/
public class GoodsType{
	private Integer uuid;//商品类型编号
	private String name;//商品类型名称
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
	
}