package yh.erp.entity;

/**
*	仓库
*/
public class Store{
	private Integer uuid;//编号
	private String name;//名称
	private Integer empUuid;//员工编号
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
	public Integer getEmpUuid() {
		return empUuid;
	}
	public void setEmpUuid(Integer empUuid) {
		this.empUuid = empUuid;
	}
}