package wudeng.entity;

import java.util.Date;
import java.util.Set;

public class PurchasePlan {
	private Integer id;
	private String planName;
	private Employee employee;
	private Date createDate;
	private Integer status;
	private Set<PurchaseInfo> purchaseInfos;
	private Set<InstockNote> instockNotes;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Set<PurchaseInfo> getPurchaseInfos() {
		return purchaseInfos;
	}
	public void setPurchaseInfos(Set<PurchaseInfo> purchaseInfos) {
		this.purchaseInfos = purchaseInfos;
	}
	public Set<InstockNote> getInstockNotes() {
		return instockNotes;
	}
	public void setInstockNotes(Set<InstockNote> instockNotes) {
		this.instockNotes = instockNotes;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	
}
