package wudeng.entity;

import java.util.Date;
import java.util.Set;

public class SellPlan {
	private Integer id;
	private String planName;
	private Employee employee;
	private Customer customer;
	private Date createDate;
	private Integer status;
	private Set<SellInfo> sellInfos;
	private Set<OutstockNote> outstockNotes;
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
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
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
	public Set<SellInfo> getSellInfos() {
		return sellInfos;
	}
	public void setSellInfos(Set<SellInfo> sellInfos) {
		this.sellInfos = sellInfos;
	}
	public Set<OutstockNote> getOutstockNotes() {
		return outstockNotes;
	}
	public void setOutstockNotes(Set<OutstockNote> outstockNotes) {
		this.outstockNotes = outstockNotes;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	
}
