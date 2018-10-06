package yh.erp.entity;

/**
 * 
	UUID int(11) NOT NULL编号
	NAME varchar(30) NULL部门名称
	TELE varchar(30) NULL联系电话
 * @author GongYuHao
 *
 */
public class Dep {
	private Integer uuid;
	private String name;
	private String tele;
	
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
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	@Override
	public String toString() {
		return "Dep [uuid=" + uuid + ", name=" + name + ", tele=" + tele + "]";
	}
	
}
