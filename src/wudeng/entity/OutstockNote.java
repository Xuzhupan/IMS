package wudeng.entity;

import java.util.Date;

public class OutstockNote {
	private Integer id;
	private SellPlan sellPlan;
	private Employee employee;
	private Date createDate;
	private Integer status;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public SellPlan getSellPlan() {
		return sellPlan;
	}
	public void setSellPlan(SellPlan sellPlan) {
		this.sellPlan = sellPlan;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
