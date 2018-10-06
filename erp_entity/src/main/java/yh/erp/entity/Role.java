package yh.erp.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
*	角色
*/
public class Role{
	private Integer uuid;//编号
	private String name;//名称
    @JSONField(serialize=false)
	private List<Menu> menus;
    @JSONField(serialize=false)
    private List<Emp> emps;
    
	public List<Emp> getEmps() {
		return emps;
	}
	public void setEmps(List<Emp> emps) {
		this.emps = emps;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

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