package yh.erp.entity;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
FieldTypeComment
UUID int(11) NOT NULL编号
USERNAME varchar(15) NULL登陆名
PWD varchar(32) NULL登陆密码
NAME varchar(28) NULL真实姓名
GENDER int(11) NULL性别
EMAIL varchar(255) NULL邮件地址
TELE varchar(30) NULL联系电话
ADDRESS varchar(255) NULL联系地址
BIRTHDAY datetime NULL出生年月日
DEPUUID int(11) NULL部门编号
 * @author GongYuHao
 *
 */
public class Emp {
	private Integer uuid;
	private String userName;
	@JSONField(serialize=false)
	private String pwd;
	private String name;
	private Integer gender;
	private String email;
	private String tele;
	private String address;
	private Date birthday;
	private Dep dep;
	private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Dep getDep() {
		return dep;
	}
	public void setDep(Dep dep) {
		this.dep = dep;
	}

}
