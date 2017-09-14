package wudeng.entity;

import java.util.Date;

public class InstockNote {
	private Integer id;
	private PurchasePlan purchasePlan;
	private Employee employee;
	private Date createDate;
	private Integer status;
	
	public PurchasePlan getPurchasePlan() {
		return purchasePlan;
	}
	public void setPurchasePlan(PurchasePlan purchasePlan) {
		this.purchasePlan = purchasePlan;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
