package myJson;

import java.util.Date;

public class InstockNoteJson {
	private Integer instockNoteId;
	private Integer purchaseId;
	private Integer plan_employeeId;
	private String plan_employeeName;
	private Integer note_employeeId;
	private String note_employeeName;
	private Date createDate;
	private Integer status;
	private boolean canRollback;
	public Integer getInstockNoteId() {
		return instockNoteId;
	}
	public void setInstockNoteId(Integer instockNoteId) {
		this.instockNoteId = instockNoteId;
	}
	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	
	public Integer getPlan_employeeId() {
		return plan_employeeId;
	}
	public void setPlan_employeeId(Integer plan_employeeId) {
		this.plan_employeeId = plan_employeeId;
	}
	public String getPlan_employeeName() {
		return plan_employeeName;
	}
	public void setPlan_employeeName(String plan_employeeName) {
		this.plan_employeeName = plan_employeeName;
	}
	public Integer getNote_employeeId() {
		return note_employeeId;
	}
	public void setNote_employeeId(Integer note_employeeId) {
		this.note_employeeId = note_employeeId;
	}
	public String getNote_employeeName() {
		return note_employeeName;
	}
	public void setNote_employeeName(String note_employeeName) {
		this.note_employeeName = note_employeeName;
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
	public boolean isCanRollback() {
		return canRollback;
	}
	public void setCanRollback(boolean canRollback) {
		this.canRollback = canRollback;
	}
}
