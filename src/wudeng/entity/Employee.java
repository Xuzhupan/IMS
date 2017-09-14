package wudeng.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Transient;


public class Employee {
	private Integer id;
	private String name;
	private Integer sex;
	private Date birthday;
	private Date entryday;
	private String tel;
	private Department department;
	private Permission permission;
	private Integer status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getEntryday() {
		return entryday;
	}
	public void setEntryday(Date entryday) {
		this.entryday = entryday;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Transient
	public Permission getPermission() {
		return permission;
	}
	@Transient
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	

}
